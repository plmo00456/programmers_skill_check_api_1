package project.main.handler;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import project.main.vo.User;
import project.util.CommonUtils;

import java.io.OutputStream;

/**
 * 주소 '/'와, '/sum'을 매핑하는 클래스 입니다.
 * '/'  서버의 상태를 표시
 * '/sum'   user의 postCount를 sum하여 표시
 *
 * @author 박우진
 */

public class MainHandler implements HttpHandler{
    Gson gson = new Gson();

    CommonUtils cu = new CommonUtils();

    @Override
    public void handle(HttpExchange he) throws IOException{
        //응답 content-type 지정
        he.getResponseHeaders().set("Content-Type", "application/json");

        // URI 가져와 URI별로 결과 반한
        String path = he.getRequestURI().getPath();
        JsonObject result = new JsonObject();
        if(path.equals("/")){
            result.addProperty("message", "server check");
        }else if(path.equals("/sum")){
            //user.json 파일을 텍스트 형식으로 변환
            String data = cu.readJsonFile("data/input/user.json");
            //user.json text를 User 클래스 배열 형식으로 변환
            User[] user = gson.fromJson(data, User[].class);

            //user.post_count의 합계 집계
            int sum = 0;
            for(int i=0; i<user.length; i++){
                sum += user[i].getPostCount();
            }

            result.addProperty("sum", sum);
        }
        String response = gson.toJson(result);

        try {
            he.sendResponseHeaders(200, response.length());
        }catch(Exception e){
            he.sendResponseHeaders(500, response.length());
        }
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}

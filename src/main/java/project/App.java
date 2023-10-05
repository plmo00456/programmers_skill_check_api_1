package project;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import project.main.handler.MainHandler;

/**
 * API Server를 구동하는 메인 클래스 입니다.
 * 0.0.0.0:5678로 서버를 실행합니다.
 *
 * @author 박우진
 */

public class App
{
    public static void main( String[] args ) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0",5678), 0);
        server.createContext("/", new MainHandler());
        server.setExecutor(null);
        server.start();
    }
}
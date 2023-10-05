package project.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class CommonUtils {

    /**
     * 파일을 읽어 String으로 뵤여주는 메소드입니다.
     *
     * @param filename 파일의 경로
     * @return 파일의 text를 반환.
     */
    public String readJsonFile(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content;
    }
}
package com.semi.gam.notion.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class NotionController {

    @GetMapping("notion")
    public String notion(HttpSession session) throws IOException {

        //노션 API 호출에 필요한 데이터 변수 준비
        String databaseId = "1544bf4b1593804690dec2d14f882182"; //데이터 베이스 아이디
        String secretKey = "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"; //노션 인증키
        String notionVersion = "2022-06-28";

        //HTTP 요청 보내고, 응답받기


        //URL 설정
        URL url = new URL("https://api.notion.com/v1/databases/" + databaseId + "/query");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //method 설정
        connection.setRequestMethod("POST");

        //data(header) 설정
        connection.setRequestProperty("Authorization", secretKey);
        connection.setRequestProperty("Notion-Version", notionVersion);
        connection.setRequestProperty("Content-Type", "application/json");

        //응답코드 얻기(400,403,404,500...)
        int responseCode = connection.getResponseCode();

        //데이터 읽기
        StringBuilder response = new StringBuilder();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String str;
        while ((str = reader.readLine()) != null) {
            response.append(str.trim());
        }
        String notion = response.toString();
        session.setAttribute("notion",notion);
        return "notion";
    }
}

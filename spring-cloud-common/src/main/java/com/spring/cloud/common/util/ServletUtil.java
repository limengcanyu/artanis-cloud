package com.spring.cloud.common.util;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtil {

    public static String getPostBody(HttpServletRequest servletRequest) {
        try {
            BufferedReader bufferedReader = servletRequest.getReader();

            StringBuilder body = new StringBuilder();
            while (bufferedReader.ready()) {
                body.append(bufferedReader.readLine().trim());
            }
            return body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeResponse(ServletResponse servletResponse, String error) throws IOException {
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json;charset:UTF-8;");
        PrintWriter writer = servletResponse.getWriter();
        writer.write(error);
    }

}

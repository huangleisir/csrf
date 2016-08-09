package com.wangbinghua.csrf.servlet;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gary on 16-8-8.
 */
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie cookie = new Cookie("username",new BASE64Encoder().encode("wangbinghua".getBytes()));
        cookie.setPath("/wbh/jsp");
        response.addCookie(cookie);
        response.sendRedirect("/wbh/jsp/index.jsp");
    }
}

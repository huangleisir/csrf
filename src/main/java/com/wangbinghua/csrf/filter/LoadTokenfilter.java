package com.wangbinghua.csrf.filter;


import sun.misc.BASE64Encoder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by gary on 16-8-8.
 */
public class LoadTokenfilter implements Filter{

    private final static String CSRF_PARAM = "CSRF_PARAM";
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpServletRequest.getSession();
        System.out.println(httpServletRequest.getRequestURL());

        if(session.getAttribute(CSRF_PARAM) == null || "".equals(session.getAttribute(CSRF_PARAM))){
            try {
                SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
                byte[] randoms = new byte[20];
                secureRandom.nextBytes(randoms);
                String token = new BASE64Encoder().encode(randoms);
                session.setAttribute(CSRF_PARAM,token);
                httpServletRequest.setAttribute(CSRF_PARAM,token);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(httpServletRequest,servletResponse);
    }

    public void destroy() {

    }
}

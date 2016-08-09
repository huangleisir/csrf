package com.wangbinghua.csrf.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by gary on 16-8-8.
 */
public class ValidateTokenFilter implements Filter {

    private final static String CSRF_PARAM = "CSRF_PARAM";
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();

        String token = request.getParameter("token");
        if(token == null || "".equals(token)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"/error.jsp");
        }
        else{
            if(session.getAttribute(CSRF_PARAM).equals(token)){
                filterChain.doFilter(request,response);
            }
            else{
                response.sendError(HttpServletResponse.SC_FORBIDDEN,"/error.jsp");
            }
        }
    }

    public void destroy() {

    }
}

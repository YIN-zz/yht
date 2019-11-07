package com.ygt.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCORSFilter implements Filter {

     @Override
     public void init(FilterConfig filterConfig) throws ServletException {

     }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     HttpServletRequest request = (HttpServletRequest) servletRequest;
     HttpServletResponse response = (HttpServletResponse) servletResponse;
     response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
     response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
     response.setHeader("Access-Control-Max-Age", "120");
     response.setHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
     response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
     filterChain.doFilter(servletRequest, servletResponse);
     }

     @Override
     public void destroy() {

     }

}


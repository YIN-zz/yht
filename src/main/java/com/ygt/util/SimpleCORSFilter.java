package com.ygt.util;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleCORSFilter extends OncePerRequestFilter {

     /*@Override
     public void init(FilterConfig filterConfig) throws ServletException {

     }*/

    /*@Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     HttpServletRequest request = (HttpServletRequest) servletRequest;
     HttpServletResponse response = (HttpServletResponse) servletResponse;
     response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
     response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
     response.setHeader("Access-Control-Max-Age", "120");
     response.setHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
     response.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
     filterChain.doFilter(servletRequest, servletResponse);
     }*/

     /*@Override
     public void destroy() {

     }
*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, If-Modified-Since");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //此行代码确保请求可以继续执行至Controller
        filterChain.doFilter(request, response);
    }
}


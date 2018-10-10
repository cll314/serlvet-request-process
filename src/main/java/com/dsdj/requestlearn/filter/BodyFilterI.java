package com.dsdj.requestlearn.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName BodyFilterI
 * @Description TODO
 * @Author ChenLiLin
 * @Date 2018/10/9 11:06
 * @Version 1.0
 **/
public class BodyFilterI implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(12);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
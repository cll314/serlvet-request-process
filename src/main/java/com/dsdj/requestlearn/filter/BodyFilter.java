package com.dsdj.requestlearn.filter;

import com.dsdj.requestlearn.wrapper.RequestParameterWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @ClassName BodyFilter
 * @Description TODO
 * @Author ChenLiLin
 * @Date 2018/10/9 11:02
 * @Version 1.0
 **/
public class BodyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal");
        RequestParameterWrapper wrapper = new RequestParameterWrapper(httpServletRequest);

        /**
         * 修改表单的数据
         */
        // 添加一个值
        Enumeration<String> newParameter = wrapper.getParameterNames();
        String vaule = "";
        while (newParameter.hasMoreElements()){
            String name = newParameter.nextElement();
            if (Objects.equals("age",name)){
                vaule = wrapper.getParameter(name);
                Integer a = Integer.valueOf(vaule);
                a+=4;
                wrapper.addParameters(name,a);
            }else if (Objects.equals("name",name)){
                vaule = wrapper.getParameter(name);
                wrapper.addParameters(name,vaule+"修改");
            }
        }


        /**
         * 下面是获取body的数据
         */
        byte[] body = wrapper.getBody();
        if (body!=null || body.length == 0){
            // 修改json数据,相应的业务操作
            String json = new String(body);
            System.out.println("json原本数据"+json);
            json = "{\"name\":\"改名字\",\"age\":\"100\"}";
            wrapper.setBody(json.getBytes());
        }
        System.out.println("==========");
        filterChain.doFilter(wrapper,httpServletResponse);
    }
}
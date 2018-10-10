package com.dsdj.requestlearn.configuration;

import com.dsdj.requestlearn.filter.BodyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Description TODO
 * @Author ChenLiLin
 * @Date 2018/10/9 11:53
 * @Version 1.0
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean addFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new BodyFilter());
        filterRegistrationBean.addUrlPatterns("*.body");
        // 数字越小。级别越高
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setName("myfilter");
        return filterRegistrationBean;
    }
}
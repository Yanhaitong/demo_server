package com.yht.demo.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private AppInterfaceInterceptor appInterfaceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterfaceInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/sendVerifyCode")
                .excludePathPatterns("/user/loginOrRegister")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
    }

    @Bean
    public FilterRegistrationBean appInterfaceFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        AppInterfaceFilter repeatedlyReadFilter = new AppInterfaceFilter();
        registration.setFilter(repeatedlyReadFilter);
        registration.addUrlPatterns("/*");
        //registration.addUrlPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        return registration;

    }

}
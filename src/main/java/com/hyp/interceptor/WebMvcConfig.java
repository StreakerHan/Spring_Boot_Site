package com.hyp.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 

* @Title: WebMvcConfig  

* @Description:  向MVC中添加自定义组件 

* @author HanYupeng  

* @date 2018-06-15 10:08
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private BaseInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor); //可以通过此方法添加拦截器, 可以是spring提供的或者自己添加的 
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/admin/**")
//                .addResourceLocations("/public", "classpath:/admin/")
//                .setCachePeriod(31556926);
//    }

}

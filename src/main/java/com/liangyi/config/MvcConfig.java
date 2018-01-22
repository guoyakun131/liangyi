package com.liangyi.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/Login").setViewName("views/login");
        registry.addViewController("/").setViewName("views/login");
        registry.addViewController("/admin/welcome").setViewName("views/welcome");
        registry.addViewController("/admin/Goods/add_goods").setViewName("views/goods/add_goods");
    }

}

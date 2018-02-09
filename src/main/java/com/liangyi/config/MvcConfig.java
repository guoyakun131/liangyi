package com.liangyi.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
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
        registry.addViewController("/User/add_g").setViewName("views/user/add_g");
        registry.addViewController("/admin/typeName").setViewName("views/article/add_typeName");
        registry.addViewController("/admin/Article/add_article").setViewName("views/article/add_article");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}

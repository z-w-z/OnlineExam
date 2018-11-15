package com.exam.web.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.exam.web.common.ExamConst;



public class ExamWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	/**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指向外部目录
        registry.addResourceHandler("/upload/**").addResourceLocations(ExamConst.UPLOAD_FILE_PATH);
        super.addResourceHandlers(registry);
    }

}

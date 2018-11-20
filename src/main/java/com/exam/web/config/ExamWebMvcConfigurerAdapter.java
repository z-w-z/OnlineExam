package com.exam.web.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.exam.web.common.ExamConst;

/**
 * @描述 SpringMVC适配器
 * @标题 ExamWebMvcConfigurerAdapter.java
 * @Package com.exam.web.config
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午1:58:55
 * @Copyright: 2018 by hungkuei All rights reserved.
 */

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

package com.exam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.exam.common.ExamConst;

/**
 * @描述 SpringMVC适配器
 * @标题 ExamWebMvcConfigurerAdapter.java
 * @Package com.exam.web.config
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午1:58:55
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
@Configuration
public class ExamWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	/**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	/**
    	 * @Description: 对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/Path/picName.jpg" />便可以直接引用图片
    	 * 这是图片的物理路径  "file:/+本地图片的地址"file:/D:/eclipse_workspace/OnlineExam/data/
    	 */ 

        //指向外部目录
        registry.addResourceHandler("/upload/**").addResourceLocations(ExamConst.UPLOAD_FILE_PATH);
        super.addResourceHandlers(registry);
    }

}

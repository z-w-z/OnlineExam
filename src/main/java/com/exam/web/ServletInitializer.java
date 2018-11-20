package com.exam.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * @描述 ***
 * @标题 ServletInitializer.java
 * @Package com.exam.web
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午2:06:06
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OnlineExamApplication.class);
    }
}


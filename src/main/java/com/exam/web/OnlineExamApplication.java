package com.exam.web;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
/**
 * @描述   程序启动类
 * @标题 OnlineExamApplication.java
 * @Package com.exam.web
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午1:52:11
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
@SpringBootApplication
public class OnlineExamApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineExamApplication.class, args);
	}

	@Bean
	public Converter<Long, Date> addNewConvert() {
		return new Converter<Long, Date>() {
			@Override
			public Date convert(Long source) {
				Date date = null;
				try {
					date = new Date(source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			container.addErrorPages(error404Page);
		});
	}

}

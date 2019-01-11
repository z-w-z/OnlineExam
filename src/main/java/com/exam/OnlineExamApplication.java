package com.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;
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
@MapperScan(basePackages = "com.exam.mapper")
public class OnlineExamApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineExamApplication.class, args);
	}
}

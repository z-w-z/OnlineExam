package com.exam.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @描述  分布式session一致性,redis,7天
 * @标题 SessionConfig.java
 * @Package com.exam.web.config
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午2:05:23
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*7)
public class SessionConfig {
}

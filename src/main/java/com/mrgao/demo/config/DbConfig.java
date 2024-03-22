package com.mrgao.demo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Mr.Gao
 * @date 2024/1/30 10:29
 * @apiNote:
 */
@Getter
@Configuration
@PropertySource(value = {"classpath:db.properties"})
public class DbConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
}

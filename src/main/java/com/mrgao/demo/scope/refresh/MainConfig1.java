package com.mrgao.demo.scope.refresh;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Gao
 * @date 2024/1/30 12:01
 * @apiNote:
 */
@Getter
@Configuration
@ComponentScan
@RefreshScope
public class MainConfig1 {

    @Value("${mail.username}")
    private String userName;

    @Override
    public String toString() {
        return "MainConfig1{" +
                "userName='" + userName + '\'' +
                '}';
    }
}

package com.mrgao.demo.scope;

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
public class MainConfig {

    //@Value("${mail.username}")
    //private String userName;
}

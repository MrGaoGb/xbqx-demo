package com.mrgao.demo;

import com.mrgao.demo.mapper.StorkMapper;
import com.mrgao.demo.service.IUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动器
 * @author Mr.Gao
 */
@EnableAsync
@EnableConfigurationProperties
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackageClasses = {StorkMapper.class})
public class XbqxDemoApplication {

    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext applicationContext = (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(XbqxDemoApplication.class, args);

        System.out.println("当前线程:" + Thread.currentThread().getName());
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        // 用户service
        userService.logRecodeInf(1000L);
        System.out.println("当前线程:" + Thread.currentThread().getName());
    }

}

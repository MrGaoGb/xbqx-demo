package com.mrgao.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Gao
 * @date 2024/1/8 15:30
 * @apiNote:
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        // redis 地址为127.0.0.1:6379 时, 可以无需配置 一行代码搞定
//        RedissonClient redisson = Redisson.create();

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setPassword(password)
                .setDatabase(1) // 存储到数据库索引值等于1的库
        ;
        return Redisson.create(config);
    }
}

package com.mrgao.demo.service;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.mrgao.demo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Gao
 * @date 2023/12/29 10:17
 * @apiNote:
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    @Qualifier("teacherService")
    private IUserService teacherService;

    /**
     * 日志记录用户信息
     *
     * @param id
     */
    @Override
    public void logRecodeInf(Long id) {
        log.info("获取(普通)用户信息. 当前线程:{}", Thread.currentThread().getName());
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUserName("O-小高");
        userInfo.setPassword("123456");
        log.info("获取(普通)用户信息完成. 存储信息:{} ,当前线程:{}", JSONObject.toJSONString(userInfo), Thread.currentThread().getName());
        // 异步调用
        teacherService.logRecodeInf(id);

        // 异步调用的sendSmsCode会失效
        sendSmsCode();
    }


    @Async
    public void sendSmsCode() {
        String smsCode = RandomUtil.randomString(6);
        log.info("当前线程:{} 发送短信验证码: {}", Thread.currentThread().getName(), smsCode);
    }
}

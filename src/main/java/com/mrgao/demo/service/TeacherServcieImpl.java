package com.mrgao.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.mrgao.demo.entity.UserInfo;
import com.mrgao.demo.mapper.StorkMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Gao
 * @date 2023/12/29 10:19
 * @apiNote:
 */
@Slf4j
@Service("teacherService")
public class TeacherServcieImpl implements IUserService {

    private final StorkMapper storkMapper;

    private final AccountService accountService;

    public TeacherServcieImpl(StorkMapper storkMapper, AccountService accountService) {
        this.storkMapper = storkMapper;
        this.accountService = accountService;
    }

    //@Async
    @Override
    public void logRecodeInf(Long id) {
        log.info("获取(教师)用户信息. 当前线程:{}", Thread.currentThread().getName());
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUserName("T-小高");
        userInfo.setPassword("123456");
        log.info("获取(教师)用户信息完成. 存储信息:{} , 当前线程:{}", JSONObject.toJSONString(userInfo), Thread.currentThread().getName());
    }
}

package com.mrgao.demo.service;

import com.mrgao.demo.entity.UserInfo;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Mr.Gao
 * @date 2023/12/29 10:14
 * @apiNote:
 */
public interface IUserService {

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @Async
    void logRecodeInf(Long id);
}

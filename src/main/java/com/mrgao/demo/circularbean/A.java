package com.mrgao.demo.circularbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Gao
 * @date 2024/3/11 11:51
 * @apiNote:循环依赖
 */
@Service
public class A {


    @Autowired
    private B b;

    /**
     * 案例描述：
     */
    @Async
    public void methodA() {
        System.out.println("方法B执行了...");
        System.out.println(b);
        System.out.println("方法B执行了...");
    }

}

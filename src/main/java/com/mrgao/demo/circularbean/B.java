package com.mrgao.demo.circularbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Gao
 * @date 2024/3/11 11:51
 * @apiNote:
 */
@Service
public class B {

    @Autowired
    @Lazy
    private A a;


    /**
     * 案例描述：
     */
    //@Async
    public void methodB() {
        System.out.println("方法A执行了...");
        System.out.println(a);
        System.out.println("方法A执行了...");
    }
}

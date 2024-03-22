package com.mrgao.demo.thread;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2024/1/8 14:06
 * @apiNote:通过代码案例验证执行sleep和wait方法时锁的释放情况
 */
public class TestSleepOrWait {

    public static void main(String[] args) throws Exception {

        // 构建锁对象
        Object lock = new Object();

        //// sleep 休眠 不释放锁
        //Thread t1 = new Thread(() -> {
        //    System.out.println(Thread.currentThread().getName() + "==准备抢锁!");
        //    // 加锁
        //    synchronized (lock) {
        //        System.out.println(Thread.currentThread().getName() + "==抢到锁了!");
        //        System.out.println(Thread.currentThread().getName() + "==准备进入沉睡,当前时间：" + LocalDateTime.now());
        //        try {
        //            // 沉睡5s
        //            TimeUnit.SECONDS.sleep(5);
        //        } catch (InterruptedException e) {
        //            throw new RuntimeException(e);
        //        }
        //        System.out.println(Thread.currentThread().getName() + "==结束沉睡，当前时间:" + LocalDateTime.now());
        //    }
        //}, "T1");

        // wait方法 休眠 释放锁资源
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "==准备抢锁!");
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "==抢到锁了!");
                System.out.println(Thread.currentThread().getName() + "==准备进入沉睡,当前时间：" + LocalDateTime.now());
                try {
                    // 休眠五秒
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "==结束沉睡，当前时间:" + LocalDateTime.now());
            }

        }, "T2");

        // 启动线程
        //t1.start();
        t2.start();

        //t2.join();// 等待线程t2执行完毕后 再向下执行(串行执行)
        // 等待线程先获取锁
        Thread.sleep(200);

        // 尝试获取锁
        System.out.println("主线程尝试获取锁" + LocalDateTime.now());
        synchronized (lock) {
            System.out.println("主线程获取到锁" + LocalDateTime.now());
        }
    }
}

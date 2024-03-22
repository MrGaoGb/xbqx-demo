package com.mrgao.demo.utils;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2024/1/5 11:36
 * @apiNote:
 */
public class CompleteFutureTest {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String resultData = UUID.randomUUID().toString();
            System.out.println(Thread.currentThread().getName() + ">唯一标识<:" + resultData);
            return resultData;
        });
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String resultData = UUID.randomUUID().toString();
            System.out.println(Thread.currentThread().getName() + "<唯一标识>:" + resultData);
            return resultData;
        });

        // 任意一个执行结束便返回结果
        //CompletableFuture.anyOf(future, future1).whenComplete((resData, ex) -> {
        //    System.out.println(Thread.currentThread().getName() + "<响应结果>:" + resData);
        //});

        // 所有线程
        CompletableFuture.allOf(future, future1).thenApply(uuid -> {
            System.out.println(Thread.currentThread().getName() + "<响应结果>:" + uuid);
            return uuid;
        });

        Thread.currentThread().join();
    }
}

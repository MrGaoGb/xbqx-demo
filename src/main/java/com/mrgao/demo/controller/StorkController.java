package com.mrgao.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrgao.demo.mapper.StorkMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2024/1/8 15:34
 * @apiNote:
 */
@Slf4j
@RestController
@RequestMapping("/stork")
public class StorkController {

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private StorkMapper storkMapper;

    @PostMapping("/timeout")
    public JSONObject helloTimeOut(@RequestParam String uuid) {
        log.info("【模拟超时请求】:{} 开始时间:{}", uuid, LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("【模拟超时请求】:{} 结束时间:{}", uuid, LocalDateTime.now());
        JSONObject result = new JSONObject();
        result.put("content", "helloTomcat");
        return result;
    }

    /**
     * 获取布隆过滤器
     *
     * @param uuid
     * @return
     */
    @PostMapping("/fetchBloomFilter")
    public String fetchBloomFilter(@RequestParam String uuid) {
        log.info("【获取布隆过滤器】:{} 开始时间:{}", uuid, LocalDateTime.now());
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter("PhoneList");
        bloomFilter.tryInit(100000, 0.01);
        //bloomFilter.contains()
        return null;
    }

    /**
     * 商品下单接口
     *
     * @param pId
     * @return
     */
    @PostMapping("/pay")
    public String payOrder(@RequestParam String pId) {
        RLock lock = redissonClient.getLock("PRD:" + pId);
        String response = null;
        try {
            log.info("线程:【{}】 尝试获取锁 ，{}", Thread.currentThread().getId(), lock);
            if (lock.tryLock(10, 20, TimeUnit.SECONDS) && lock.isLocked()) {
                log.info("线程:【{}】 下单中", Thread.currentThread().getId());
                Integer count = storkMapper.getStorkCountByPid(pId);
                if ((count - 1) < 0) {
                    throw new IllegalArgumentException("库存不足");
                }
                TimeUnit.SECONDS.sleep(2);
                // 更新库存
                storkMapper.updateStorkByPid(pId);
                response = "success";
            } else {
                lock = null;
                response = "请勿重复下单";
            }

        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                lock = null;
                Thread.currentThread().interrupt();
                log.error("获取锁失败,线程已被中断: 唯一流水号:{}", pId);
                return "获取锁失败,锁中断咯";
            } else {
                log.error("执行异常，异常原因:{} 唯一流水号:{}", e.getMessage(), pId);
            }
        } finally {
            if (null != lock && lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("线程:【{}】 锁释放完成!", Thread.currentThread().getId());
            }
            log.info("线程:【{}】 下单完成 响应信息:{}", Thread.currentThread().getId(), response);
        }
        return response;
    }
}

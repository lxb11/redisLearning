package com.hmdp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author dell
 * @Date 2025/4/28 16:48
 * @Description
 */
@Slf4j
@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redissonClient;
    private RLock lock;

    @BeforeEach
    public void setUp() {
        lock = redissonClient.getLock("order");
    }

    @Test
    public void method1() {
        // 尝试获取锁
        boolean isLock = this.lock.tryLock();
        if (!isLock) {
            log.error("获取锁失败 .... 1");
            return;
        }
        try {
            log.info("获取锁成功 .... 1");
            method2();
            log.info("开始执行业务 .... 1");
        } finally {
            log.warn("准备释放锁 .... 1");
            lock.unlock();
        }
    }


    public void method2() {
        // 尝试获取锁
        boolean isLock = this.lock.tryLock();
        if (!isLock) {
            log.error("获取锁失败 .... 2");
            return;
        }
        try {
            log.info("获取锁成功 .... 2");
            log.info("开始执行业务 .... 2");
        } finally {
            log.warn("准备释放锁 .... 2");
            lock.unlock();
        }
    }
}

package com.hmdp.utils;

/**
 * @Author dell
 * @Date 2025/4/25 9:51
 * @Description
 */
public interface ILock {

    /**
     * 尝试获取锁
     * @param timeoutSec 所持有的超时时间，过期后自动释放
     * @return true代表获取锁成功；false代表获取锁失败
     */
    boolean tryLock(long timeoutSec);

    /**
     * 释放锁
     */
    void unlock();
}

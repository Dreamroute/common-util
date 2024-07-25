package com.github.dreamroute.common.util;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author w.dehai.2024/7/25.15:28
 */
class AsyncUtilTest {

    private static final ExecutorService POOL = Executors.newFixedThreadPool(10);

    @Test
    void baseTest() throws Exception {
        // 正常返回
        String nomal = AsyncUtil.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "a";
        }, POOL).get();
        System.err.println("nomal: " + nomal);

        // 超时返回默认值null
        String nomalNull = AsyncUtil.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "a";
        }, POOL).get();
        System.err.println("nomalNull: " + nomalNull);

        // 超时返回指定值
        String defaultValue = AsyncUtil.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "a";
        }, POOL, "c").get();
        System.err.println("defaultValue: " + defaultValue);

        // 异常返回默认值
        Object exDefaultValue = AsyncUtil.supplyAsync(() -> {
            throw new RuntimeException("ex");
        }, POOL).get();
        System.err.println("exDefaultValue: " + exDefaultValue);

        // 异常返回指定值
        String exValue = AsyncUtil.supplyAsync(() -> {
            throw new RuntimeException("ex");
        }, POOL, "d").get();
        System.err.println("exValue: " + exValue);

        // 抛出异常
        AsyncUtil.supplyAsyncThrowEx(() -> {
            throw new RuntimeException();
        }, POOL).exceptionally(e -> {
            System.err.println("抛出异常, 链式捕获");
            return null;
        }).get();

        // 抛出异常
        try {
            AsyncUtil.supplyAsyncThrowEx(() -> {
                throw new RuntimeException();
            }, POOL).get();
        } catch (Exception e) {
            System.err.println("抛出异常, 正常捕获");
        }
    }

}

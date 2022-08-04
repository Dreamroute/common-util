package com.github.dreamroute.common.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * 描述：异步处理工具类
 *
 * @author w.dehai
 */
public class AsyncUtil {

    private AsyncUtil() {}

    public  static <U> CompletableFuture<U> supply(Supplier<U> supplier) {
        return CompletableFuture.supplyAsync(supplier);
    }

    public  static <U> CompletableFuture<U> supply(Supplier<U> supplier, Executor executor) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }

    public static CompletableFuture<Void> run(Runnable runnable) {
        return CompletableFuture.runAsync(runnable);
    }

    public static CompletableFuture<Void> run(Runnable runnable, Executor executor) {
        return CompletableFuture.runAsync(runnable, executor);
    }

    public static CompletableFuture<Void> allOf(CompletableFuture<?>... cs) {
        return CompletableFuture.allOf(cs);
    }

    public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cs) {
        return CompletableFuture.anyOf(cs);
    }
}

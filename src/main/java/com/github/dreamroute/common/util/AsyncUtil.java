package com.github.dreamroute.common.util;

import org.apache.skywalking.apm.toolkit.trace.SupplierWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 描述: Java8通过CompletableFuture创建的任务不具备超时能力, 这里封装一个类似于CompletableFuture功能的工具, 具备创建超时任务的能力, 通过此工具创建的任务都带有超时失败的功能, 默认值为
 * {@link AsyncUtil#DEFAULT_TIMEOUT}
 *
 * @author w.dehi
 */
public class AsyncUtil {
    private AsyncUtil() {
    }

    /**
     * 默认超时时间
     */
    private static final long DEFAULT_TIMEOUT = 3L;

    /**
     * 默认超时时间单位
     */
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    private static final ScheduledThreadPoolExecutor DELAYER = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());

    static {
        DELAYER.setRemoveOnCancelPolicy(true);
    }

    private static final class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread daemonThread = new Thread(runnable);
            daemonThread.setDaemon(true);
            daemonThread.setName("AyncUtil-Scheduled-Thread");
            return daemonThread;
        }
    }

    private static <U> CompletableFuture<U> timeoutTask(long timeout, TimeUnit unit) {
        CompletableFuture<U> result = new CompletableFuture<>();
        DELAYER.schedule(() -> result.completeExceptionally(new TimeoutException()), timeout, unit);
        return result;
    }

    private static <U> CompletableFuture<U> common(Supplier<U> supplier, Executor executor, long timeout, TimeUnit unit) {
        // 用skywalking工具包装一下适应skywalking链路功能
        SupplierWrapper<U> supplierWrapper = SupplierWrapper.of(supplier);
        CompletableFuture<U> c = CompletableFuture.supplyAsync(supplierWrapper, executor);
        CompletableFuture<U> timeoutFuture = timeoutTask(timeout, unit);
        return c.applyToEither(timeoutFuture, Function.identity());
    }

    /**
     * 异步任务
     *
     * @param supplier     业务逻辑
     * @param executor     线程池
     * @param defaultValue 异常时默认值
     * @param timeout      超时时间
     * @param unit         超时单位
     * @param <U>          返回值类型
     * @return 返回带有超时能力的任务
     */
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor, U defaultValue, long timeout, TimeUnit unit) {
        return common(supplier, executor, timeout, unit).exceptionally(throwable -> defaultValue);
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor, long timeout, TimeUnit unit) {
        return common(supplier, executor, timeout, unit).exceptionally(throwable -> null);
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor, U defaultValue) {
        return common(supplier, executor, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT).exceptionally(throwable -> defaultValue);
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
        return common(supplier, executor, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT).exceptionally(throwable -> null);
    }

    public static <U> CompletableFuture<U> supplyAsyncThrowEx(Supplier<U> supplier, Executor executor, long timeout, TimeUnit unit) {
        return common(supplier, executor, timeout, unit);
    }

    public static <U> CompletableFuture<U> supplyAsyncThrowEx(Supplier<U> supplier, Executor executor) {
        return common(supplier, executor, DEFAULT_TIMEOUT, DEFAULT_TIME_UNIT);
    }

    public static CompletableFuture<Void> allOf(CompletableFuture<?>... cs) {
        return CompletableFuture.allOf(cs);
    }

    public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cs) {
        return CompletableFuture.anyOf(cs);
    }
}
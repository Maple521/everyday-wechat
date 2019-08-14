package com.maple.everyday.wechat.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description 线程池管理
 * @Date 2019/7/11 13:22
 * @Created by 王弘博
 */
@Slf4j
public class ExecutorUtils {

    private static ExecutorService executor = new ThreadPoolExecutor(4, 16, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(4096), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.warn("too many request, reject.");
        }
    });

    /**
     * @param command
     */
    public static void execute(Runnable command) {
        executor.execute(command);
    }
}

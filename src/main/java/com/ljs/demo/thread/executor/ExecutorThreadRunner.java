package com.ljs.demo.thread.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorThreadRunner implements Runnable {

    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    private Integer num;

    public ExecutorThreadRunner(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {

        System.out.println("thread:" + Thread.currentThread().getName() + ", time:" + format.format(new Date()));
        try {
            {
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 总结：
     - 池中线程数量固定，不会发生变化
     - 使用无界的LinkedBlockingQueue，要综合考虑生成与消费能力，生成过剩，可能导致堆内存溢出。
     - 适用一些很稳定很固定的正规并发线程，多用于服务器
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 50; i++) {
            pool.submit(new ExecutorThreadRunner(i + 1));
        }
        pool.shutdown();
    }


}

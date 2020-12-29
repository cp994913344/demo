package com.ljs.demo.utils.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchRunner1 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.wait();
                    System.out.println(Thread.currentThread().getName() + "开始运行");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(2000);
        countDownLatch.countDown();
    }
}

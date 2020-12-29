package com.ljs.demo.utils.CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class CountDownLatchRunner2 {

    public static void main(String[] args) throws InterruptedException{

        //计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);

        //写入的结果
        final List<String> list = new ArrayList<>();

        //四列

        for (int i = 0; i < 5; i++) {
            final int index = 1;
            final String a = "123";
            new Thread(() -> {
                try{
                    Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
                    System.out.println("finish" + index + Thread.currentThread().getName());
                    list.add(a);
                    countDownLatch.countDown();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.await();

        System.out.println("主线程：执行完所有任务后，进行汇总");
        System.out.println(list);
        System.out.println(list.size());

    }

}

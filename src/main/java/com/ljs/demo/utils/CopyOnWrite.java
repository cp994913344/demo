package com.ljs.demo.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWrite {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("1");
        list.add("2");
        list.add("3");

        final Iterator<String> it = list.iterator();

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                }
            });
        }

        for (int i = 1; i < 10; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    list.add("小花");
                }
            });
        }

    }

}

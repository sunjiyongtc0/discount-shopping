package com.glisten.discount.shopping.ThreadWork;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class MapClear implements Runnable {


    private ConcurrentHashMap<String,Object> concurrentHashMap;
    public   MapClear(ConcurrentHashMap<String,Object> concurrentHashMap){
        this.concurrentHashMap=concurrentHashMap;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                System.out.println("监听数据");
                for (String s : concurrentHashMap.keySet()) {
                    System.out.println(s);
                    System.out.println(concurrentHashMap.get(s));
                    if ((long) concurrentHashMap.get(s) + 10 * 1000 > new Date().getTime()) {
                        System.out.println("删除数据");
                        concurrentHashMap.remove(s);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }





}

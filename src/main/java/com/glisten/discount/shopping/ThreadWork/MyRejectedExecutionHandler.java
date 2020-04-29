package com.glisten.discount.shopping.ThreadWork;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 超过最大处理线程数时，被执行拒绝策略
 * */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {


    public  void rejectedExecution(Runnable r, ThreadPoolExecutor executor){
        System.out.println("阻塞数据处理问题工作===============");

    }
}

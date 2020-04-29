package com.glisten.discount.shopping.ThreadWork;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.*;

/**
 *自定义线程池
 * */
public class MyThreadPoolExecutor extends ThreadPoolExecutor{

    private static final Logger logger = LoggerFactory.getLogger(MyThreadPoolExecutor.class);

    private ExecutorService tpe;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }


//    public ExecutorService getPool(BlockingQueue<Runnable> queue ){
//        logger.info(" This is MyThreadPoolExecutor info!!!");
//
//        MyRejectedExecutionHandler mr=new MyRejectedExecutionHandler();
//        this.tpe= new ThreadPoolExecutor(4,5,1, TimeUnit.SECONDS, queue,mr);
//        return  this.tpe;
//    }

    public Date ShutDownNew(){
        super.shutdown();
        Date overdate=new Date();
        return overdate;
    }

}

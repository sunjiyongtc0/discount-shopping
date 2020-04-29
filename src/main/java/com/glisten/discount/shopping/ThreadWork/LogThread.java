package com.glisten.discount.shopping.ThreadWork;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LogThread {
private  static  MyThreadPoolExecutor pool;

    static {
        LinkedBlockingQueue queue=  new LinkedBlockingQueue<Runnable>(3);
        MyRejectedExecutionHandler mr=new MyRejectedExecutionHandler();
        pool= new MyThreadPoolExecutor(2,5,1, TimeUnit.SECONDS, queue,mr);


    }

     public   void ActionLog(LinkedBlockingQueue work ,  ConcurrentHashMap<String,Object>  concurrentHashMap ){

         Thread thread0 = new Thread( new MapClear(concurrentHashMap));
         thread0.run();
        // 新建10个任务，并将它们添加到线程池中。
         for (int i = 1; i < 5; i++) {
             Runnable myrun = new LogRunnable(work);
             pool.execute(myrun);
         }
         Runnable runner1=new MapClear(concurrentHashMap);
            Thread thread1 = new Thread(runner1);
            thread1.run();
            // 关闭线程池
         pool.shutdown();

     }


}

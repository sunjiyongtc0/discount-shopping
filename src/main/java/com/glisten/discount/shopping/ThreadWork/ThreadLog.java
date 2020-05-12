package com.glisten.discount.shopping.ThreadWork;


import com.glisten.discount.shopping.Domain.TLoggerAction;
import com.glisten.discount.shopping.Service.Logger.LogActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 操作日志入库处理
 */
public class ThreadLog {
    protected static final Logger logger = LoggerFactory.getLogger(ThreadLog.class);
    private static final int queueSize = 50; // 入库队列大小
    private static boolean threadRun = true; // 线程是否运行

    /**
     * 队列
     */
    private static Queue<TLoggerAction> queue = new ConcurrentLinkedQueue<TLoggerAction>(); //	此队列按照 FIFO（先进先出）原则对元素进行排序

    public static void setThreadRun(boolean threadRun) {
        ThreadLog.threadRun = threadRun;
    }

    /**
     * 向队列中增加Syslog对象，基于ConcurrentLinkedQueue
     * @param syslog
     */
    public static void add(TLoggerAction syslog){
        if(null != syslog){	// 此队列不允许使用 null 元素
            synchronized(queue) {
                if(queue.size() <= queueSize){
                    queue.offer(syslog);
                }else{
                    queue.poll(); // 获取并移除此队列的头，如果此队列为空，则返回 null
                    queue.offer(syslog); // 将指定元素插入此队列的尾部
                    logger.error("日志队列：超过" + queueSize);
                }
            }
        }
    }

    /**
     * 获取Syslog对象，基于ConcurrentLinkedQueue
     * @return
     */
    public static TLoggerAction getLog(){
        synchronized(queue) {
            if(queue.isEmpty()){
                return null;
            }else{
                return queue.poll(); // 获取并移除此队列的头，如果此队列为空，则返回 null
            }
        }
    }

    /**
     * 启动日志入库线程
     */
    public static void startSaveDBThread() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread insertDbThread = new Thread(new Runnable() {
                    public void run() {
                        while (threadRun) {
                            try {
                                // 取队列数据
                                TLoggerAction log = getLog();
                                if(null == log){
                                    Thread.sleep(200);
                                } else {
                                    logger.info("保存操作日志到数据库start......"+log.toString());
//                                    LogService.SaveLog(log) ;// 日志入库
                                    logger.info("保存操作日志到数据库end......");
                                }
                            } catch (Exception e) {
                                logger.error("保存操作日志到数据库异常",e);
                                throw new RuntimeException("ThreadLog -> save Exception");
                            }
                        }
                    }
                });
                insertDbThread.setName("Thread-SysLog-insertDB-" + (i + 1));
                insertDbThread.start();
            }
            logger.info("启动日志入库线程成功");
        } catch (Exception e) {
            throw new RuntimeException("ThreadLog new Thread Exception");
        }
    }

}
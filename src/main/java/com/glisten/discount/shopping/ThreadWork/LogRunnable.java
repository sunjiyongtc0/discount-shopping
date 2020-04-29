package com.glisten.discount.shopping.ThreadWork;

import com.glisten.discount.shopping.Domain.TLoggerAction;

import java.util.concurrent.LinkedBlockingQueue;

public class LogRunnable implements Runnable {
    private LinkedBlockingQueue work;

    public LogRunnable( LinkedBlockingQueue work){
        this.work=work;
    }


    @Override
    public  void run(){
        try {
            TLoggerAction la =(TLoggerAction) work.take();
            System.out.println(la.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

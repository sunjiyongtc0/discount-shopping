package com.glisten.discount.shopping.ThreadWork;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1) // 执行顺序
public class InitData implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 开启日志启动线程
//        ThreadLog.startSaveDBThread();
    }
}

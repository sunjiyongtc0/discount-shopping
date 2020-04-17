package com.glisten.discount.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.glisten.discount.shopping.Mapper")
public class DiscountShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscountShoppingApplication.class, args);
    }

}

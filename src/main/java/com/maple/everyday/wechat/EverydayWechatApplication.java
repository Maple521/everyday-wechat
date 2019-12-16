package com.maple.everyday.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.maple.everyday.wechat.dao")
@ComponentScan({"com.bj.loan", "com.maple.everyday.wechat"})
@SpringBootApplication
public class EverydayWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(EverydayWechatApplication.class, args);
        System.out.println("----------everyday-wechat启动成功！");
    }

}

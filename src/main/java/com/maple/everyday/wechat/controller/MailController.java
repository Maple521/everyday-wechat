package com.maple.everyday.wechat.controller;

import com.maple.everyday.wechat.common.ExecutorUtils;
import com.maple.everyday.wechat.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Date 2019/8/14 15:34
 * @Created by 王弘博
 */
@RestController
public class MailController {

    @Resource
    private MailService mailService;

    @GetMapping("/testMail")
    public void testMail() {
        ExecutorUtils.execute(() -> {
            mailService.sendSimpleMail("547156671@qq.com", "么么哒", "hello，送你一颗小心心，并且给你翁栽倒");
        });
    }
}

package com.maple.everyday.wechat.controller;

import com.maple.everyday.wechat.common.Constants;
import com.maple.everyday.wechat.common.HttpClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 豆瓣电影
 * @Date 2019/12/16 21:40
 * @Created by 王弘博
 */
@RestController
public class DouBanController {

    @Resource
    private HttpClient httpClient;

    private static final String HOME = "https://movie.douban.com";

    @GetMapping("/movie")
    public String movie() {
        String result = httpClient.httpGet(HOME);
        return result;
    }
}

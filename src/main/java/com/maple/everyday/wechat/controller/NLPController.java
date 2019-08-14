package com.maple.everyday.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.maple.everyday.wechat.manager.NLPChat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Date 2019/8/14 11:38
 * @Created by 王弘博
 */
@RestController
public class NLPController {

    @Resource
    private NLPChat nlpChat;

    @GetMapping("/wechat")
    public String crawlingOne(String question) {
        JSONObject jsonObject = nlpChat.wechat(question);
        Integer ret = jsonObject.getInteger("ret");
        String msg = jsonObject.getString("msg");
        if (0 != ret) {
            return msg;
        }
        String answer = jsonObject.getString("answer");
        return answer;
    }
}

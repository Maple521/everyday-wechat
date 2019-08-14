package com.maple.everyday.wechat.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maple.everyday.wechat.common.Constants;
import com.maple.everyday.wechat.common.HttpClient;
import com.maple.everyday.wechat.common.Md5;
import com.maple.everyday.wechat.common.Utils;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description 对接腾讯的智能闲聊
 * @Date 2019/8/14 10:37
 * @Created by 王弘博
 */
@Component
public class NLPChat {

    private static final String URL = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";

    @Resource
    private HttpClient httpClient;

    public JSONObject wechat(String question) {

        long start = System.currentTimeMillis() / 1000;
        String uuid = Utils.uuid();

        Map<String, String> params = new TreeMap();
        params.put("app_id", Constants.APP_ID);
        params.put("time_stamp", String.valueOf(start));
        params.put("nonce_str", uuid);
        params.put("session", "100000");
        params.put("question", question);
        System.out.println(params);

        String sign = getSign(params);
        params.put("sign", sign);

        try {
            params.put("question", URLEncoder.encode(question, Constants.UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(params);
        String resposne = httpClient.httpPost(URL, JSON.toJSONString(params), ContentType.APPLICATION_FORM_URLENCODED);
        System.out.println(resposne);
        JSONObject jsonObject = JSON.parseObject(resposne);
        return jsonObject;
    }

    private String getSign(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((key, value) -> {
            try {
                sb.append(key).append("=").append(URLEncoder.encode(value, Constants.UTF8)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        sb.append("app_key=").append(Constants.APP_KEY);
        System.out.println("MD5之前为：" + sb.toString());
        String sign = Md5.encode(sb.toString(), Constants.UTF8).toUpperCase();
        System.out.println("MD5之后签名为：" + sign);
        return sign;
    }

}

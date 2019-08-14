package com.maple.everyday.wechat.common;

import com.bj.loan.util.common.http.HttpInvoker;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description HttpClient工具类
 * @Date 2019/8/14 9:20
 * @Created by 王弘博
 */
@Component
public class HttpClient {

    @Resource
    private HttpInvoker httpInvoker;

    public String httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpInvoker.setConnectionRequestTimeout(10000);
        httpInvoker.setConnectTimeout(10000);
        httpInvoker.setSocketTimeout(10000);
        httpInvoker.init();
        return httpInvoker.invoke(httpGet, HttpInvoker.STRING_ENTITY_HANDLER);
    }

    public String httpPost(String url, String params) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(params, ContentType.APPLICATION_JSON));
        httpInvoker.init();
        return httpInvoker.invoke(httpPost, HttpInvoker.STRING_ENTITY_HANDLER);
    }

    public String httpPost(String url, String params, ContentType contentType) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(params, contentType));
        httpInvoker.init();
        return httpInvoker.invoke(httpPost, HttpInvoker.STRING_ENTITY_HANDLER);
    }


}

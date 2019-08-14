package com.maple.everyday.wechat.common;

import java.util.UUID;

/**
 * @Description TODO
 * @Date 2019/8/14 10:59
 * @Created by 王弘博
 */
public class Utils {

    /**
     * UUID
     *
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

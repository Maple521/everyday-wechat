package com.maple.everyday.wechat.model.dto;

import lombok.Data;

/**
 * @Description TODO
 * @Date 2019/8/14 12:10
 * @Created by 王弘博
 */
@Data
public class NLPChatDTO {

    private String app_id;
    private String time_stamp;
    private String nonce_str;
    private String session;
    private String question;
    private String sign;

}

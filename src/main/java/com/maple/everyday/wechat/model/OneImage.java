package com.maple.everyday.wechat.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 一个---摄影/插画
 * @Date 2019/8/13 22:04
 * @Created by 王弘博
 */
@Data
@TableName("one_image")
public class OneImage implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("vol_image")
    private String volImage;

    private String type;

    private String content;

    @TableField("image_address")
    private String imageAddress;

    @TableField("crawl_time")
    private Date crawlTime;

    @TableField("create_time")
    private Date createTime;
}

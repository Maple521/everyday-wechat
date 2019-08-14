package com.maple.everyday.wechat.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 一个---问题
 * @Date 2019/8/13 22:04
 * @Created by 王弘博
 */
@Data
@TableName("one_question")
public class OneQuestion implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("vol_question")
    private String volQuestion;

    private String type;

    @TableField("question_title")
    private String questionTitle;

    @TableField("question_address")
    private String questionAddress;

    @TableField("crawl_time")
    private Date crawlTime;

    @TableField("create_time")
    private Date createTime;
}

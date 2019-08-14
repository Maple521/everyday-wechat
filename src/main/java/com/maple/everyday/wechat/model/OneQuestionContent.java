package com.maple.everyday.wechat.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 一个---问题的内容
 * @Date 2019/8/13 22:04
 * @Created by 王弘博
 */
@Data
@TableName("one_question_content")
public class OneQuestionContent implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("one_question_id")
    private Long oneQuestionId;

    @TableField("question_content")
    private String questionContent;
}

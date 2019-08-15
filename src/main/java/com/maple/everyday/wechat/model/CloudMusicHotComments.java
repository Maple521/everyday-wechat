package com.maple.everyday.wechat.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 网易云音乐---热评
 * @Date 2019/8/13 22:04
 * @Created by 王弘博
 */
@Data
@TableName("cloud_music_hot_comments")
public class CloudMusicHotComments implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("song_id")
    private Long songId;

    @TableField("user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_pic")
    private String userPic;

    @TableField("like_count")
    private Integer likeCount;

    @TableField("comment_date")
    private Date commentDate;

    private String content;

    @TableField("create_time")
    private Date createTime;
}

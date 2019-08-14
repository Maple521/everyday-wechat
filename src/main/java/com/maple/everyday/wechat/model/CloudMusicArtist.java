package com.maple.everyday.wechat.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 网易云音乐---歌手
 * @Date 2019/8/13 22:04
 * @Created by 王弘博
 */
@Data
@TableName("cloud_music_artist")
public class CloudMusicArtist implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("artist_id")
    private Long artistId;

    @TableField("artist_name")
    private String artistName;

    @TableField("artist_desc")
    private String artistDesc;

    @TableField("artist_pic")
    private String artistPic;

    @TableField("create_time")
    private Date createTime;
}

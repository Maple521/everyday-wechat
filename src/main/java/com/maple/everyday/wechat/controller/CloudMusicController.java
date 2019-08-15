package com.maple.everyday.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maple.everyday.wechat.common.Constants;
import com.maple.everyday.wechat.common.ExecutorUtils;
import com.maple.everyday.wechat.common.HttpClient;
import com.maple.everyday.wechat.model.CloudMusicArtist;
import com.maple.everyday.wechat.model.CloudMusicComments;
import com.maple.everyday.wechat.model.CloudMusicHotComments;
import com.maple.everyday.wechat.model.CloudMusicSong;
import com.maple.everyday.wechat.service.CloudMusicArtistService;
import com.maple.everyday.wechat.service.CloudMusicCommentsService;
import com.maple.everyday.wechat.service.CloudMusicHotCommentsService;
import com.maple.everyday.wechat.service.CloudMusicSongService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 网易云音乐 https://api.imjad.cn/cloudmusic/?type=?&id=?
 * @Date 2019/8/13 19:23
 * @Created by 王弘博
 */
@RestController
public class CloudMusicController {

    @Resource
    private HttpClient httpClient;

    //拿到一个歌手的所有歌曲
    //https://music.163.com/artist?id=6452

    //通过歌曲ID拿到分页所有的评论
    //http://music.163.com/api/v1/resource/comments/R_SO_4_210049?limit=20&offset=40

    //查看一个歌曲的地址
    //https://api.imjad.cn/cloudmusic/?song=?&id=210049

    /**
     * 王力宏的ID
     */
    private static final int WLH_ID = 5346;

    @Resource
    private CloudMusicArtistService artistService;

    @Resource
    private CloudMusicSongService songService;

    @Resource
    private CloudMusicCommentsService commentsService;

    @Resource
    private CloudMusicHotCommentsService hotCommentsService;


    @GetMapping("/music")
    public void music() {
        String result = httpClient.httpGet(Constants.CLOUD_MUSIC_ARTIST + WLH_ID);
        JSONObject json = JSON.parseObject(result);
        Integer code = json.getInteger("code");
        if (!code.equals(200)) {
            return;
        }
        CloudMusicArtist artist = new CloudMusicArtist();
        JSONObject artistJson = json.getJSONObject("artist");
        if (null != artistJson) {
            artist.setArtistId(artistJson.getLong("id"));
            artist.setArtistName(artistJson.getString("name"));
            artist.setArtistDesc(artistJson.getString("briefDesc"));
            artist.setArtistPic(artistJson.getString("picUrl"));
            artistService.insert(artist);
        }
        JSONArray hotSongsArray = json.getJSONArray("hotSongs");
        if (CollectionUtils.isEmpty(hotSongsArray)) {
            return;
        }
        for (Object hotSong : hotSongsArray) {
            ExecutorUtils.execute(() -> {
                JSONObject song = (JSONObject) hotSong;
                CloudMusicSong bean = new CloudMusicSong();
                JSONArray arArray = song.getJSONArray("ar");
                if (CollectionUtils.isNotEmpty(arArray)) {
                    StringBuilder sbId = new StringBuilder();
                    StringBuilder sbName = new StringBuilder();
                    for (Object ar : arArray) {
                        JSONObject arJson = (JSONObject) ar;
                        sbId.append(arJson.getLong("id")).append("&");
                        sbName.append(arJson.getString("name")).append("&");
                    }
                    bean.setArtistId(StringUtils.substringBeforeLast(sbId.toString(), "&"));
                    bean.setArtistName(StringUtils.substringBeforeLast(sbName.toString(), "&"));
                }
                Long songId = song.getJSONObject("privilege").getLong("id");
                bean.setSongId(songId);
                bean.setSongName(song.getString("name"));
                bean.setSongAddress(Constants.CLOUD_MUSIC_SONG + songId + ".mp3");
                songService.insert(bean);

                String commnetsResult = httpClient.httpGet(Constants.CLOUD_MUSIC_COMMNENTS + songId);
                JSONObject jsonObject = JSON.parseObject(commnetsResult);
                if (200 != jsonObject.getInteger("code")) {
                    return;
                }
                List<CloudMusicHotComments> needHotComments = new ArrayList<>();
                JSONArray hotCommentsArray = jsonObject.getJSONArray("hotComments");
                if (CollectionUtils.isNotEmpty(hotCommentsArray)) {
                    for (Object hotComment : hotCommentsArray) {
                        JSONObject hotCommentJson = (JSONObject) hotComment;
                        CloudMusicHotComments hotComments = new CloudMusicHotComments();
                        hotComments.setCommentDate(new Date(Long.valueOf(hotCommentJson.getString("time"))));
                        hotComments.setContent(hotCommentJson.getString("content"));
                        hotComments.setLikeCount(hotCommentJson.getIntValue("likedCount"));
                        hotComments.setSongId(songId);
                        JSONObject user = hotCommentJson.getJSONObject("user");
                        hotComments.setUserId(user.getLong("userId"));
                        hotComments.setUserName(user.getString("nickname"));
                        hotComments.setUserPic(user.getString("avatarUrl"));
                        needHotComments.add(hotComments);
                    }
                }

                List<CloudMusicComments> needComments = new ArrayList<>();
                JSONArray commentsArray = jsonObject.getJSONArray("comments");
                if (CollectionUtils.isNotEmpty(commentsArray)) {
                    for (Object comment : commentsArray) {
                        JSONObject commentJson = (JSONObject) comment;
                        CloudMusicComments comments = new CloudMusicComments();
                        comments.setCommentDate(new Date(Long.valueOf(commentJson.getString("time"))));
                        comments.setContent(commentJson.getString("content"));
                        comments.setLikeCount(commentJson.getIntValue("likedCount"));
                        comments.setSongId(songId);
                        JSONObject user = commentJson.getJSONObject("user");
                        comments.setUserId(user.getLong("userId"));
                        comments.setUserName(user.getString("nickname"));
                        comments.setUserPic(user.getString("avatarUrl"));
                        needComments.add(comments);
                    }
                }
                if (CollectionUtils.isNotEmpty(needHotComments)) {
                    hotCommentsService.insertBatch(needHotComments);
                }
                if (CollectionUtils.isNotEmpty(needComments)) {
                    commentsService.insertBatch(needComments);
                }
            });
        }
    }
}


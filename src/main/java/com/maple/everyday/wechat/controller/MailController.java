package com.maple.everyday.wechat.controller;

import com.maple.everyday.wechat.common.ExecutorUtils;
import com.maple.everyday.wechat.model.CloudMusicSong;
import com.maple.everyday.wechat.service.CloudMusicSongService;
import com.maple.everyday.wechat.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Date 2019/8/14 15:34
 * @Created by 王弘博
 */
@RestController
public class MailController {

    @Resource
    private MailService mailService;

    @Resource
    private CloudMusicSongService songService;

    @GetMapping("/mail")
    public void mail() {
        ExecutorUtils.execute(() -> {
            CloudMusicSong cloudMusicSong = songService.selectById(1);
            mailService.sendSimpleMail("295613906@qq.com", "小可爱，送你一首歌", cloudMusicSong.getSongAddress());
        });
    }
}

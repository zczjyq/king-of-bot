package com.kob.backend.controller.info;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.user.info.OtherUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class OtherUserInfoController {
    @Autowired
    private OtherUserInfoService otherUserInfoService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/otheruserinfo/")
    public JSONObject getOtherUserInfo(@RequestParam Map<String, String> data) {
        Integer id = Integer.valueOf(data.get("id"));
        String username = otherUserInfoService.getUsername(id);
        String photo = otherUserInfoService.getPhoto(id);
        Integer rating = otherUserInfoService.getRating(id);
        Integer follows = otherUserInfoService.getFollows(id);
        String signature = otherUserInfoService.getSignature(id);
        Date registerTime = otherUserInfoService.getRegisterTime(id);
        Integer ranking = otherUserInfoService.getRanking(id);
        String teams = otherUserInfoService.getTeams(id);
        String ratingList = otherUserInfoService.getRatingList(id);

        JSONObject resp = new JSONObject();
        // 将数据放入 resp
        resp.put("id", id);
        resp.put("username", username);
        resp.put("photo", photo);
        resp.put("rating", rating);
        resp.put("follows", follows);
        resp.put("signature", signature);
        resp.put("registerTime", registerTime);
        resp.put("ranking", ranking);
        resp.put("teams", teams);
        resp.put("ratingList", ratingList); // 如果 ratingList 是可以直接放入 JSONObject 的
        resp.put("teamId", userMapper.selectById(id).getTeamId());
        return resp;
    }
}

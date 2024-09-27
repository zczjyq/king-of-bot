package com.kob.backend.service.impl.info;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.user.info.OtherUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OtherUserInfoServiceImpl implements OtherUserInfoService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String getUsername(Integer id) {
        return userMapper.selectById(id).getUsername();
    }

    @Override
    public String getPhoto(Integer id) {
        return userMapper.selectById(id).getPhoto();
    }

    @Override
    public Integer getRating(Integer id) {
        return userMapper.selectById(id).getRating();
    }

    @Override
    public Integer getFollows(Integer id) {
        return userMapper.selectById(id).getFollows();
    }

    @Override
    public String getSignature(Integer id) {
        return userMapper.selectById(id).getSignature();
    }

    @Override
    public Date getRegisterTime(Integer id) {
        return userMapper.selectById(id).getRegistertime();
    }

    @Override
    public Integer getRaking(Integer id) {
        Integer rating = userMapper.selectById(id).getRating();
        List<Integer> ratings = userMapper.getAllRatings();
        // 假设 ratings 是一个 List<Integer>
        ratings.sort(Collections.reverseOrder());

        int res = 0;
        while (ratings.get(res) > rating) res++;
        return res + 1;
    }

    @Override
    public String getTeams(Integer id) {
        return userMapper.selectById(id).getTeams();
    }

    @Override
    public List<Map<Date, Integer>> getRatingList(Integer id) {
        return userMapper.selectById(id).getRatinglist();
    }
}

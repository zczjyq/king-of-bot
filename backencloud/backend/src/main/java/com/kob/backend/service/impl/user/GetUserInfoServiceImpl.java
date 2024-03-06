package com.kob.backend.service.impl.user;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetUserInfoServiceImpl implements GetUserInfoService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> getUserInfo(Map<String, String> data) {
        int id = Integer.parseInt(data.get("id"));
        User user =  userMapper.selectById(id);
        Map<String, String> map = new HashMap<>();

        String photo = user.getPhoto();
        String username = user.getUsername();
        int follows = user.getFollows();
        int rating = user.getRating();

        map.put("username", username);
        map.put("follows", String.valueOf(follows));
        map.put("rating", String.valueOf(rating));
        map.put("photo", photo);

        return map;
    }
}

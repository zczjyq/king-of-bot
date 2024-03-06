package com.kob.backend.service.impl.follows;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.follows.AddFollowsCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddFollowsCountServiceImpl implements AddFollowsCountService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> addcount(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();

        int id = Integer.parseInt(data.get("user_id"));
        int change = Integer.parseInt(data.get("change"));

        User user = userMapper.selectById(id);
        user.setFollows(user.getFollows() + change);

        userMapper.updateById(user);
        map.put("follows", String.valueOf(user.getFollows()));
        return map;
    }
}

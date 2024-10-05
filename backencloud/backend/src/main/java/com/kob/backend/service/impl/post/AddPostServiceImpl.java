package com.kob.backend.service.impl.post;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.post.AddPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddPostServiceImpl implements AddPostService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
    Map<String, String> map = new HashMap<>();
        map.put("error_message", "sccuess");
        return map;
    }
}

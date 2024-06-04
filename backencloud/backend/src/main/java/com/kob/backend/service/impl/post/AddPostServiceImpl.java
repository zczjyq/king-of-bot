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
        int id = Integer.parseInt(data.get("id"));
        String post = data.get("post");
        User user = userMapper.selectById(id);
        String[] posts = user.getPosts().split(" ");
        int len = Integer.parseInt(posts[0]);
        len ++;
        posts[0] = String.valueOf(len);
        String new_posts = String.join(" ", posts);
        new_posts += (" " + String.valueOf(len) + " " + post);
        user.setPosts(new_posts);
        userMapper.updateById(user);
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "sccuess");
        return map;
    }
}

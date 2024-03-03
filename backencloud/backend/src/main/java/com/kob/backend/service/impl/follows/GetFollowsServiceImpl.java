package com.kob.backend.service.impl.follows;

import com.kob.backend.mapper.FollowsMapper;
import com.kob.backend.pojo.Follows;
import com.kob.backend.service.follows.GetFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetFollowsServiceImpl implements GetFollowsService {
    @Autowired
    private FollowsMapper followsMapper;

    @Override
    public Map<String, String> getfollows(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        String status;
        boolean is_match = false;

        int id = 0;
        int follower = Integer.parseInt(data.get("follower"));
        int following = Integer.parseInt(data.get("following"));
        if (follower == following) {
            map.put("error_message", "self");
            return map;
        }

        // 找出总共有多少数据
        Long all = followsMapper.selectCount(null);

        // 寻找在数据库中的索引是什么
        for (int i = 1; i <= all; i ++ ) {
            Follows follows = followsMapper.selectById(i);
            int followingId = follows.getFollowingId();
            int followerId = follows.getFollowerId();
            if (followerId == follower && followingId == following) {
                is_match = true;
                id = i;
                break;
            }
        }
        if (is_match) {
            status = followsMapper.selectById(id).getStatus();
            map.put("error_message", status);
            return map;
        } else {
            map.put("error_message", "unfollow");
            return map;
        }
    }
}

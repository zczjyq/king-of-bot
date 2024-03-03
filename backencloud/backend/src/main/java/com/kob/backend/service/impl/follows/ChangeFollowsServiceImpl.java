package com.kob.backend.service.impl.follows;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kob.backend.mapper.FollowsMapper;
import com.kob.backend.pojo.Follows;
import com.kob.backend.service.follows.ChangeFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChangeFollowsServiceImpl implements ChangeFollowsService {
    @Autowired
    private FollowsMapper followsMapper;

    @Override
    public Map<String, String> change(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        boolean is_match = false;

        int id = 0;
        int follower = Integer.parseInt(data.get("follower"));
        int following = Integer.parseInt(data.get("following"));
        String status = data.get("status");
        if (follower == following) {
            map.put("error_message", "不能自己关注自己");
            return map;
        }
        Long all = followsMapper.selectCount(null);
        System.out.println(all);

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

        // 如果不在数据库里面，将新建数据就加入数据库
        if (!is_match) {
            Follows follows = new Follows(null, follower, following, new Date(), new Date(), status);
            followsMapper.insert(follows);

            map.put("error_message", "成功关注");
            return map;
        } else {
            // 如果在数据库里面，并且状态需要更新则更新状态
            if ( !status.equals(followsMapper.selectById(id).getStatus())){
                Follows follows = new Follows(id, follower, following, new Date(), new Date(), status);
                UpdateWrapper<Follows> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", id);
                followsMapper.updateById(follows);

                map.put("error_message", "成功修改");
                return map;
            }
        }
        map.put("error_message", "状态无需更新");
        return map;
    }
}

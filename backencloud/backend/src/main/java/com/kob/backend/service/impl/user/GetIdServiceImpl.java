package com.kob.backend.service.impl.user;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.user.GetIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GetIdServiceImpl implements GetIdService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> getid(Map<String, String>data) {
        System.out.println("data" + data.toString());
        String name = data.get("username");
        long all = userMapper.selectCount(null);
        Map<String, String> map = new HashMap<>();

        for (int id = 1; id <= all; id ++ ) {
            System.out.println("username" + name + "  " + userMapper.selectById(id).getUsername());
            if (userMapper.selectById(id).getUsername().equals(name)) {
                System.out.println(id);
                map.put("id", String.valueOf(id));
                return map;
            }
        }
        map.put("id","0");
        return map;
    }
}

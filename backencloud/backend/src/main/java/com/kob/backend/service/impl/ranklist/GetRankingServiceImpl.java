package com.kob.backend.service.impl.ranklist;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.ranklist.GetRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class GetRankingServiceImpl implements GetRankingService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String getRanking(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        // 获取自己的rating分数
        Integer rating = userMapper.selectById(user.getId()).getRating();
        // 获取所有rating分数 并 排序
        List<Integer> ratings =  userMapper.getAllRatings();
        // 假设 ratings 是一个 List<Integer>
        ratings.sort(Collections.reverseOrder());

        int res = 0;
        while (ratings.get(res) > rating) res++;
        return Integer.toString(res + 1);
    }
}

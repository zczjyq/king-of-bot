package com.kob.backend.service.impl.ranklist;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kob.backend.DTO.UserTeamDTO;
import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.User;
import com.kob.backend.service.ranklist.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddRankListServiceImpl implements GetRankListService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public JSONObject getList(Integer page) {
        if (page == null || page < 1) {
            page = 1; // 默认值
        }

        IPage<User> userIPage = new Page<>(page, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rating");
        // 排除密码字段
        queryWrapper.select(User.class, field -> !field.getColumn().equals("password"));

        List<User> users = userMapper.selectPage(userIPage, queryWrapper).getRecords();
        List<UserTeamDTO> userTeamDTOList = new ArrayList<>();  // 用来存储用户和团队信息
        JSONObject resp = new JSONObject();

        // 遍历用户并获取 team 信息
        for (User user : users) {
            user.setPassword("");  // 避免返回敏感信息

            // 根据 teamId 查询团队信息
            Team team = teamMapper.selectById(user.getTeamId());
            UserTeamDTO userTeamDTO = new UserTeamDTO(user, team); // 将用户和团队放入DTO
            userTeamDTOList.add(userTeamDTO);  // 添加到DTO列表
        }

        // 将 DTO 列表返回到 JSON 响应中
        resp.put("users", userTeamDTOList);
        resp.put("users_count", userMapper.selectCount(null));

        return resp;
    }
}

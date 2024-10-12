package com.kob.backend.service.impl.team.disslove;

import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.pojo.User;
import com.kob.backend.service.team.disslove.TeamDissloveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamDissloveServiceImpl implements TeamDissloveService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Override
    public String TeamDisslove(Integer teamId) {
        teamMapper.deleteById(teamId);
        return "success";
    }

    @Override
    public String UserDisslove(Integer teamId) {
        List<User> users = userMapper.selectByTeamId(teamId);

        // 将所有 team_id 设为 null
        for (User user : users) {
            user.setTeamId(0);
            System.out.println(user.getTeamId());
            userMapper.updateById(user);  // 更新 user 记录
        }

        // 然后删除 team 记录
        teamMapper.deleteById(teamId);
        return "success";
    }

    @Override
    public String TeammemberDisslove(Integer teamId) {
        List<TeamMember> teamMembers = teamMemberMapper.selectByTeamId(teamId);
        for (TeamMember teamMember : teamMembers) {
            teamMemberMapper.deleteById(teamMember);
        }
        return "success";
    }
}

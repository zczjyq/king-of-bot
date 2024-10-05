package com.kob.backend.service.impl.team.members;

import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.service.team.members.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.PrimitiveIterator;

@Service
public class MembersServiceImpl implements MembersService {
    @Autowired
    private TeamMapper teamMapper;

    @Override
    public void addTeamMembers(Map<String, String> data) {
        String userId = data.get("userId");
        String teamId = data.get("teamId");
        Team team = teamMapper.selectById(teamId);
        String teamMembers = team.getTeamMember();
        teamMembers += "," + userId;
        team.setTeamMember(teamMembers);
        teamMapper.updateById(team);
    }
}

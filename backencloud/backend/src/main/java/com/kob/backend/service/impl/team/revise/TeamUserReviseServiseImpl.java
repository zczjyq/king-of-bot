package com.kob.backend.service.impl.team.revise;

import com.kob.backend.constant.TeamRole;
import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.pojo.User;
import com.kob.backend.service.team.TeamMemberService;
import com.kob.backend.service.team.revise.TeamUserReviseServise;
import org.jetbrains.annotations.TestOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class TeamUserReviseServiseImpl implements TeamUserReviseServise {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Override
    public Map<String, String> reviseUserRole(Map<String, String> data) {
        String userId = data.get("userId");
        String newRole = data.get("newRole");
        TeamMember teamMember = teamMemberMapper.selectByUserId(Integer.valueOf(userId));
        TeamRole fromRoleName = TeamRole.fromRoleName(newRole);
        teamMember.setRole(fromRoleName.getCode());
        teamMemberMapper.updateById(teamMember);
        System.out.println("wtfee");
        Map<String, String> map = new HashMap<>();
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }

    @Override
    public Map<String, String> removeUser(Map<String, String> data) {
        String userId = data.get("userId");
        String teamId = data.get("teamId");
        // 删除teamMemberMapper的信息
        teamMemberMapper.deleteById(teamMemberMapper.selectByUserId(Integer.valueOf(userId)));
        // 更新userMapper的信息
        User user = userMapper.selectById(userId);
        user.setTeamId(0);
        userMapper.updateById(user);
        // 更新teamMapper的信息
        Team team = teamMapper.selectById(teamId);
        String[] teamMembers = team.getTeamMember().split(",");
        String resp = "";
        for (String teamMember : teamMembers) {
            if (teamMember.equals(userId)) continue;
            else resp += teamMember + ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        team.setTeamMember(resp);
        teamMapper.updateById(team);
        Map<String, String> map = new HashMap<>();
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }
}

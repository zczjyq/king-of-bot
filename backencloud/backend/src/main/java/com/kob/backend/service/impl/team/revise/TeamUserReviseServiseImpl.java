package com.kob.backend.service.impl.team.revise;

import com.kob.backend.constant.TeamRole;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.service.team.TeamMemberService;
import com.kob.backend.service.team.revise.TeamUserReviseServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class TeamUserReviseServiseImpl implements TeamUserReviseServise {
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
}

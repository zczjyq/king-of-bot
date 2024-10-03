package com.kob.backend.service.impl.team;

import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.pojo.SignIn;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.kob.backend.constant.TeamConstant.*;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Override
    public Integer getTeamMemberStatus(Integer userId) {

        TeamMember teamMember = teamMemberMapper.selectByUserId(userId);
        if (teamMember == null) return TEAM_MEMBER_HAS_NO_TEAM;
        Integer status = teamMember.getStatus();

        if (Objects.equals(status, TEAM_MEMBER_STATUS_NORMAL)) {
            return TEAM_MEMBER_HAS_TEAM;
        }
        return TEAM_MEMBER_HAS_NO_TEAM;
    }

    @Override
    public Map<String, String> addTeamMember(Map<String, String> data) {
        Integer userId = Integer.valueOf(data.get("user_id"));
        Integer teamId = Integer.valueOf(data.get("team_id"));
        Integer role = Integer.valueOf(data.get("role"));
        TeamMember teamMember = new TeamMember(null, (long)teamId, (long)userId, role, new Date(), TEAM_MEMBER_STATUS_NORMAL);
        teamMemberMapper.insert(teamMember);
        Map<String, String> map = new HashMap<>();
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }

    @Override
    public Map<String, String> deleteTeamMember(Map<String, String> data) {
        return null;
    }

    @Override
    public Map<String, String> queryTeamMember(Map<String, String> data) {
        return null;
    }

    @Override
    public Map<String, String> deleteAllTeamMember(Map<String, String> data) {
        return null;
    }

    @Override
    public Map<String, String> postTeamMemberStatus(Map<String, String> data) {
        return null;
    }
}

package com.kob.backend.service.impl.team;

import com.kob.backend.constant.TeamRole;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.service.team.TeamJoinService;
import com.kob.backend.service.team.members.MembersService;
import com.kob.backend.service.user.teamId.TeamIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.kob.backend.constant.TeamConstant.*;
import static com.kob.backend.constant.TeamConstant.TEAM_JOIN_NO_TEAM;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class TeamJoinServiceImpl implements TeamJoinService {
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Autowired
    private MembersService membersService;

    @Autowired
    private TeamIdService teamIdService;

    @Override
    public Integer check(Map<String, String> data) {
        String teamId = data.get("teamId");
        String userId = data.get("userId");
        TeamMember teamMember = teamMemberMapper.selectByUserId(Integer.valueOf(userId));
        if (teamMember == null) {
            return TEAM_JOIN_NO_TEAM;
        }
        if (Objects.equals(teamMember.getTeamId(), Long.valueOf(teamId))) {
            return TEAM_JOIN_HAS_JOIN_THIS_TEAM;
        }

        return TEAM_JOIN_HAS_TEAM;
    }

    @Override
    public Map<String, String> join(Map<String, String> data) {
        HashMap<String, String> map = new HashMap<>();
        String teamId = data.get("teamId");
        String userId = data.get("userId");
        // 更新user表
        teamIdService.updateUserTeam(Integer.valueOf(teamId));
        // 更新team表
        membersService.addTeamMembers(data);

        TeamMember teamMember = teamMemberMapper.selectByUserId(Integer.valueOf(userId));
        if (teamMember == null) {
            teamMember = new TeamMember();
            teamMember.setMemberId(null);
            teamMember.setTeamId(Long.valueOf(teamId));
            teamMemberMapper.insert(teamMember);
        }
        teamMember.setUserId(Long.valueOf(userId));
        teamMember.setRole(TeamRole.MEMBER.getCode());
        teamMember.setJoinedAt(new Date());
        teamMember.setStatus(TEAM_MEMBER_STATUS_NORMAL);
        teamMemberMapper.updateById(teamMember);
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }
}

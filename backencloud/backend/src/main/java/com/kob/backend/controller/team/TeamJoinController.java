package com.kob.backend.controller.team;

import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.service.team.TeamJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.TeamConstant.*;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@RestController
public class TeamJoinController {
    @Autowired
    private TeamJoinService teamJoinService;

    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @PostMapping("/api/team/checkHasTeam/")
    Integer checkHasTeam(@RequestParam Map<String, String> data) {
        String userId = data.get("userId");
        TeamMember teamMember = teamMemberMapper.selectByUserId(Integer.valueOf(userId));
        if (teamMember == null) {
            return TEAM_JOIN_NO_TEAM;
        }
        return 1;
    }
    @PostMapping("/api/team/join/")
    Map<String, String> join(@RequestParam Map<String, String> data) {
        String userId = data.get("userId");
        String teamId = data.get("teamId");
        Map<String, String> map = new HashMap<>();

        Integer userState = teamJoinService.check(data);
        if (userState.equals(TEAM_JOIN_HAS_TEAM)) {
            map.put(STATICRETURNMESSAGE, TEAM_JOIN_HAS_TEAM_MEG);
            return map;
        }
        if (userState.equals(TEAM_JOIN_HAS_TEAM)) {
            map.put(STATICRETURNMESSAGE, TEAM_JOIN_HAS_TEAM_MEG);
            return map;
        }
        if (userState.equals(TEAM_JOIN_HAS_JOIN_THIS_TEAM)) {
            map.put(STATICRETURNMESSAGE, TEAM_JOIN_HAS_JOIN_THIS_TEAM_MEG);
            return map;
        }
        if (userState.equals(TEAM_JOIN_BANNED_TEAM)) {
            map.put(STATICRETURNMESSAGE, TEAM_JOIN_BANNED_TEAM_MEG);
            return map;
        }

        teamJoinService.join(data);
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }
}

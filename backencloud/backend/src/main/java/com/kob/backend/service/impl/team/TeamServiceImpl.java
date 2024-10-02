package com.kob.backend.service.impl.team;

import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kob.backend.constant.TeamConstant.TEAM_STATUS_NORMAL;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    // todo
    @Override
    public Map<String, String> check(Map<String, String> data) {
        String teamName = data.get("teamName");
        System.out.println(teamName);
        Map<String, String> map = new HashMap<>();
        if (teamName.isEmpty()) {
            map.put(STATICRETURNMESSAGE, "战队名称不能为空");
            return map;
        }
        List<String> teamNames = teamMapper.selectTeamNames();
        System.out.println(teamNames.toString());
        for (String name : teamNames) {
            if (name.equals(teamName)) {
                map.put(STATICRETURNMESSAGE, "战队名称已被占用,请换个名字");
                return map;
            }
        }
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }

    @Override
    public Map<String, String> createNewTeam(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Team team = new Team(null,
                data.get("teamName"),
                data.get("teamDescription"),
                new Date(),
                new Date(),
                user.getId(),
                TEAM_STATUS_NORMAL,
                "",
                "",
                data.get("src")

        );
        teamMapper.insert(team);
        map.put("error_message", "success");
        return map;
    }
}

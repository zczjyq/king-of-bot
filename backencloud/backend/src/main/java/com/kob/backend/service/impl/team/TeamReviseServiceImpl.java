package com.kob.backend.service.impl.team;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.constant.TeamRole;
import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.team.TeamMemberService;
import com.kob.backend.service.team.TeamReviseService;
import com.kob.backend.service.team.members.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.TeamConstant.*;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class TeamReviseServiceImpl implements TeamReviseService {
    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeamMemberMapper teamMemberMapper;


    @Override
    public Map<String, String> reviseNewTeam(Map<String, String> data) {
        Map<String, String> map = new HashMap<>();

        String teamId = data.get("teamId");
        Team team1 = teamMapper.selectById(teamId);
        team1.setTeamName(data.get("teamName"));
        team1.setTeamDescription(data.get("teamDescription"));
        team1.setTeamHead(data.get("src"));

        teamMapper.updateById(team1);


        map.put("error_message", "success");
        return map;
    }

    @Override
    public JSONObject reviseTeamInfoGet(Map<String, String> data) {
        JSONObject map = new JSONObject();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        Integer teamId = user.getTeamId();

        Team team = teamMapper.selectById(teamId);
        // 获取职位
        {
            TeamMember teamMember = teamMemberMapper.selectByUserId(user.getId());
            Integer role = teamMember.getRole();
            TeamRole teamRole = TeamRole.fromCode(role);
            String roleName = teamRole.getRoleName();
            map.put("role", roleName);
        }
        String leaderName = userMapper.selectById(team.getTeamLeaderId()).getUsername();
        map.put("leaderName", leaderName);
        map.put("teamInfo", team);

        return map;
    }
}

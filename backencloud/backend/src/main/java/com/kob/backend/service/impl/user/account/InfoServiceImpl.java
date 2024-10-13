package com.kob.backend.service.impl.user.account;

import com.kob.backend.constant.TeamRole;
import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.mapper.TeamMemberMapper;
import com.kob.backend.pojo.Team;
import com.kob.backend.pojo.TeamMember;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Override
    public Map<String, String> getinfo() {
        // 从 Spring Security 上下文中获取认证信息
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = new HashMap<>();
        // 从认证信息中获取当前登录用户的详细信息
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user  = loginUser.getUser();

        Team team = teamMapper.selectById(user.getTeamId());
        if (team != null) {
            String teamName = team.getTeamName();
            map.put("teamName", teamName);
            map.put("teamId", String.valueOf(team.getId()));
        } else {
            map.put("teamName", "");
            map.put("teamId", "");
        }

        TeamMember teamMember = teamMemberMapper.selectByUserId(user.getId());
        if (teamMember != null) {
            String roleName = TeamRole.fromCode(teamMember.getRole()).getRoleName();
            map.put("teamRole", roleName);
        } else {
            map.put("teamRole", "");
        }

        map.put("error_message", "success");
        map.put("id", user.getId().toString());
        map.put("username", user.getUsername());
        map.put("photo", user.getPhoto());
        map.put("signature", user.getSignature());
        map.put("rating", user.getRating().toString());


        return map;
    }
}

package com.kob.backend.service.impl.user.teamId;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.teamId.TeamIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TeamIdServiceImpl implements TeamIdService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void updateUserTeam(Integer teamId) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl)authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        User user1 = userMapper.selectById(user.getId());
        user1.setTeamId(teamId);
        userMapper.updateById(user1);
    }
}

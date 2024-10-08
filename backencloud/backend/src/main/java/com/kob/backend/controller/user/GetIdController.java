package com.kob.backend.controller.user;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.GetIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetIdController {
    @Autowired
    GetIdService getIdService;

    @PostMapping("/api/user/getid/")
    public Map<String, String> getid(@RequestParam Map<String, String>data) {
        return getIdService.getid(data);
    }

    @GetMapping("/api/user/getTeamId/")
    public Integer getTeamId() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl)authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        System.out.println(user.getTeamId());
        return user.getTeamId();
    }
}

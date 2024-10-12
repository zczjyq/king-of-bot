package com.kob.backend.controller.team.disslove;

import com.kob.backend.service.team.disslove.TeamDissloveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TeamDissloveController {
    @Autowired
    private TeamDissloveService teamDissloveService;

    @PostMapping("/api/team/disslove/")
    String disslove(@RequestParam Map<String, String> data) {
        Integer teamId = Integer.parseInt(data.get("teamId"));
        teamDissloveService.TeammemberDisslove(teamId);
        teamDissloveService.UserDisslove(teamId);
//        teamDissloveService.TeamDisslove(teamId);
        return "success";
    }
}

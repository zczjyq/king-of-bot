package com.kob.backend.controller.team;

import com.kob.backend.mapper.TeamMapper;
import com.kob.backend.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/api/team/check/")
    public Map<String, String> check(@RequestParam Map<String, String> data) {
        return teamService.check(data);
    }

    @PostMapping("/api/team/create/")
    public Map<String, String> createNewTeam(@RequestParam Map<String, String> data) {
        return teamService.createNewTeam(data);
    }
}

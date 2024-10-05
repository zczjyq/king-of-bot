package com.kob.backend.controller.team;

import com.alibaba.fastjson2.JSONObject;
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

    @GetMapping("/api/team/getallteam/")
    public JSONObject getAllTeams(@RequestParam Map<String, String> data) {
        return teamService.getAllTeams(data);
    }

    @GetMapping("/api/team/info/")
    public JSONObject getTeamInfo(@RequestParam Map<String, String> data) {
        return teamService.getTeamsInfo(data);
    }
}
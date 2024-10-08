package com.kob.backend.controller.team;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.service.team.TeamReviseService;
import com.kob.backend.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TeamReviseController {
    @Autowired
    private TeamReviseService teamReviseService;
    @PostMapping("/api/team/getreviseinfo/")
    public JSONObject getReviseTeamInfo(@RequestParam Map<String, String> data) {
        return teamReviseService.reviseTeamInfoGet(data);
    }

    @PostMapping("/api/team/revise/")
    public Map<String, String> reviseTeamInfoGet(@RequestParam Map<String, String> data) {
        return teamReviseService.reviseNewTeam(data);
    }
}

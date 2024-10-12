package com.kob.backend.controller.team.revise;

import com.kob.backend.service.team.revise.TeamUserReviseServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TeamUserReviseController {
    @Autowired
    private TeamUserReviseServise teamUserReviseServise;

    @PostMapping("/api/user/updateRole/")
    public Map<String, String> updateRole(@RequestParam Map<String, String> data) {
        System.out.println("开始更新");
        return teamUserReviseServise.reviseUserRole(data);
    }
}

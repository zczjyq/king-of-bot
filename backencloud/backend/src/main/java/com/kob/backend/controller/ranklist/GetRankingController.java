package com.kob.backend.controller.ranklist;

import com.kob.backend.service.ranklist.GetRankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetRankingController {
    @Autowired
    private GetRankingService getRankingService;

    @GetMapping("/api/user/ranking/get/")
    public String getRanking(@RequestParam Map<String, String> data) {
        return getRankingService.getRanking(data);
    }
}

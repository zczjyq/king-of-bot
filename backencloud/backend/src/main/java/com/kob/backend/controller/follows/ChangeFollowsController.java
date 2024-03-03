package com.kob.backend.controller.follows;

import com.kob.backend.service.follows.ChangeFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ChangeFollowsController {
    @Autowired
    private ChangeFollowsService changeFollowsService;

    @GetMapping("/api/follows/change/")
    public Map<String, String> change(@RequestParam Map<String, String> data) {
        return changeFollowsService.change(data);
    }
}

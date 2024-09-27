package com.kob.backend.controller.user.follows;

import com.kob.backend.service.user.follows.followsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class followsController {
    @Autowired
    private followsService followsService;

    @GetMapping("/api/user/follow/get/")
    public String getFollows(@RequestParam Map<String, String> data) {
        return followsService.getFollows(data);
    }
}

package com.kob.backend.controller.follows;


import com.kob.backend.service.follows.GetFollowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetFollowsController {

    @Autowired
    private GetFollowsService getFollowsService;

    @GetMapping("/api/follows/get/")
    public Map<String, String> get(@RequestParam Map<String, String> data) {
        return getFollowsService.getfollows(data);
    }
}

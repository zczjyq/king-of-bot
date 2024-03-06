package com.kob.backend.controller.follows;

import com.kob.backend.service.follows.AddFollowsCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddFollowsCountController {

    @Autowired
    private AddFollowsCountService addFollowsCountService;

    @GetMapping("/api/follows/add/")
    public Map<String, String> addcount(@RequestParam Map<String, String> data) {
        return addFollowsCountService.addcount(data);
    }
}

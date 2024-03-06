package com.kob.backend.controller.user;

import com.kob.backend.service.user.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetUserInfoController {
    @Autowired
    private GetUserInfoService getUserInfoService;

    @PostMapping("/api/user/getInfo/")
    public Map<String, String> getInfo(@RequestParam Map<String, String> data) {
        return getUserInfoService.getUserInfo(data);
    }
}

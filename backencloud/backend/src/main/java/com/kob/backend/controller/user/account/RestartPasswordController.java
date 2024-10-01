package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.RestartPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RestartPasswordController {
    @Autowired
    private RestartPasswordService restartPasswordService;

    @PostMapping("/api/user/password/update/")
    public Map<String, String > updatePassword(@RequestParam Map<String, String> data) {
        return restartPasswordService.updatePassword(data);
    }
}

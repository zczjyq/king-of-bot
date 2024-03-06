package com.kob.backend.controller.user;

import com.kob.backend.service.user.GetIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetIdController {
    @Autowired
    GetIdService getIdService;

    @PostMapping("/api/user/getid/")
    public Map<String, String> getid(@RequestParam Map<String, String>data) {
        return getIdService.getid(data);
    }
}

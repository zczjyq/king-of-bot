package com.kob.backend.controller.signIn;

import com.kob.backend.mapper.SignInMapper;
import com.kob.backend.pojo.SignIn;
import com.kob.backend.service.signIn.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@RestController
public class SignInController {
    @Autowired
    private SignInService signInService;

    @PostMapping("/api/user/signin/")
    public Map<String, String> signIn(@RequestParam Map<String, String> data) {
        return signInService.signIn(data);
    }

    @GetMapping("/api/user/signin/get/")
    public Map<String, String> getSignIn(@RequestParam Map<String, String> data) {
        return signInService.getSignInInfo(data);
    }
}

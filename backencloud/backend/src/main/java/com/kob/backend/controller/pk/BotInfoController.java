package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pk/")
public class BotInfoController {

    @RequestMapping("getbotinfo/")
    public Map<String, String> getInfo(){
        Map<String, String> bot1 = new HashMap<>();
        bot1.put("name", "zzy");
        bot1.put("rating", "1238201983");
        return bot1;
    }
}

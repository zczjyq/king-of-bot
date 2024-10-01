package com.kob.backend.controller.mail;

import com.kob.backend.service.mail.feedbackService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class feedbackController {
    @Autowired
    private feedbackService feedbackService;
    @SneakyThrows
    @GetMapping("/api/feedback/")
    public Map<String, String> getFeedback(@RequestParam Map<String, String> data) {
        return feedbackService.getFeedback(data);
    }
}

package com.kob.backend.service.mail;

import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface feedbackService {
    Map<String, String> getFeedback(@RequestParam Map<String, String> data) throws MessagingException, UnsupportedEncodingException;
}

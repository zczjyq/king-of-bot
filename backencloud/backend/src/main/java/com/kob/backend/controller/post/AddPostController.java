package com.kob.backend.controller.post;

import com.kob.backend.service.post.AddPostService;
import com.kob.backend.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddPostController {
    @Autowired
    private AddPostService addPostService;

    @PostMapping("/api/user/posts/add/")
    public Map<String, String> add(@RequestParam Map<String, String> data) {
        return addPostService.add(data);
    }
}

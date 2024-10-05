package com.kob.backend.controller.ranklist;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.service.ranklist.GetRankListService;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetRankListController {
    @Autowired
    private GetRankListService getRankListService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/api/ranklist/getlist/")
    public JSONObject getList(@RequestParam Map<String, String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        return getRankListService.getList(page);
    }

    @GetMapping("/api/ranklist/getlistlimit/")
    public JSONObject getListLimit() {
        JSONObject resp = new JSONObject();
        resp.put("users", userMapper.getListLimit());
        return resp;
    }
}

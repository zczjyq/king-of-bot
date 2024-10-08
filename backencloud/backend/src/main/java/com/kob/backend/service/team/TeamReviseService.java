package com.kob.backend.service.team;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface TeamReviseService {
    Map<String, String> reviseNewTeam(Map<String, String> data);
    JSONObject reviseTeamInfoGet(Map<String, String> data);
}

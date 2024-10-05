package com.kob.backend.service.team;

import com.alibaba.fastjson2.JSONObject;

import java.util.Map;

public interface TeamService {
    Map<String, String> check(Map<String, String> data);
    Map<String, String> createNewTeam(Map<String, String> data);
    JSONObject getAllTeams(Map<String, String> data);
    JSONObject getTeamsInfo(Map<String, String> data);
}

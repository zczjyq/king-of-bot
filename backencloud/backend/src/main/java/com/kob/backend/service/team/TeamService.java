package com.kob.backend.service.team;

import java.util.Map;

public interface TeamService {
    Map<String, String> check(Map<String, String> data);
    Map<String, String> createNewTeam(Map<String, String> data);
}

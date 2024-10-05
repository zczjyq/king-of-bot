package com.kob.backend.service.team;

import java.util.Map;

public interface TeamJoinService {
    Integer check(Map<String, String> data);
    Map<String, String> join(Map<String, String> data);
}

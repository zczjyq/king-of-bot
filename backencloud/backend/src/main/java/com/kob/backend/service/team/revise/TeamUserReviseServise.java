package com.kob.backend.service.team.revise;

import java.util.Map;

public interface TeamUserReviseServise {
    Map<String, String> reviseUserRole(Map<String, String> data);

    Map<String, String> removeUser(Map<String, String> data);
}

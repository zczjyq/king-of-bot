package com.kob.backend.service.user.account;

import java.util.Map;

public interface RestartPasswordService {
    public Map<String, String> updatePassword(Map<String, String> data);
}

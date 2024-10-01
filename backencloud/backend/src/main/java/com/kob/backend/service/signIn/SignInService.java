package com.kob.backend.service.signIn;

import java.util.Map;

public interface SignInService {
    Map<String, String> init();
    Map<String, String> signIn(Map<String, String> data);
    Map<String, String> getSignInInfo(Map<String, String> data);
}

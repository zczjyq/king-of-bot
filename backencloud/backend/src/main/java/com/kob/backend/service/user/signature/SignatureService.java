package com.kob.backend.service.user.signature;

import java.util.Map;

public interface SignatureService {
    Map<String, String> getSignature(Map<String, String> data);
    Map<String, String> updateSignature(Map<String, String> data);
}

package com.kob.backend.service.user.info;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OtherUserInfoService {
    String getUsername(Integer id);
    String getPhoto(Integer id);
    Integer getRating(Integer id);
    Integer getFollows(Integer id);
    String getSignature(Integer id);
    Date getRegisterTime(Integer id);
    Integer getRaking(Integer id);
    String getTeams(Integer id);
    List<Map<Date, Integer>> getRatingList(Integer id);
}

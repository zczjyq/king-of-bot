package com.kob.backend.service.team;

import java.rmi.MarshalledObject;
import java.util.Map;

public interface TeamMemberService {
    Integer getTeamMemberStatus(Integer userId);
    // 增加一个战队的队员
    Map<String, String> addTeamMember(Map<String, String> data);
    // 删除一个战队的队员
    Map<String, String> deleteTeamMember(Map<String, String> data);
    // 查询一个战队的所有队员
    Map<String, String> queryTeamMember(Map<String, String> data);
    // 解散一个战队 删除所有队员
    Map<String, String> deleteAllTeamMember(Map<String, String> data);
    // 改变一个队员的状态
    Map<String, String> postTeamMemberStatus(Map<String, String> data);
}

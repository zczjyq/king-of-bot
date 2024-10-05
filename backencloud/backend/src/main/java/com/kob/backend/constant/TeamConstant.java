package com.kob.backend.constant;

import java.util.List;

public class TeamConstant {
    public static final Integer TEAM_STATUS_NORMAL = 1;
    public static final Integer TEAM_LEADER = 1;
    // 当前某个用户的状态
    public static final Integer TEAM_MEMBER_STATUS_NORMAL = 1;
    public static final Integer TEAM_MEMBER_HAS_TEAM = 1;
    public static final Integer TEAM_MEMBER_HAS_NO_TEAM = 0;
    public static final Integer TEAM_SOTRED_BY_NAME = 0;
    public static final Integer TEAM_SOTRED_BY_REGISTER = 1;
    public static final Integer TEAM_SOTRED_BY_RATING = 2;
    public static final Integer TEAM_START_RATING = 800;
    public static final Integer TEAM_JOIN_NO_TEAM = 0;
    public static final Integer TEAM_JOIN_HAS_TEAM = 1;
    public static final String TEAM_JOIN_HAS_TEAM_MEG = "您已经有战队";
    public static final Integer TEAM_JOIN_HAS_JOIN_THIS_TEAM = 2;
    public static final String TEAM_JOIN_HAS_JOIN_THIS_TEAM_MEG = "您已经加入了这个战队";
    public static final Integer TEAM_JOIN_BANNED_TEAM = 3;
    public static final String TEAM_JOIN_BANNED_TEAM_MEG = "您还不能加入战队";

}


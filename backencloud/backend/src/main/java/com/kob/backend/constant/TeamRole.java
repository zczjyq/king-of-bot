package com.kob.backend.constant;

public enum TeamRole {
    BLACKLIST(0, "黑名单"),
    LEADER(1, "队长"),
    VICE_LEADER(2, "副队长"),
    MEMBER(3, "成员");

    private final int code;
    private final String roleName;

    // 构造函数
    TeamRole(int code, String roleName) {
        this.code = code;
        this.roleName = roleName;
    }

    // 获取角色的数字值
    public int getCode() {
        return code;
    }

    // 获取角色名称
    public String getRoleName() {
        return roleName;
    }

    // 根据数字值获取对应的枚举
    public static TeamRole fromCode(int code) {
        for (TeamRole role : TeamRole.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role found with code: " + code);
    }
}


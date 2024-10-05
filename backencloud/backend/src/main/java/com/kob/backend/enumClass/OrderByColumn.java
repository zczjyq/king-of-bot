package com.kob.backend.enumClass;

import lombok.Getter;

/**
 * 枚举类：OrderByColumn
 * 用于定义数据库查询时的排序字段
 * <p>
 * 本枚举包含了允许用于排序的字段名，并提供了校验方法来验证排序字段是否合法
 * 枚举值包括：name, registertime, rating
 */
@Getter
public enum OrderByColumn {

    /**
     * 按名字排序
     */
    NAME("team_name"),

    /**
     * 按注册时间排序
     */
    REGISTERTIME("registertime"),

    /**
     * 按评分排序
     */
    RATING("rating");

    /**
     * 数据库对应的字段名称
     * -- GETTER --
     *  获取数据库字段名
     *
     * @return 数据库中的字段名

     */
    private final String column;

    /**
     * 构造函数：初始化枚举值
     *
     * @param column 数据库中的字段名
     */
    OrderByColumn(String column) {
        this.column = column;
    }

    /**
     * 校验排序字段是否有效
     *
     * @param column 排序字段名
     * @return 如果字段存在枚举中，返回true，否则返回false
     */
    public static boolean isValid(String column) {
        for (OrderByColumn orderBy : values()) {
            if (orderBy.getColumn().equalsIgnoreCase(column)) {
                return true;
            }
        }
        return false;
    }
}

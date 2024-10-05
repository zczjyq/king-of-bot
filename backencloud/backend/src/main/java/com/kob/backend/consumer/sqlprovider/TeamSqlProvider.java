package com.kob.backend.consumer.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class TeamSqlProvider {

    public String getTeamsWithOrder(@Param("orderByColumn") String orderByColumn, @Param("orderDirection") String orderDirection) {
        // 校验参数，防止 SQL 注入
        String validOrderByColumn = validateOrderByColumn(orderByColumn);
        String validOrderDirection = validateOrderDirection(orderDirection);

        // 动态构造 SQL 查询语句
        return new SQL() {{
            SELECT("*");
            FROM("team");
            ORDER_BY(validOrderByColumn + " " + validOrderDirection);
        }}.toString();
    }

    // 校验排序字段是否有效，防止SQL注入
    private String validateOrderByColumn(String orderByColumn) {
        switch (orderByColumn) {
            case "team_name":
            case "registertime":
            case "rating":
                return orderByColumn;
            default:
                throw new IllegalArgumentException("Invalid order by column: " + orderByColumn);
        }
    }

    // 校验排序方向是否有效，防止SQL注入
    private String validateOrderDirection(String orderDirection) {
        if ("ASC".equalsIgnoreCase(orderDirection) || "DESC".equalsIgnoreCase(orderDirection)) {
            return orderDirection;
        } else {
            throw new IllegalArgumentException("Invalid order direction: " + orderDirection);
        }
    }
}

package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.consumer.sqlprovider.TeamSqlProvider;
import com.kob.backend.pojo.Team;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {
    @Select("SELECT team_name FROM team")
    List<String> selectTeamNames();

    @SelectProvider(type = TeamSqlProvider.class, method = "getTeamsWithOrder")
    List<String> getTeamsWithOrder(@Param("orderByColumn") String orderByColumn, @Param("orderDirection") String orderDirection);
}

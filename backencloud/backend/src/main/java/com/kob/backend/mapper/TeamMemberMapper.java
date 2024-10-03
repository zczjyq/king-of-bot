package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.SignIn;
import com.kob.backend.pojo.TeamMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeamMemberMapper extends BaseMapper<TeamMember> {
    @Select("SELECT * FROM team_member WHERE user_id = #{userId}")
    TeamMember selectByUserId(Integer userId);
}

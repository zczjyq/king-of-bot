package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.SignIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SignInMapper extends BaseMapper<SignIn> {
    // 通过userId查询签到记录
    @Select("SELECT * FROM sign_in WHERE user_id = #{userId}")
    SignIn selectByUserId(String userId);
}

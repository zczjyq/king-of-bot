package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT rating FROM user")
    List<Integer> getAllRatings();

    @Select("SELECT * FROM user ORDER BY rating DESC LIMIT 10")
    List<User> getListLimit();

}

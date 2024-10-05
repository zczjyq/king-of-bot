package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @TableId(type = IdType.AUTO)
    private Long id; // 唯一ID
    private String teamName;
    private String teamDescription;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updatedAt;

    private Integer teamLeaderId;
    private Integer teamStatus;
    private String teamAchievements;
    private String teamSignature;
    private String teamHead;
    private Integer teamRating;

    // 存储 List<Integer> 转换为 JSON 字符串
    private String teamMember;  // 数据库存储 JSON 字符串

    private Integer ranking;
}

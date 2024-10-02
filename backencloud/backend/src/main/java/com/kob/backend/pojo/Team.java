package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @TableId(type = IdType.AUTO)
    private Long id; // 唯一ID
    private String teamName;
    private String teamDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date updatedAt;
    private Integer teamLeaderId;
    private Integer teamStatus;
    private String teamAchievements;
    private Integer teamSignature;
    private String teamHead;
}

package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {
    @TableId(type = IdType.AUTO)
    private Long id; // 唯一ID
    private Long userId; // 用户ID
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date signDate; // 签到日期
    private Boolean isSigned; // 是否已签到
    private Integer consecutiveDays; // 连续签到天数
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date lastSignInDate; // 上次签到日期
    private Integer cumulativeDays; // 累计签到天数
}
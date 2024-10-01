package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questionfeedback {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String description;
    private String mail;
    private String feedbacktype;
}

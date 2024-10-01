package com.kob.backend.controller.signIn;

import com.kob.backend.mapper.SignInMapper;
import com.kob.backend.pojo.SignIn;
import com.kob.backend.service.signIn.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@RestController
public class SignInController {
    @Autowired
    private SignInService signInService;
    @Autowired
    private SignInMapper signInMapper;

    @PostMapping("/api/user/signin/")
    public Map<String, String> postSignIn(@RequestParam Map<String, String> data) {
        SignIn signIn = signInMapper.selectByUserId(data.get("user_id"));
        Map<String, String> map = new HashMap<>();
        if (signIn == null) {
            // 调用初始化方法
            return signInService.init();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        // 获取签到信息
        Date signDate = signIn.getSignDate();           // 当前签到日期
        Date lastSignInDate = signIn.getLastSignInDate(); // 上次签到日期
        Integer consecutiveDays = signIn.getConsecutiveDays(); // 连续签到天数
        Boolean isSigned = signIn.getIsSigned();        // 今天是否已经签到

        // 格式化上次签到日期
        String lastSignInDay = lastSignInDate != null ? sdf.format(lastSignInDate) : "";

        // 如果已经签到
        if (today.equals(lastSignInDay) && isSigned) {
            map.put("message", "今天已经签到过了！");
            map.put("consecutiveDays", consecutiveDays.toString());
            map.put("status", "fail");
            return map;
        }// 获取当前年月日


        // 如果没有签到，则执行签到逻辑
        consecutiveDays = (lastSignInDate != null && isYesterday(lastSignInDate))
                ? consecutiveDays + 1 : 1; // 如果昨天签到，则连续天数加1，否则重置为1
        signIn.setConsecutiveDays(consecutiveDays);
        signIn.setSignDate(new Date()); // 设置今天的签到日期
        signIn.setIsSigned(true);       // 标记为已经签到

        // 更新数据库操作 (可以调用Mapper的更新方法)
        signInMapper.updateById(signIn);

        map.put("message", "签到成功！");
        map.put("consecutiveDays", String.valueOf(consecutiveDays));
        map.put("status", "success");
        return map;
    }

    // 判断上次签到日期是否是昨天
    private boolean isYesterday(Date lastSignInDate) {
        Date today = new Date();
        long difference = today.getTime() - lastSignInDate.getTime();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // 一天的毫秒数
        return difference >= oneDayInMillis && difference < 2 * oneDayInMillis;
    }
}

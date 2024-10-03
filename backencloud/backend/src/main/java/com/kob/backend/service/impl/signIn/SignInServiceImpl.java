package com.kob.backend.service.impl.signIn;

import com.kob.backend.mapper.SignInMapper;
import com.kob.backend.pojo.SignIn;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.signIn.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private SignInMapper signInMapper;
    @Override
    public Map<String, String> init() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        SignIn signIn = new SignIn(null, (long)user.getId(), null, false, 0, null, 0);
        signInMapper.insert(signIn);
        Map<String, String> map = new HashMap<>();
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }

    @Override
    public Map<String, String> signIn(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        SignIn signIn = signInMapper.selectByUserId(String.valueOf(user.getId()));
        Map<String, String> map = new HashMap<>();
        if (signIn == null) {
            // 调用初始化方法
            init();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        // 获取签到信息
        Date signDate = signIn.getSignDate();           // 当前签到日期
        Date lastSignInDate = signIn.getLastSignInDate(); // 上次签到日期
        Integer consecutiveDays = signIn.getConsecutiveDays(); // 连续签到天数
        Boolean isSigned = signIn.getIsSigned();        // 今天是否已经签到
        Integer cumulativeDays = signIn.getCumulativeDays();

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
        cumulativeDays += 1;

        signIn.setConsecutiveDays(consecutiveDays);
        signIn.setSignDate(new Date()); // 设置今天的签到日期
        signIn.setIsSigned(true);       // 标记为已经签到
        signIn.setLastSignInDate(new Date());
        signIn.setCumulativeDays(cumulativeDays);

        // 更新数据库操作 (可以调用Mapper的更新方法)
        signInMapper.updateById(signIn);

        map.put("message", "签到成功！");
        map.put("consecutiveDays", String.valueOf(consecutiveDays));
        map.put("cumulativeDays", String.valueOf(cumulativeDays));
        map.put("status", "success");
        return map;
    }

    // 获取签到信息
    @Override
    public Map<String, String> getSignInInfo(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        SignIn signIn = signInMapper.selectByUserId(String.valueOf(user.getId()));
        if (signIn == null) {
            init();
            signIn = signInMapper.selectByUserId(String.valueOf(user.getId()));
        }
        Map<String, String> map = new HashMap<>();
        map.put("consecutiveDays", String.valueOf(signIn.getConsecutiveDays()));
        map.put("cumulativeDays", String.valueOf(signIn.getCumulativeDays()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String today = sdf.format(new Date());
        Date lastSignInDate = signIn.getLastSignInDate(); // 上次签到日期
        // 格式化上次签到日期
        String lastSignInDay = lastSignInDate != null ? sdf.format(lastSignInDate) : "";
        if (today.equals(lastSignInDay) && signIn.getIsSigned()) {
            map.put("isSigned", String.valueOf(true));
        } else {
            map.put("isSigned", String.valueOf(false));
        }
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

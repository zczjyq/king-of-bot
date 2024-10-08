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
import java.util.Calendar;
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
        // 从当前上下文中获取认证信息
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        // 从认证信息中获取当前登录用户
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser(); // 获取用户信息
        // 根据用户ID查询用户的签到信息
        SignIn signIn = signInMapper.selectByUserId(String.valueOf(user.getId()));

        // 如果签到信息为空，初始化签到信息并重新查询
        if (signIn == null) {
            init();
            signIn = signInMapper.selectByUserId(String.valueOf(user.getId()));
        }

        // 初始化返回的map对象，用于存储签到信息
        Map<String, String> map = new HashMap<>();
        if (!(isYesterday(signIn.getLastSignInDate()) || isToday(signIn.getLastSignInDate()))) {
            signIn.setConsecutiveDays(0);
            signInMapper.updateById(signIn);
        }
        map.put("consecutiveDays", String.valueOf(signIn.getConsecutiveDays())); // 连续签到天数
        map.put("cumulativeDays", String.valueOf(signIn.getCumulativeDays())); // 累计签到天数

        // 格式化当前日期为 yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date()); // 获取今天的日期

        // 获取上次签到的日期
        Date lastSignInDate = signIn.getLastSignInDate();
        // 如果上次签到日期不为空，则将其格式化为 yyyy-MM-dd
        String lastSignInDay = lastSignInDate != null ? sdf.format(lastSignInDate) : "";

        // 检查用户今天是否已经签到
        if (today.equals(lastSignInDay) && signIn.getIsSigned()) {
            map.put("isSigned", String.valueOf(true)); // 如果今天已经签到，设置为true
        } else {
            map.put("isSigned", String.valueOf(false)); // 如果今天没有签到，设置为false
        }

        // 设置操作状态为成功
        map.put("status", "success");

        // 返回签到信息
        return map;
    }


    // 判断上次签到日期是否是昨天
    private boolean isYesterday(Date lastSignInDate) {
        Date today = new Date();
        long difference = today.getTime() - lastSignInDate.getTime();
        long oneDayInMillis = 24 * 60 * 60 * 1000; // 一天的毫秒数
        return difference >= oneDayInMillis && difference < 2 * oneDayInMillis;
    }

    // 判断上次签到日期是否是今天
    private boolean isToday(Date lastSignInDate) {
        // 获取当前日期的Calendar实例
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        // 获取签到日期的Calendar实例
        Calendar signInDay = Calendar.getInstance();
        signInDay.setTime(lastSignInDate);

        // 比较年、月、日是否相同
        return today.get(Calendar.YEAR) == signInDay.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == signInDay.get(Calendar.DAY_OF_YEAR);
    }

}

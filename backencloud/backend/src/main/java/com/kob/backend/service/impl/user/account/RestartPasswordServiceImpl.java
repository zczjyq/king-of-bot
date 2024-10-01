package com.kob.backend.service.impl.user.account;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.RestartPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class RestartPasswordServiceImpl implements RestartPasswordService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param data 懒得写
     * @return {@link Map }<{@link String }, {@link String }>
     */
    @Override
    public Map<String, String> updatePassword(Map<String, String> data) {
        // 定义返回数据
        Map<String, String> map = new HashMap<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        String SQLPassword = loginUser.getUser().getPassword();

        // 获取数据
        String oldPassword = data.get("oldPassword");
        String newPassword = data.get("newPassword");
        String confirmPassword = data.get("confirmPassword");

        // 检查是否为空
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            map.put(STATICRETURNMESSAGE, "请正确填写所有字段");
            return map; // 直接返回
        }

        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, SQLPassword)) {
            map.put(STATICRETURNMESSAGE, "旧密码输入错误");
            return map; // 直接返回
        }
        System.out.println(newPassword.length());
        if (newPassword.length() < 6) {
            map.put(STATICRETURNMESSAGE, "密码长度不能小于6位");
            return map;
        }

        if (newPassword.length() > 30) {
            map.put(STATICRETURNMESSAGE, "密码长度不能大于30");
            return map;
        }

        // 检查新密码和确认密码
        if (!newPassword.equals(confirmPassword)) {
            map.put(STATICRETURNMESSAGE, "新密码与确认密码不一致");
            return map; // 直接返回
        }

        // 更新密码
        User user = loginUser.getUser();
        user.setPassword(passwordEncoder.encode(newPassword)); // 直接加密新密码
        userMapper.updateById(user);
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }
}

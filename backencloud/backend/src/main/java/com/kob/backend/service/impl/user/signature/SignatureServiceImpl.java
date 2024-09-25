package com.kob.backend.service.impl.user.signature;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.signature.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.kob.backend.constant.UserConstant.SIGNATURE_IS_EMPTY;
import static com.kob.backend.constant.UserConstant.SIGNATURE_LENGTH_MAX;

/**
 * 签名相关操作服务实现类。
 * <p>负责获取和更新用户的签名信息。</p>
 *
 * @author zczjyq
 * {@code @date} 2024/09/25
 */
@Service
public class SignatureServiceImpl implements SignatureService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取当前用户的签名信息。
     * <p>从安全上下文中获取当前用户信息，并返回用户的签名。如果签名为空，则返回相应的提示信息。</p>
     *
     * @param data 参数Map，暂时未使用此参数
     * @return {@link Map}<{@link String}, {@link String}> 返回一个包含签名或错误信息的Map
     */
    @Override
    public Map<String, String> getSignature(Map<String, String> data) {
        // 从安全上下文中获取当前认证信息，并提取出登录用户的详情
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl)authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        // 获取用户的签名
        String signature = user.getSignature();
        Map<String, String> map = new HashMap<>();

        // 检查签名是否为空
        if (signature.isEmpty()) {
            map.put("error_message", SIGNATURE_IS_EMPTY);
            return map;
        } else {
            // 返回签名信息
            map.put("error_message", signature);
            return map;
        }
    }

    /**
     * 更新当前用户的签名信息。
     * <p>根据传入的数据更新用户的签名。如果签名为空、签名长度超过限制或签名与当前签名相同，则返回相应的错误提示信息。</p>
     *
     * @param data 包含签名信息的Map，key为"signature"表示签名
     * @return {@link Map}<{@link String}, {@link String}> 返回一个包含错误信息或成功提示的Map，若更新成功则返回"success"
     */
    @Override
    public Map<String, String> updateSignature(Map<String, String> data) {
        // 从安全上下文中获取当前认证信息，并提取出登录用户的详情
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        // 获取签名信息
        String signature = data.get("signature");
        Map<String, String> map = new HashMap<>();

        // 检查签名是否为空
        if (signature.isEmpty()) {
            map.put("error_message", "签名不能为空");
            return map;
        }

        // 检查签名长度是否超出限制
        if (signature.length() > SIGNATURE_LENGTH_MAX) {
            map.put("error_message", "签名不能大于" + SIGNATURE_LENGTH_MAX + "个字符");
            return map;
        }

        // 检查新签名是否与当前签名相同
        String presignature = userMapper.selectById(user.getId()).getSignature();
        if (signature.equals(presignature)) {
            map.put("error_message", "签名不能重复");
            return map;
        }

        // 更新数据库中的用户签名信息
        user.setSignature(signature);
        userMapper.updateById(user);

        // 返回更新成功的消息
        map.put("error_message", "success");
        return map;
    }
}

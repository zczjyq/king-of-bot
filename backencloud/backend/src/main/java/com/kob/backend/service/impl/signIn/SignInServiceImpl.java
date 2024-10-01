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
        SignIn signIn = new SignIn(null, (long)user.getId(), new Date(), true, 1, new Date());
        signInMapper.insert(signIn);
        Map<String, String> map = new HashMap<>();
        map.put(STATICRETURNMESSAGE, "success");
        return map;
    }

    @Override
    public Map<String, String> signIn(Map<String, String> data) {
        return null;
    }
}

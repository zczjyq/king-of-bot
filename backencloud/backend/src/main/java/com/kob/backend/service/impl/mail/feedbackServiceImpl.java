package com.kob.backend.service.impl.mail;

import com.kob.backend.mapper.QuestionFeedbackMapper;
import com.kob.backend.pojo.Questionfeedback;
import com.kob.backend.service.mail.feedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.kob.backend.constant.UtilMessage.QUESTIONFEEDBACK;
import static com.kob.backend.constant.UtilMessage.STATICRETURNMESSAGE;

@Service
public class feedbackServiceImpl implements feedbackService {
    // 定义一个正则表达式用于验证邮箱格式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false; // 处理 null 值
        }
        return EMAIL_PATTERN.matcher(email).matches(); // 返回匹配结果
    }

    private boolean sendMail(String contact) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        // 创建 MimeMessageHelper
        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        // 发件人邮箱和名称
        helper.setFrom("937473303@qq.com", "springdoc");
        // 收件人邮箱
        helper.setTo(contact);
        // 邮件标题
        helper.setSubject("问题发送成功");
        // 邮件正文，第二个参数表示是否是HTML正文
        helper.setText(QUESTIONFEEDBACK, true);
        // 发送
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private QuestionFeedbackMapper questionFeedbackMapper;

    @Override
    public Map<String, String> getFeedback(Map<String, String> data) throws MessagingException, UnsupportedEncodingException {

        Map<String, String> map = new HashMap<>();
        System.out.println("开始发送邮件");
        String username = data.get("username");
        String feedbackType = data.get("feedbackType");
        String description = data.get("description");
        String contact = data.get("contact");
        if (!isValidEmail(contact)) {
            map.put(STATICRETURNMESSAGE, "邮箱发送失败，请检查邮箱是否填写正确。");
            return map;
        }

        // 发送邮件
        if (sendMail(contact)) {
            System.out.println("发送邮件成功");
            map.put(STATICRETURNMESSAGE, "success");
            Questionfeedback questionfeedback = new Questionfeedback(null, username, description, contact, feedbackType);
            questionFeedbackMapper.insert(questionfeedback);
            return map;
        } else  {
            map.put(STATICRETURNMESSAGE, "error");
            return map;
        }
    }
}

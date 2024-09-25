package com.kob.backend.controller.user.signature;

import com.kob.backend.service.user.signature.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 签名操作的控制器。
 * <p>处理与签名相关的请求，如获取签名和更新签名。</p>
 *
 * @author zczjyq
 * {@code @date} 2024/09/25
 */
@RestController
public class SignatureController {

    @Autowired
    private SignatureService signatureService;

    /**
     * 获取当前用户的签名信息。
     * <p>通过调用服务层获取签名，并返回签名或相应的错误信息。</p>
     *
     * @param data 包含请求参数的Map，key为"signature"
     * @return {@link Map}<{@link String}, {@link String}> 返回包含签名或错误信息的Map
     */
    @GetMapping("/api/user/signature/get/")
    public Map<String, String> getSignature(@RequestParam Map<String, String> data) {
        return signatureService.getSignature(data);
    }

    /**
     * 更新当前用户的签名信息。
     * <p>通过调用服务层更新签名，若签名为空或长度超限，将返回对应的错误提示；否则返回更新成功的消息。</p>
     *
     * @param data 包含请求参数的Map，key为"signature"表示新签名
     * @return {@link Map}<{@link String}, {@link String}> 返回包含更新结果的Map
     */
    @PostMapping("/api/user/signature/update/")
    public Map<String, String> updateSignature(@RequestParam Map<String, String> data) {
        return signatureService.updateSignature(data);
    }
}

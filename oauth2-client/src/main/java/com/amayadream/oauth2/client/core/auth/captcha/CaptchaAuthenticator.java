package com.amayadream.oauth2.client.core.auth.captcha;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import com.amayadream.oauth2.client.core.auth.Authenticator;
import com.amayadream.oauth2.client.core.domain.model.User;
import com.amayadream.oauth2.client.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 短信验证码/邮箱验证码认证
 * @author : Amayadream
 * @date :   2018-09-18 15:40
 */
@Slf4j
@Component
public class CaptchaAuthenticator implements Authenticator {

    @Resource
    private UserService userService;

    @Override
    public User authenticate(AuthRequest authRequest) {
        Assert.isTrue(authRequest instanceof CaptchaAuthRequest,
                String.format("认证策略选取异常, 目标策略 %s, 当前策略 %s", authRequest.type(), "Basic"));
        CaptchaAuthRequest request = (CaptchaAuthRequest) authRequest;

        User user = null;
        switch (request.getCaptchaType()) {
            case EMAIL:
                user = userService.findByEmail(request.getLoginName());
                break;
            case PHONE:
                user = userService.findByPhone(request.getLoginName());
                break;
        }

        if (user == null) {
            log.error("用户 {} / {} 不存在", request.getLoginName(), request.getCaptchaType());
            return null;
        }

        //FIXME 略去验证码逻辑
        boolean checkStatus = "123456".equals(request.getCaptcha());
        if (!checkStatus) {
            log.error("用户 {} / {} 认证不通过", request.getLoginName(), request.getCaptchaType());
            return null;
        }

        return user;
    }

}

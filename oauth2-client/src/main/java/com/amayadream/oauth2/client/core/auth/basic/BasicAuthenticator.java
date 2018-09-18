package com.amayadream.oauth2.client.core.auth.basic;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import com.amayadream.oauth2.client.core.auth.Authenticator;
import com.amayadream.oauth2.client.core.domain.model.User;
import com.amayadream.oauth2.client.core.service.UserService;
import com.amayadream.oauth2.client.core.util.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 密码认证
 * @author : Amayadream
 * @date :   2018-09-18 15:39
 */
@Slf4j
@Component
public class BasicAuthenticator implements Authenticator {

    @Resource
    private UserService userService;

    @Override
    public User authenticate(AuthRequest authRequest) {
        Assert.isTrue(authRequest instanceof BasicAuthRequest,
                String.format("认证策略选取异常, 目标策略 %s, 当前策略 %s", authRequest.type(), "Basic"));
        BasicAuthRequest request = (BasicAuthRequest) authRequest;

        User user = null;
        switch (request.getBasicType()) {
            case EMAIL:
                user = userService.findByEmail(request.getLoginName());
                break;
            case PHONE:
                user = userService.findByPhone(request.getLoginName());
                break;
        }

        if (user == null) {
            log.error("用户 {} / {} 不存在", request.getLoginName(), request.getBasicType());
            return null;
        }

        boolean checkStatus = PasswordUtils.check(request.getPassword(), user.getPassword(), user.getSalt());
        if (!checkStatus) {
            log.error("用户 {} / {} 认证不通过", request.getLoginName(), request.getBasicType());
            return null;
        }

        return user;
    }

}

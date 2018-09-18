package com.amayadream.oauth2.client.web.controller;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import com.amayadream.oauth2.client.core.auth.Authenticator;
import com.amayadream.oauth2.client.core.auth.basic.BasicAuthRequest;
import com.amayadream.oauth2.client.core.auth.captcha.CaptchaAuthRequest;
import com.amayadream.oauth2.client.core.domain.model.User;
import com.amayadream.oauth2.client.core.util.RegexUtils;
import com.amayadream.oauth2.client.core.util.result.ResultConstant;
import com.amayadream.oauth2.client.core.util.result.ResultData;
import com.amayadream.oauth2.client.core.util.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : Amayadream
 * @date :   2018-09-18 16:59
 */
@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Resource
    private Authenticator basicAuthenticator;
    @Resource
    private Authenticator captchaAuthenticator;

    @RequestMapping(value = "/basic_login", method = RequestMethod.GET)
    public Results basicLogin(String loginName, String password) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
            return Results.nok(ResultConstant.PARAMS_ERROR);
        }
        BasicAuthRequest.BasicType basicType = null;
        if (RegexUtils.isValidEmail(loginName)) {
            basicType = BasicAuthRequest.BasicType.EMAIL;
        }
        if (RegexUtils.isValidPhone(loginName)) {
            basicType = BasicAuthRequest.BasicType.PHONE;
        }
        if (basicType == null) {
            return Results.nok(ResultConstant.PARAMS_ERROR, "登录名称不匹配邮箱和手机号");
        }

        AuthRequest authRequest = BasicAuthRequest.builder()
                .loginName(loginName)
                .basicType(basicType)
                .password(password)
                .build();
        User user = basicAuthenticator.authenticate(authRequest);
        if (user == null) {
            return Results.nok(ResultConstant.REQUEST_ERROR, "登录失败, 账户不存在或密码错误");
        }

        ResultData data = ResultData.builder()
                .append("user", user)
                .build();
        return Results.ok(data);
    }

    @RequestMapping(value = "/captcha_login", method = RequestMethod.GET)
    public Results captchaLogin(String loginName, String captcha) {
        if (StringUtils.isBlank(loginName) || StringUtils.isBlank(captcha)) {
            return Results.nok(ResultConstant.PARAMS_ERROR);
        }
        CaptchaAuthRequest.CaptchaType captchaType = null;
        if (RegexUtils.isValidEmail(loginName)) {
            captchaType = CaptchaAuthRequest.CaptchaType.EMAIL;
        }
        if (RegexUtils.isValidPhone(loginName)) {
            captchaType = CaptchaAuthRequest.CaptchaType.PHONE;
        }
        if (captchaType == null) {
            return Results.nok(ResultConstant.PARAMS_ERROR, "登录名称不匹配邮箱和手机号");
        }

        AuthRequest authRequest = CaptchaAuthRequest.builder()
                .loginName(loginName)
                .captchaType(captchaType)
                .captcha(captcha)
                .build();
        User user = captchaAuthenticator.authenticate(authRequest);
        if (user == null) {
            return Results.nok(ResultConstant.REQUEST_ERROR, "登录失败, 账户不存在或验证码错误");
        }

        ResultData data = ResultData.builder()
                .append("user", user)
                .build();
        return Results.ok(data);
    }

}

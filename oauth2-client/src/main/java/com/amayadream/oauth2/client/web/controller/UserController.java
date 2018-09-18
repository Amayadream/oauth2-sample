package com.amayadream.oauth2.client.web.controller;

import com.amayadream.oauth2.client.core.domain.model.User;
import com.amayadream.oauth2.client.core.service.UserService;
import com.amayadream.oauth2.client.core.util.RegexUtils;
import com.amayadream.oauth2.client.core.util.result.ResultConstant;
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
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/create_by_email", method = RequestMethod.GET)
    public Results createByEmail(String email, String password) {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
            return Results.nok(ResultConstant.PARAMS_ERROR);
        }
        if (!RegexUtils.isValidEmail(email)) {
            return Results.nok(ResultConstant.PARAMS_ERROR, String.format("邮箱 %s 格式有误", email));
        }

        User user = userService.createByEmail(email, password);
        if (user == null) {
            return Results.nok(ResultConstant.REQUEST_ERROR, String.format("创建用户失败, 邮箱 %s 可能已被注册", email));
        }

        return Results.ok();
    }

    @RequestMapping(value = "/create_by_phone", method = RequestMethod.GET)
    public Results createByPhone(String phone, String password) {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
            return Results.nok(ResultConstant.PARAMS_ERROR);
        }
        if (!RegexUtils.isValidPhone(phone)) {
            return Results.nok(ResultConstant.PARAMS_ERROR, String.format("手机 %s 格式有误", phone));
        }

        User user = userService.createByPhone(phone, password);
        if (user == null) {
            return Results.nok(ResultConstant.REQUEST_ERROR, String.format("创建用户失败, 手机 %s 可能已被注册", phone));
        }

        return Results.ok();
    }


}

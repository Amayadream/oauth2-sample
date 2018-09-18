package com.amayadream.oauth2.client.web.controller;

import com.amayadream.oauth2.client.core.auth.Authenticator;
import com.amayadream.oauth2.client.core.auth.thirdparty.AuthType;
import com.amayadream.oauth2.client.core.auth.thirdparty.github.GithubAuthRequest;
import com.amayadream.oauth2.client.core.domain.model.User;
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
 * @date :   2018-09-18 18:05
 */
@Slf4j
@RestController
@RequestMapping(value = "/oauth")
public class OAuthController {

    @Resource
    private Authenticator githubAuthenticator;

    @RequestMapping(value = "/github/callback", method = RequestMethod.GET)
    public Results callback(String code, String state) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(state)) {
            return Results.nok(ResultConstant.PARAMS_ERROR);
        }

        GithubAuthRequest authRequest = GithubAuthRequest.builder()
                .authType(AuthType.GITHUB)
                .code(code)
                .state(state)
                .build();
        User user = githubAuthenticator.authenticate(authRequest);
        if (user == null) {
            return Results.nok(ResultConstant.REQUEST_ERROR, "Github登录失败");
        }

        ResultData data = ResultData.builder()
                .append("user", user)
                .build();
        return Results.ok(data);
    }


}

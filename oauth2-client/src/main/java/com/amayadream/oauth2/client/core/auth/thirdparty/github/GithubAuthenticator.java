package com.amayadream.oauth2.client.core.auth.thirdparty.github;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import com.amayadream.oauth2.client.core.auth.Authenticator;
import com.amayadream.oauth2.client.core.domain.model.User;
import com.amayadream.oauth2.client.web.controller.vo.GithubAccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author : Amayadream
 * @date :   2018-09-18 18:55
 */
@Slf4j
@Component
public class GithubAuthenticator implements Authenticator {

    public static final String LOGIN_URL = "https://github.com/login/oauth/authorize?client_id=93ebe2ced7e5c02fb8e9&state=ok";

    @Resource
    private RestTemplate restTemplate;

    @Override
    public User authenticate(AuthRequest authRequest) {
        Assert.isTrue(authRequest instanceof GithubAuthRequest,
                String.format("认证策略选取异常, 目标策略 %s, 当前策略 %s", authRequest.type(), "Basic"));
        GithubAuthRequest request = (GithubAuthRequest) authRequest;

        //1.获取Github令牌
        String url = "https://github.com/login/oauth/access_token";

        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("client_id", "93ebe2ced7e5c02fb8e9");
        paramMap.add("client_secret", "input your client secret");
        paramMap.add("code", request.getCode());
        paramMap.add("redirect_uri", "http://127.0.0.1:8888/oauth/github/callback");
        paramMap.add("state", request.getState());

        GithubAccessTokenResponse response = restTemplate.postForObject(url, paramMap, GithubAccessTokenResponse.class);
        if (response == null || StringUtils.isBlank(response.getAccess_token())) {
            return null;
        }

        //TODO 2.判定是否绑定过第三方账号



        return null;
    }


}

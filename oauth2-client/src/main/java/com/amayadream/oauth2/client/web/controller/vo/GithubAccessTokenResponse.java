package com.amayadream.oauth2.client.web.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Amayadream
 * @date :   2018-09-18 18:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubAccessTokenResponse implements Serializable {

    private String access_token;
    private String token_type;
    private String scope;

}

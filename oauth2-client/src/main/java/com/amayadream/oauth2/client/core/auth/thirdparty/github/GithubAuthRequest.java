package com.amayadream.oauth2.client.core.auth.thirdparty.github;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import com.amayadream.oauth2.client.core.auth.thirdparty.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubAuthRequest implements AuthRequest {

    private AuthType authType;
    private String code;
    private String state;

    @Override
    public String type() {
        return "Github";
    }

}

package com.amayadream.oauth2.client.core.auth;

import com.amayadream.oauth2.client.core.domain.model.User;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:37
 */
public interface Authenticator {

    User authenticate(AuthRequest authRequest);

}

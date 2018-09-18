package com.amayadream.oauth2.client.core.service;

import com.amayadream.oauth2.client.core.domain.model.User;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:22
 */
public interface UserService {

    User findByUserId(String userId);

    User findByEmail(String email);

    User findByPhone(String phone);

    User createByEmail(String email, String password);

    User createByPhone(String phone, String password);

    User createByThirdParty();

}

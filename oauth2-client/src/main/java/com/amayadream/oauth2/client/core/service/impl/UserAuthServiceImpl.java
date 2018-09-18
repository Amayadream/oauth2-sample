package com.amayadream.oauth2.client.core.service.impl;

import com.amayadream.oauth2.client.core.domain.repository.UserAuthRepository;
import com.amayadream.oauth2.client.core.service.UserAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:23
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Resource
    private UserAuthRepository userAuthRepository;



}

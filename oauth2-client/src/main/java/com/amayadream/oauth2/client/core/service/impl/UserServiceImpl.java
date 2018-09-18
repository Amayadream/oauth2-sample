package com.amayadream.oauth2.client.core.service.impl;

import com.amayadream.oauth2.client.core.domain.model.User;
import com.amayadream.oauth2.client.core.domain.repository.UserRepository;
import com.amayadream.oauth2.client.core.service.UserService;
import com.amayadream.oauth2.client.core.util.PasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User createByEmail(String email, String password) {
        if (this.findByEmail(email) != null) {
            return null;
        }
        String salt = PasswordUtils.getSalt();
        String hashPassword = PasswordUtils.hash(password, salt);

        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userId(PasswordUtils.getSalt())
                .salt(salt)
                .password(hashPassword)
                .email(email)
                .createdTime(now)
                .updatedTime(now)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User createByPhone(String phone, String password) {
        if (this.findByPhone(phone) != null) {
            return null;
        }
        String salt = PasswordUtils.getSalt();
        String hashPassword = PasswordUtils.hash(password, salt);

        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userId(PasswordUtils.getSalt())
                .salt(salt)
                .password(hashPassword)
                .phone(phone)
                .createdTime(now)
                .updatedTime(now)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User createByThirdParty() {
        LocalDateTime now = LocalDateTime.now();
        User user = User.builder()
                .userId(PasswordUtils.getSalt())
                .createdTime(now)
                .updatedTime(now)
                .build();
        return userRepository.save(user);
    }

}

package com.amayadream.oauth2.client.core.domain.repository;

import com.amayadream.oauth2.client.core.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:21
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);

    User findByEmail(String email);

    User findByPhone(String phone);

}

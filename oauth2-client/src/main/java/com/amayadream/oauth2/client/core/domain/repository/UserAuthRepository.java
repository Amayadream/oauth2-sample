package com.amayadream.oauth2.client.core.domain.repository;

import com.amayadream.oauth2.client.core.domain.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:21
 */
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {



}

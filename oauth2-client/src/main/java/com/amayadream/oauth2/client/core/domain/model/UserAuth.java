package com.amayadream.oauth2.client.core.domain.model;

import com.amayadream.oauth2.client.core.auth.thirdparty.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Amayadream
 * @date :   2018-09-18 14:59
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;              //用户唯一ID
    private String accessToken;         //业务访问令牌, 这个令牌交付客户端保存, 防止auth_token被窃取导致的失控
    @Enumerated(value = EnumType.STRING)
    private AuthType authType;          //第三方认证类型
    private String authId;              //第三方用户ID
    private String authToken;           //认证令牌
    private Long authExpire;            //认证失效时间

}

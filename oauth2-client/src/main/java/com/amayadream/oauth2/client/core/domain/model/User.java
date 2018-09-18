package com.amayadream.oauth2.client.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : Amayadream
 * @date :   2018-09-18 14:48
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                        //主键ID
    private String userId;                  //用户唯一ID
    private String nickname;                //昵称
    private String salt;                    //盐
    private String password;                //密码
    private String avatar;                  //头像
    private String email;                   //邮箱
    private String phone;                   //手机
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

}

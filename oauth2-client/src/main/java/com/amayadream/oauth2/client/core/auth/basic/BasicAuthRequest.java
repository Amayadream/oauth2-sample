package com.amayadream.oauth2.client.core.auth.basic;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:47
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicAuthRequest implements AuthRequest {

    private BasicType basicType;
    private String loginName;
    private String password;

    @Override
    public String type() {
        return "Basic";
    }

    public enum BasicType {
        EMAIL,
        PHONE
    }

}

package com.amayadream.oauth2.client.core.auth.captcha;

import com.amayadream.oauth2.client.core.auth.AuthRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Amayadream
 * @date :   2018-09-18 15:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaAuthRequest implements AuthRequest {

    private CaptchaType captchaType;
    private String loginName;
    private String captcha;

    @Override
    public String type() {
        return "Captcha";
    }

    public enum CaptchaType {
        EMAIL,
        PHONE
    }

}

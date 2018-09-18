package com.amayadream.oauth2.client.core.util;

import java.util.regex.Pattern;

/**
 * @author : Amayadream
 * @date :   2018-09-18 17:31
 */
public class RegexUtils {

    public static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String PHONE_REGEX = "^((13[0-9])|(15[^4,\\D])|(17[6-7])|(18[0,5-9]))\\d{8}$";

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

}

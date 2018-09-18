package com.amayadream.oauth2.client.core.util;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.buf.HexUtils;

import java.util.Objects;
import java.util.UUID;

/**
 * @author : Amayadream
 * @date :   2018-09-18 16:25
 */
public class PasswordUtils {

    public static String getSalt() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String hash(String originalPassword, String salt) {
        byte[] bytes = DigestUtils.digest(DigestUtils.getSha256Digest(), (originalPassword + salt).getBytes(Charsets.UTF_8));
        return HexUtils.toHexString(bytes);
    }

    public static boolean check(String originalPassword, String hashPassword, String salt) {
        String calHashPassword = hash(originalPassword, salt);
        return Objects.equals(calHashPassword, hashPassword);
    }

    public static void main(String[] args) {
        String salt = getSalt();
        String originalPassword = "123456";
        String hashPassword = hash(originalPassword, salt);
        boolean checkStatus = check(originalPassword, hashPassword, salt);
        System.out.println("OriginalPassword: " + originalPassword);
        System.out.println("HashPassword: " + hashPassword);
        System.out.println("Check: " + checkStatus);
    }

}

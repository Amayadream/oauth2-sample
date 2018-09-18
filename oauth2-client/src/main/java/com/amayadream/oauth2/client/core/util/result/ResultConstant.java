package com.amayadream.oauth2.client.core.util.result;

/**
 * 通用返回枚举
 * @author : Amayadream
 * @date :   2017-12-01 11:07
 */
public enum ResultConstant {

    SUCCESS(0, "ok"),

    //通用错误
    PARAMS_ERROR(1, "请求参数不完整或格式有误"),
    ENTITY_NOT_FOUND(2, "请求数据不存在"),
    REQUEST_ERROR(3, "请求过程中发生错误"),

    ;

    public final long code;
    public final String message;

    ResultConstant(long code, String message) {
        this.code = code;
        this.message = message;
    }

}

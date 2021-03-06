package com.questionnaire.ssm.module.global.enums;

/**
 * Created by 郑晓辉 on 2017/3/30.
 * Description: 代码信息枚举,系统代码信息统一管理
 */
public enum CodeForVOEnum {
    /*全局信息编码*/
    RESOURCE_NOT_FOUND(404, "找不到资源!"),
    UNKNOWN_ERROR(500, "系统未知错误!"),
    REQUEST_SUCCESS(200, "请求成功!"),
    REQUEST_ERROR(400, "请求失败!"),

    /*操作数据库信息编码*/
    DB_SELECT_NO_MESSAGE(-5, "没有查询到您想要的信息"),
    DB_DELETE_FAIL(-4, "删除数据失败!"),
    DB_SELECT_FAIL(-3, "查询数据失败!"),
    DB_INSERT_FAIL(-2, "插入数据失败!"),
    DB_UPDATE_FAIL(-1, "更新数据失败!"),
    DB_UPDATE_SUCCESS(1, "更新数据成功!"),
    DB_INSERT_SUCCESS(2, "插入数据成功!"),
    DB_SELECT_SUCCESS(3, "查询数据成功!"),
    DB_DELETE_SUCCESS(4, "删除数据成功!"),


    /*用户信息校验编码*/
    VALIDA_FAIL(9, "校验失败"),
    VALIDA_OK(10, "校验成功!"),
    NOT_LOGIN(11, "用户未登录!"),
    NO_ROLE(12, "用户不具备角色!"),
    NO_PERMISSION(13, "用户无相应权限!"),
    OLD_PASSWORD_ERROR(14, "旧密码错误!"),

    /*参数校验信息编码*/
    VALID_FAIL_CREATE_QUESTIONNAIRE(-1000, "创建问卷视图数据校验失败!"),
    QUESTIONNAIRE_IDS_NULL(-1010, "未选择要操作的问卷!"),
    TEMPLATE_IDS_NULL(-1020, "未选择要操作的模板"),;

    /*错误代码*/
    private int code;
    /*错误代码代表信息*/
    private String message;

    CodeForVOEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

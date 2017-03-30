package com.questionnaire.ssm.module.global.exception;

import com.questionnaire.ssm.module.global.enums.CodeEnum;

/**
 * Created by 郑晓辉 on 2017/3/25.
 * Description: 用户未登录、不具备角色、不具有权限 前后密码不同异常
 */
public class UserValidaException extends RuntimeException {
    /*错误代码*/
    private int code;

    public UserValidaException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

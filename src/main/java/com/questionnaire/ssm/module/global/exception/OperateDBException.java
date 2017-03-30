package com.questionnaire.ssm.module.global.exception;

import com.questionnaire.ssm.module.global.enums.CodeEnum;

/**
 * Created by 郑晓辉 on 2017/3/22.
 * Description:插入数据库异常
 */
public class OperateDBException extends RuntimeException {
    /*错误编号*/
    private int code;
    /*错误表格*/
    private String tableNme;

    public OperateDBException(CodeEnum codeEnum, String tableNme) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.tableNme = tableNme;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTableNme() {
        return tableNme;
    }

    public void setTableNme(String tableNme) {
        this.tableNme = tableNme;
    }
}

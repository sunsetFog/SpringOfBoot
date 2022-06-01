package com.core.common.util;
/*
    http状态码
    枚举类更加直观，类型安全

    ResponseStatus.ERROR.getCode()
*/
public enum ResponseStatus {

    SUCCESS("200", "请求成功"),
    ERROR("1111", "请求失败"),
    SYSTEM_ERROR("1000", "系统异常"),
    BUSSINESS_ERROR("2001", "业务逻辑错误"),
    VERIFY_CODE_ERROR("2002", "业务参数错误"),
    PARAM_ERROR("2002", "业务参数错误");

    private String code;
    private String msg;

    ResponseStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

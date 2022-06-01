package com.core.common.util;
/*
    二、方法重载：接口成功与失败回调
    
    泛型方法：public static <T>
 */
public class ResponseDataUtil {
    // type1
    public static <T> ResponseData buildSuccess(String code, String msg, T data) {
        return new ResponseData<T>(code, msg, data);// 实例化，触发构造器
    }
    
    // type1
    public static <T> ResponseData buildSuccess(T data) {

        return new ResponseData<T>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMsg(), data);
    }

    // type2
    public static ResponseData buildSuccess() {
        return new ResponseData(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMsg());
    }

    // type2
    public static ResponseData buildSuccess(String code, String msg) {
        return new ResponseData(code, msg);
    }

    // type2
    public static ResponseData buildSuccess(ResponseStatus responseStatus) {
        return new ResponseData(responseStatus.getCode(), responseStatus.getMsg());
    }



    // type1
    public static <T> ResponseData buildError(String code, String msg, T data) {
        return new ResponseData<T>(code, msg, data);
    }

    // type1
    public static <T> ResponseData buildError(T data) {
        return new ResponseData<T>(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg(), data);
    }

    // type2
    public static ResponseData buildError() {
        return new ResponseData(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMsg());
    }

    // type2
    public static ResponseData buildError(String code, String msg) {
        return new ResponseData(code, msg);
    }

    // type2
    public static ResponseData buildError(ResponseStatus responseStatus) {
        return new ResponseData(responseStatus.getCode(), responseStatus.getMsg());
    }
}

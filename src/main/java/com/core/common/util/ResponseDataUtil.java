package com.core.common.util;
/*
    二、方法重载：接口成功与失败回调
    
    泛型方法：public static <T>
 */
public class ResponseDataUtil {
    // type1
    public static <T> ResponseData buildSuccess(String code, String message, T data) {
        return new ResponseData<T>(code, message, data);// 实例化，触发构造器
    }
    
    // type1
    public static <T> ResponseData buildSuccess(T data) {

        return new ResponseData<T>(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), data);
    }

    // type2
    public static ResponseData buildSuccess() {
        return new ResponseData(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage());
    }

    // type2
    public static ResponseData buildSuccess(String code, String message) {
        return new ResponseData(code, message);
    }

    // type2
    public static ResponseData buildSuccess(ResponseStatus responseStatus) {
        return new ResponseData(responseStatus.getCode(), responseStatus.getMessage());
    }



    // type1
    public static <T> ResponseData buildError(String code, String message, T data) {
        return new ResponseData<T>(code, message, data);
    }

    // type1
    public static <T> ResponseData buildError(T data) {
        return new ResponseData<T>(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMessage(), data);
    }

    // type2
    public static ResponseData buildError() {
        return new ResponseData(ResponseStatus.ERROR.getCode(), ResponseStatus.ERROR.getMessage());
    }

    // type2
    public static ResponseData buildError(String code, String message) {
        return new ResponseData(code, message);
    }

    // type2
    public static ResponseData buildError(ResponseStatus responseStatus) {
        return new ResponseData(responseStatus.getCode(), responseStatus.getMessage());
    }
}

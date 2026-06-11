package com.heima.ymhaicodermother.exception;

public class ThrowUtils {

    /**
     * 条件成立则抛出异常
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException){
        if(condition){
            throw runtimeException;
        }
    }

    public static void throwIf(boolean condition, ErrorCode errorCode){
        if(condition){
            throw new BusinessException(errorCode);
        }
    }

    public static void throwIf(boolean condition, ErrorCode errorCode, String msg) {
        throwIf(condition,new BusinessException(errorCode,msg));
    }
}

package com.youthchina.exception.zhongyang.error;

/**
 * Created by zhongyangwu on 4/16/19.
 */
public class BaseError extends RuntimeException {
    String message;
    Class causedBy;

    public BaseError(String message, Class causedBy) {
        this.message = message;
        this.causedBy = causedBy;
    }

}

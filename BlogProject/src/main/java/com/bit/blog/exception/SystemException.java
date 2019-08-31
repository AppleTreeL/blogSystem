package com.bit.blog.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname SystemException
 * @Description
 * @Date 2019/8/29 10:59
 * @Created by AppleTree
 */

@Getter
@Setter
public class SystemException extends RuntimeException{

    private String code;

    public SystemException(String message) {
        super("系统异常"+ message);
        this.code = "501";
    }

    public SystemException(String message, Throwable cause) {
        super("系统异常"+ message, cause);
        this.code = "501";
    }
}

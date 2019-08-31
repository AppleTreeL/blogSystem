package com.bit.blog.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Classname BusinessException
 * @Description
 * @Date 2019/8/29 15:07
 * @Created by AppleTree
 */

@Setter
@Getter
public class BusinessException extends RuntimeException{
    private String code;

    public BusinessException(String message) {
        super("业务异常"+ message);
        this.code = "401";
    }

    public BusinessException(String message, Throwable cause) {
        super("业务异常"+message, cause);
        this.code = "401";
    }
}

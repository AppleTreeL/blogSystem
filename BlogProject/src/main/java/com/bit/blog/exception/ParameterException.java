package com.bit.blog.exception;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname ParameterException
 * @Description
 * @Date 2019/8/29 10:20
 * @Created by AppleTree
 */

@Getter
@Setter
public class ParameterException extends RuntimeException{

    private String code;

    public ParameterException(String message) {
        super(message);
        this.code = "400";
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
        this.code = "400";
    }
}

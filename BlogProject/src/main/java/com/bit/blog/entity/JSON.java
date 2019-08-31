package com.bit.blog.entity;

/**
 * @Classname JSON
 * @Description TODO
 * @Date 2019/8/23 17:27
 * @Created by AppleTree
 */
public class JSON {

    private boolean success;//whether operation successfully
    private String code; //erro code
    private String message;//erro message
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

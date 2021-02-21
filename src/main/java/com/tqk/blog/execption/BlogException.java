package com.tqk.blog.execption;

import com.tqk.blog.enums.ResultEnum;

/**
 * 自定义异常
 * @author tqk
 * @date
 */
public class BlogException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private Integer errorCode = ResultEnum.ERROR.getCode();
    private String  errorMsg;
    /**
     *
     * @param resultEnum
     */
    public BlogException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
        this.errorMsg=resultEnum.getMsg();
    }
    /**
     *
     * @param resultEnum
     */
    public BlogException(ResultEnum resultEnum, String msg) {
        super(msg);
        this.errorCode = resultEnum.getCode();
        this.errorMsg=msg;
    }
    public BlogException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public BlogException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BlogException(String msg) {
        super(msg);
    }

    public BlogException(Throwable throwable) {
        super(throwable);
    }

    public BlogException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}

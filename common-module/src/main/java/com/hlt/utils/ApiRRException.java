package com.hlt.utils;

/**
 * @Author: houlintao
 * @Date:2020/7/7 下午2:55
 * @email 437547058@qq.com
 * @Version 1.0
 * 自定义api模块内的运行时异常类
 */
public class ApiRRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errmsg;
    private int errno = 500;

    public ApiRRException(String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
    }

    public ApiRRException(String errmsg, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
    }

    public ApiRRException(String errmsg, int errno) {
        super(errmsg);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public ApiRRException(String errmsg, int errno, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}

package com.wan.synthesize.common;

import com.wan.synthesize.baseenum.ReturenCodeEnum;

/**
 * Created by wzx on 2016/7/28.
 */
public class ReturnResult {
    /**
     * 是否成功
     */
    private boolean isSuccess = true;
    /**
     * 返回信息
     */
    private String message = ReturenCodeEnum.SUCCESS.getDesc();
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 返回码
     */
    private String code = ReturenCodeEnum.SUCCESS.getCode();

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

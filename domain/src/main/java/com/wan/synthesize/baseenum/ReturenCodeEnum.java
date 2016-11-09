package com.wan.synthesize.baseenum;

/**
 * Created by zhixiang.wan on 2016/10/24.
 *  返回码通用枚举类
 */
public enum ReturenCodeEnum {
    SUCCESS("E000","操作成功"),
    //参数错误码
    DATA_IS_NULL("E001","参数为空"),
    DATA_TYPE_ERROR("E002","参数格式错误"),
    DATA_VALUE_ERROR("E003","参数值错误"),

    OTHER_ERROR("E999","其他错误")
    ;


    ReturenCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 码
     */
    private String code;
    /**
     * 描述
     */
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

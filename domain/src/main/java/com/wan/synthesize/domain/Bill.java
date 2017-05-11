package com.wan.synthesize.domain;

import java.util.Date;

/**
 * Created by wzx on 2017/2/9.
 */
public class Bill {
    private String name;
    private Double price;
    private String date;
    private Date createDate = new Date();
    private String remark;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String validate(){
        StringBuffer sb= new StringBuffer();
        if (this.name==null || "".equals(this.name)){
            sb.append("消费名称不能为空;");
        }if (this.price==null || this.price==0){
            sb.append("消费金额不能为空;");
        }if (this.date == null || "".equals(this.date)){
            sb.append("消费日期不能为空;");
        }if (this.address == null || "".equals(this.address)){
            sb.append("消费地址不能为空");
        }
        return sb.toString();
    }

}

package com.exam.model;

import javax.persistence.*;
import java.io.Serializable;

public class SysConfig implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * key
     */
    private String sysKey;

    /**
     * value
     */
    private String sysValue;

    /**
     * 状态  1：有效 0：无效
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取key
     *
     * @return sys_key - key
     */
    public String getSysKey() {
        return sysKey;
    }

    /**
     * 设置key
     *
     * @param sysKey key
     */
    public void setSysKey(String sysKey) {
        this.sysKey = sysKey == null ? null : sysKey.trim();
    }

    /**
     * 获取value
     *
     * @return sys_value - value
     */
    public String getSysValue() {
        return sysValue;
    }

    /**
     * 设置value
     *
     * @param sysValue value
     */
    public void setSysValue(String sysValue) {
        this.sysValue = sysValue == null ? null : sysValue.trim();
    }

    /**
     * 获取状态  1：显示 2：隐藏
     *
     * @return status - 状态  1：显示 2：隐藏   
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态  1：显示 2：隐藏
     *
     * @param status 状态  1：显示 2：隐藏   
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
package com.exam.model;

import java.util.List;

import javax.persistence.Transient;

import com.exam.vo.base.BaseVo;


public class BizCategory extends BaseVo {
	
    private Integer pid;
    private String name;
    private String description;
    private Integer sort;
    private Integer status;
    private String icon;


    @Transient
    private BizCategory parent;
    @Transient
    private List<BizCategory> nodes;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public BizCategory getParent() {
        return parent;
    }

    public void setParent(BizCategory parent) {
        this.parent = parent;
    }

    public List<BizCategory> getNodes() {
        return nodes;
    }

    public void setNodes(List<BizCategory> nodes) {
        this.nodes = nodes;
    }
}

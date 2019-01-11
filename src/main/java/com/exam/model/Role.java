package com.exam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @描述  实体类
 * @标题 Role.java
 * @Package com.hungkuei.model
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午13:30:31
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
public class Role implements Serializable {
	
	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	//角色Id
	private String roleId;
	
	//角色名称
	private String name;
	
	//角色描述
	private String description;
	
	//状态： 1有效， 0无效
	private Integer status;
	
	//创建时间
	private Date createTime;
	
	//更新时间
	private Date updateTime;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

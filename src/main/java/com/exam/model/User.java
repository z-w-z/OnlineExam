package com.exam.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @描述  实体类
 * @标题 User.java
 * @Package com.exam.model
 * @版本 v1.0
 * @作者 HungKuei
 * @日期 2018年11月19日 下午13:22:11
 * @Copyright: 2018 by hungkuei All rights reserved.
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8736616045315083846L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// 主键
	private Integer id;

	// 用户id
	private String userId;

	// 用户姓名
	private String username;

	// 密码
	private String password;

	// 盐加密
	private String salt;

	// 昵称
	private String nickname;

	// 邮箱
	private String email;

	// 电话
	private String phone;

	// 性别
	private Integer sex;

	// 年龄
	private Integer age;

	// 头像
	private String img;

	// 状态
	private Integer status;

	// 注册时间
	private Date createTime;

	// 更新时间
	private Date updateTime;

	// 上次登陆时间
	private Date lastLoginTime;

	// 登录IP地址
	@Transient
	private String loginIpAddress;

	// 角色
	@Transient
	private List<Role> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
	
	public String getCredentialsSalt() {
        return username + "nbclass.com" + salt;
    }

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}

package com.exam.vo;

import com.exam.vo.base.BaseConditionVo;

public class QuestionConditionVo extends BaseConditionVo {
	
	private Integer subjectId;
	private Integer status;
	
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}

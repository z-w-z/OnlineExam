package com.exam.model;

import com.exam.vo.base.BaseVo;

public class Grade extends BaseVo {
	
	private String userId;
	private Integer examId;
	private Integer result;
	private Integer autoResult;
	private Integer manulResult;
	private String answerJson;
	private Integer status;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Integer getAutoResult() {
		return autoResult;
	}
	public void setAutoResult(Integer autoResult) {
		this.autoResult = autoResult;
	}
	public Integer getManulResult() {
		return manulResult;
	}
	public void setManulResult(Integer manulResult) {
		this.manulResult = manulResult;
	}
	public String getAnswerJson() {
		return answerJson;
	}
	public void setAnswerJson(String answerJson) {
		this.answerJson = answerJson;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}

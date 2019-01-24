package com.exam.model;

import com.exam.vo.base.BaseVo;

public class ExamQuestion extends BaseVo {
	
	private Integer examId;
	
	private Integer questionId;

	public Integer getExamId() {
		return examId;
	}
	
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}


}

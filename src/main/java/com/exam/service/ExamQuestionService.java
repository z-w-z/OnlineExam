package com.exam.service;

import com.exam.model.ExamQuestion;

public interface ExamQuestionService extends BaseService<ExamQuestion> {

	/**
	 * 通过考试id删除考试-标签关联数据
	 * @param examId
	 * @return
	 */
	int removeByExamId(Integer examId);
	
	/**
	 * 批量添加试题
	 * @param questionIds
	 * @param examId
	 */
	void insertList(Integer examId, Integer[] questionIds);
}

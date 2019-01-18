package com.exam.service;

import java.util.List;

import com.exam.model.Question;
import com.exam.vo.QuestionConditionVo;

public interface QuestionService extends BaseService<Question> {
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<Question> findByCondition(QuestionConditionVo vo);
	
	/**
	 * 插入
	 * @param question
	 * @return
	 */
	Question insertQuestion(Question question);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(Integer[] ids);
	
	/**
	 * 根据课程Id回去问题列表
	 * @param subjectId
	 * @return
	 */
	List<Question> selectBySubjectId(Integer subjectId);
	
	/**
	 * 根据考试id获取问题列表
	 * @param examId
	 * @return
	 */
	List<Question> getQuestionsByExamId(int examId);

}

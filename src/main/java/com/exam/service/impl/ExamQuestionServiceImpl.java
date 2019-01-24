package com.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.ExamQuestionMapper;
import com.exam.model.ExamQuestion;
import com.exam.service.ExamQuestionService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ExamQuestionServiceImpl extends BaseServiceImpl<ExamQuestion> implements ExamQuestionService {

	@Autowired
	private ExamQuestionMapper examQuestionMapper;
	
	
	@Override
	public int removeByExamId(Integer examId) {
		Example example = new Example(ExamQuestion.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("examId", examId);
		return examQuestionMapper.deleteByExample(example);
	}

	@Override
	public void insertList(Integer examId, Integer[] questionIds) {
		Date date = new Date();
		List<ExamQuestion> examQuestions = new ArrayList<>();
		for(Integer questionId : questionIds) {
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setExamId(examId);
			examQuestion.setQuestionId(questionId);
			examQuestion.setCreateTime(date);
			examQuestion.setUpdateTime(date);
			examQuestionMapper.insert(examQuestion);
		}
	}

}

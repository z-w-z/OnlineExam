package com.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.ExaminationMapper;
import com.exam.mapper.QuestionMapper;
import com.exam.model.Examination;
import com.exam.service.ExaminationService;
import com.exam.vo.ExaminationConditionVo;

@Service
public class ExaminationServiceImpl extends BaseServiceImpl<Examination> implements ExaminationService {
	
	@Autowired
	private ExaminationMapper examMapper;
	
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Examination> findByCondition(ExaminationConditionVo vo) {
		List<Examination> examList = examMapper.findByCondition(vo);
		if(!CollectionUtils.isEmpty(examList)) {
			List<Integer> ids = new ArrayList<>();
			for(Examination examination : examList) {
				ids.add(examination.getId());
			}
			List<Examination> listQuestion = examMapper.listQuestionsByExamId(ids);
			//listQuestion,重新组装数据为{id: Examination}
			Map<Integer, Examination> questionMap = new LinkedHashMap<>(listQuestion.size());
			for(Examination examination : listQuestion) {
				questionMap.put(examination.getId(), examination);
			}
			for(Examination examination : examList) {
				Examination questionExam = questionMap.get(examination.getId());
				examination.setQuestions(questionExam.getQuestions());
			}
		}
		return examList;
	}

	@Override
	public Examination insertExam(Examination examination) {
		Date date = new Date();
		examination.setCreateTime(date);
		examination.setUpdateTime(date);
		examMapper.insertSelective(examination);
		return examination;
	}

}

package com.exam.service;

import java.util.List;

import com.exam.model.Examination;
import com.exam.vo.ExaminationConditionVo;

public interface ExaminationService extends BaseService<Examination> {
	
	/**
	 * 分页查询所有考试
	 * @param vo
	 * @return
	 */
	List<Examination> findByCondition(ExaminationConditionVo vo);
	
	/**
	 * 发布考试
	 * @param examination
	 * @return
	 */

	Examination insertExam(Examination examination);
	
}

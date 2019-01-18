package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.ExaminationMapper;
import com.exam.model.Examination;
import com.exam.service.ExaminationService;

@Service
public class ExaminationServiceImpl extends BaseServiceImpl<Examination> implements ExaminationService {
	
	@Autowired
	private ExaminationMapper examinationMapper;

}

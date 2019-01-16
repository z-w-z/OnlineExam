package com.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.exam.mapper.SubjectMapper;
import com.exam.model.Subject;
import com.exam.service.SubjectService;

public class SubjectServiceImpl extends BaseServiceImpl<Subject> implements SubjectService {
	
	@Autowired
	private SubjectMapper subjectMapper;

	@Override
	public List<Subject> selectCategories(Subject subject) {
		return subjectMapper.selectSubjects(subject);
	}

	@Override
	public int deleteBatch(Integer[] ids) {
		return subjectMapper.deleteBatch(ids);
	}

	@Override
	public Subject selectById(Integer id) {
		return subjectMapper.selectById(id);
	}

}

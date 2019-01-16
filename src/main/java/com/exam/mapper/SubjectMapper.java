package com.exam.mapper;


import java.util.List;

import com.exam.model.Subject;
import com.exam.util.MapperUtil;

public interface SubjectMapper extends MapperUtil<Subject> {
	
	List<Subject> selectSubjects(Subject subject);
	
	int deleteBatch(Integer[] ids);
	
	Subject selectById(Integer id);

}

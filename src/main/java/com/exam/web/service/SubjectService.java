package com.exam.web.service;


import java.util.List;
import java.util.Map;

import com.exam.web.model.Subject;

public interface SubjectService {

    int addSubject(Subject subject);

    boolean updateSubject(Subject subject);

    Subject getSubjectById(int id);

    Map<String, Object> getSubjects(int pageNum, int pageSize);

    List<Subject> getSubjects();

    boolean deleteSubjectById(int id);
}

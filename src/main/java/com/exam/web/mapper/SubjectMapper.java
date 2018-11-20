package com.exam.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.exam.web.model.Subject;

@Component
@Mapper
public interface SubjectMapper {

    int insertSubject(@Param("subject") Subject subject);

    int updateSubjectById(@Param("subject") Subject subject);

    Subject getSubjectById(@Param("id") int id);

    int getCount();

    List<Subject> getSubjects();

    int deleteSubjectById(@Param("id") int id);
}

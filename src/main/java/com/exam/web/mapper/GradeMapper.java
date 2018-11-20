package com.exam.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.exam.web.model.Grade;

@Component
@Mapper
public interface GradeMapper {

    int insertGrade(@Param("grade") Grade grade);

    int deleteGrade(@Param("id") int id);

    int updateGradeById(@Param("grade") Grade grade);

    Grade getGradeById(@Param("id") int id);

    int getCountByStudentId(@Param("studentId") int studentId);

    List<Grade> getGradesByStudentId(@Param("studentId") int studentId);

    List<Grade> getGradesByContestId(@Param("contestId") int contestId);
}

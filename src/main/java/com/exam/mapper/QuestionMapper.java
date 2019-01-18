package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.model.Question;
import com.exam.util.MapperUtil;
import com.exam.vo.QuestionConditionVo;

public interface QuestionMapper extends MapperUtil<Question> {
	
	/**
	 * 分页查询，关联查询问题类型
	 * @param vo
	 * @return
	 */
	List<Question> findByCondition(QuestionConditionVo vo);
	
	/**
	 * 批量删除问题
	 * @param ids
	 * @return
	 */
	int deleteBatch(Integer[] ids);

    int deleteQuestion(@Param("id") int id);

    Question getQuestionById(@Param("id") int id);

    int getCountByContent(@Param("content") String content);

    List<Question> getQuestionsByContent(@Param("content") String content);

    List<Question> getQuestionByExamId(@Param("examtId") int examId);

    int getCountByProblemsetIdAndContentAndDiffculty(@Param("id") int id,
                                                     @Param("content") String content,
                                                     @Param("difficulty") int diffculty);

    List<Question> getQuestionsByProblemsetIdAndContentAndDiffculty(@Param("id") int id,
                                                                    @Param("content") String content,
                                                                    @Param("difficulty") int diffculty);

}

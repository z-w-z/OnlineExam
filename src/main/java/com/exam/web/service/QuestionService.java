package com.exam.web.service;

import java.util.List;
import java.util.Map;

import com.exam.web.model.Question;

public interface QuestionService {

	int addQuestion(Question question);

	boolean updateQuestion(Question question);

	List<Question> getQuestionsByContestId(int contestId);

	Map<String, Object> getQuestionsByContent(int pageNum, int pageSize, String content);

	Map<String, Object> getQuestionsByProblemsetIdAndContentAndDiffculty(int pageNum, int pageSize, int problemsetId,
			String content, int diffcult);

	boolean deleteQuestion(int id);

	Question getQuestionById(int id);

	boolean updateQuestionsStateByContestId(int contestId, int state);
}

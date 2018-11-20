package com.exam.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.web.common.AjaxResult;
import com.exam.web.common.ExamConst;
import com.exam.web.model.Grade;
import com.exam.web.model.Question;
import com.exam.web.model.User;
import com.exam.web.service.GradeService;
import com.exam.web.service.QuestionService;

@Controller
@RequestMapping(value = "/grade")
public class GradeController {

	private static Log LOG = LogFactory.getLog(GradeController.class);

	@Autowired
	private GradeService gradeService;
	@Autowired
	private QuestionService questionService;

	// 提交试卷
	@RequestMapping(value = "/api/submitContest", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult submitContest(HttpServletRequest request, @RequestBody Grade grade) {
		AjaxResult ajaxResult = new AjaxResult();
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_USER);
		List<String> answerStrs = Arrays.asList(grade.getAnswerJson().split(ExamConst.SPLIT_CHAR));
		int autoResult = 0;
		List<Question> questions = questionService.getQuestionsByContestId(grade.getContestId());

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			if (question.getQuestionType() <= 1 && question.getAnswer().equals(answerStrs.get(i))) {
				autoResult += question.getScore();
			}
		}
		grade.setStudentId(currentUser.getId());
		grade.setResult(autoResult);
		grade.setAutoResult(autoResult);
		grade.setManulResult(0);
		int gradeId = gradeService.addGrade(grade);
		return ajaxResult.setData(gradeId);
	}

	// 完成批改试卷
	@RequestMapping(value = "/api/finishGrade", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult finishGrade(@RequestBody Grade grade) {
		AjaxResult ajaxResult = new AjaxResult();
		grade.setResult(grade.getAutoResult() + grade.getManulResult());
		grade.setFinishTime(new Date());
		grade.setState(1);
		boolean result = gradeService.updateGrade(grade);
		return ajaxResult.setData(result);
	}
}

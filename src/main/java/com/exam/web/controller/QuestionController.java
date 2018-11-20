package com.exam.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.web.common.AjaxResult;
import com.exam.web.model.Question;
import com.exam.web.service.QuestionService;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

	private static Log LOG = LogFactory.getLog(QuestionController.class);

	@Autowired
	private QuestionService questionService;

	// 添加题目
	@RequestMapping(value = "/api/addQuestion", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addQuestion(@RequestBody Question question) {
		AjaxResult ajaxResult = new AjaxResult();
		int questionId = questionService.addQuestion(question);
		return new AjaxResult().setData(questionId);
	}

	// 更新题目信息
	@RequestMapping(value = "/api/updateQuestion", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateQuestion(@RequestBody Question question) {
		AjaxResult ajaxResult = new AjaxResult();
		boolean result = questionService.updateQuestion(question);
		return new AjaxResult().setData(result);
	}

	// 删除题目信息
	@DeleteMapping("/api/deleteQuestion/{id}")
	public AjaxResult deleteContest(@PathVariable int id) {
		AjaxResult ajaxResult = new AjaxResult();
		boolean result = questionService.deleteQuestion(id);
		return new AjaxResult().setData(result);
	}
}

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
import com.exam.web.model.Contest;
import com.exam.web.service.ContestService;
import com.exam.web.service.QuestionService;

@RestController
@RequestMapping(value = "/contest")
public class ContestController {

	private static Log LOG = LogFactory.getLog(ContestController.class);

	@Autowired
	private ContestService contestService;
	@Autowired
	private QuestionService questionService;

	// 添加考试
	@RequestMapping(value = "/api/addContest", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addContest(@RequestBody Contest contest) {
		AjaxResult ajaxResult = new AjaxResult();
		int contestId = contestService.addContest(contest);
		return new AjaxResult().setData(contestId);
	}

	// 更新考试信息
	@RequestMapping(value = "/api/updateContest", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateContest(@RequestBody Contest contest) {
		AjaxResult ajaxResult = new AjaxResult();
		boolean result = contestService.updateContest(contest);
		return ajaxResult.setData(result);
	}

	// 删除考试信息
	@DeleteMapping("/api/deleteContest/{id}")
	public AjaxResult deleteContest(@PathVariable int id) {
		AjaxResult ajaxResult = new AjaxResult();
		boolean result = contestService.deleteContest(id);
		return ajaxResult.setData(result);
	}

	// 完成考试批改
	@RequestMapping(value = "/api/finishContest/{id}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult finishContest(@PathVariable int id) {
		AjaxResult ajaxResult = new AjaxResult();
		Contest contest = contestService.getContestById(id);
		contest.setState(3);
		questionService.updateQuestionsStateByContestId(id, 1);
		boolean result = contestService.updateContest(contest);
		return ajaxResult.setData(result);
	}

}

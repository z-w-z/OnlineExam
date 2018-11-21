package com.exam.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.web.common.AjaxResult;
import com.exam.web.model.Reply;
import com.exam.web.service.ReplyService;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {

	private static Log LOG = LogFactory.getLog(ReplyController.class);

	@Autowired
	private ReplyService replyService;

	// 添加回复
	@RequestMapping(value = "/api/addReply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addReply(@RequestBody Reply reply) {
		AjaxResult ajaxResult = new AjaxResult();
		int replyId = replyService.addReply(reply);
		return ajaxResult.setData(replyId);
	}
}

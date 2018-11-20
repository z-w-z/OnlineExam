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
import com.exam.web.model.Comment;
import com.exam.web.service.CommentService;
import com.exam.web.service.PostService;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

	private static Log LOG = LogFactory.getLog(CommentController.class);

	@Autowired
	private CommentService commentService;
	@Autowired
	private PostService postService;

	// 添加评论
	@RequestMapping(value = "/api/addComment", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addComment(@RequestBody Comment comment) {
		AjaxResult ajaxResult = new AjaxResult();
		postService.updateReplyNumById(comment.getPostId());
		int commentId = commentService.addComment(comment);
		return ajaxResult.setData(commentId);
	}

	// 删除评论
	@DeleteMapping("/api/deleteComment/{id}")
	public AjaxResult deleteComment(@PathVariable int id) {
		AjaxResult ajaxResult = new AjaxResult();
		boolean result = commentService.deleteCommentById(id);
		return ajaxResult.setData(result);
	}
}

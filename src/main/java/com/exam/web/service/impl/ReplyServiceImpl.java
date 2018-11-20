package com.exam.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.web.mapper.ReplyMapper;
import com.exam.web.model.Reply;
import com.exam.web.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public int addReply(Reply reply) {
		return replyMapper.insertReply(reply);
	}

	@Override
	public List<Reply> getReliesByPostId(int postId) {
		return replyMapper.getReliesByPostId(postId);
	}
}

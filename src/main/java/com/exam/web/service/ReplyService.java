package com.exam.web.service;


import java.util.List;

import com.exam.web.model.Reply;

public interface ReplyService {

    int addReply(Reply reply);

    List<Reply> getReliesByPostId(int postId);
}

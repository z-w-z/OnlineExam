package com.exam.web.service;


import java.util.List;
import java.util.Map;

import com.exam.web.model.Comment;

public interface CommentService {

    int addComment(Comment comment);

    List<Comment> getCommentsByPostId(int postId);

    Map<String, Object> getComments(int pageNum, int pageSize);

    boolean deleteCommentById(int id);
}

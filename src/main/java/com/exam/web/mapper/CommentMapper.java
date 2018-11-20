package com.exam.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.exam.web.model.Comment;

@Component
@Mapper
public interface CommentMapper {

    int insertComment(@Param("comment") Comment comment);

    List<Comment> getCommentsByPostId(@Param("postId") int postId);

    int getCount();

    List<Comment> getComments();

    int deleteCommentById(@Param("id") int id);
}

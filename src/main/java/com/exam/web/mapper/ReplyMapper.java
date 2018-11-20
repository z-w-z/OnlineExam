package com.exam.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.exam.web.model.Reply;

@Component
@Mapper
public interface ReplyMapper {

    int insertReply(@Param("reply") Reply reply);

    List<Reply> getReliesByPostId(@Param("postId") int postId);
}

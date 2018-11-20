package com.exam.web.service;


import java.util.Map;

import com.exam.web.model.Post;

public interface PostService {

    int addPost(Post post);

    boolean updatePostById(Post post);

    boolean deletePostById(int id);

    Map<String, Object> getPosts(int pageNum, int pageSize);

    Post getPostById(int id);

    boolean updateReplyNumById(int id);

    Map<String, Object> getPostsByAuthorId(int pageNum, int pageSize, int authorId);
}

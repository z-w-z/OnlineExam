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
import com.exam.web.model.Post;
import com.exam.web.service.PostService;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private static Log LOG = LogFactory.getLog(PostController.class);

    @Autowired
    private PostService postService;

    //添加帖子
    @RequestMapping(value="/api/addPost", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addPost(@RequestBody Post post) {
        AjaxResult ajaxResult = new AjaxResult();
        int postId = postService.addPost(post);
        return new AjaxResult().setData(postId);
    }

    //更新帖子
    @RequestMapping(value="/api/updatePost", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult updatePost(@RequestBody Post post) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean result = postService.updatePostById(post);
        return new AjaxResult().setData(result);
    }

    //删除帖子
    @DeleteMapping("/api/deletePost/{id}")
    public AjaxResult deletePost(@PathVariable int id) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean result = postService.deletePostById(id);
        return new AjaxResult().setData(result);
    }
}

package com.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.BizCommentMapper;
import com.exam.model.BizComment;
import com.exam.service.BizCommentService;
import com.exam.vo.CommentConditionVo;

@Service
public class BizCommentServiceImpl extends BaseServiceImpl<BizComment> implements BizCommentService {
    @Autowired
    private BizCommentMapper commentMapper;
    @Override
    public List<BizComment> selectComments(CommentConditionVo vo) {
        return commentMapper.selectComments(vo);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return commentMapper.deleteBatch(ids);
    }
}

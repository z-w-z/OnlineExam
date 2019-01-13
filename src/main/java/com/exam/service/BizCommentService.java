package com.exam.service;

import java.util.List;

import com.exam.model.BizComment;
import com.exam.vo.CommentConditionVo;

public interface BizCommentService extends BaseService<BizComment> {
    List<BizComment> selectComments(CommentConditionVo vo);

    int deleteBatch(Integer[]ids);

}

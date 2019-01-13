package com.exam.mapper;

import java.util.List;

import com.exam.model.BizComment;
import com.exam.util.MapperUtil;
import com.exam.vo.CommentConditionVo;

public interface BizCommentMapper extends MapperUtil<BizComment> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<BizComment> selectComments(CommentConditionVo vo);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);
}

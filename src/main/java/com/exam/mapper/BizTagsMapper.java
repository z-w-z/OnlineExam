package com.exam.mapper;

import java.util.List;

import com.exam.model.BizTags;
import com.exam.util.MapperUtil;

public interface BizTagsMapper extends MapperUtil<BizTags> {

    List<BizTags> selectTags(BizTags bizTags);

    int deleteBatch(Integer[] ids);
}

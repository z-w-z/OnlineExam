package com.exam.service;

import java.util.List;

import com.exam.model.BizTags;

public interface BizTagsService extends BaseService<BizTags>{
    List<BizTags> selectTags(BizTags bizTags);

    int deleteBatch(Integer[]ids);
}

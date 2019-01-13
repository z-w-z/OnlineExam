package com.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.BizTagsMapper;
import com.exam.model.BizTags;
import com.exam.service.BizTagsService;

@Service
public class BizTagsServiceImpl extends BaseServiceImpl<BizTags> implements BizTagsService {

    @Autowired
    private BizTagsMapper bizTagsMapper;

    @Override
    public List<BizTags> selectTags(BizTags bizTags) {
        return bizTagsMapper.selectTags(bizTags);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return bizTagsMapper.deleteBatch(ids);
    }
}

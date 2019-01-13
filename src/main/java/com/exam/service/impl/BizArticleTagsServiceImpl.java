package com.exam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.BizArticleTagsMapper;
import com.exam.model.BizArticleTags;
import com.exam.service.BizArticleTagsService;

import tk.mybatis.mapper.entity.Example;

@Service
public class BizArticleTagsServiceImpl extends BaseServiceImpl<BizArticleTags> implements BizArticleTagsService {
    @Autowired
    private BizArticleTagsMapper bizArticleTagsMapper;
    @Override
    public int removeByArticleId(Integer articleId) {
        Example example = new Example(BizArticleTags.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        return bizArticleTagsMapper.deleteByExample(example);
    }

    @Override
    public void insertList(Integer[] tagIds, Integer articleId) {
        Date date = new Date();
        List<BizArticleTags> articleTagses = new ArrayList<>();
        for(Integer tagId : tagIds){
            BizArticleTags articleTags = new BizArticleTags();
            articleTags.setTagId(tagId);
            articleTags.setArticleId(articleId);
            articleTags.setCreateTime(date);
            articleTags.setUpdateTime(date);
            bizArticleTagsMapper.insert(articleTags);
        }
    }
}

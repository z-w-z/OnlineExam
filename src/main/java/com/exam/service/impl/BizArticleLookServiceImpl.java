package com.exam.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.BizArticleLookMapper;
import com.exam.model.BizArticleLook;
import com.exam.service.BizArticleLookService;

@Service
public class BizArticleLookServiceImpl extends BaseServiceImpl<BizArticleLook> implements BizArticleLookService {
    @Autowired
    private BizArticleLookMapper articleLookMapper;
    @Override
    public int checkArticleLook(Integer articleId, String userIp, Date lookTime) {
        return articleLookMapper.checkArticleLook(articleId,userIp,lookTime);
    }
}

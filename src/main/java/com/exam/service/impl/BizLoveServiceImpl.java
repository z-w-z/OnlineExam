package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.mapper.BizLoveMapper;
import com.exam.model.BizLove;
import com.exam.service.BizLoveService;

@Service
public class BizLoveServiceImpl extends BaseServiceImpl<BizLove> implements BizLoveService {
    @Autowired
    private BizLoveMapper loveMapper;
    @Override
    public BizLove checkLove(Integer bizId, String userIp) {
        return loveMapper.checkLove(bizId,userIp);
    }
}

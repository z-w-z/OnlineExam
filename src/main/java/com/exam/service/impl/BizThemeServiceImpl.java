package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.annotation.RedisCache;
import com.exam.mapper.BizThemeMapper;
import com.exam.model.BizTheme;
import com.exam.service.BizThemeService;
import com.exam.util.CoreConst;

@Service
public class BizThemeServiceImpl extends BaseServiceImpl<BizTheme> implements BizThemeService {

    @Autowired
    private BizThemeMapper themeMapper;
    @Override
    @RedisCache(flush = true)
    public int useTheme(Integer id) {
        themeMapper.setInvaid();
        return themeMapper.updateStatusById(id);
    }

    @Override
    @RedisCache
    public BizTheme selectCurrent() {
        BizTheme bizTheme = new BizTheme();
        bizTheme.setStatus(CoreConst.STATUS_VALID);
        return themeMapper.selectOne(bizTheme);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return themeMapper.deleteBatch(ids);
    }
}

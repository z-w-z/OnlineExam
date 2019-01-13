package com.exam.mapper;

import com.exam.model.BizTheme;
import com.exam.util.MapperUtil;

public interface BizThemeMapper extends MapperUtil<BizTheme> {
    int setInvaid();
    int updateStatusById(Integer id);
    int deleteBatch(Integer[] ids);
}
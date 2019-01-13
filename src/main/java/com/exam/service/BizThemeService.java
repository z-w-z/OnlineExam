package com.exam.service;

import com.exam.model.BizTheme;

public interface BizThemeService  extends BaseService<BizTheme>  {
    int useTheme(Integer id);

    BizTheme selectCurrent();

    int deleteBatch(Integer[]ids);

}

package com.exam.mapper;

import java.util.List;

import com.exam.model.BizCategory;
import com.exam.util.MapperUtil;

public interface BizCategoryMapper extends MapperUtil<BizCategory> {

    List<BizCategory> selectCategories(BizCategory bizCategory);

    int deleteBatch(Integer[] ids);

    BizCategory selectById(Integer id);
}

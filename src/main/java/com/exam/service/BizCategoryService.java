package com.exam.service;

import java.util.List;

import com.exam.model.BizCategory;

public interface BizCategoryService extends BaseService<BizCategory>{

    List<BizCategory> selectCategories(BizCategory bizCategory);
    int deleteBatch(Integer[] ids);
    BizCategory selectById(Integer id);
    List<BizCategory> selectByPid(Integer pid);

}

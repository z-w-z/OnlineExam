package com.exam.mapper;

import java.util.List;

import com.exam.model.BizLink;
import com.exam.util.MapperUtil;

public interface BizLinkMapper extends MapperUtil<BizLink> {

    List<BizLink> selectLinks(BizLink bizLink);

    int deleteBatch(Integer[] ids);

}

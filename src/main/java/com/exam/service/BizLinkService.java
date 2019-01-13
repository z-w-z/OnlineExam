package com.exam.service;

import java.util.List;

import com.exam.model.BizLink;

public interface BizLinkService extends BaseService<BizLink> {
    List<BizLink> selectLinks(BizLink bizLink);

    int deleteBatch(Integer[]ids);

}

package com.exam.service;

import java.util.Date;

import com.exam.model.BizArticleLook;

public interface BizArticleLookService extends BaseService<BizArticleLook> {
    int checkArticleLook( Integer articleId, String userIp, Date lookTime);
}

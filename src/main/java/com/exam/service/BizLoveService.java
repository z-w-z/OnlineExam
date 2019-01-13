package com.exam.service;

import com.exam.model.BizLove;

public interface BizLoveService extends BaseService<BizLove> {
    BizLove checkLove(Integer bizId,String userIp);
}

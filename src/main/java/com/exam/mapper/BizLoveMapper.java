package com.exam.mapper;

import org.apache.ibatis.annotations.Param;

import com.exam.model.BizLove;
import com.exam.util.MapperUtil;

public interface BizLoveMapper extends MapperUtil<BizLove> {
    BizLove checkLove(@Param("bizId")Integer bizId,@Param("userIp")String userIp);
}

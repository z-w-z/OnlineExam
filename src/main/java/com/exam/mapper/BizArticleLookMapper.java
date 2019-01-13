package com.exam.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.exam.model.BizArticleLook;
import com.exam.util.MapperUtil;

public interface BizArticleLookMapper extends MapperUtil<BizArticleLook> {

    int checkArticleLook(@Param("articleId") Integer articleId, @Param("userIp") String userIp, @Param("lookTime") Date lookTime);
}

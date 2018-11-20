package com.exam.web.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.exam.web.model.Contest;

@Component
@Mapper
public interface ContestMapper {

    int insertContest(@Param("contest") Contest contest);

    int updateContestById(@Param("contest") Contest contest);

    Contest getContestById(@Param("id") int id);

    int getCount();

    List<Contest> getContests();

    int deleteContest(@Param("id") int id);

    int updateStateToStart(@Param("currentTime") Date currentTime);

    int updateStateToEnd(@Param("currentTime") Date currentTime);

    List<Contest> getContestsByContestIds(@Param("contestIds") Set<Integer> contestIds);
}

package com.exam.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exam.web.model.Contest;

public interface ContestService {

	int addContest(Contest contest);

	boolean updateContest(Contest contest);

	Contest getContestById(int id);

	Map<String, Object> getContests(int pageNum, int pageSize);

	boolean deleteContest(int id);

	boolean updateStateToStart();

	boolean updateStateToEnd();

	List<Contest> getContestsByContestIds(Set<Integer> contestIds);
}

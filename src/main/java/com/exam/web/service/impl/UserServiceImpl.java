package com.exam.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.web.common.ExamConst;
import com.exam.web.mapper.UserMapper;
import com.exam.web.model.User;
import com.exam.web.service.UserService;
import com.github.pagehelper.PageHelper;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		user.setAvatarImgUrl(ExamConst.DEFAULT_AVATAR_IMG_URL);
		return userMapper.insertUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUserById(user) > 0;
	}

	@Override
	public boolean updateAvatarImgUrlById(String avatarImgUrl, int id) {
		// TODO Auto-generated method stub
		return userMapper.updateAvatarImgUrlById(avatarImgUrl, id) > 0;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUsername(username);
	}

	@Override
	public List<User> getUsersByStudentIds(List<Integer> studentIds) {
		// TODO Auto-generated method stub
		return userMapper.getUsersByIds(studentIds);
	}

	@Override
	public Map<String, Object> getUsers(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<>();
		int count = userMapper.getCount();
		if (count == 0) {
			data.put("pageNum", 0);
			data.put("pageSize", 0);
			data.put("totalPageNum", 1);
			data.put("totalPageSize", 0);
			data.put("users", new ArrayList<>());
			return data;
		}
		int totalPageNum = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		if (pageNum > totalPageNum) {
			data.put("pageNum", 0);
			data.put("pageSize", 0);
			data.put("totalPageNum", totalPageNum);
			data.put("totalPageSize", 0);
			data.put("users", new ArrayList<>());
			return data;
		}
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userMapper.getUsers();
		data.put("pageNum", pageNum);
		data.put("pageSize", pageSize);
		data.put("totalPageNum", totalPageNum);
		data.put("totalPageSize", count);
		data.put("users", users);
		return data;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.deleteUser(id) > 0;
	}

	@Override
	public boolean disabledUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.updateState(id, 1) > 0;
	}

	@Override
	public boolean abledUser(int id) {
		// TODO Auto-generated method stub
		return userMapper.updateState(id, 0) > 0;
	}

	@Override
	public List<User> getUsersByIds(Set<Integer> ids) {
		// TODO Auto-generated method stub
		return userMapper.getUsersByIdSets(ids);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

}

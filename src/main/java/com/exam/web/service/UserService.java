package com.exam.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.exam.web.model.User;

public interface UserService {
	
	int addUser(User user);

    boolean updateUser(User user);

    boolean updateAvatarImgUrlById(String avatarImgUrl, int id);

    User getUserByUsername(String username);

    List<User> getUsersByStudentIds(List<Integer> studentIds);

    Map<String, Object> getUsers(int pageNum, int pageSize);

    boolean deleteUser(int id);

    boolean disabledUser(int id);

    boolean abledUser(int id);

    List<User> getUsersByIds(Set<Integer> ids);

    User getUserById(int id);

}

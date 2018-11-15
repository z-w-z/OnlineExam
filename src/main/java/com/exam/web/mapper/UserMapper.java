package com.exam.web.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.exam.web.model.User;

@Component
@Mapper
public interface UserMapper {

	int insertUser(@Param("user") User user);

	int updateUserById(@Param("user") User user);

	int updateAvatarImgUrlById(@Param("avatarImgUrl") String avatarImgUrl, @Param("id") int id);

	User getUserByUsername(@Param("username") String username);

	List<User> getUsersByIds(@Param("studentIds") List<Integer> studentIds);

	int getCount();

	List<User> getUsers();

	int deleteUser(@Param("id") int id);

	int updateState(@Param("id") int id, @Param("state") int state);

	List<User> getUsersByIdSets(@Param("ids") Set<Integer> ids);

	User getUserById(@Param("id") int id);

}

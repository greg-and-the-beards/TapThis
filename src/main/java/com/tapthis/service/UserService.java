package com.tapthis.service;

import java.util.List;

import com.tapthis.entity.UserInfo;

public interface UserService {
	
	List<UserInfo> getUsers();
	UserInfo getUserById(int userId);
	boolean addUser(UserInfo userId);
	void updateUser(UserInfo userId);
	void deleteUser(int userId);
}

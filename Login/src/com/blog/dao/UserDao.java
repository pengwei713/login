package com.blog.dao;

import java.util.List;

import com.blog.entity.User;

public interface UserDao {
	public int add(User user);
	public int edit(User user);
	public User getOne(String username);
	public List<User> listUser();
	public int delete(Integer userid);
}

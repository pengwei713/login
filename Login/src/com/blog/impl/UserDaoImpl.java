package com.blog.impl;

import java.util.List;

import com.blog.dao.BaseDAO;
import com.blog.dao.UserDao;
import com.blog.entity.User;

public class UserDaoImpl extends BaseDAO implements UserDao {

	@Override
	public int add(User user) {
		String sql = "insert into usertbl(username,password,nickname,headimg,remark,sex,admin) value(?,?,?,?,?,?,?)";
		Object[] params = {user.getUsername(),user.getPassword(),user.getNickname(),user.getHeadimg(),user.getRemark(),user.getSex(),user.getAdmin()};
		return executeUpdate(sql, params);
	}

	@Override
	public int edit(User user) {
		String sql = "update usertbl set username = ?,password = ?,nickname = ?,headimg = ?,remark = ?,sex = ?,admin = ? where userid = ?";
		Object[] params = {user.getUsername(),user.getPassword(),user.getNickname(),user.getHeadimg(),user.getRemark(),user.getSex(),user.getAdmin(),user.getUserid()};
		return executeUpdate(sql, params);
	}

	@Override
	public User getOne(String username) {
		String sql = "select * from usertbl where username = ?";
		Object[] params = {username};
		return find(sql, params, User.class).size() == 0 ? null : find(sql, params, User.class).get(0);
	}

	@Override
	public List<User> listUser() {
		String sql = "select * from usertbl";
		Object[] params = {};
		return find(sql, params, User.class);
	}

	@Override
	public int delete(Integer userid) {
		String sql = "delete from usertbl where userid = ?";
		Object[] params = {userid};
		return executeUpdate(sql, params);
	}

}

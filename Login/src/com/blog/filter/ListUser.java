package com.blog.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.blog.entity.User;
import com.blog.impl.UserDaoImpl;

@WebFilter("/listuser.jsp")
public class ListUser implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		List<User> listUser = null;
		if(user.getAdmin() == 1) {
			listUser = new UserDaoImpl().listUser();
		}else {
			listUser = new ArrayList<>();
			listUser.add(user);
		}
		req.setAttribute("listuser", listUser);
		chain.doFilter(req, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

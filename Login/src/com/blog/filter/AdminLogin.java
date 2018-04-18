package com.blog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.blog.entity.User;
@WebFilter("/admin_index.jsp")
public class AdminLogin implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			req.setAttribute("message", "请登录后在做操作");
			req.getRequestDispatcher("admin.jsp").forward(req, response);
		}else {
			chain.doFilter(req, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

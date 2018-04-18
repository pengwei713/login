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

import com.blog.entity.Article;
import com.blog.entity.User;
import com.blog.impl.ArticleDaoImpl;
import com.blog.impl.UserDaoImpl;
@WebFilter("/articlelist.jsp")
public class ArticleList implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("user");
		List<Article> list = null;
		if(user.getAdmin() == 1) {
			list = new ArticleDaoImpl().getList();
		}else {
			list = new ArticleDaoImpl().getUserList(user.getUserid());
		}
		request.setAttribute("articlelist", list);
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

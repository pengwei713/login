package com.blog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.blog.entity.Article;
import com.blog.entity.User;
import com.blog.impl.ArticleDaoImpl;
import com.blog.impl.UserDaoImpl;
@WebFilter("/addarticle.jsp")
public class ArticleEdit implements Filter {
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String username = request.getParameter("articleid");
		if(username != null) {
			Article getone = new ArticleDaoImpl().getone(Integer.parseInt(username));
			request.setAttribute("articleedit", getone);
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

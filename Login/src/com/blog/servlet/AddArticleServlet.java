package com.blog.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.entity.Article;
import com.blog.entity.User;
import com.blog.impl.ArticleDaoImpl;
@WebServlet("/addarticle")
@MultipartConfig
public class AddArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String article1 = request.getParameter("articleid");
		try {
			Integer articleid = Integer.parseInt(article1);
			Integer userid = Integer.parseInt(request.getParameter("userid"));
			String title = request.getParameter("title");
			String authour = request.getParameter("authour");
			String gen = request.getParameter("gen");
			String context = request.getParameter("context");
			String image = request.getParameter("image");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sf.format(date);
			Article article = new Article(articleid, userid, title, authour, gen, format, context, image);
			int add = new ArticleDaoImpl().edit(article);
			response.setContentType("text/html;charset=utf-8");
			if(add == 1) {
				response.getWriter().print("修改文章成功");
			}
		}catch(Exception e) {
			Integer userid = ((User)session.getAttribute("user")).getUserid();
			String title = request.getParameter("title");
			String authour = ((User)session.getAttribute("user")).getUsername();
			String gen = request.getParameter("gen");
			String context = request.getParameter("context");
			String image = request.getParameter("image");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sf.format(date);
			Article article = new Article(null, userid, title, authour, gen, format, context, image);
			int add = new ArticleDaoImpl().add(article);
			response.setContentType("text/html;charset=utf-8");
			if(add == 1) {
				response.getWriter().print("插入文章成功");
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

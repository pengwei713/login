package com.blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.impl.ArticleDaoImpl;
@WebServlet("/deletearticle")
public class DeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleid = request.getParameter("articleid");
		int delete = new ArticleDaoImpl().delete(Integer.parseInt(articleid));
		if(delete == 1) {
			response.getWriter().print("1");
		}else {
			response.getWriter().print("0");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

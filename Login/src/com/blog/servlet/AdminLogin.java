package com.blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.entity.User;
import com.blog.impl.UserDaoImpl;
import com.google.gson.Gson;
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new UserDaoImpl().getOne(username);
		response.setContentType("text/html;charset=utf-8");
		if(user == null) {
			request.setAttribute("message", "用户名不存在");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else if(!user.getPassword().equals(password)){
			request.setAttribute("message", "密码不正确");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("admin_index.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

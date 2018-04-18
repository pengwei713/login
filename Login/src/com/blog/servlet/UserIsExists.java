package com.blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.entity.User;
import com.blog.impl.UserDaoImpl;
@WebServlet("/userexists")
public class UserIsExists extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		User one = new UserDaoImpl().getOne(username);
		if(userid != null && one != null && userid != "") {
			if(Integer.parseInt(userid) == one.getUserid()) {
				response.getWriter().print("1");
			}else {
				response.getWriter().print("0");
			}
		}else {
			if(one == null) {
				response.getWriter().print("1");
			}else {
				response.getWriter().print("0");
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

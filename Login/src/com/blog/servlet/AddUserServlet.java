package com.blog.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.blog.entity.User;
import com.blog.impl.UserDaoImpl;
@WebServlet("/adduser")
@MultipartConfig
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		User one = userDaoImpl.getOne(username);
		response.setContentType("text/html;charset=utf-8");
		String userid = request.getParameter("userid");
		try{
			String userid1 = request.getParameter("userid");
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			String headimg = request.getParameter("headimg");
			String remark = request.getParameter("remark");
			String sex = request.getParameter("sex");
			Integer admin = Integer.parseInt(request.getParameter("admin"));
			User user = new User(Integer.parseInt(userid1), username, password, nickname, headimg, remark, sex,admin);
			int add = userDaoImpl.edit(user);
			if(add == 1) {
				response.getWriter().print("2");
			}
		}catch(Exception e) {
			if(one != null){
				response.getWriter().print("请不要重复注册");
			}else {
				String password = request.getParameter("password");
				String nickname = request.getParameter("nickname");
				String headimg = request.getParameter("headimg");
				String remark = request.getParameter("remark");
				String sex = request.getParameter("sex");
				Integer admin = Integer.parseInt(request.getParameter("admin"));
				User user = new User(null, username, password, nickname, headimg, remark, sex,admin);
				int add = userDaoImpl.add(user);
				if(add == 1) {
					response.getWriter().print("1");
				}
			}
		}
	}
	public static String fileUpload(HttpServletRequest request,String partName) throws IOException, ServletException, FileNotFoundException {
		Part part = request.getPart(partName);
		String header = part.getHeader("Content-Disposition");
		String cds[] = header.split(";");
		String fileName = null;
		OutputStream os = null;
		String path = request.getServletContext().getRealPath("upload");
		try {
			fileName = cds[2].substring(cds[2].lastIndexOf("\\")+1, cds[2].length()-1);
			fileName = System.currentTimeMillis()+"_"+fileName;
			os = new FileOutputStream(path+"/"+fileName);
		} catch (Exception e) {
			fileName = cds[2].substring(cds[2].indexOf("\"")+1, cds[2].length()-1);
			fileName = System.currentTimeMillis()+"_"+fileName;
			os = new FileOutputStream(path+"/"+fileName);
		}
		InputStream is = part.getInputStream();
		byte[] b = new byte[1024];
		int len = 0;
		while((len = is.read(b)) != -1) {
			os.write(b, 0, len);
		}
		os.close();
		is.close();
		return fileName;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

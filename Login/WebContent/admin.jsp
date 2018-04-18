<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>欢迎登陆后台管理</title>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
			list-style-type: none;
		}
		html{
			height: 100%;
		}
		body{
			height:100%;
			background-color: #343434;
		}
		h1{
			color: #ccc;
		}
		ul{
			margin: 200px auto;
			width: 280px;
		}
		input{
			padding-left: 5px;
			width: 250px;
			border:none;
			outline: none;
			height: 40px;
			margin-top: 10px;
			font-size: 1.2em;
			box-shadow: 1px 1px 1px 1px #544d4d,-1px -1px 1px 1px #221b1b;
			background: #222;
			color: #ccc;
		}
		input[type='submit']{
			width: 256px;
			cursor: pointer;
			box-shadow: 1px 1px 1px 1px #221b1b,-1px -1px 1px 1px #544d4d;
			background: #ccc;
			color: #3c3c3c;
			font-weight: bold;
		}
		p{
			text-align: center;
			color: red;
			line-height: 30px;
			width: 255px;
		}
	</style>
</head>
<body>
	<ul>
		<li><h1>欢迎登陆博客后台</h1></li>
		<form action="adminlogin" method="post">
			<li><input type="text" placeholder="用户名" name="username"></li>
			<li><input type="password" placeholder="密码" name="password"></li>
			<li><input type="submit" value="登录后台"></li>
			<li><p>${message}</p></li>
		</form>
	</ul>
</body>
</html>
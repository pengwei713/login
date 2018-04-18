<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title style='color:orange'>欢迎使用后台管理</title>
	<style type="text/css">
		*{
			margin:0;
			padding: 0;
			list-style: none;
			text-decoration: none;
		}
		body{
			background:#343434;
		}
		header{
			width: 100%;
			height: 70px;
			background: #222222;
		}
		#nav{
			width: 100%;
			height: 70px;
			line-height: 70px;
			margin:auto;
			position: relative;
		}
		#nav h2{
			color: #fff;
			margin-left: 10px;
			display: inline-block;
		}
		.ass:nth-child(2){
			margin-left: 30px;
		}
		.ass{
			display: inline-block;
			padding: 0 20px;
			color: #ccc;
			height: 65px;
		}
		.active{
			border-bottom: 1px solid #fff;
		}
		#nav span{
			position: absolute;
			right: 20px;
			top: 0;
			color: #ccc;
		}
		#nav span a{
			color: #ccc;
		}
		#nav span a:hover{
			text-decoration: underline;
		}
		#left{
			width: 20%;
			display: inline-block;
			vertical-align: top;
		}
		#right{
			width: 80%;
			height: 800px;
			display: inline-block;
			vertical-align: top;
		}
		#left ul>li{
			width: 100%;
			box-shadow: 1px 1px 1px 1px #544d4d,-1px -1px 1px 1px #221b1b;
			margin-top: 15px;
			background: #222;
		}
		#left ul>li a{
			display: block;
			height: 40px;
			line-height: 40px;
			text-align: center;
			color: #ccc;
		}
		#left ul>li a:hover{
			font-weight: bold;
		}
		.a1:nth-child(2){
			display: none;
		}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			var nav1 = ['listuser.jsp','articlelist.jsp']
			$('.ass').click(function(){
				$(this).addClass('active').siblings().removeClass('active')
				$('.a1:eq('+($(this).index()-1)+')').show().siblings().hide()
				$('iframe').attr('src',nav1[$(this).index()-1])
			})
			var nav = ['listuser.jsp','register.jsp','articlelist.jsp','addarticle.jsp']
			$('.anav').click(function(){
				$('iframe').attr('src',nav[$(this).attr('id')])
				$('.anav').css('background','#222')
				$(this).css('background','black')
			})
			$('#logout').click(function(e){
				e.preventDefault();
				$.post('logout',function(da){
					if(da == "1"){
						location.href="index.html"
					}
				})
			})
		})
	</script>
</head>
<body>
	<header>
		<div id="nav">
			<h2>欢迎使用后台管理</h2><a href="javascript:;" class="ass active">用户管理</a><a href="javascript:;" class="ass">文章管理</a><span><a href="index.html">前台首页</a> 欢迎登陆，管理员【${user.username}】 <a href="" id="logout">注销</a></span>
		</div>
		<div id="left">
			<ul class="a1">
				<li><a href="javascript:;" class="anav" id="0">用户列表</a></li>
				<li><a href="javascript:;" class="anav" id="1">添加用户</a></li>
			</ul>
			<ul class="a1">
				<li><a href="javascript:;" class="anav" id="2">文章列表</a></li>
				<li><a href="javascript:;" class="anav" id="3">添加文章</a></li>
			</ul>
		</div><div id="right">
			<iframe src="listuser.jsp" frameborder="0" width="100%" height="100%"></iframe>
		</div>
	</header>
</body>
</html>
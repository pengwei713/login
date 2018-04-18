<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户列表</title>
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
		table{
			color: #ccc;
			border-color: #ccc;
			margin: auto;
			margin-top: 100px;
			text-align: center;
		}
		table td{
			padding: 10px;
		}
		.cont{
			width: 300px;
			text-overflow:ellipsis;
			white-space: nowrap;
			overflow: hidden;
		}
		table td a{
			color: #ccc;
		}
		table tr:hover{
			background: black;
		}
		table td a:hover{
			text-decoration: underline;
		}
		table td img{
			width: 100px;
			max-height: 100px;
		}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$('.delete').click(function(e){
				e.preventDefault();
				var $a = $(this)
				var a = confirm("确定要删除用户【"+$a.parent().parent().find('td:eq(1)').text()+"】吗？")
				if(a){
					$.post('deleteuser',{'userid':$a.parent().parent().find('td:eq(0)').text()},function(data){
						if(data == "1"){
							alert('删除成功')
							location.reload()
						}else{
							alert('删除失败')
						}
					})
				}
			})
		})
	</script>
</head>
<body>
	<table border="1" cellspacing="0">
		<tr>
			<th>用户编号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>用户昵称</th>
			<th>用户头像</th>
			<th>用户简介</th>
			<th>用户性别</th>
			<th>是否管理</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${listuser}" var="u">
			<tr>
				<td>${u.userid }</td>
				<td>${u.username }</td>
				<td>${u.password }</td>
				<td>${u.nickname }</td>
				<td><img src="upload/${u.headimg }"></td>
				<td><p class="cont">${u.remark }</p></td>
				<td>${u.sex }</td>
				<td>
					<c:if test="${u.admin == 1 }">
						是
					</c:if>
					<c:if test="${u.admin == 0 }">
						否
					</c:if>
				</td>
				<td><a href="register.jsp?username=${u.username }">编辑</a> <a href="" class="delete">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
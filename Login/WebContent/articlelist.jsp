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
			min-width: 1000px;
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
		.ps{
			width: 300px;
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
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
				var a = confirm("确定要删除文章【"+$a.parent().parent().find('td:eq(1)').text()+"】吗？")
				if(a){
					$.post('deletearticle',{'articleid':$a.parent().parent().find('td:eq(0)').text()},function(data){
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
			<th>文章编号</th>
			<th>文章标题</th>
			<th>发帖用户</th>
			<th>文章概要</th>
			<th>发布时间</th>
			<th>文章图片</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${articlelist }" var="a">
			<tr>
				<td>${a.articleid }</td>
				<td>${a.title }</td>
				<td>${a.authour }</td>
				<td><p class="ps">${a.gen }</p></td>
				<td>${a.publishtime }</td>
				<td><img src="upload/${a.image }" alt=""></td>
				<td><a href="addarticle.jsp?articleid=${a.articleid }">编辑</a> <a href="" class="delete">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
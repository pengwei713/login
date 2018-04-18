<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册网页</title>
	<style type="text/css">
		#register{
			position: absolute;
			width: 100%;
			left: 0;
			top: 0;
			background: #343434;
		}
		#main{
			width:460px;
			padding: 20px;
			margin: auto;
			background: #222;
			border-radius: 10px;
			margin-top:100px;
			box-shadow: 1px 1px 1px 1px #221b1b,-1px -1px 1px 1px #544d4d;
			color: #ccc;
		}
		#main h1{
			color: #ccc;
		}
		#main table{
			margin: auto;
			margin-top: 20px;
		}
		#main table tr td:nth-child(1){
			height: 25px;
			text-align: right;
		}
		#main table input{
			height: 20px;
			outline: none;
			border:none;
			background: #6c6c6c;
			color: #fff;
			font-weight: bold;
		}
		#main table textarea{
			background: #6c6c6c;
			outline: none;
			border:none;
			height: 120px;
			color: #fff;
			font-weight: bold;
		}
		#main table tr .tdleft{
			text-align: left;
		}
		#main table input[type='submit']{
			cursor: not-allowed;
		}
		table tr td:nth-child(2) span{
			display: none;
		}
		input[type='file']{
			display: none;
		}
		#uploa{
			display: inline-block;
			cursor: pointer;
			border:1px solid #ccc;
			padding: 5px;
		}
		#uploa img{
			width: 100px;
		}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){//find('span:eq(2)').after
			$('table tr td').append('<span style="display:block;height:20px;"></span>')
			$('#sub').click(function(e){
				e.preventDefault()
				var formFile = new FormData()
				formFile.append('username',$('#username').val())
				formFile.append('password',$('#password').val())
				formFile.append('nickname',$('#nickname').val())
				formFile.append('remark',$('#remark').val())
				formFile.append('sex',$('.sex:[checked="checked"]').val())
				formFile.append('admin',$('.admin:[checked="checked"]').val())
				formFile.append('userid',$('#userid').val())
				try{
					var src = $('#uploa img').attr('src').replace('upload/','')
				}catch(e){
					alert('请上传图片')
					return;
				}
				formFile.append('headimg',src)
				$.ajax({
					url:'adduser',
					data:formFile,
					type:'post',
					processData:false,
					contentType:false,
					success:function(res){
						if(res == '1'){
							alert('注册成功')
						}else if(res == '2'){
							alert('修改成功')
						}else{
							alert(res)
						}
					}
				})
			})
			$('.impc').blur(function(){
				var $a = $(this)
				if($a.val().trim() == ""){
					$a.parent('td').find('span:eq(0)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}else if($a.val().trim().length < parseInt($a.attr('len'))){
					$a.parent('td').find('span:eq(1)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}else{
					$a.parent('td').find('span:eq(2)').css({'color':'green','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','true')
					var flag = true
					for(var i = 0;i < $('.impc').length;i++){
						if($('.impc:eq('+i+')').attr('flag') == 'false'){
							flag = false;
							break;
						}
					}
					if(flag == true){
						$('#sub').removeAttr('disabled').css('cursor','pointer')
					}
				}
			})
			$('#password2').blur(function(){
				var $a = $(this)
				console.log()
				if($a.val().trim() == ""){
					$a.parent('td').find('span:eq(0)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}else if($a.val().trim().length < parseInt($a.attr('len'))){
					$a.parent('td').find('span:eq(1)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}else if($a.val() == $('#password').val()){
					$a.parent('td').find('span:eq(2)').css({'color':'green','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','true')
					var flag = true
					for(var i = 0;i < $('.impc').length;i++){
						if($('.impc:eq('+i+')').attr('flag') == 'false'){
							flag = false;
							break;
						}
					}
					if(flag == true){
						$('#sub').removeAttr('disabled').css('cursor','pointer')
					}
				}else{
					$a.parent('td').find('span:eq(3)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}
			})
			$('#uploa').click(function(){
				$('input[type="file"]').click()
			})
			$('input[type="file"]').change(function(){
				var formFile = new FormData()
				var fileObj = document.getElementById('headimg').files[0]
				formFile.append('headimg',fileObj)
				$.ajax({
					url:'addprcture',
					data:formFile,
					type:'post',
					processData:false,
					contentType:false,
					success:function(res){
						$('#uploa').html('<img src="upload/'+res+'">')
					}
				})
			})
			$('#username').blur(function(){
				var $a = $(this)
				if($a.val().trim() == ""){
					$a.parent('td').find('span:eq(0)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}else if($a.val().trim().length < parseInt($a.attr('len'))){
					$a.parent('td').find('span:eq(1)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
					$a.attr('flag','false')
					$('#sub').attr('disabled','disabled').css('cursor','not-allowed')
				}else{
					$.post('userexists',{'username':$a.val(),'userid':$('#userid').val()},function(dat){
						if(dat == '1'){
							$a.parent('td').find('span:eq(2)').css({'color':'green','display':'block'}).siblings('span').css('display','none')
							$a.attr('flag','true')
							var flag = true
							for(var i = 0;i < $('.impc').length;i++){
								if($('.impc:eq('+i+')').attr('flag') == 'false'){
									flag = false;
									break;
								}
							}
							if(flag == true){
								$('#sub').removeAttr('disabled').css('cursor','pointer')
							}
						}else{
							$a.parent('td').find('span:eq(3)').css({'color':'red','display':'block'}).siblings('span').css('display','none')
						}
					})
				}
			})
		})
	</script>
</head>
<body>
	<div id="register">
		<div id="main">
			<c:if test="${useredit.userid == null}">
			<h1>欢迎注册博客</h1>
			</c:if>
			<c:if test="${useredit.userid != null}">
			<h1>修改用户信息</h1>
			</c:if>
			<form action="adduser" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>用户名：</td>
						<td>
							<input type="hidden" value="${useredit.userid }" name="userid" id="userid">
							<input type="text" name="username" value="${useredit.username }" id="username" len="3" flag="false">
							<span>用户名不能为空</span>
							<span>用户名至少3个字符</span>
							<span>用户名可用</span>
							<span>用户名已存在</span>
						</td>
					</tr>
					<tr>
						<td>登录密码：</td>
						<td>
							<input type="password" value="${useredit.password }" name="password" id="password" class="impc" len="5" flag="false">
							<span>密码不能为空</span>
							<span>密码至少5个字符</span>
							<span>密码可用</span>
						</td>
					</tr>
					<tr>
						<td>重复密码：</td>
						<td>
							<input type="password" value="${useredit.password }" id="password2" len="5" flag="false">
							<span>密码不能为空</span>
							<span>密码至少5个字符</span>
							<span>密码重复正确</span>
							<span>密码重复错误</span>
						</td>
					</tr>
					<tr>
						<td>昵称：</td>
						<td>
							<input type="text" value="${useredit.nickname }" name="nickname" id="nickname" class="impc" len="3" flag="false">
							<span>昵称不能为空</span>
							<span>昵称至少3个字符</span>
							<span>昵称可用</span>
						</td>
					</tr>
					<tr>
						<td>头像：</td>
						<td>
							<c:if test="${useredit == null}">
							<b id="uploa">上传图片</b>
							</c:if>
							<c:if test="${useredit != null}">
							<b id="uploa"><img src="upload/${useredit.headimg}"></b>
							</c:if>
							<input type="file" accept="image/*" name="headimg" id="headimg">
							<span>头像不能为空</span>
							<span></span>
							<span>头像可用</span>
						</td>
					</tr>
					<tr>
						<td>简介：</td>
						<td>
							<textarea cols="30" rows="10" name="remark" id="remark" class="impc" len="3" flag="false">${useredit.remark}</textarea>
							<span>简介不能为空</span>
							<span>简介至少3个字符</span>
							<span>简介可用</span>
						</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td><input type="radio" name="sex" value="男" checked="checked" class="sex">男<input type="radio" name="sex" value="女" class="sex">女</td>
					</tr>
					<tr>
						<td>超级管理：</td>
						<td><input type="radio" name="admin" value="1" checked="checked" class="admin">是<input type="radio" name="admin" value="0" class="admin" checked="checked">否</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left">
						<c:if test="${useredit == null }">
							<input type="submit" value="注册" id="sub" disabled="disabled">
						</c:if>
						<c:if test="${useredit != null }">
							<input type="submit" value="修改" id="sub" disabled="disabled">
						</c:if>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<c:if test="${useredit != null }">
	<script type="text/javascript">
		$('.impc').attr('flag','true')
		$('#sub').css('cursor','pointer').removeAttr('disabled')
		$('.sex[value="${useredit.sex}"]').attr('checked','checked')
		$('.admin[value="${useredit.admin}"]').attr('checked','checked')
	</script>
</c:if>
</html>
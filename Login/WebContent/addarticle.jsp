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
			height: 100%;
			left: 0;
			top: 0;
			background: #343434;
		}
		#main{
			width: 460px;
			padding: 20px;
			margin: auto;
			margin-top:100px;
			background: #222;
			border-radius: 10px;
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
			width: 250px;
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
			width: 80px;
			cursor: not-allowed;
		}
		table tr td:nth-child(2) span{
			display: none;
		}
		textarea{
			overflow: hidden;
			width: 250px;
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
		$(function(){
			$('table tr td').find('span:eq(2)').after('<span style="display:block;height:19px;"></span>')
			$('#sub').click(function(e){
				e.preventDefault()
				var formFile = new FormData()
				var fileObj = document.getElementById('image').files[0]
				formFile.append('title',$('#title').val())
				formFile.append('gen',$('#gen').val())
				formFile.append('context',$('#content').val())
				formFile.append('articleid',$('#articleid').val())
				formFile.append('userid',$('#userid').val())
				formFile.append('authour',$('#authour').val())
				try{
					var src = $('#uploa img').attr('src').replace('upload/','')
				}catch(e){
					alert('请上传图片')
					return;
				}
				formFile.append('image',src)
				$.ajax({
					url:'addarticle',
					data:formFile,
					type:'post',
					processData:false,
					contentType:false,
					success:function(res){
						alert(res)
					}
				})
			})
			$('.impc').blur(function(){
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
			$('#uploa').click(function(){
				$('input[type="file"]').click()
			})
			$('input[type="file"]').change(function(){
				var formFile = new FormData()
				var fileObj = document.getElementById('image').files[0]
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
		})
	</script>
</head>
<body>
	<div id="register">
		<div id="main">
			<c:if test="${articleedit == null}">
			<h1>增加一篇文章</h1>
			</c:if>
			<c:if test="${articleedit != null}">
			<h1>修改文章</h1>
			</c:if>
			<form action="addarticle" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>文章标题：</td>
						<td>
							<input type="hidden" value="${articleedit.articleid }" name="articleid" id="articleid">
							<input type="hidden" value="${articleedit.userid }" name="userid" id="userid">
							<input type="hidden" value="${articleedit.authour }" name="authour" id="authour">
							<input type="text" name="title" value="${articleedit.title }" id="title" class="impc" len="3" flag="false">
							<span>文章标题不能为空</span>
							<span>文章标题至少3个字符</span>
							<span>文章标题可用</span>
						</td>
					</tr>
					<tr>
						<td>文章概要：</td>
						<td>
							<textarea name="gen" cols="30" rows="10" id="gen" class="impc" len="3" flag="false">${articleedit.gen }</textarea>
							<span>文章概要不能为空</span>
							<span>文章概要至少3个字符</span>
							<span>文章概要可用</span>
						</td>
					</tr>
					<tr>
						<td>文章内容：</td>
						<td>
							<textarea name="context" cols="30" rows="10" id="content" class="impc" len="3" flag="false">${articleedit.context }</textarea>
							<span>文章内容不能为空</span>
							<span>文章内容至少3个字符</span>
							<span>文章内容可用</span>
						</td>
					</tr>
					<tr>
						<td>文章缩略图：</td>
						<td>
							<c:if test="${articleedit == null}">
							<b id="uploa">上传图片</b>
							</c:if>
							<c:if test="${articleedit != null}">
							<b id="uploa"><img src="upload/${articleedit.image}"></b>
							</c:if>
							<input type="file" accept="image/*" name="image" id="image">
							<span>文章缩略图不能为空</span>
							<span></span>
							<span>文章缩略图可用</span>
						</td>
					</tr>
					<tr>
						<c:if test="${articleedit == null }">
							<td colspan="2" style="text-align: left"><input type="submit" id="sub" value="确定添加" disabled="disabled"></td>
						</c:if>
						<c:if test="${articleedit != null }">
							<td colspan="2" style="text-align: left"><input type="submit" id="sub" value="确定修改" disabled="disabled"></td>
						</c:if>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<c:if test="${articleedit != null }">
	<script type="text/javascript">
		$('.impc').attr('flag','true')
		$('#sub').css('cursor','pointer').removeAttr('disabled')
	</script>
</c:if>
</html>
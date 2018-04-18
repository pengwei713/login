$(function(){
var log = $('#log').html()
	$('#subm').click(function(e){
		e.preventDefault()
		var $a = $(this)
		if($('#usernam').val().trim() == "" || $('#passwor').val().trim() == ""){
			$a.next('b').html('用户名或密码不能为空').css({'font-size':'12px','color':'red'})
		}else{
			$.post('userlogin',{'username':$('#usernam').val(),'password':$('#passwor').val()},function(data){
				if(data == "1"){
					$a.next('b').html("用户不存在").css({'font-size':'12px','color':'red'})
				}else if(data == "2"){
					$a.next('b').html("密码不正确").css({'font-size':'12px','color':'red'})
				}else{
					$a.next('b').html("登陆成功").css({'font-size':'12px','color':'green'})
					var obj = JSON.parse(data)
					setTimeout(function(){
						$('#log').fadeOut(600,function(){
							$('#login').html('<div id="inf"><span><img src="upload/'+obj.headimg
									+'" alt=""></span><ul id="loul"><li>昵称：'+obj.nickname
									+'</li><li>性别：'+obj.sex
									+'</li><li>简介：'+obj.remark+'</li><li><a href="javascript:;" id="logout">注销</a></li></ul></div>')
							$('#logout').click(function(){
								$.post('logout',function(da){
									if(da == "1"){
										$('#inf').fadeOut(300,function(){
											$('#login').html('<div id="log">'+log+'</div>')
											$.getScript('js/login.js')
										})
									}
								})
							})
						})
					}, 500)
				}
			})
		}
	})
	$.post('getuser',function(data){
		if(data == "0"){
			
		}else{
			var obj = JSON.parse(data)
			$('#log').fadeOut(100,function(){
				$('#login').html('<div id="inf"><span><img src="upload/'+obj.headimg
						+'" alt=""></span><ul id="loul"><li>昵称：'+obj.nickname
						+'</li><li>性别：'+obj.sex
						+'</li><li>简介：'+obj.remark+'</li><li><a href="javascript:;" id="logout">注销</a></li></ul></div>')
				$('#logout').click(function(){
					$.post('logout',function(da){
						if(da == "1"){
							$('#inf').fadeOut(300,function(){
								$('#login').html('<div id="log">'+log+'</div>')
								$.getScript('js/login.js')
							})
						}
					})
				})
			})
		}
	})
	$('#rega').click(function(){
		$('#register').fadeIn()
		$(document).scroll(function(){
			$('#register').css('top',$(this).scrollTop()+"px")
		})
		$('#register').click(function(){
			$(this).fadeOut()
		})
	})
	$('#main').click(function(e){
		e.stopPropagation()
	})
	$('#main table tr td').append('<span style="display:block;height:20px;"></span>')
	$('#sub').click(function(e){
		e.preventDefault()
		var formFile = new FormData()
		formFile.append('username',$('#username').val())
		formFile.append('password',$('#password').val())
		formFile.append('nickname',$('#nickname').val())
		formFile.append('remark',$('#remark').val())
		formFile.append('sex',$('.sex:[checked="checked"]').val())
		formFile.append('admin',$('.admin:[checked="checked"]').val())
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
					$("#sub").next('b').css('color','green').html('注册成功')
					$('#log').fadeOut(500,function(){
						location.reload()
					})
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
			$.post('userexists',{'username':$a.val()},function(dat){
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
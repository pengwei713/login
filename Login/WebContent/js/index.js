$(function(){
	$.getJSON('listarticle',function(data){
		for(var i = 0;i < data.length;i++){
			$('#uid').append('<li><h4>'+data[i].title
					+'</h4><p>发布时间：'+data[i].publishtime
					+' 作者：'+data[i].authour+' 分类 慢生活</p><span class="imd"><img src="upload/'+data[i].image
					+'"></span><span class="con">'+data[i].context+'...</span><p align="right" class="info"><a href="">详细信息</a></p></li>')
			if(i < 4){
				if(i == 0){
					$('#tituid').append('<li><span class="active">0<span>'+(i+1)+'</span></span> '+data[i].title+'</li>')
				}else{
					$('#tituid').append('<li><span>0<span>'+(i+1)+'</span></span> '+data[i].title+'</li>')
				}
				$('#titul').append('<li><span><img src="upload/'+data[i].image+'" alt=""></span><span><p>'+data[i].title+'...</p><a href="">查看详细</a></span></li>')
				$('#titul1').append('<li><span>【<span>慢生活</span>】</span> '+data[i].title+'</li>')
			}
		}
	})
})
layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	//登录按钮事件
	form.on("submit(login)",function(data){
		$.ajax({
    		url:'user.do?login',
    		type:'POST',
    		dataType:'json',
    		data:data.field,
    		success:function(d){
    			if(d.success){
    				layer.msg('登陆成功，正在跳转......', { icon: 6 });
    	        	 layer.closeAll('page');
    	             setTimeout(function () {
    	                 location.href = "portal.do?toIndex";
    	             }, 1000);
    			}else{
    				layer.msg(d.msg, { icon: 5 });
    			}
    		}
    	});
        return false;//阻止表单跳转
	});
})

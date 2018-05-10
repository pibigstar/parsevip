var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;

	//提交添加用户表单
 	form.on("submit(addUser)",function(data){
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            top.layer.close(index);
        	$.ajax({
        		url : "user.do?addUser",
        		type : "post",
        		data : data.field,
        		dataType : "json",
        		success : function(d){
        			if(d.success){
        				layer.msg(d.msg,{ icon: 6 });
        				layer.closeAll("iframe");
        		 		//刷新父页面
        		 		parent.location.reload();
        			}else{
        				top.layer.msg(d.msg,{ icon: 5 });
        				layer.closeAll("iframe");
        		 		//刷新父页面
        		 		parent.location.reload();
        			}
        		}
        	});
        },500);
 		return false;
 	})
})

//格式化时间
function formatTime(_time){
    var year = _time.getFullYear();
    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}

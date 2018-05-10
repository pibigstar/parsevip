layui.config({
	base : "js/"
}).use(['form','layer','jquery','layedit','laydate'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		layedit = layui.layedit,
		laydate = layui.laydate,
		$ = layui.jquery;

	//创建一个编辑器
 	var editIndex = layedit.build('books_content');
 	form.on("submit(addBooks)",function(data){
 		//弹出loading
 		console.log(data.field.author);
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
        	top.layer.close(index);
    	 	$.ajax({
    	 		url:'book.do?addBook',
    	 		type:'POST',
    	 		data:{name:data.field.name,
    	 			author:data.field.author,
    	 			tips:data.field.tips,
    	 			state:data.field.state,
    	 			bookDesc:data.field.bookDesc,
    	 			createTime:data.field.createTime,
    	 			lookPer:data.field.lookPer},
    	 		dataType:'json',
    	 		success:function(d){
    	 			if(d.success){
    	 				top.layer.msg("文章添加成功！");
    	 				layer.closeAll("iframe");
    	 				//刷新父页面
    	 		 		parent.location.reload();
    	 			}else{
    	 				top.layer.msg("文章添加失败！");
    	 				layer.closeAll("iframe");
    	 				//刷新父页面
    	 		 		parent.location.reload();
    	 			}
    	 		}
    	 	});
        },2000);
 		return false;
 	})
})

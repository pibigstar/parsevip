layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

 	var systemParameter;
 	form.on("submit(systemParameter)",function(data){
 		$.ajax({
			url : "sysInfo.do?updateInfo",
			type : "POST",
			data:data.field,
			dataType : "json",
			success : function(d){
				if(d.code==0){
					//弹出loading
			 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			        setTimeout(function(){
			            layer.close(index);
						layer.msg("系统基本参数修改成功！");
			        },500);
				}else{
					layer.msg(d.msg,{icon:5});
				}
			}
		})
 		return false;
 	})

 	//加载默认数据
 	if(window.sessionStorage.getItem("systemParameter")){
 		var data = JSON.parse(window.sessionStorage.getItem("systemParameter"));
 		fillData(data);
 	}else{
 		$.ajax({
			url : "sysInfo.do?getInfo",
			type : "get",
			dataType : "json",
			success : function(d){
				if(d.code==0){
					fillData(data);
				}else{
					layer.msg(d.msg,{icon:5});
				}
			}
		})
 	}
 	//填充数据方法
 	function fillData(data){
 		$(".version").val(data.version);      //当前版本
		$(".author").val(data.author);        //开发作者
		$(".homePage").val(data.homePage);    //网站首页
		$(".server").val(data.server);        //服务器环境
		$(".dataBase").val(data.dataBase);    //数据库版本
		$(".maxUpload").val(data.maxUpload);  //最大上传限制
		$(".userRights").val(data.userRights);//当前用户权限
		$(".cmsName").val(data.cmsName);      //模版名称
		$(".description").val(data.description);//站点描述
		$(".powerby").val(data.powerby);      //版权信息
		$(".record").val(data.record);      //网站备案号
		$(".keywords").val(data.keywords);    //默认关键字
 	}
 	
})

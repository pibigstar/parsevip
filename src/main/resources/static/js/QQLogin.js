   //调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
   QC.Login({
       //btnId：插入按钮的节点id，必选
       btnId:"qqLoginBtn",    
       //用户需要确认的scope授权项，可选，默认all
       scope:"all",
       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
       size: "B_M"
   }, function(reqData, opts){//登录成功
       //根据返回数据，更换按钮显示状态方法
       var dom = document.getElementById(opts['btnId']),
       _logoutTemplate=[
            //头像
            '<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img id="qqimg" src="{figureurl}" class="{size_key}"/><span class="caret"></span></a><ul class="dropdown-menu">',
            //昵称
            '<li><a href="#">{nickname}</a></li>',
			' <li><a href="#">邮箱</a></li>',
			' <li role="separator" class="divider"></li>',
            //退出
            ' <li><a href="javascript:QC.Login.signOut();">注销</a></li>',
			'</ul></li>'
       ].join("");//join("")将数组连接成一个字符串
       dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
           nickname : QC.String.escHTML(reqData.nickname), //做xss过滤
           figureurl : reqData.figureurl
       }));
   }, function(opts){//注销成功
         alert('QQ登录 注销成功');
   }
);

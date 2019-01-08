# VIP资源解析

## 1 使用的技术

### 1.1 SpringBoot方面

- [x] **多环境开发配置**
- [x] **定时执行任务**
- [x] **执行异步任务**
- [x] **Shiro权限管理**
- [x] **Swagger生成文档**
- [x] **整合mybatis**
- [x] **邮箱发送功能**

### 1.2 其他方面

- [x] **QQ登录**
- [x] **xss过滤**
- [x] **统一异常处理**
- [x] **自定义拦截器**
- [x] **面向切面的日志打印**

### 1.3 工具类
- [x] **PDF添加水印**
- [x] **二维码生成**
- [x] **Excel操作**
- [x] **CSV操作**
- [x] **绘制海报**
- [x] **网页截图**
- [x] **token加密**

## 2 VIP资源解析部分

### 2.1 酷狗音乐下载地址解析
1. 拿到歌曲的hash值
2. 根据hash值算出md5值
3. 将hash值和md5值发送给后端接口拿到下载地址

### 2.2 QQ音乐下载地址解析
1. 根据歌曲的URL（音乐馆界面）拿到歌曲的songmid
2. 根据songmid拼接出filename
3. 计算出guid的值
4. 根据songmid、filename、guid的值请求接口拿到歌曲的vkey
5. 根据filename、vkey、guid拼接出真实地址

### 2.3 腾讯视频真实地址解析
1. 根据视频地址通过正则拿到视频的vid
2. 构造参数拿到视频的url_prefix、streamID、filename值
3. 根据第二步拿到的值再次构造参数请求接口拿到视频key值
4. 根据视频的url_prefix、filename、key值拼接出视频的真实地址

### 2.4 人人视频真实地址解析
1. 根据视频地址的URL拿到视频的id
2. 设置请求头header{clientVersion，clientType}
2. 根据视频的id和请求头请求接口拿到视频播放的真实地址

## 3 交流&&提问

- 留言：[https://github.com/pibigstar/parsevip/issues](https://github.com/pibigstar/parsevip/issues)

- QQ群：[752146272](https://shang.qq.com/wpa/qunwpa?idkey=875408aae56499d92ddcdda3966fa7c01e1d3b587b038d335917df7d41893170)

- 官方网站：[http://mxspvip.cn](http://mxspvip.cn)

## 4 关注我

关注我微信公众号，每天获取最新Java干货

![](https://github.com/pibigstar/parsevip/blob/master/pibigstar.png)

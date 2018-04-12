# VIP资源解析

### 酷狗音乐下载地址解析
1. 拿到歌曲的hash值
2. 根据hash值算出md5值
3. 将hash值和md5值发送给后端接口拿到下载地址

### QQ音乐下载地址解析
1. 根据歌曲的URL（音乐馆界面）拿到歌曲的songmid
2. 根据songmid拼接出filename
3. 计算出guid的值
4. 根据songmid、filename、guid的值请求接口拿到歌曲的vkey
5. 根据filename、vkey、guid拼接出真实地址


### 腾讯视频真实地址解析
1. 根据视频地址通过正则拿到视频的vid
2. 构造参数拿到视频的url_prefix、streamID、filename值
3. 根据第二步拿到的值再次构造参数请求接口拿到视频key值
4. 根据视频的url_prefix、filename、key值拼接出视频的真实地址

### 人人视频真实地址解析
1. 根据视频地址的URL拿到视频的id
2. 设置请求头header{clientVersion，clientType}
2. 根据视频的id和请求头请求接口拿到视频播放的真实地址


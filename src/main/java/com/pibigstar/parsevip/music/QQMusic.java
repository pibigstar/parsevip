package com.pibigstar.parsevip.music;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
/**
 * QQ音乐下载地址解析
 * @author pibigstar
 *
 */
public class QQMusic {
	
//	public static void main(String[] args) throws Exception {
//		/**
//		 * https://y.qq.com/n/yqq/song/003vUjJp3QwFcd.html
//		 * 歌曲的URL
//		 */
//		String url = "https://y.qq.com/n/yqq/song/003vUjJp3QwFcd.html";
//		//parse(url);
//		//getUrls("最后的最后"); 根据名字查询url还未完成
//	}

	public static String parse(String url){
		String songmid = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
		String  filename = "C400" + songmid + ".m4a";
		String guid =Math.abs((long)(Math.random() * 2147483647) * (int)(System.currentTimeMillis() * 1000) % 10000000000l)+"";
		
		Map<String, String> data = new HashMap<>();
		data.put("format", "json");
		data.put("cid", "205361747");
		data.put("outCharset", "utf8");
		data.put("uin", "0");
		data.put("songmid", songmid);
		data.put("filename", filename);
		data.put("guid", guid);
		
		
		String audioUrl = "";
		try {
			String vkeyContent = Jsoup.connect("https://c.y.qq.com/base/fcgi-bin/fcg_music_express_mobile3.fcg").ignoreContentType(true).data(data).get().getElementsByTag("body").text();
			JSONObject json = new JSONObject(vkeyContent);
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonData = (JSONObject) json.get("data");
			JSONObject jsonVkey = (JSONObject) jsonData.getJSONArray("items").get(0);
			String vkey = jsonVkey.get("vkey").toString();
			
			audioUrl = "http://dl.stream.qqmusic.qq.com/"+filename+"?vkey="+vkey+"&guid="+guid+"&uin=0&fromtag=66";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return audioUrl;
	}
	
	private static String getUrls(String name) {
		String enName = "";
		try {
			enName = URLEncoder.encode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "https://y.qq.com/portal/search.html?page=1&searchid=1&remoteplace=txt.yqq.top&t=song&w=最后的最后";
		try {
			String string = Jsoup.connect(url).get().toString();
			System.out.println(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

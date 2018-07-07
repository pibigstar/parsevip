package com.pibigstar.parsevip.parsevip.video;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

/**
 * 人人视频解析
 * @author pibigstar
 *
 */
public class Renren {
	public static void main(String[] args) throws Exception {
		String url= "http://rr.tv/#/video/172278";
		parse(url);
	}

	public static String parse(String url){
		String videoId = url.substring(url.lastIndexOf("/")+1);
		Map<String, String> data = new HashMap<>();
		data.put("videoId", videoId);
		Map<String, String> header = new HashMap<>();
		header.put("clientVersion", "0.1.0");
		header.put("clientType", "web");
		String playLink = "";
		try {
			String bodyContent = Jsoup.connect("http://api.rr.tv/v3plus/video/getVideoPlayLinkByVideoId").headers(header).data(data).ignoreContentType(true).post().getElementsByTag("body").text();
			JSONObject jsonObject = new JSONObject(bodyContent);
			JSONObject jsonData = (JSONObject) jsonObject.get("data");
			playLink = jsonData.get("playLink").toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playLink;
	}

}

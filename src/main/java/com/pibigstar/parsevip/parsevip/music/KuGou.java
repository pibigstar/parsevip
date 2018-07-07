package com.pibigstar.parsevip.parsevip.music;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pibigstar.parsevip.parsevip.bean.Song;
/**
 * 酷狗音乐下载
 * @author pibigstar
 *
 */
public class KuGou {
	
//	public static void main(String[] args) {
//		try {
//			Scanner scanner = new Scanner(System.in);
//			String name = new String(scanner.nextLine().getBytes("utf-8"),"gbk");
//			String urlName = URLEncoder.encode(name, "gbk");
//			scanner.close();
//			parse(urlName);
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		} 
//	}
	
	/**
	 * 解析歌曲数据
	 * @param name
	 * @return 
	 * @throws JSONException 
	 */
	public static List<Song> parse(String name){
		List<Song> songs = new ArrayList<>();
		Document document = null;
		try {
			document = Jsoup.connect("http://mobilecdn.kugou.com/api/v3/search/song?format=json&keyword="+ name +"&page=1&pagesize=10").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String allData = document.select("body").text();
		try {
			JSONObject json = new JSONObject(allData);
			JSONObject data = (JSONObject) json.get("data");
			JSONArray infos =  (JSONArray) data.get("info");
			for (int i = infos.length()-1; i > 0; i--) {
				JSONObject info = (JSONObject)infos.get(i);
				String songname = (String) info.get("songname");
				String hash = (String) info.get("hash");
				String singername = (String) info.get("singername");
				Song song = new Song();
				song.setSongname(songname);
				song.setSingername(singername);
				song.setHash(hash);
				String download = getKey(hash);;
				song.setDownload(download);
				songs.add(song);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return songs;
	}
	
	
	/**
	 * 返回下载地址
	 * @param hash
	 * @return
	 * @throws JSONException 
	 */
	private static String getKey(String hash) throws JSONException {
		String md5 = DigestUtils.md5Hex(hash+"kgcloud");
		String url = "http://trackercdn.kugou.com/i/?cmd=4&hash="+ hash +"&key="+ md5 +"&pid=1&forceDown=0&vip=1";
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			String data = document.select("body").text();
			JSONObject json = new JSONObject(data);
			return json.getString("url");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

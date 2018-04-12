package com.pibigstar.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.parsevip.bean.Song;
import com.pibigstar.parsevip.music.KuGou;
import com.pibigstar.parsevip.music.QQMusic;
import com.pibigstar.util.JsonResult;

@RestController
@RequestMapping("music")
public class MusicController {
	private Logger log = LoggerFactory.getLogger(MusicController.class);
	
	@RequestMapping("seach")
	public JsonResult seach(String type,String music) {
		
		log.info("type:"+type+" music:"+music);
		int key = Integer.parseInt(type);
		List<Song> songs = new ArrayList<>();
		String realUrl = "";
		switch (key) {
		case 0:
			songs = KuGou.parse(music);
			if (songs.size()>10&&songs!=null) {
				songs = songs.subList(0, 10);
			}
			break;
		case 1:
			realUrl = QQMusic.parse(music);
			break;
		default:
			break;
		}
		return JsonResult.success(realUrl, "OK!");
	}
	
}

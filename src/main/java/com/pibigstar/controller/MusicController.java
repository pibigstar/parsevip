package com.pibigstar.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.parsevip.bean.Song;
import com.pibigstar.parsevip.music.KuGou;
import com.pibigstar.util.JsonResult;

@RestController
@RequestMapping("music")
public class MusicController {
	private Logger log = LoggerFactory.getLogger(MusicController.class);
	
	@RequestMapping("seach")
	public JsonResult seach(String musictype,String music) {
		
		log.info("type:"+musictype+"music:"+music);
		int key = Integer.parseInt(musictype);
		List<Song> songs = null;
		switch (key) {
		case 0:
			songs = KuGou.parse(music);
			break;
		case 1:
			break;
		default:
			break;
		}
		return JsonResult.success(songs, "OK!");
	}

}

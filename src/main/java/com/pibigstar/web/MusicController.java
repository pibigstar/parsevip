package com.pibigstar.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.common.aop.MyLogger;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.parsevip.bean.Song;
import com.pibigstar.parsevip.music.KuGou;
import com.pibigstar.parsevip.music.QQMusic;

/**
 * 音乐
 * @author pibigstar
 *
 */

@RestController
@RequestMapping("music")
public class MusicController extends BaseController{
	
	@MyLogger(description = "音乐解析")
	@RequestMapping(value = "seach",method = RequestMethod.POST)
	public MyResponse seach(String type,String music) throws Exception {
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
			return success("OK!", songs);
		case 1:
			realUrl = QQMusic.parse(music);
			throw new Exception("测试异常抛出");
			//return success("OK!", realUrl);
		default:
			throw new Exception();
		}
	}
	
}

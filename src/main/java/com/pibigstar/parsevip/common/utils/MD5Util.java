package com.pibigstar.parsevip.common.utils;

import org.springframework.util.DigestUtils;
import com.pibigstar.parsevip.common.Constant;

/**
 * MD5工具类
 * @author pibigstar
 *
 */
public class MD5Util {
	//盐，用于混交md5
	private static final String slat = Constant.PASSWORD_KEY;
	/**
	 * 生成md5
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
		String base = str +"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}

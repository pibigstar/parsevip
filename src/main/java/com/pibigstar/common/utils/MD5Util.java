package com.pibigstar.common.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.util.DigestUtils;

import com.pibigstar.common.Constant;

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
	 * @param seckillId
	 * @return
	 */
	public static String getMD5(String str) {
		String base = str +"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
}

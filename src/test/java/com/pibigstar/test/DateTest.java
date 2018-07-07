package com.pibigstar.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.thymeleaf.util.DateUtils;

import com.pibigstar.parsevip.common.utils.MD5Util;
import com.pibigstar.parsevip.common.utils.MyMD5Util;

public class DateTest {
	@Test
	public void getNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
	}
	@Test
	public void md5Test() {
		System.out.println(MD5Util.getMD5("admin"));
	}

}

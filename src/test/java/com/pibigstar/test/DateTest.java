package com.pibigstar.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.thymeleaf.util.DateUtils;

public class DateTest {
	@Test
	public void getNow() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(new Date()));
	}

}

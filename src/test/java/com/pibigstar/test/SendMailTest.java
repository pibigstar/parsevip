package com.pibigstar.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pibigstar.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMailTest {
	
	@Autowired
	private MailService mailService;
	/**
	 * 发送普通邮件
	 */
	@Test
	public void testSendSimpleMail() {
		mailService.sendSimpleMail("leikewei123@163.com", "测试简单邮件", "这是一个测试邮件");
	}
	/**
	 * 发送html邮件
	 */
	@Test
	public void testSendHtmlMail() {
		String content = "<html><body><h1>这是一个测试邮件</h1></body></html>";
		mailService.sendHtmlMail("leikewei123@163.com", "测试HTML邮件", content);
	}
	/**
	 * 发送带附件的邮件
	 */
	@Test
	public void testSendAttachMail() {
		mailService.sendAttachmentsMail("leikewei123@163.com", "测试", "这是一个带附件的邮件","F:\\学习计划.txt");
	}

}

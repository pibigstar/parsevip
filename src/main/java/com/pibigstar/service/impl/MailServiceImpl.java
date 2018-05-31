package com.pibigstar.service.impl;


import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pibigstar.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.sender}")
	private String sender;

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("简单邮件已发送！");
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			//true 表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message,true);

			helper.setFrom(sender);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);

			mailSender.send(message);
			logger.info("html邮件已发送！");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			//true 表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message,true);

			helper.setFrom(sender);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);
			
			//添加附件
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = file.getFilename();
			helper.addAttachment(fileName, file);

			mailSender.send(message);
			logger.info("带附件的邮件已发送！");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}

package com.pibigstar.parsevip.service;

public interface MailService {
	/**
	 * 发送一个简单的文字邮件
	 * @param to 发给谁
	 * @param subject 主题
	 * @param content 内容
	 */
	public void sendSimpleMail(String to,String subject,String content);
	
	/**
	 * 发送html格式邮件
	 * @param to 发给谁
	 * @param subject 主题
	 * @param content 内容
	 */
	public void sendHtmlMail(String to,String subject,String content);
	
	/**
	 * 发送带附件的邮件
	 * @param to 发给谁
	 * @param subject 主题
	 * @param content 内容
	 * @param filePath 附件路径
	 */
	public void sendAttachmentsMail(String to,String subject,String content,String filePath);
}

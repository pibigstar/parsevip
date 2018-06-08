package com.pibigstar.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pibigstar.service.MailService;

@Component
@EnableScheduling
public class SendEmailTask {
	
	@Autowired
	private MailService mailService;
	
	 /**
	  * 每隔一分钟执行一次 
	  */
    //@Scheduled(cron="1 * * * * ?")    
	//private void process() {}
    /**
     * 每隔一天执行一次 
     */
    @Scheduled(cron="1 * * * * ?")    
    private void sendEmail() {
    	mailService.sendHtmlMail("pibigstar@163.com", "我，秦始皇", "<h1><a href='http://mxspvip.cn'>我，秦始皇，麻溜打钱！</a></h1>");
    }
}

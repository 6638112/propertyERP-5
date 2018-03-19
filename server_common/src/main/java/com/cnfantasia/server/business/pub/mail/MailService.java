package com.cnfantasia.server.business.pub.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

public class MailService {
	private static Log logger = LogFactory.getLog(MailService.class);
	private static final String MAIL_PROPERTIE_NAME = "JavaMail.properties";
	private static Properties mailPro = new Properties();
	private static Executor executor = Executors.newFixedThreadPool(10);

	static {
		//初始化，读取属性文件的过程
		InputStream in = null;
		try {
			in = MailService.class.getResourceAsStream(MAIL_PROPERTIE_NAME);
			mailPro.load(in);
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			if (logger.isErrorEnabled()) {
				logger.error(e);
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}
			}
		}

	}

	public static boolean sendMail(String subject, String content, String to) {
		return sendMail(subject, content, to, "", "");
	}
	
	public static boolean sendMail(String subject, String content, String to, String cc, String bcc) {
		Mail mail = new Mail();
		//163规定：发件人必须与登录账号一致，否则老是报 550: Invalid User，被坑了好久
		mail.setSender("jiefangqu_service@163.com");
		mail.setSubject("" + subject);
		mail.setBody("" + content);
		
		mail.setRecipientsTo(to);
		mail.setRecipientsCc(cc);
		mail.setRecipientsBcc(bcc);
		
		return new MailService().sendMail(mail);
	}
	
	
	private boolean sendMail(final Mail mail) {
		//创建邮件发送任务
		Runnable task = new Runnable() {
			@Override
			public void run() {
				final String username = mailPro.getProperty(MailProperties.MAIL_SMTP_USER);
				final String password = mailPro.getProperty(MailProperties.MAIL_SMTP_PASSWORD);
				//创建发送邮件的会话
				Session session = Session.getDefaultInstance(mailPro, new Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(username, password);
							}
						});
				
			    try {
			    	//创建邮件消息
			    	MimeMessage msg = new MimeMessage(session);
			    	//设置邮件发送人
					msg.setFrom(new InternetAddress(StringUtils.isEmpty(mail.getSender()) ? mailPro.getProperty(MailProperties.MAIL_SMTP_USER) : mail.getSender()));
					//分别设置邮件的收件人、抄送人和密送人
				    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipientsTo()));
				    msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mail.getRecipientsCc()));
				    msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mail.getRecipientsBcc()));
				    //设置邮件主题
				    msg.setSubject(mail.getSubject());
				    
				    Multipart mp = new MimeMultipart();
				    
				    //创建邮件主体内容
				    MimeBodyPart mbp1 = new MimeBodyPart();
				    mbp1.setText(mail.getBody());
				    mp.addBodyPart(mbp1);
				    
				    if(!CollectionUtils.isEmpty(mail.getAttachments())){
				    	//循环添加邮件附件
				    	MimeBodyPart attach = null;
				    	for(String path : mail.getAttachments()){
				    		attach = new MimeBodyPart();
				    	    try {
				    	    	attach.attachFile(path);
				    	    	mp.addBodyPart(attach);
							} catch (IOException e) {
								if (logger.isErrorEnabled()) {
									logger.error(e);
								}
							}

				    	}
				    }
				    
				    msg.setContent(mp);
				    msg.setSentDate(new Date());
				    
				    //邮件开始发送
				    Transport.send(msg);
				} catch (Exception e) {
					if (logger.isErrorEnabled()) {
						logger.error(e);
					}
				}
			    
				
			}

		};
		//使用Executor框架的线程池执行邮件发送任务
		executor.execute(task);
		return true;
	}
	
	public static void main(String[] args) {
		MailService.sendMail("email title test 报价", "this is a email body 请见附件", "41750812@qq.com,wen_fuqiang@163.com,");
	}
	

}

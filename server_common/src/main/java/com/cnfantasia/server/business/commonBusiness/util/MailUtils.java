package com.cnfantasia.server.business.commonBusiness.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MailUtils{
	private Log logger = LogFactory.getLog(getClass());
	
	private MimeMessage mimeMsg;
	private Session session;
	private Properties props;
	private String username;
	private String password;
	private Multipart mp;

	public MailUtils(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	public void setSmtpHost(String hostName) {
		logger.info("设置系统属性：mail.smtp.host=" + hostName);
		if (props == null) {
			props = System.getProperties();
		}
		props.put("mail.smtp.host", hostName);
	}

	public boolean createMimeMessage() {
		try {
			logger.info("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null);
		} catch (Exception e) {
			logger.info("获取邮件会话错误！" + e);
			return false;
		}
		logger.info("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			logger.info("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/*定义SMTP是否需要验证*/
	public void setNeedAuth(boolean need) {
		logger.info("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/*定义邮件主题*/
	public boolean setSubject(String mailSubject) {
		logger.info("定义邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			System.err.println("定义邮件主题发生错误！");
			return false;
		}
	}

	/*定义邮件正文*/
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailBody, "text/html;charset=GBK");
			mp.addBodyPart(bp);
			return true;
		} catch (Exception e) {
			System.err.println("定义邮件正文时发生错误！" + e);
			return false;
		}
	}

	/*设置发信人*/
	public boolean setFrom(String from) {
		logger.info("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); //发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*定义收信人*/
	public boolean setTo(String to) {
		if (to == null)
			return false;
		logger.info("定义收信人！");
		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*定义抄送人*/
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*发送邮件模块*/
	public boolean sendOut() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			logger.info("邮件发送中....");
			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username, password);
			transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
			logger.info("发送成功！");
			transport.close();
			return true;
		} catch (Exception e) {
			logger.info("邮件失败！" + e);
			return false;
		}
	}

	/*调用sendOut方法完成发送*/
	public static boolean sendAndCc(String smtp, String from, String to, String copyto, String subject, String content, String username, String password) {
		MailUtils theMail = new MailUtils(smtp);
		theMail.setNeedAuth(true); // 验证
		if (!theMail.setSubject(subject))
			return false;
		if (!theMail.setBody(content))
			return false;
		if (!theMail.setTo(to))
			return false;
		if (!theMail.setCopyTo(copyto))
			return false;
		if (!theMail.setFrom(from))
			return false;
		theMail.setNamePass(username, password);
		if (!theMail.sendOut())
			return false;
		return true;
	}

	public static void sendMail(String subject, String content, String to) {
		sendMail(subject, content, to, "");
	}

	public static void sendMail(String subject, String content, String to, String copyto) {
		String smtp = "smtp.163.com";// smtp服务器 
		String from = "jiefangqu_service@163.com";// 邮件显示名称 
		String username = "jiefangqu_service";// 发件人真实的账户名 
		String password = "jfq_service";// 发件人密码 

		MailUtils.sendAndCc(smtp, from, to, copyto, subject, content, username, password);
	}
	
	public static void main(String[] args) {
		sendMail("测试邮件", "这是一封测试邮件，请删除 ", "41750812@qq.com,wen_fuqiang@163.com,"); 
	}

}

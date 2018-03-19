package com.cnfantasia.server.business.pub.mail;

public abstract class MailProperties {
	/**
	 * SMTP服务器
	 */
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	/**
	 * SMTP服务器端口号
	 */
	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	/**
	 * 登录SMTP服务器是否需要通过授权。可选值为true和false
	 */
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	/**
	 * 登录SMTP服务器默认邮箱账号
	 */
	public static final String MAIL_SMTP_USER = "mail.smtp.user";
	/**
	 * 登录SMTP服务器默认邮箱账号对应密码
	 */
	public static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
	/**
	 * 是否打开程序调试。可选值包括true和false
	 */
	public static final String MAIL_DEBUG = "mail.debug";
}

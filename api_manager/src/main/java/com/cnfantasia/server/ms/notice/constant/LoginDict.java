/**   
 * Filename:    LoginDict.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午7:58:04   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.ms.notice.constant;

/**
 * Filename: LoginDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午7:58:04 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class LoginDict {
	/**
	 * 登录类型
	 */
	public static class LoginType {
		/** 手机+密码 */
		public static final Long MOBILE = 0L;
		/** 邮箱+密码 */
		public static final Long MAIL = 1L;
		/** 手机与短信验证码 */
		public static final Long MOBILE_VALICODE = 2L;
		/** 花号+密码 */
		public static final Long HUA_ID = 3L;
		// /**3rd,第三方登录方式*/
		// public static final Long THIRD_TYPES= 4L;
		// /**手动注册账号登录*/
		// public static final Long HANDEL_ACC= 99L;
	}

	/**
	 * 验证码获取方式
	 */
	public static class ValiCodeGetType {
		/** 注册获取 */
		public static final Integer REGIST = 0;
		/** 忘记密码获取 */
		public static final Integer FORGET_PASSWORD = 1;
		/** 登录获取 */
		public static final Integer LOGIN = 2;
		/** 修改密码、绑定新手机 */
		public static final Integer UPDATE_PASSWORD = 3;
		/** 解绑手机 */
		public static final Integer UNBIND_PHONE = 4;
	}

	/**
	 * 第三方注册方式
	 */
	public static class Regist3rd_Type {
		/** QQ注册 */
		public static final Integer QQ = 1;
		/** 淘宝账号 */
		public static final Integer TAOBAO = 2;
		/** 新浪微博 */
		public static final Integer SINA_MICROBLOG = 3;
		/** 腾讯微博 */
		public static final Integer TECENT_MICROBLOG = 4;
		/** 微信登录 */
		public static final Integer WEI_XIN = 5;
	}
	
	/**
	 * 账号类型
	 */
	public static class AccountType {
		/** 手机 */
		public static final Long MOBILE = 0L;
		/** 邮箱 */
		public static final Long MAIL = 1L;
		/** 花号 */
		public static final Long HUA_ID = 2L;
		/** QQ注册 */
		public static final Long QQ = 3L;
		/** 淘宝账号 */
		public static final Long TAOBAO = 4L;
		/** 新浪微博 */
		public static final Long SINA_MICROBLOG = 5L;
		/** 腾讯微博 */
		public static final Long TECENT_MICROBLOG = 6L;
		/** 微信 */
		public static final Long WEI_XIN = 7L;
		// /** 手动注册账号 */
		// public static final Long HANDEL_ACC = 99L;
	}
	
	/**
	 * 常规注册方式
	 */
	public static class RegistComm_Type {
		/** 手机注册 */
		public static final Integer MOBILE = 0;
		/** 邮箱注册 */
		public static final Integer MAIL = 1;
	}

	/**
	 * 区分临时用户或注册用户
	 */
	public static class UserRegistOrTmp {
		/** 注册用户 */
		public static final Integer REGIST_USER = 1;
		/** 临时用户 */
		public static final Integer TMP_USER = 2;
	}
	/**
	 * 忘记密码的找回密码的方式
	 */
	public static class DoForgetPwd_Type{
		/** 手机 */
		public static final Integer MOBILE = 0;
		/** 邮箱 */
		public static final Integer MAIL = 1;
	}
}

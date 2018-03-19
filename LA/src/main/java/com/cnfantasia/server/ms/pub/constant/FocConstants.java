package com.cnfantasia.server.ms.pub.constant;

import java.math.BigInteger;


/**
 * 描述:常量类
 * 
 */
public class FocConstants {
	//TODO ...以下两个字段待重新处理
	/**用户登录时，验证码过滤器保存在session中的用户名key*/
	public static final String Logn_Session_LoginName="sTmp_userName";
	/** 默认错误信息 */
  public static final String DEFAULT_SYS_ERRMESSAGE = "系统错误";
	
  
  
  public static final String REGX_MOBILE = "^[0-9]{11}$";//先，11位数字
  public static final String REGX_HUAID = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{1,}$";//必须包含数字和字母，大于1位的字母数字
  
	public static final String DEFAULT_SYS_ERRCODE = "system.error";
	public static final String DEFAULT_BUSINESS_ERRCODE = "business.error";
	/**默认手机验证码长度*/
	public static final Integer DEFAULT_PHONE_VALICODE_LENGTH=4;
	public static final Integer DEFAULT_PASSWORD_LENGTH=6;
	
	/**t_real_room空房间默认Id*/
	public static final BigInteger ROOM_NULL_ID_REAL = new BigInteger("-1");
	/**分页的相关key值*/
	public static class PageKey{
		public static final String LIST = "list";
		public static final String HAS_NEXT = "hasNext";
		/** parameter参数的起始页号*/
		public static final String PAGE = "page";
		/** parameter参数的每页显示数*/
		public static final String PAGE_NUM = "pageNum";
	}
	/**
	 * 响应结果状态信息
	 */
	public static class ResponseStatus{
		/**成功*/
		public static final String SUCCESS="0000";
		/**校验异常*/
		public static final String VALIDATE_ERR="0001";
		/**系统异常*/
		public static final String SYSTEM_ERR="0002";
		/**登录超时*/
		public static final String LOGIN_TIME_OUT="0003";
		/**业务处理异常*/
		public static final String BUSINESS_FAILED="0004";
		/**通讯异常*/
		public static final String COMMUNICATE_ERR="9***";
	}
	
	
	
  /**交易状态、处理结果,success成功 error失败*/
  public static final String TRANS_RESULT="operRes";//命名与界面js关联,不要随意更改
  public static final String TRAN_STATUS_SUCCESSFUL="success";
  public static final String TRAN_STATUS_FAILED="error";
  /** 错误代码 */
  public static final String ERRCODE = "errCode";
  /** 错误信息 */
  public static final String ERRMESSAGE = "errMessage";

  /** 防止重复提交的token名称 **/
  public static final String TOKEN = "_token";
  
  /**数据字典的统一前缀(用于根据messages.properties得到对应的字典定义)*/
  public static final String DICT_SRC_KEY_PRE="DICT";
  /**拼装结果数据时，对应的字典数据命名统一附加的后缀*/
  public static final String DICT_RES_KEY_TAIL="_DICT";
  
}

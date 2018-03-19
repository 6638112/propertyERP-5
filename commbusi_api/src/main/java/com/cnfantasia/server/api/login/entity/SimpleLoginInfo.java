/**   
* Filename:    SimpleLoginInfo.java   
* @version:    1.0  
* Create at:   2014年5月20日 上午3:25:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月20日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.pub.springSecurity.EncodeImpl;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename:    SimpleLoginInfo.java
 * @version:    1.0.0
 * Create at:   2014年5月20日 上午3:25:11
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月20日       shiyl             1.0             1.0 Version
 */
public class SimpleLoginInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String number;
	private String password;
	private Long loginType;
	private Long accountType;
	/**
	 * 构造方法
	 * @param number 账号
	 * @param password 密码
	 * @param loginType 登录方式
	 * @param accountType 账号类型
	 */
	public SimpleLoginInfo(String number,String password,Long loginType,Long accountType){
		this.number = number;
		this.password = password;
		this.loginType = loginType;
		this.accountType = accountType;
		/*if(loginType!=null&&accountType==null){
			this.accountType = loginType2AccountType(loginType);
		}else if(loginType==null&&accountType!=null){
			this.loginType = accountType2LoginType(accountType);
		}*/
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getLoginType() {
		return loginType;
	}
	public void setLoginType(Long loginType) {
		this.loginType = loginType;
	}
	public Long getAccountType() {
		return accountType;
	}
	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}
	
	/**
	 * 通过账号格式解析出账号类型
	 * @return
	 */
	public static Long getLoginTypeByNumberFormat(String number){
		Long type = null;
		String mobileRegx = LoginConstant.REGX_MOBILE;//先，11位数字
		String huaRegx = LoginConstant.REGX_HUAID;//后，大于1位的字母数字
		if(number.matches(mobileRegx)){type = LoginDict.LoginType.MOBILE;}//syl--upd 2014-6-12 17:53:56
		else if(number.matches(huaRegx)){type = LoginDict.LoginType.HUA_ID;}//syl--upd 2014-6-12 17:53:56
		else{ 
			throw new ValidateRuntimeException("SimpleLoginInfo.getLoginTypeByNumberFormat.unknownFormat.error");
		}
		return type;
	}
	
//	/**
//	 * 登录方式转账号类型
//	 * @param loginType
//	 * @return
//	 */
//	public static Long loginType2AccountType(Long loginType){
//		Long accountType = null;
//		if(DictConstants.LoginType.MOBILE.compareTo(loginType)==0){
//			accountType = DictConstants.AccountType.MOBILE;
//		}else if(DictConstants.LoginType.MOBILE_VALICODE.compareTo(loginType)==0){
//			accountType =DictConstants.AccountType.MOBILE;
//		}else if(DictConstants.LoginType.MAIL.compareTo(loginType)==0){
//			accountType = DictConstants.AccountType.MAIL;
//		}else if(DictConstants.LoginType.HUA_ID.compareTo(loginType)==0){
//			accountType = DictConstants.AccountType.HUA_ID;
//		}else if(DictConstants.LoginType.THIRD_TYPES.compareTo(loginType)==0){
//			accountType = null;//多个，取值为空
//		}else{
//			throw new ValidateRuntimeException("SimpleLoginInfo.loginType2AccountType.unknownLoginType.error");
//		}
//		return accountType;
//	}
	
//	/**
//	 * 账号类型转登录类型
//	 * @param accountType
//	 * @return
//	 */
//	public static Long accountType2LoginType(Long accountType){
//		Long loginType = null;
//		if(DictConstants.AccountType.HUA_ID.compareTo(accountType)==0){
//			loginType = DictConstants.LoginType.HUA_ID;
//		}else if(DictConstants.AccountType.MAIL.compareTo(accountType)==0){
//			loginType = DictConstants.LoginType.MAIL;
//		}else if(DictConstants.AccountType.MOBILE.compareTo(accountType)==0){
//			loginType = null;//多个，取值为空
//		}else if(DictConstants.AccountType.QQ.compareTo(accountType)==0){
//			loginType = DictConstants.LoginType.THIRD_TYPES;
//		}else if(DictConstants.AccountType.SINA_MICROBLOG.compareTo(accountType)==0){
//			loginType = DictConstants.LoginType.THIRD_TYPES;
//		}else if(DictConstants.AccountType.TAOBAO.compareTo(accountType)==0){
//			loginType = DictConstants.LoginType.THIRD_TYPES;
//		}else if(DictConstants.AccountType.TECENT_MICROBLOG.compareTo(accountType)==0){
//			loginType = DictConstants.LoginType.THIRD_TYPES;
//		}else{
//			throw new ValidateRuntimeException("SimpleLoginInfo.accountType2LoginType.unknownAccountType.error");
//		}
//		return loginType;
//	}
	
	public static SimpleLoginInfo parseLoginInfo(HttpServletRequest request){
		SimpleLoginInfo simpleLoginInfo = null;
		//常规登录方式
		String numberParam = request.getParameter("number");
		String passwordParam = request.getParameter("password");
		String loginTypeStrParam = request.getParameter("loginType");
		if(!StringUtils.isEmpty(numberParam)&&!StringUtils.isEmpty(passwordParam)){//优先通过parameter方式确定账号信息
			//passwordParam = new String(Base64.decodeBase64(passwordParam));//前端用Base64编码传过来的， 此处加上解码 added by wenfq 2017-06-29
			Long loginTypeTmp = null;
			if(!StringUtils.isEmpty(loginTypeStrParam)){
				loginTypeTmp=Long.parseLong(loginTypeStrParam);
			}else{//未传入登录方式
				loginTypeTmp = getLoginTypeByNumberFormat(numberParam);//通过账号解析登录方式
			}
			
			//syl-add 2015-4-2 16:55:33 MD5密码加密
			if (LoginDict.LoginType.MOBILE_VALICODE.compareTo(loginTypeTmp) == 0) {// 手机短信验证码登录
				simpleLoginInfo = new SimpleLoginInfo(numberParam, passwordParam, loginTypeTmp, null);
			}else{//密码登录方式 需要加密
				simpleLoginInfo = new SimpleLoginInfo(numberParam, EncodeImpl.doEncodePassword(passwordParam), loginTypeTmp, null);
			}
			
		}else{//通过attribute方式确定账号信息
			String numberAttr =  (String)request.getAttribute(LoginConstant.ATTRIBUTE_NAME_ACCOUNT);
			String passwordAttr = (String)request.getAttribute(LoginConstant.ATTRIBUTE_NAME_PASSWD);
			Long accountType = (Long)request.getAttribute(LoginConstant.ATTRIBUTE_ACCOUNT_TYPE);
			if(accountType==null){
				throw new BusinessRuntimeException("SimpleLoginInfo.parseLoginInfo.accountType.isNull");
//				accountType = getAccountTypeByNumberFormat(numberAttr);
			}
			simpleLoginInfo = new SimpleLoginInfo(numberAttr, passwordAttr, null, accountType);
		}
		return simpleLoginInfo;
	}
	
}

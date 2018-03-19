/**   
* Filename:    LoginConstant.java   
* @version:    1.0  
* Create at:   2014年8月8日 上午7:14:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.constant;


/**
 * Filename:    LoginConstant.java
 * @version:    1.0.0
 * Create at:   2014年8月8日 上午7:14:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月8日       shiyl             1.0             1.0 Version
 */
public class LoginConstant {
  public static final String REGX_MOBILE = "^[0-9]{11}$";//先，11位数字
	//public static final String REGX_HUAID = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]{1,}$";//必须包含数字和字母，大于1位的字母数字
	public static final String REGX_HUAID = "^[0-9]{1,}$";//至少一位数字
	
	/**默认手机验证码长度*/
	public static final Integer DEFAULT_PHONE_VALICODE_LENGTH=4;
	public static final Integer DEFAULT_PASSWORD_LENGTH=6;
	
	
	/**记录忘记密码第一步的验证码验证结果*/
	public static final String Forget_Password_Result = LoginConstant.class.getName()+"Forget_Password_Result";
	/**换绑手机第一步的验证码验证结果*/
	public static final String Change_Phone_Bind_Result = LoginConstant.class.getName()+"Change_Phone_Bind_Result";
	/**修改密码第一步的验证码验证结果*/
	public static final String Change_Password_Result = LoginConstant.class.getName()+"Change_Password_Result";
	/**
	 * 各个短信验证码的存储
	 * validateCodeMap：{验证码类型1：验证码取值1，验证码类型2：验证码取值2},例如{1:"1232",2:"6593",3:"0625"}
	 */
	public static final String SESSION_KEY_VALIDATE_CODE_MAP=LoginConstant.class.getName()+"validateCodeMap";
	
	public static final String ATTRIBUTE_NAME_ACCOUNT= LoginConstant.class.getName()+"account";
	public static final String ATTRIBUTE_NAME_PASSWD= LoginConstant.class.getName()+"password";
	public static final String ATTRIBUTE_ACCOUNT_TYPE= LoginConstant.class.getName()+"accountType";
}

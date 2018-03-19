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
package com.cnfantasia.server.ms.login.entity;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

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
	private String valicode;
	/**
	 * 构造方法
	 * @param number 账号
	 * @param password 密码
	 * @param loginType 登录方式
	 * @param accountType 账号类型
	 */
	public SimpleLoginInfo(String number,String password,String valicode){
		this.number = number;
		this.password = password;
		this.valicode = valicode;
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
	public String getValicode() {
		return valicode;
	}
	public void setValicode(String valicode) {
		this.valicode = valicode;
	}
	public static SimpleLoginInfo parseLoginInfo(HttpServletRequest request){
		SimpleLoginInfo simpleLoginInfo = null;
		//常规登录方式
		String numberParam = request.getParameter("number");
		String passwordParam = request.getParameter("password");
		String valicodeParam = request.getParameter("valicode");
		simpleLoginInfo = new SimpleLoginInfo(numberParam, passwordParam, valicodeParam);
		return simpleLoginInfo;
	}
	
}

/**   
* Filename:    EncodeImpl.java   
* @version:    1.0  
* Create at:   2014年5月19日 上午8:46:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.pub.springSecurity;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.cnfantasia.server.business.pub.springSecurity.IEncode;

/**
 * Filename:    EncodeImpl.java
 * @version:    1.0.0
 * Create at:   2014年5月19日 上午8:46:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月19日       shiyl             1.0             1.0 Version
 */
public class EncodeImpl extends Md5PasswordEncoder implements IEncode{
//	public static void main(String[] args) {//1b71218a047f06ef8b7a76af562c80b9
//			String rawPass = "000000";
//			Md5PasswordEncoder encoderObj =  new Md5PasswordEncoder();
//		  String encoderpwd = encoderObj.encodePassword(getDealPass(rawPass), null);
//		  System.out.println(encoderpwd);
//	}
	
	private static final String start = "7xmksl";
	private static final String end = "t8uykx";
	private static String getDealPass(String rawPass){
		Md5PasswordEncoder encoderObj =  new Md5PasswordEncoder();
	  String encoderpwd = encoderObj.encodePassword(rawPass, null);
		return start+encoderpwd+end;
	}
	public static String doEncodePassword(String rawPass){
		rawPass = getDealPass(rawPass);
		Object saltsrc = null;
	  Md5PasswordEncoder encoderObj =  new Md5PasswordEncoder();
	  String encoderpwd = encoderObj.encodePassword(rawPass, saltsrc);
	  return encoderpwd;
	}
	
  /**
   * 加密密码
   * @param password 明文密码
   * @param saltsrc 盐值
   * @return
   */
  @Override
  public String encodePassword(String rawPass, Object salt){
//    Md5PasswordEncoder encoderObj =  new Md5PasswordEncoder();
//    String encoderpwd = encoderObj.encodePassword(password, saltsrc); 
//    return encoderpwd;
//    return super.encodePassword(rawPass, salt);
  	rawPass = getDealPass(rawPass);
  	return super.encodePassword(rawPass, salt);
  }

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		if(encPass.equals(rawPass)){
			return true;
		}
		return super.isPasswordValid(encPass, rawPass, salt);
	}

}

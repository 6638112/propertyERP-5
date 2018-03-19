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
package com.cnfantasia.server.ms.pub.springSecurity;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

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
    return super.encodePassword(rawPass, salt);
  }

}

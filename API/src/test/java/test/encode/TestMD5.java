/**   
* Filename:    TestMD5.java   
* @version:    1.0  
* Create at:   2015年4月2日 上午7:54:16   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月2日    shiyl      1.0         1.0 Version   
*/
package test.encode;

import com.cnfantasia.server.business.pub.utils.Md5Util;

/**
 * Filename:    TestMD5.java
 * @version:    1.0.0
 * Create at:   2015年4月2日 上午7:54:16
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月2日       shiyl             1.0             1.0 Version
 */
public class TestMD5 {
	public static void main(String[] args) {
		System.out.println(Md5Util.MD5("_2efwffffffffffffffffffffff").length());
	}
}

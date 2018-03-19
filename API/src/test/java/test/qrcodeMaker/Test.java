/**   
* Filename:    Test.java   
* @version:    1.0  
* Create at:   2014年12月18日 上午6:29:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月18日    shiyl      1.0         1.0 Version   
*/
package test.qrcodeMaker;

import java.io.UnsupportedEncodingException;

import com.cnfantasia.server.api.pub.qrcode.QRCodeUtil;
import com.google.zxing.WriterException;

/**
 * Filename:    Test.java
 * @version:    1.0.0
 * Create at:   2014年12月18日 上午6:29:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月18日       shiyl             1.0             1.0 Version
 */
public class Test {
	public static void main(String[] args) throws WriterException, UnsupportedEncodingException {
		QRCodeUtil.main(null);
	}
}

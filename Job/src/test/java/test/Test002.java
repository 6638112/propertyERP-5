/**   
* Filename:    Test002.java   
* @version:    1.0  
* Create at:   2014年6月16日 上午4:00:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月16日    shiyl      1.0         1.0 Version   
*/
package test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Filename:    Test002.java
 * @version:    1.0.0
 * Create at:   2014年6月16日 上午4:00:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月16日       shiyl             1.0             1.0 Version
 */
public class Test002 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//外部
		{
			String s="http://112.124.33.142:8080/weixinPay/payNotify.json?&out_trade_no=no40001&partner=1219483001&sign=61218126546459C2EF7EA5794076CB01&spbill_create_ip=202.170.133.210&total_fee=1180";
			String res = URLDecoder.decode(s,"UTF-8");
			System.out.println(res);
		}
		
		//内部
		{
			String s="http://202.170.133.211:8080/weixinPay/payNotify.json?&out_trade_no=no10102&partner=1219483001&sign=74118F93A390EB7E9FE650CF9DF18A56&spbill_create_ip=202.170.133.210&total_fee=7340";
			String res = URLDecoder.decode(s,"UTF-8");
			System.out.println(res);
		}
	}
}

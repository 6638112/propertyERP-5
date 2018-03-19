/**   
* Filename:    SqPayUtil.java   
* @version:    1.0  
* Create at:   2015年10月13日 上午11:01:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年10月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Filename:    SqPayUtil.java
 * @version:    1.0.0
 * Create at:   2015年10月13日 上午11:01:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年10月13日       shiyl             1.0             1.0 Version
 */
public class SqPayUtil {
	private static Properties prop = new Properties();
	static{
		InputStream in = SqPayUtil.class.getResourceAsStream("/sqpay.properties");//"sqpay.properties"当前类目录
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getValue(String key){
		return prop.getProperty(key);
	}
	
	public static String getTerminalKind(){
		return getValue("sqpay.terminalKind");
	}
	public static String getMerNo(){
		return getValue("sqpay.MerNo");
	}
	public static String getMD5key(){
		return getValue("sqpay.MD5key");
	}
	public static String getPaymentType(){
		return getValue("sqpay.PaymentType");
	}
	public static String getPayType(){
		return getValue("sqpay.PayType");
	}
	
	public static void main(String[] args) {
		System.out.println(getTerminalKind());
		System.out.println(getMerNo());
		System.out.println(getMD5key());
		System.out.println(getPaymentType());
		System.out.println(getPayType());
	}
	
}

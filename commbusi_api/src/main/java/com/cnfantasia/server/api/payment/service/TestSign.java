/**   
* Filename:    TestSign.java   
* @version:    1.0  
* Create at:   2014年12月10日 上午2:00:47   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.config.AlipayConfig;
import com.alipay.sign.RSA;
import com.alipay.util.AlipayCore;
import com.alipay.util.AlipayNotify;
import com.cnfantasia.server.api.payment.util.AlipayUtils;
import com.cnfantasia.server.business.pub.utils.ByteHexConvertUtil;
import com.cnfantasia.server.common.cryption.oneWay.MD5Crypt;

/**
 * Filename:    TestSign.java
 * @version:    1.0.0
 * Create at:   2014年12月10日 上午2:00:47
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月10日       shiyl             1.0             1.0 Version
 */
public class TestSign {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		test002();
//		testSuccess();
		String data = "/redenvelope/discount2hbPreQry.json?year=2014&month=12";
		byte[] resData = MD5Crypt.encrypt(data.getBytes());
		byte[] resData2 = MD5Crypt.encrypt(resData);
		String res = ByteHexConvertUtil.bytesToHexString(resData2);
		System.out.println(res);
		
	}
	
	public static void test002(){
		String sign="IFgs9fl1Y3JRF7C0Q6s8HBSII7uR8JuGdc+CujSZDSSt7mdmVy2JJ1PRZ/U4pRxYG5rXGHgnJLGjHhaF3qcpLkoySh8+G0p6WsRoTGmw7Gh4ztmulHNj9vePdhyaMCnbu+vaHHJCivhEZF36X228rfIauEmhr3fqqEsSy1r+kSY=";
//		String preSignStr = "body=\"Hochwald好沃德冰咖啡牛奶\"&buyer_email=\"18679120727\"&buyer_id=\"2088412627528021\"&discount=\"0.00\"&gmt_create=\"2014-12-10 16:31:08\"&is_total_fee_adjust=\"Y\"&notify_id=\"123539e84265c1587fb5973899e7a78825\"&notify_time=\"2014-12-10 16:31:08\"&notify_type=\"trade_status_sync\"&out_trade_no=\"201412100429100306\"&payment_type=\"1\"&price=\"13.90\"&quantity=\"1\"&seller_email=\"linlile@cnfantasia.com\"&seller_id=\"2088711343843097\"&subject=\"Hochwald好沃德冰咖啡牛奶\"&total_fee=\"13.90\"&trade_no=\"2014121090474202\"&trade_status=\"WAIT_BUYER_PAY\"&use_coupon=\"N\"";
		String preSignStr = "body=Hochwald好沃德冰咖啡牛奶&buyer_email=18679120727&buyer_id=2088412627528021&discount=0.00&gmt_create=2014-12-10 16:31:08&is_total_fee_adjust=Y&notify_id=123539e84265c1587fb5973899e7a78825"
				+ "&notify_time=2014-12-10 16:31:08"
				+ "&notify_type=trade_status_sync&out_trade_no=201412100429100306&payment_type=1&price=13.90&quantity=1&seller_email=linlile@cnfantasia.com"
				+ "&seller_id=2088711343843097&subject=Hochwald好沃德冰咖啡牛奶&total_fee=13.90&trade_no=2014121090474202&trade_status=WAIT_BUYER_PAY&use_coupon=N";
//		String mySign = RSA.sign(preSignStr, AlipayConfig.private_key, AlipayConfig.input_charset);
//		System.out.println(mySign);
		boolean res = RSA.verify(preSignStr, sign,  AlipayConfig.ali_public_key,  AlipayConfig.input_charset);
//		boolean res2 = RSA.verify(preSignStr, mySign,  AlipayConfig.ali_public_key,  AlipayConfig.input_charset);
		System.out.println(res);
//		System.out.println(res2);
		System.out.println(AlipayConfig.ali_public_key.length());
		Map<String,String> srcData = new HashMap<String, String>();
		srcData.put("body", "Hochwald好沃德冰咖啡牛奶");
		srcData.put("buyer_email", "18679120727");
		srcData.put("buyer_id", "2088412627528021");
		srcData.put("discount", "0.00");
		srcData.put("gmt_create", "2014-12-10 16:31:08");
		srcData.put("is_total_fee_adjust", "Y");
		srcData.put("notify_id", "123539e84265c1587fb5973899e7a78825");
		srcData.put("notify_time", "2014-12-10 16:31:08");
		srcData.put("notify_type", "trade_status_sync");
		srcData.put("out_trade_no", "201412100429100306");
		srcData.put("payment_type", "1");
		srcData.put("price", "13.90");
		srcData.put("quantity", "1");
		srcData.put("seller_email", "linlile@cnfantasia.com");
		srcData.put("seller_id", "2088711343843097");
		srcData.put("subject", "Hochwald好沃德冰咖啡牛奶");
		srcData.put("total_fee", "13.90");
		srcData.put("trade_no", "2014121090474202");
		srcData.put("trade_status", "WAIT_BUYER_PAY");
		srcData.put("use_coupon", "N");
		srcData.put("sign", sign);
		srcData.put("sign_type", "RSA");
		System.out.println(AlipayNotify.verify(srcData));
	}
	
	public static void testSuccess() throws UnsupportedEncodingException{
		boolean withMark = false;
		Map<String,String> srcData = new HashMap<String, String>();
		srcData.put("body", "Hochwald好沃德冰咖啡牛奶");
		srcData.put("buyer_email", "18679120727");
		srcData.put("buyer_id", "2088412627528021");
		srcData.put("discount", "0.00");
		srcData.put("gmt_create", "2014-12-10 16:31:08");
		srcData.put("is_total_fee_adjust", "Y");
		srcData.put("notify_id", "123539e84265c1587fb5973899e7a78825");
		srcData.put("notify_time", "2014-12-10 16:31:08");
		srcData.put("notify_type", "trade_status_sync");
		srcData.put("out_trade_no", "201412100429100306");
		srcData.put("payment_type", "1");
		srcData.put("price", "13.90");
		srcData.put("quantity", "1");
		srcData.put("seller_email", "linlile@cnfantasia.com");
		srcData.put("seller_id", "2088711343843097");
		srcData.put("subject", "Hochwald好沃德冰咖啡牛奶");
		srcData.put("total_fee", "13.90");
		srcData.put("trade_no", "2014121090474202");
		srcData.put("trade_status", "WAIT_BUYER_PAY");
		srcData.put("use_coupon", "N");
		srcData = AlipayCore.paraFilter(srcData);
		Map<String,String> resSignMap = AlipayUtils.getSign(srcData,withMark);
		String mySign = URLDecoder.decode(resSignMap.get("sign"), AlipayConfig.input_charset);
		System.out.println(mySign);
		resSignMap.put("sign", mySign);
		System.out.println(AlipayNotify.verify(resSignMap));
		
		System.out.println("===========");
		
		Map<String, String> sPara = AlipayCore.paraFilter(srcData);
    String prestr = AlipayUtils.createLinkString(sPara,withMark); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
    boolean res2 = RSA.verify(prestr, mySign,  AlipayConfig.ali_public_key,  AlipayConfig.input_charset);
    System.out.println(res2);
    
    String sign="IFgs9fl1Y3JRF7C0Q6s8HBSII7uR8JuGdc+CujSZDSSt7mdmVy2JJ1PRZ/U4pRxYG5rXGHgnJLGjHhaF3qcpLkoySh8+G0p6WsRoTGmw7Gh4ztmulHNj9vePdhyaMCnbu+vaHHJCivhEZF36X228rfIauEmhr3fqqEsSy1r+kSY=";
    boolean res1 = RSA.verify(prestr, sign,  AlipayConfig.ali_public_key,  AlipayConfig.input_charset);
    System.out.println(res1); 
	}
	
	@SuppressWarnings("unchecked")
	public static void test001() throws UnsupportedEncodingException{
		String resJsonStr = "{\"body\":\"深圳市福田区公路局小区1栋1-701,2014年12月物业费\",\"buyer_email\":\"18679120727\",\"buyer_id\":\"2088412627528021\",\"discount\":\"0.00\",\"gmt_create\":\"2014-12-09 18:42:01\",\"gmt_payment\":\"2014-12-09 18:42:02\",\"is_total_fee_adjust\":\"N\",\"notify_id\":\"548538cf1c0c660422e221911f199ff124\",\"notify_time\":\"2014-12-09 18:42:02\",\"notify_type\":\"trade_status_sync\",\"out_trade_no\":\"201412090640100231\",\"payment_type\":\"1\",\"price\":\"0.19\",\"quantity\":\"1\",\"seller_email\":\"linlile@cnfantasia.com\",\"seller_id\":\"2088711343843097\",\"sign\":\"QLbYH5h9DD+52NZP22Hg8NozJMLX+QwPeXekPGisOVaH5ASp+Graags0fgamLOUexjNJXL/UAJ2KOWzyYDYmxaX1RnzeU2AQsyZ8iR9XWe7DQPtg0dOC2c//pb1qLF8/sAZaQM5hDSoJ/4q0FYMgz9FurmEgoM65mqwaiH1/6yY=\",\"sign_type\":\"RSA\",\"subject\":\"深圳市福田区公路局小区1栋1-701,2014年12月物业费\",\"total_fee\":\"0.19\",\"trade_no\":\"2014120955370902\",\"trade_status\":\"TRADE_SUCCESS\",\"use_coupon\":\"N\"}";
		Map<String,String> aaMap = JSON.parseObject(resJsonStr, Map.class);
		System.out.println(aaMap.get("sign"));
		System.out.println(aaMap.get("sign_type"));
		System.out.println("-----------");
		for(String key:aaMap.keySet()){
			aaMap.put(key, new String(aaMap.get(key).getBytes("ISO-8859-1"), "utf-8"));
		}
		System.out.println(JSON.toJSONString(aaMap));
		System.out.println(aaMap.get("sign"));
		System.out.println(aaMap.get("sign_type"));
		System.out.println(AlipayNotify.verify(aaMap));
		System.out.println(AlipayNotify.verify(aaMap));
		aaMap.remove("sign");
		aaMap.remove("sign_type");
		System.out.println(AlipayNotify.verify(aaMap));
		System.out.println(AlipayNotify.verify(aaMap));
		
		{
			System.out.println("Map<String,String> myMapIsSigned = AlipayUtils.getSignWithMark(aaMap);");
			boolean withMark = true;
			//执行自身的处理
			Map<String,String> myMapIsSigned = AlipayUtils.getSign(aaMap,withMark);
			String mySign = URLDecoder.decode(myMapIsSigned.get("sign"), AlipayConfig.input_charset);
			System.out.println(mySign);
			myMapIsSigned.put("sign", mySign);
			System.out.println(AlipayNotify.verify(myMapIsSigned));
		}
		{
			System.out.println("Map<String,String> myMapIsSigned = AlipayUtils.getSign(aaMap);");
			//执行自身的处理
			boolean withMark = false;
			Map<String,String> myMapIsSigned = AlipayUtils.getSign(aaMap,withMark);
			String mySign = URLDecoder.decode(myMapIsSigned.get("sign"), AlipayConfig.input_charset);
			System.out.println(mySign);
			myMapIsSigned.put("sign", mySign);
			System.out.println(AlipayNotify.verify(myMapIsSigned));
		}
		
	}
	
	
}


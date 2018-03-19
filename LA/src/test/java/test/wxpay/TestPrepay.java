package test.wxpay;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.wxap.util.MD5Util;


public class TestPrepay {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String postData = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		HttpPost httpPost = new HttpPost(postData);
		String packageData = null;

		{
			SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
			parameters.put("appid", "wx681fb1b637c7c74f");
			parameters.put("mch_id", "1226657802");
			parameters.put("nonce_str", CreateNoncestr());
			parameters.put("body", "商品描述");
			parameters.put("out_trade_no", System.currentTimeMillis() + "");
			parameters.put("total_fee", "1");
			parameters.put("spbill_create_ip", "127.0.0.1");
			parameters.put("notify_url", "www.baidu.com");
			parameters.put("trade_type", "JSAPI");
			parameters.put("openid", "o33fQtzx0kf6e3FNvFJXhAF_rcdY");
			parameters.put("spbill_create_ip", "127.0.0.1");
			parameters.put("sign", createSign("UTF-8", parameters));
			String reqXml = getRequestXml(parameters);
			System.out.println(reqXml);
			packageData = reqXml;
		}

		{
			StringBuffer sbf = new StringBuffer();
			sbf.append("<xml>");
			sbf.append("<appid>wx2421b1c4370ec43b</appid>");
			sbf.append("<attach>支付测试</attach>");
			sbf.append("<body>JSAPI支付测试</body>");
			sbf.append("<mch_id>10000100</mch_id>");
			sbf.append("<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>");
			sbf.append("<notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url>");
			sbf.append("<openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>");
			sbf.append("<out_trade_no>1415659990</out_trade_no>");
			sbf.append("<spbill_create_ip>14.23.150.211</spbill_create_ip>");
			sbf.append("<total_fee>1</total_fee>");
			sbf.append("<trade_type>JSAPI</trade_type>");
			sbf.append("<openid>o33fQtzx0kf6e3FNvFJXhAF_rcdY</openid>");
			sbf.append("<sign>0CB01533B8C1EF103065174F50BCA001</sign>");
			sbf.append("</xml>");
			packageData = sbf.toString();
		}
		{
			packageData = "<xml><appid>wxf85b8444f7a2dc5f</appid><body><![CDATA[商品描述]]></body><mch_id>1225481801</mch_id><nonce_str>1425897515406</nonce_str><notify_url>www.baidu.com</notify_url><openid>o33fQtzx0kf6e3FNvFJXhAF_rcdY</openid><out_trade_no>1425897515406</out_trade_no><sign><![CDATA[056214D8DE2760BCB6EB1B04E6028CCF]]></sign><spbill_create_ip>127.0.0.1</spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type></xml>";
		}
		{//post param
			HttpEntity entity = new StringEntity(packageData);
			httpPost.setEntity(entity);
		}
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = client.execute(httpPost);
		{// response.getEntity()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String res = new String(EntityUtils.toByteArray(entity), "UTF-8");
					System.out.println(res);
				}
			}
		}
	}

	/**
	 * @author 李欣桦
	 * @date 2014-12-5下午2:29:34
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String createSign(String characterEncoding, SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + "5myUu1waw2AN9B1GLbrvShSwU2JouTJQ");
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * @author 李欣桦
	 * @date 2014-12-5下午2:32:05
	 * @Description：将请求参数转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}

	public static String CreateNoncestr(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < length; i++) {
			Random rd = new Random();
			res += chars.indexOf(rd.nextInt(chars.length() - 1));
		}
		return res;
	}

	public static String CreateNoncestr() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String res = "";
		for (int i = 0; i < 16; i++) {
			Random rd = new Random();
			res += chars.charAt(rd.nextInt(chars.length() - 1));
		}
		return res;
	}
}

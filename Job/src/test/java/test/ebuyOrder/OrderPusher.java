package test.ebuyOrder;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.business.pub.utils.Md5Util;

public class OrderPusher {

	/**
	 * 推送订单到海吉星
	 * 
	 * @param order
	 *            推送内容，JSON格式
	 * @return responseString 对方服务器返回的结果串
	 * @see com.cnfantasia.server.api.commonBusiness.service.CommonEbuyService.
	 *      pushOrder2HJX(Order4HJX order)
	 */
	@SuppressWarnings("deprecation")
	private static String pushOrder2HJX(String pushContentr) {
		CloseableHttpClient client = HttpClients.createDefault();

		// 创建参数队列 
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		formparams.add(new BasicNameValuePair(SysParamKey.HJX_Source_tag, "JFQ842566"));
		formparams.add(new BasicNameValuePair(SysParamKey.HJX_Version, "1"));
		formparams.add(new BasicNameValuePair("order", pushContentr));

		StringBuilder paramString = new StringBuilder();
		paramString.append(SysParamKey.HJX_Source_tag + "=" + "JFQ842566" + "|");
		paramString.append(SysParamKey.HJX_Version + "=" + "1" + "|");
		paramString.append("order=" + pushContentr + "|");
		paramString.append("V5GHP3YA");
		String signature = Md5Util.MD5(paramString.toString()); //签名
		formparams.add(new BasicNameValuePair("signature", signature));

		//String unifiedorderURL = "http://119.136.31.227:86/api/3rd/place_order_v1.php";
		//String unifiedorderURL = "http://www.hjxmall.com/api/3rd/place_order_v1.php";
		HttpPost httpPost = new HttpPost("http://www.hjxmall.com/api/3rd/place_order_v1.php");

		HttpEntity entity;
		String responseString = null;
		try {
			entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			httpPost.setEntity(entity);
			CloseableHttpResponse hcResponse = client.execute(httpPost);
			HttpEntity responseEntity = hcResponse.getEntity();
			responseString = EntityUtils.toString(responseEntity, HTTP.UTF_8);
			System.out.println("response from hjx is: " + responseString);
			//{"code":"0","order_tag":"100058180","sub_order_tags":["1501271146560000091202"], "description":"成功！"}
			//{"code":"-1","description":"重复订单！"}
			client.close();
			hcResponse.close();
		} catch (Exception e) {
			e.printStackTrace();
			responseString = "{\"code\":\"-1\",\"description\":\"有异常！\"}";
		}
		return responseString;
	}

	public static void main(String[] args) {
		String pushContent = "{\"address\":\"广东省深圳市南山区广东省深圳市南山区星海名城五期4栋9D\",\"edoId\":\"70443\",\"goods\":[{\"price\":3.65,\"quantity\":1,\"tag\":\"5662\",\"title\":\"冠牌白醋 500ml\"},{\"price\":6.85,\"quantity\":1,\"tag\":\"5718\",\"title\":\"香港盛记番茄酱 500g\"}],\"orderId\":\"100661\",\"phone\":\"13713884729\",\"receiver\":\"叶惠敏\",\"source_order_tag\":\"201504031610100661\"}";
		String responseMsg = pushOrder2HJX(pushContent);
		System.out.println(responseMsg);
	}
}

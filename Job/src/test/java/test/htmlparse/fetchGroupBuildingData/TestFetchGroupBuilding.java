/**   
* Filename:    TestFetchGroupBuilding.java   
* @version:    1.0  
* Create at:   2014年11月10日 上午8:10:35   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月10日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse.fetchGroupBuildingData;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import test.htmlparse.EncodeUtil;
import test.messyCode.FetchEncodeUtil;

/**
 * Filename:    TestFetchGroupBuilding.java
 * @version:    1.0.0
 * Create at:   2014年11月10日 上午8:10:35
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月10日       shiyl             1.0             1.0 Version
 */
public class TestFetchGroupBuilding {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
//		String url = "http://zu.sz.fang.com/house-a085-b0327/";
		String url = "http://zu.sz.fang.com/house-a085-b0327/";
		String resHtml = doGet(url);
//		System.out.println(resHtml);
		
	}
	
	@SuppressWarnings("unused")
	public static String doGet(String totalUrl) throws Exception{
		String url = totalUrl;
		String encoding = "ISO-8859-1";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
//		{
//			getMethod.addRequestHeader(new Header("Host", "zu.sz.fang.com"));
//			getMethod.addRequestHeader(new Header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0"));
////			getMethod.addRequestHeader(new Header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
//			getMethod.addRequestHeader(new Header("Accept", "*/*"));
////			getMethod.addRequestHeader(new Header("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"));
////			getMethod.addRequestHeader(new Header("Accept-Encoding", "text/html; charset=ISO-8859-1"));
////			getMethod.addRequestHeader(new Header("Accept-Encoding", "ISO-8859-1"));
////			getMethod.addRequestHeader(new Header("Accept-Encoding", "gzip, deflate"));
//			getMethod.addRequestHeader(new Header("Referer", "http://zu.sz.fang.com/house-a085/"));
//			getMethod.addRequestHeader(new Header("Cookie", "city=sz; global_cookie=zb3kf64tsgodmxcc278trimqj17i1sysybe; __utma=147393320.701774780.1415344573.1415351836.1415607110.3; __utmz=147393320.1415607110.3.3.utmcsr=zu.sz.fang.com|utmccn=(referral)|utmcmd=referral|utmcct=/house-a085-b0327/; unique_cookie=U_78g6filkjczhvm25mj5uccmoj1ii2b544uq*10; SoufunSessionID_Rent=0_1415582328_5302; actcity=%c9%ee%db%da; ghVerifyCode=E1E7D823EE9CE26A; Rent_StatLog=20e640f6-5321-4a07-b6f4-1b44a607f172; __utmb=147393320.15.10.1415607110; __utmc=147393320; __utmt_t0=1; __utmt_t1=1; __utmt_t2=1"));
//			getMethod.addRequestHeader(new Header("Connection", "keep-alive"));
//		}
//		{
//			// getMethod.setParams(params);
//			getMethod.getParams().setHttpElementCharset(encoding);
//			getMethod.getParams().setUriCharset(encoding);
//			getMethod.getParams().setContentCharset(encoding);
//			getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//			getMethod.addRequestHeader("Content-Type", "text/html; charset=ISO-8859-1");
//			getMethod.setRequestHeader("Content-Type", "text/html; charset=ISO-8859-1");
//			// 设置编码格式
//			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//			client.getParams().setContentCharset(encoding);
//			client.getParams().setHttpElementCharset(encoding);
//			client.getParams().setUriCharset(encoding);
//		}
		/* 执行方法 */
		try {
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			/* 获得返回的结果 */
			String responseHtmlStr = null;
			byte[] resBytes = getMethod.getResponseBody();
			String data = new String(resBytes);
			System.out.println(data);
			FetchEncodeUtil.checkCount(data.substring(0, 35), 2);
			System.out.println("data ="+EncodeUtil.getEncoding(data));
			//开始解析数据
			return responseHtmlStr;
		}finally {
			getMethod.releaseConnection();
		}
	}
}

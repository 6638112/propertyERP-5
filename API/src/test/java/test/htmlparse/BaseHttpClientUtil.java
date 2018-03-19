/**   
* Filename:    BaseHttpClientUtil.java   
* @version:    1.0  
* Create at:   2014年11月10日 上午8:34:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月10日    shiyl      1.0         1.0 Version   
*/
package test.htmlparse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Filename:    BaseHttpClientUtil.java
 * @version:    1.0.0
 * Create at:   2014年11月10日 上午8:34:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月10日       shiyl             1.0             1.0 Version
 */
public class BaseHttpClientUtil {
	public static String doGet(String totalUrl) throws Exception{
		String url = totalUrl;
		String encoding = "ISO-8859-1";
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		{
			getMethod.addRequestHeader(new Header("Host", "www.stats.gov.cn"));
			getMethod.addRequestHeader(new Header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:29.0) Gecko/20100101 Firefox/29.0"));
			getMethod.addRequestHeader(new Header("Accept", "*/*"));
//			getMethod.addRequestHeader(new Header("Accept-Encoding","GB2312, deflate"));//
//			 getMethod.addRequestHeader(new Header("Accept-Encoding","gzip, deflate"));//gzip 是一种数据格式，deflate是压缩算法
			getMethod.addRequestHeader(new Header("Cookie", "AD_RS_COOKIE=20080917"));
			getMethod.addRequestHeader(new Header("Connection", "keep-alive"));
			getMethod.addRequestHeader(new Header("Pragma", "no-cache"));
			getMethod.addRequestHeader(new Header("Cache-Control", "no-cache"));
		}
		{
			// getMethod.setParams(params);
			getMethod.getParams().setHttpElementCharset(encoding);
			getMethod.getParams().setUriCharset(encoding);
			getMethod.getParams().setContentCharset(encoding);
			getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//			getMethod.addRequestHeader("Content-Type", "text/html; charset=gb2312");
//			getMethod.setRequestHeader("Content-Type", "text/html; charset=gb2312");
			// 设置编码格式
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
			client.getParams().setContentCharset(encoding);
			client.getParams().setHttpElementCharset(encoding);
			client.getParams().setUriCharset(encoding);
		}
		/* 执行方法 */
		try {
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
			}
			/* 获得返回的结果 */
			String responseHtmlStr = null;
			{
				String data = getMethod.getResponseBodyAsString();
				{
					String step01 = new String(data.getBytes("UTF-8"));
					String step02 = new String(step01.getBytes("ISO-8859-1"), "GB2312");
//					System.out.println("data ="+EncodeUtil.getEncoding(data));
//					System.out.println("step01 ="+EncodeUtil.getEncoding(step01));
//					System.out.println("step02 ="+EncodeUtil.getEncoding(step02));
					responseHtmlStr = step02;
				}
			}
			//开始解析数据
			return responseHtmlStr;
		}finally {
			getMethod.releaseConnection();
		}
	}
}

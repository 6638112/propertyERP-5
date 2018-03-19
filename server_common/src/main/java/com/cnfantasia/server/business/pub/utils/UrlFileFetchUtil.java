/**   
 * Filename:    UrlFileFetchUtil.java   
 * @version:    1.0  
 * Create at:   2015年1月7日 上午6:41:34   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年1月7日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.utils;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Filename: UrlFileFetchUtil.java
 * 
 * @version: 1.0.0 Create at: 2015年1月7日 上午6:41:34 Description:网络图片抓取工具类
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年1月7日 shiyl 1.0 1.0 Version
 */
public class UrlFileFetchUtil {
	private static Log logger = LogFactory.getLog(UrlFileFetchUtil.class);
	
//	public static void main(String[] args) throws Exception {
//		String uri = "http://pic.mljia.cn/cn.mljia.web/download/image/68819";
//		// byte[] resBytes = getByte(uri);
//		byte[] resBytes = getBytesByUrl(uri);
//		if(resBytes!=null&&resBytes.length>0){
//			FileUtils.byteToFile(resBytes, "D:/a3.jpg");
//		}
//	}

	private static RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(5000)
			.setConnectTimeout(5000).setConnectionRequestTimeout(5000).setStaleConnectionCheckEnabled(true).build();

	/**
	 * 获取图片字节流
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	public static byte[] getBytesByUrl(String url) {
		byte[] resBytes = null;
		HttpUriRequest httpUriRequest = new HttpGet(url);
		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
		try {
			CloseableHttpResponse response = client.execute(httpUriRequest);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					resBytes = EntityUtils.toByteArray(entity);
				}
			}
		} catch (Exception e) {
			logger.debug("UrlFileFetchUtil.getBytesByUrl.client.execute cause exception",e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.debug("UrlFileFetchUtil.getBytesByUrl.client.close cause exception",e);
			}
		}
		return resBytes;
	}

}

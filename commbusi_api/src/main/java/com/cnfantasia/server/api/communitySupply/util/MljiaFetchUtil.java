/**   
* Filename:    MljiaFetchUtil.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午10:22:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.communitySupply.constant.MljiaConstant;
import com.cnfantasia.server.api.communitySupply.entity2mljia.MljiaBaseJsonResponse;
import com.cnfantasia.server.api.communitySupply.entity2mljia.MljiaShopEntity;

/**
 * Filename:    MljiaFetchUtil.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午10:22:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class MljiaFetchUtil {
	private static Log logger = LogFactory.getLog(MljiaFetchUtil.class);
	
//	public static void main(String[] args) {//图片不支持
//		Document doc = doConnectPost("http://pic.mljia.cn/cn.mljia.web/download/image/68819", null);
//		Element body = doc.body();
//		String bodyHtml = body.html();
//		System.out.println(bodyHtml);
//	}
	
	public static List<MljiaShopEntity> fetchShopInfo(String longitude,String latitude){
		List<MljiaShopEntity> shopEntityList = null;
		String url = MljiaConstant.Url_ShopInfo;
		Map<String,String> params = new HashMap<String, String>();
		params.put("longitude", longitude);
		params.put("latitude", latitude);
		Document doc = doConnectPost(url,params);
		try {
			MljiaBaseJsonResponse tmpResponseData = respondeDocument2MljData(doc);
			if(tmpResponseData!=null){
				shopEntityList = tmpResponseData.parseContentList(MljiaShopEntity.class);
			}
		} catch (Exception e) {
			logger.debug("MljiaFetchUtil.fetchShopInfo.cause exception,url is "+url+",longitude is "+longitude+",latitude is "+latitude,e);
		}
		return shopEntityList;
	}
	
	private static MljiaBaseJsonResponse respondeDocument2MljData(Document doc){
		MljiaBaseJsonResponse tmpResponseData = null;
		try {
			Element body = doc.body();
			String bodyHtml = body.html();
			tmpResponseData = JSON.parseObject(bodyHtml, MljiaBaseJsonResponse.class);
		} catch (Exception e) {
			logger.debug("MljiaFetchUtil.respondeDocument2MljData.cause exception,doc is "+doc==null?null:JSON.toJSONString(doc),e);
		}
		if(tmpResponseData==null||tmpResponseData.getStatus()!=200){
			logger.info("MljiaFetchUtil.respondeDocument2MljData.cause business error,tmpResponseData is "+tmpResponseData==null?null:JSON.toJSONString(tmpResponseData));
			tmpResponseData = null;
		}
		return tmpResponseData;
	}
	
	private static Document doConnectPost(String url,Map<String,String> params){
		Document doc = null;
		try {
			Connection conn = Jsoup.connect(url);
			if(params!=null){
				conn.data(params);
			}
			doc = conn.timeout(60000).post();
		} catch (IOException e) {
			logger.error("Operation of doConnect cause exception,the url is :"+url,e);
		}
		return doc;
	}
	
	
}

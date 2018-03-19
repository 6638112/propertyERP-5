/**   
* Filename:    TestFetchShopList.java   
* @version:    1.0  
* Create at:   2015年1月6日 上午7:43:24   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月6日    shiyl      1.0         1.0 Version   
*/
package test.mlijia;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.communitySupply.entity2mljia.MljiaBaseJsonResponse;
import com.cnfantasia.server.api.communitySupply.entity2mljia.MljiaShopEntity;

/**
 * Filename:    TestFetchShopList.java
 * @version:    1.0.0
 * Create at:   2015年1月6日 上午7:43:24
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月6日       shiyl             1.0             1.0 Version
 */
public class TestFetchShopList {
	
	public static void main(String[] args) {
		String url = "http://api.mljia.cn/thirdparty/shop/info";
		Map<String,String> params = new HashMap<String, String>();
		params.put("longitude", "114.048479");
		params.put("latitude", "22.534359");
		Document doc = doConnectPost(url,params);
		Element body = doc.body();
		String bodyHtml = body.html();
		System.out.println(bodyHtml);
		MljiaBaseJsonResponse resData = JSON.parseObject(bodyHtml, MljiaBaseJsonResponse.class);
		System.out.println(resData);
		List<MljiaShopEntity> shopEntityList = resData.parseContentList(MljiaShopEntity.class);
		System.out.println(shopEntityList.size());
		for(MljiaShopEntity tmp:shopEntityList){
			System.out.println(JSON.toJSONString(tmp));
		}
	}
	
	private static Document doConnectPost(String url,Map<String,String> params){
		Document doc = null;
		try {
			Connection conn = Jsoup.connect(url);
			conn.data(params);
			doc = conn.timeout(60000).post();
		} catch (IOException e) {
			System.err.println("Operation of doConnect cause exception,the url is :"+url);
			e.printStackTrace();
		}
		return doc;
	}
	
//	private static Document doConnectGet(String url){
//		Document doc = null;
//		try {
//			doc = Jsoup.connect(url).timeout(60000).get();
//		} catch (IOException e) {
//			System.err.println("Operation of doConnect cause exception,the url is :"+url);
//			e.printStackTrace();
//		}
//		return doc;
//	}
	
}

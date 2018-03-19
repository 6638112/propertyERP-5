/**   
 * Filename:    BaseFetchUtil.java   
 * @version:    1.0  
 * Create at:   2014年11月26日 上午7:03:51   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年11月26日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.communitySupply.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.communitySupply.entity02.CoordinateJsonResponse;
import com.cnfantasia.server.api.communitySupply.entity02.CoordinateJsonResponse.CoordinateResult;
import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.communitySupply.entity02.MerchantJsonResponse;
import com.cnfantasia.server.api.communitySupply.entity02.MerchantResult;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename: BaseFetchUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年11月26日 上午7:03:51 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年11月26日 shiyl 1.0 1.0 Version
 */
public class BaseFetchUtil {
	public static final Integer totalMaxCount = 20;
//	private static final String baiduLbsAK="Kbf7C7Nrrr43Az1HnDHHknWH";
	// 8DSoVzHUwGrRcC31LOXYPThS
	// XUeDBrxR1T6LV5QGkqCvfQuI
	
	// Kbf7C7Nrrr43Az1HnDHHknWH
//	public static void main(String[] args) throws IOException {
//		CoordinateResult tmpCoordinateResult = getLocationByName(baiduLbsAK,"深圳市福田区", "上沙");
//		//System.out.println(tmpCoordinateResult);
//		List<MerchantResult> totalMerchantResultResults = getListByLocation(baiduLbsAK,tmpCoordinateResult, "美容",200);
//		System.out.println("查询总数据为：" + (totalMerchantResultResults==null?"-1":(totalMerchantResultResults.size() + "个。")));
//	}
	
	
	public static CoordinateResult getLocationByName(String baiduLbsAK,String city, String address){
		String url = "http://api.map.baidu.com/geocoder/v2/?ak="+baiduLbsAK+"&callback=renderOption&output=json&address="
				+ address + "&city=" + city + "";
		//System.out.println(url);
		Document doc = doConnectGet(url);if(doc==null){return null;}
		Element body = doc.body();
		// ////System.out.println(body.getElementsByTag("result").first().html());;
		String bodyHtml = body.html();
		int startIndex = bodyHtml.indexOf("(") + "(".length();
		int endIndex = bodyHtml.lastIndexOf(")");
		String resJsonData = bodyHtml.substring(startIndex, endIndex);
		CoordinateJsonResponse tmpCoordinateEntity = JSON.parseObject(resJsonData, CoordinateJsonResponse.class);
		// ////System.out.println(tmpCoordinateEntity.getResult().getLocation().getLat());
		if(tmpCoordinateEntity.getStatus()!=0){
			return null;
		}
		return tmpCoordinateEntity.getResult();
	}
	public static List<MerchantResult> getListByLocation(String baiduLbsAK,CoordinateResult tmpCoordinateResult, List<String> searchNameList,Integer radius){
		List<MerchantResult> totalMerchantResultResults = new ArrayList<MerchantResult>();
		for(String searchName:searchNameList){
			if(totalMerchantResultResults.size()>=totalMaxCount){return totalMerchantResultResults;}
			List<MerchantResult> tmpList = getListByLocation(baiduLbsAK,tmpCoordinateResult, searchName,radius);
			if(tmpList!=null&&tmpList.size()>0){
				totalMerchantResultResults.addAll(tmpList);
			}
		}
		return totalMerchantResultResults;
	}
	public static List<MerchantResult> getListByLocation(String baiduLbsAK,CoordinateResult tmpCoordinateResult, String searchName,Integer radius){
		List<MerchantResult> totalMerchantResultResults = new ArrayList<MerchantResult>();
		if(tmpCoordinateResult==null){return null;}
		Integer length = 20;
		PageModel pageModel = null;
		while (pageModel == null || (pageModel.isLast!=null&&!pageModel.isLast)) {
			if(totalMerchantResultResults.size()>=totalMaxCount){return totalMerchantResultResults;}
			if (pageModel == null) {
				pageModel = new PageModel((1 - 1) * length, length);
			}
			////System.out.println("当前查询：" + JSON.toJSONString(pageModel));
			List<MerchantResult> merchantResultResults = getListByLocation(baiduLbsAK,tmpCoordinateResult, searchName, pageModel,radius);
			////System.out.println(merchantResultResults);
			if(merchantResultResults!=null&&merchantResultResults.size()>0){
				totalMerchantResultResults.addAll(merchantResultResults);
			}
			pageModel.setBegin(pageModel.getBegin() + length);// 开始数量增加length
		}
		////System.out.println(totalMerchantResultResults);
		////System.out.println("查询总数据为：" + totalMerchantResultResults.size() + "个。");
		return totalMerchantResultResults;
	}
	
	public static List<MerchantResult> getListByLocation(String baiduLbsAK,CoordinateResult coordinateResult, String searchName,
			PageModel pageModel,Integer radius){
		Location tmpLocation = coordinateResult.getLocation();
		String url = "http://api.map.baidu.com/place/v2/search?ak="+baiduLbsAK+"&output=json&query=" + searchName
				+ "&page_size=" + pageModel.getLength() + "&page_num=" + pageModel.currentPage + "&scope=1&location="
				+ tmpLocation.getLat() + "," + tmpLocation.getLng() + "&radius="+radius;//参数调整为1公里,300米
		//System.out.println(url);
		Document doc = doConnectGet(url);if(doc==null){return null;}
		Element body = doc.body();
		String bodyHtml = body.html();
		MerchantJsonResponse baiduMapJsonResponse = JSON.parseObject(bodyHtml, MerchantJsonResponse.class);
		if(baiduMapJsonResponse.getStatus()!=0){
			return null;
		}
		Integer total = baiduMapJsonResponse.getTotal();
		pageModel.freshPageModel(baiduMapJsonResponse.getResults().size(), total);
		//System.out.println(JSON.toJSONString(baiduMapJsonResponse));
		return baiduMapJsonResponse.getResults();
	}
	
	private static Document doConnectGet(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(60000).get();
		} catch (IOException e) {
			System.err.println("Operation of doConnect cause exception,the url is :"+url);
			e.printStackTrace();
		}
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return doc;
	}
	
}

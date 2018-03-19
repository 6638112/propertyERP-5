///**   
//* Filename:    BaseFetchUtil.java   
//* @version:    1.0  
//* Create at:   2014年11月6日 上午5:07:12   
//* Description:  
//*   
//* Modification History:   
//* Date        Author      Version     Description   
//* ----------------------------------------------------------------- 
//* 2014年11月6日    shiyl      1.0         1.0 Version   
//*/
//package test.htmlparse.fetchCityData;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.htmlparser.Node;
//import org.htmlparser.NodeFilter;
//import org.htmlparser.Parser;
//import org.htmlparser.util.NodeList;
//
//import test.htmlparse.BaseHttpClientUtil;
//import test.stringCut.StringCutUtil;
//
//import com.alibaba.fastjson.JSON;
//import com.cnfantasia.server.common.utils.FileUtils;
//
///**
// * Filename:    BaseFetchUtil.java
// * @version:    1.0.0
// * Create at:   2014年11月6日 上午5:07:12
// * Description:
// *
// * Modification History:
// * Date           Author           Version           Description
// * ------------------------------------------------------------------
// * 2014年11月6日       shiyl             1.0             1.0 Version
// */
//public class BaseFetchUtil {
//	public static final String BASEURL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013";
//	public static final String ProvinceHtml = "/index.html";
//	public static void main(String[] args) throws Exception {
//		{
//			//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/index.html
//			List<AddressInfoEntity> provinceList = fetchProvince(null,BASEURL+ProvinceHtml);
//			System.out.println(JSON.toJSONString(provinceList));
//			FileUtils.byteToFile(JSON.toJSONString(provinceList).getBytes(), "F:/province.json");
//		}
////		{
////			//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/13.html
//////			http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/11.html
////			List<AddressInfoEntity> resList =fetchCity("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/11.html");
////			System.out.println(JSON.toJSONString(resList));
////		}
//		
////		{
////			//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/13/1309.html
////			List<AddressInfoEntity> resList =fetchCounty("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/13/1309.html");
////			System.out.println(JSON.toJSONString(resList));
////		}
//		
////		{//TODO 后续再完善
////			//http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/13/09/130921.html 沧县
////			List<AddressInfoEntity> resList =fetchTown("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/13/09/130921.html");
////			System.out.println(JSON.toJSONString(resList));
////		}
//		
//	}
//	public static List<AddressInfoEntity> fetchCounty(AddressInfoEntity parentInfo,String totalUrl) throws Exception{
//		String currBaseUrl = null;
//		{
//			int lastIndex = totalUrl.lastIndexOf("/");
//			currBaseUrl = totalUrl.substring(0, lastIndex);
//		}
//		List<AddressInfoEntity> resList = new ArrayList<AddressInfoEntity>();
//		NodeList nodes = doGetNodeList(totalUrl);
//		for(int i=0;i<nodes.size();i++){
//			Node node = nodes.elementAt(i);
//			String currStr = nodeToString(node);
//			if(currStr.contains("table class='countytable'")){
//				NodeList tbody = node.getChildren();
//				for(int k=0;k<tbody.size();k++){
//					Node tbody_tr = tbody.elementAt(k);//<tr class="citytr">
//					String tbody_tr_Str = nodeToString(tbody_tr);
//					if(tbody_tr_Str.contains("class='countytr'")){
//						AddressInfoEntity tmpMap = new AddressInfoEntity();
//						NodeList tbody_tr_child = tbody_tr.getChildren();
//						for(int m=0;m<tbody_tr_child.size();m++){
//							Node tbody_tr_td = tbody_tr_child.elementAt(m);//<td>
//							NodeList tbody_tr_td_child = tbody_tr_td.getChildren();
//							{
//								Node tbody_tr_td_ahref = tbody_tr_td_child.elementAt(0);//<a href>
//								String tbody_tr_td_ahref_Str = nodeToString(tbody_tr_td_ahref);
//								if(tbody_tr_td_ahref.getFirstChild()==null){//130101000000	市辖区
//									continue;
//								}
//								String placeName = nodeToString(tbody_tr_td_ahref.getFirstChild());
//								String placeCode = null;
//								{
//									placeCode = StringCutUtil.fethchFirstMatcher(tbody_tr_td_ahref_Str, "\\d*/\\d*\\.html");
//									placeCode = StringCutUtil.fethchFirstMatcher(placeCode, "/\\d*\\.html");
//									placeCode = StringCutUtil.fethchFirstMatcher(placeCode, "\\d*");
//								}
//								String href = null;
//								{
//									int startIndex = tbody_tr_td_ahref_Str.indexOf("='");
//									int endIndex = tbody_tr_td_ahref_Str.lastIndexOf("'");
//									href = tbody_tr_td_ahref_Str.substring(startIndex+"='".length(), endIndex);
//								}
//								System.out.println(placeCode+"---"+placeName+"---"+currBaseUrl+"/"+href);
//								if(m==0){
//									tmpMap.setTotalCode(placeName);
//								}else if(m==1){
//									tmpMap.setPlaceCode(placeCode);
//									tmpMap.setPlaceName(placeName);
//									tmpMap.setSubUrl(BASEURL+"/"+href);
//									tmpMap.setParentAddressInfoEntity(parentInfo);
//								}
//							}
//						}
//						if(!tmpMap.checkIsEmptyData()){
//							resList.add(tmpMap);
//						}
//					}
//				}
//			}
//		}
//		return resList;
//	}
//	public static List<AddressInfoEntity> fetchCity(AddressInfoEntity parentInfo,String totalUrl) throws Exception{
//		List<AddressInfoEntity> resList = new ArrayList<AddressInfoEntity>();
//		NodeList nodes = doGetNodeList(totalUrl);
//		for(int i=0;i<nodes.size();i++){
//			Node node = nodes.elementAt(i);
//			String currStr = nodeToString(node);
//			if(currStr.contains("table class='citytable'")){
//				NodeList tbody = node.getChildren();
//				for(int k=0;k<tbody.size();k++){
//					Node tbody_tr = tbody.elementAt(k);//<tr class="citytr">
//					String tbody_tr_Str = nodeToString(tbody_tr);
//					if(tbody_tr_Str.contains("class='citytr'")){
//						AddressInfoEntity tmpMap = new AddressInfoEntity();
//						NodeList tbody_tr_child = tbody_tr.getChildren();
//						for(int m=0;m<tbody_tr_child.size();m++){
//							Node tbody_tr_td = tbody_tr_child.elementAt(m);//<td>
//							NodeList tbody_tr_td_child = tbody_tr_td.getChildren();
//							{
//								Node tbody_tr_td_ahref = tbody_tr_td_child.elementAt(0);//<a href>
//								String tbody_tr_td_ahref_Str = nodeToString(tbody_tr_td_ahref);
//								String placeName = nodeToString(tbody_tr_td_ahref.getFirstChild());
//								String placeCode = null;
//								{
//									placeCode = StringCutUtil.fethchFirstMatcher(tbody_tr_td_ahref_Str, "\\d*/\\d*\\.html");
//									placeCode = StringCutUtil.fethchFirstMatcher(placeCode, "\\d*\\.html");
//									placeCode = StringCutUtil.fethchFirstMatcher(placeCode, "\\d*");
//								}
//								String href = null;
//								{
//									int startIndex = tbody_tr_td_ahref_Str.indexOf("='");
//									int endIndex = tbody_tr_td_ahref_Str.lastIndexOf("'");
//									href = tbody_tr_td_ahref_Str.substring(startIndex+"='".length(), endIndex);
//								}
//								System.out.println(placeCode+"---"+placeName+"---"+BASEURL+"/"+href);
//								if(m==0){
//									tmpMap.setTotalCode(placeName);
//								}else if(m==1){
//									tmpMap.setPlaceCode(placeCode);
//									tmpMap.setPlaceName(placeName);
//									tmpMap.setSubUrl(BASEURL+"/"+href);
//									tmpMap.setParentAddressInfoEntity(parentInfo);;
//								}
//							}
//						}
//						if(!tmpMap.checkIsEmptyData()){
//							resList.add(tmpMap);
//						}
//					}
//				}
//			}
//		}
//		return resList;
//	}
//	
//	public static List<AddressInfoEntity> fetchProvince(AddressInfoEntity parentInfo,String totalUrl) throws Exception{
//		List<AddressInfoEntity> resList = new ArrayList<AddressInfoEntity>();
//		NodeList nodes = doGetNodeList(totalUrl);
//		for(int i=0;i<nodes.size();i++){
//			Node node = nodes.elementAt(i);
//			String currStr = nodeToString(node);
//			if(currStr.contains("table class='provincetable'")){
//				System.out.println("==="+currStr);
//				System.out.println(node.getChildren().size());
//				NodeList tbody = node.getChildren();
//				for(int k=0;k<tbody.size();k++){
//					Node tbody_tr = tbody.elementAt(k);//<tr class="provincetr">
//					String tbody_tr_Str = nodeToString(tbody_tr);
//					if(tbody_tr_Str.contains("class='provincetr'")){
//						NodeList tbody_tr_child = tbody_tr.getChildren();
//						for(int m=0;m<tbody_tr_child.size();m++){
//							Node tbody_tr_td = tbody_tr_child.elementAt(m);//<td>
//							NodeList tbody_tr_td_child = tbody_tr_td.getChildren();
//							{
//								Node tbody_tr_td_ahref = tbody_tr_td_child.elementAt(0);//<a href>
//								String tbody_tr_td_ahref_Str = nodeToString(tbody_tr_td_ahref);
//								String placeName = nodeToString(tbody_tr_td_ahref.getFirstChild());
//								String placeCode = null;
//								{
//									placeCode = StringCutUtil.fethchFirstMatcher(tbody_tr_td_ahref_Str, "\\d*\\.html");
//									placeCode = StringCutUtil.fethchFirstMatcher(placeCode, "\\d*");
//								}
//								String href = null;
//								{
//									int startIndex = tbody_tr_td_ahref_Str.indexOf("='");
//									int endIndex = tbody_tr_td_ahref_Str.lastIndexOf("'");
//									href = tbody_tr_td_ahref_Str.substring(startIndex+"='".length(), endIndex);
//								}
//								System.out.println(placeCode+"---"+placeName+"---"+BASEURL+"/"+href);
//								AddressInfoEntity tmpMap = new AddressInfoEntity();
//								tmpMap.setPlaceCode(placeCode);
//								tmpMap.setPlaceName(placeName);
//								tmpMap.setSubUrl(BASEURL+"/"+href);
//								tmpMap.setParentAddressInfoEntity(parentInfo);
//								if(!tmpMap.checkIsEmptyData()){
//									resList.add(tmpMap);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return resList;
//	}
//	public static NodeList doGetNodeList(String totalUrl) throws Exception{
//		String responseStr = BaseHttpClientUtil.doGet(totalUrl);
//		//解析数据
//			Parser parser = Parser.createParser(responseStr, "GB2312");
//			NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
//				private static final long serialVersionUID = 1L;
//				public boolean accept(Node node) {
//					return true;
//				}
//			});
//			return nodes;
//	}
//	
//	
//	
//	public static String nodeToString(Node node) throws UnsupportedEncodingException {
//		String currStr = new String(node.getText());
//		return currStr;
//	}
//	
//}

///**   
//* Filename:    DispatcherFetchAddressData.java   
//* @version:    1.0  
//* Create at:   2014年11月6日 上午7:34:55   
//* Description:  
//*   
//* Modification History:   
//* Date        Author      Version     Description   
//* ----------------------------------------------------------------- 
//* 2014年11月6日    shiyl      1.0         1.0 Version   
//*/
//package test.htmlparse.fetchCityData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.alibaba.fastjson.JSON;
//import com.cnfantasia.server.common.utils.FileUtils;
//
///**
// * Filename:    DispatcherFetchAddressData.java
// * @version:    1.0.0
// * Create at:   2014年11月6日 上午7:34:55
// * Description:
// *
// * Modification History:
// * Date           Author           Version           Description
// * ------------------------------------------------------------------
// * 2014年11月6日       shiyl             1.0             1.0 Version
// */
//public class DispatcherFetchAddressData {
//	public static void main(String[] args) throws Exception {
//		//查询所有的省
//		List<AddressInfoEntity> provinceList = BaseFetchUtil.fetchProvince(null,BaseFetchUtil.BASEURL+BaseFetchUtil.ProvinceHtml);
//		System.out.println(JSON.toJSONString(provinceList));
//		FileUtils.byteToFile(JSON.toJSONString(provinceList).getBytes(), "F:/province.json");
//		
//		//遍历查询下面的市
//		List<AddressInfoEntity> totalCityList = new ArrayList<AddressInfoEntity>();
//		for(AddressInfoEntity tmpProvince:provinceList){
////			String placeCode = tmpProvince.getPlaceCode();
////			String totalCode = tmpProvince.getTotalCode();
////			String placeName = tmpProvince.getPlaceName();
//			String subUrl = tmpProvince.getSubUrl();
//			List<AddressInfoEntity> currCityList = BaseFetchUtil.fetchCity(tmpProvince,subUrl);
//			if(currCityList==null||currCityList.size()<=0){
//				System.err.println("CityData is empty,curr data is :"+tmpProvince);
//			}else{
//				totalCityList.addAll(currCityList);
//			}
//		}
//		FileUtils.byteToFile(JSON.toJSONString(totalCityList).getBytes(), "F:/city.json");
//		
//		//遍历查询市下面的区
//		List<AddressInfoEntity> totalCountyList = new ArrayList<AddressInfoEntity>();
//		for(AddressInfoEntity tmpCity:totalCityList){
////			String placeCode = tmpCity.getPlaceCode();
////			String totalCode = tmpCity.getTotalCode();
////			String placeName = tmpCity.getPlaceName();
//			String subUrl = tmpCity.getSubUrl();
//			List<AddressInfoEntity> currCountyList = BaseFetchUtil.fetchCounty(tmpCity,subUrl);
//			if(currCountyList==null||currCountyList.size()<=0){
//				System.err.println("CountyData is empty,curr data is :"+tmpCity);
//				/*
//				 * CountyData is empty,subUrl is :http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/4419.html
//				CountyData is empty,subUrl is :http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/4420.html
//				 */
//				/*
//				 * TODO ...
//				 * CountyData is empty,curr data is :placeCode=4419;totalCode=441900000000;placeName=东莞市;subUrl=http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/4419.html;parentAddressInfoEntity={placeCode=44;totalCode=null;placeName=广东省;subUrl=http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44.html;parentAddressInfoEntity=null};};
//					 CountyData is empty,curr data is :placeCode=4420;totalCode=442000000000;placeName=中山市;subUrl=http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44/4420.html;parentAddressInfoEntity={placeCode=44;totalCode=null;placeName=广东省;subUrl=http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/44.html;parentAddressInfoEntity=null};};
//				 */
//			}else{
//				totalCountyList.addAll(currCountyList);
//			}
//		}
//		FileUtils.byteToFile(JSON.toJSONString(totalCountyList).getBytes(), "F:/county.json");
//		
//	}
//	
//}

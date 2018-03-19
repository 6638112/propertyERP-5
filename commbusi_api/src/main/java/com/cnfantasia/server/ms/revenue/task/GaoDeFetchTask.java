package com.cnfantasia.server.ms.revenue.task;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.constant.CommonModuleDict;
import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.communitySupply.util.LocationConverter;
import com.cnfantasia.server.api.gaode.entity.POI;
import com.cnfantasia.server.api.gaode.entity.PlaceReturn;
import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.api.room.service.GroupBuildingService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressBlock.service.IAddressBlockBaseService;
import com.cnfantasia.server.domainbase.addressCity.dao.IAddressCityBaseDao;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;

/**
 * 高德地图抓取Task
 * @author wenfq  2017年7月4日
 */
public class GaoDeFetchTask {

	@Resource
	IAddressCityBaseDao addressCityBaseDao;
	
	@Resource
	IAddressBlockBaseService addressBlockBaseService;
	
	@Resource
	IGroupBuildingBaseService groupBuildingBaseService;
	
	@Resource
	IUuidManager uuidManager;
	
	private static Log logger = LogFactory.getLog(GroupBuildingService.class);
	
	private final int pageNum = 20;//每页条数
	
	private final String key = "444a8c8a80df1eb4149fd473ced88957"; //用的摩拜小程序中的key，调用次数不限
	
	public Integer execTask() throws HttpException, IOException {
		int successInsert = 0;
		
		HttpUtil httpUtil = new HttpUtil();
		httpUtil.addParameter("city", "昆明");
		httpUtil.addParameter("citylimit", "true");
		httpUtil.addParameter("types", "120300"); //商务住宅;住宅区;住宅小区
		httpUtil.addParameter("key", key);
		httpUtil.addParameter("page", "1");
		httpUtil.addParameter("offset", pageNum+"");

		List<AddressCity> acList = addressCityBaseDao.selectAddressCityByCondition(null, false);
		Collections.reverse(acList);
		String url = "https://restapi.amap.com/v3/place/text";
		logger.info("url is: " + url);
		
		String[] hasImportedCities = {"哈尔滨市","葫芦岛市","铁岭市","营口市","本溪市","鞍山市","沈阳市","廊坊市","承德市","保定市","秦皇岛市","天津市","北京市","济南市","青岛市","淄博市","枣庄市","东营市","烟台市","潍坊市","济宁市","泰安市","威海市","日照市","莱芜市","临沂市","德州市","聊城市","滨州市","菏泽市","开封市","洛阳市","平顶山市","安阳市","鹤壁市","新乡市","焦作市","濮阳市","许昌市","漯河市","三门峡市","南阳市","商丘市","信阳市","周口市","驻马店市","黄石市","十堰市","宜昌市","襄阳市","鄂州市","荆门市","孝感市","荆州市","黄冈市","咸宁市","随州市","株洲市","湘潭市","衡阳市","邵阳市","岳阳市","常德市","张家界市","郴州市","永州市","怀化市","娄底市","韶关市","汕头市","江门市","湛江市","茂名市","梅州市","汕尾市","潮州市","揭阳市","云浮市","柳州市","梧州市","北海市","防城港市","钦州市","贵港市","玉林市","百色市","贺州市","河池市","来宾市","崇左市","海口市","攀枝花市","泸州市","德阳市","绵阳市","广元市","遂宁市","内江市","乐山市","南充市","眉山市","宜宾市","广安市","雅安市","巴中市","资阳市","贵阳市","六盘水市","遵义市","安顺市","毕节市","铜仁市","昆明市","曲靖市","玉溪市","保山市","昭通市","丽江市","普洱市","临沧市","拉萨市","铜川市","宝鸡市","咸阳市","渭南市","延安市","汉中市","榆林市","安康市","商洛市","兰州市","金昌市","天水市","武威市","张掖市","平凉市","酒泉市","庆阳市","定西市","陇南市","西宁市","白银市","海东市","银川市","石嘴山市","吴忠市","固原市","中卫市","乌鲁木齐市","克拉玛依市","哈密地区","济源市","仙桃市","潜江市","天门市","五指山市","琼海市","儋州市","文昌市","万宁市","东方市"};
		for (int i = 0; i < acList.size(); i++) {
			AddressCity addressCity = acList.get(i);
			
			boolean hasImported = false;
			for (String cityName : hasImportedCities) {
				if(cityName.equals(addressCity.getName())){
					hasImported = true;
					break;
				}
			}
			
			if(hasImported) continue;
			
			httpUtil.addParameter("city", addressCity.getName());
			
			AddressBlock abQry = new AddressBlock();
			abQry.settCityFId(addressCity.getId());
			List<AddressBlock> abList = addressBlockBaseService.getAddressBlockByCondition(MapConverter.convertBean(abQry));
			for (AddressBlock addressBlock : abList) {
				httpUtil.addParameter("keywords", addressBlock.getName());
				
				int thisGetCount = 0;
				int page = 1;
				do {
					httpUtil.addParameter("page", (page++) + "");
					logger.info("httpUtil's parameter is: " + httpUtil.getParameters());
					
					String returnString = httpUtil.post(url);
					logger.info(returnString);
					
					PlaceReturn placeReturn = JSON.parseObject(returnString, PlaceReturn.class);
					
					thisGetCount = placeReturn.getPois().size();
					List<GroupBuilding> gbAddList = new ArrayList<GroupBuilding>();
					List<GroupBuilding> gbUpdList = new ArrayList<GroupBuilding>();
					List<BigInteger> gbIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_group_building, thisGetCount);
					for(int j = 0; j < thisGetCount; j++){
						POI poi = placeReturn.getPois().get(j);
						String locations[] = poi.getLocation().split(",");
						Location location = new Location(locations[0], locations[1]);
						location = LocationConverter.gcj02ToBd09(location);
						
						GroupBuilding gb = new GroupBuilding();
						gb.setName(poi.getName());
						gb.settBlockFId(addressBlock.getId());
						List<GroupBuilding> groupBuildingExistList = groupBuildingBaseService.getGroupBuildingByCondition(MapConverter.toMap(gb));
						if(groupBuildingExistList.size() > 0 
								&& groupBuildingExistList.get(0).getSys0AddTime() != null
								&& DateUtils.getCompareDate(groupBuildingExistList.get(0).getSys0AddTime(), "2014-12-27 20:43:50") > 0 ){ //已存在的小区，需要更新百度坐标
							GroupBuilding gbUpd = new GroupBuilding();
							gbUpd.setId(groupBuildingExistList.get(0).getId());
							gbUpd.setBaiduLocatLat(location.getLat());
							gbUpd.setBaiduLocatLng(location.getLng());
							gbUpdList.add(gbUpd);
							continue;
						}
						
						gb.setId(gbIdList.get(j));
						gb.setSignStatus(0);
						gb.setCheckStatus(GroupBuildingDict.CheckStatus.WuXuShenHe);
						gb.setSourceType(CommonModuleDict.Data_SourceType.GAODE_MAP_API);
						
						gb.setBaiduLocatLat(location.getLat());
						gb.setBaiduLocatLng(location.getLng());
						
						gb.setAddressDesc(poi.getAddress());
						gbAddList.add(gb);
					}
					
					successInsert += groupBuildingBaseService.updateGroupBuildingBatch(gbUpdList);
					successInsert += groupBuildingBaseService.insertGroupBuildingBatch(gbAddList);
					
				} while (thisGetCount == pageNum); //本次查找的条数小于pageNum，表示已达最后一页
			}
		}
		
		return successInsert;
	}
}

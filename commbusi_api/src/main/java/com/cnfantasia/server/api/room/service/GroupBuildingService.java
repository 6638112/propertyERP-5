package com.cnfantasia.server.api.room.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.entity02.CoordinateJsonResponse;
import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.communitySupply.util.BaseFetchUtil;
import com.cnfantasia.server.api.room.dao.IGroupBuildingDao;
import com.cnfantasia.server.api.room.entity.BuildingAndRealRoomListEntity;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.SimpleGbInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
//import com.cnfantasia.server.api.groupBuilding.dao.IGroupBuildingDao;
/**
 * 描述:(小区信息) 具体业务Service层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupBuildingService implements IGroupBuildingService{
	private IGroupBuildingDao groupBuildingDao;
	public void setGroupBuildingDao(IGroupBuildingDao groupBuildingDao) {
		this.groupBuildingDao = groupBuildingDao;
	}

	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}

	private IGroupBuildingBaseService groupBuildingBaseService;
	public void setGroupBuildingBaseService(IGroupBuildingBaseService groupBuildingBaseService) {
		this.groupBuildingBaseService = groupBuildingBaseService;
	}

	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	//	@Override
//	public List<GroupBuildingEntity> getGroupBuildingDimBySearchKey(String searchKey,BigInteger cityId,String cityName,BigInteger userId) {
//		return groupBuildingDao.selectGroupBuildingDimBySearchKey(searchKey, cityId, cityName,userId);
//	}

	@Override
	public List<GroupBuildingEntity> getGroupBuildingDimBySearchKey(String searchKey,GbQueryAddressParam addressParam,
			PageModel pageModel,BigInteger userId) {
		if(searchKey!=null){
			searchKey = searchKey.trim();
		}
		return groupBuildingDao.selectGroupBuildingDimBySearchKey(searchKey, addressParam, pageModel,userId);
	}

	@Override
	public List<BuildingAndRealRoomListEntity> getBuildingAndRoomList(BigInteger groupBuildId) {
		return groupBuildingDao.selectBuildingAndRoomList(groupBuildId);
	}

	/**
	 * 根据小区id查询小区信息（gbName, blockName, cityName, provinceName）
	 * @param gbId
	 * @return
	 */
	@Override
	public Map<String, Object> queryGroupBuildingInfoById(BigInteger gbId){
		return groupBuildingDao.queryGroupBuildingInfoById(gbId);
	}
	
	/**
	 * 根据市、区、小区名称查询小区是否存在，并返回其id
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectGroupBuildingByAddress(Map<String, Object> map) {
		return groupBuildingDao.selectGroupBuildingByAddress(map);
	}

	@Override
	public int updateGroupBuildingBillAvgBacth(List<Map<String, Object>> bgAvgList) {
		return groupBuildingDao.updateGroupBuildingBillAvgBacth(bgAvgList);
	}

	/**
	 * 获取经纬度，房间号传了按房间查，不传按小区查，不存在则返回null
	 * @param gbId 小区ID
	 * @param roomId 房间ID
     * @return
     */
	@Override
	public Location getLocationByGbIdOrRoomId(BigInteger gbId, BigInteger roomId) {
		Location location = null;
		if (roomId != null) {
			Map<String, Object> roomAddressIdByRoom = commonRoomService.getRoomAddressIdByRoom(roomId);
			gbId = new BigInteger(roomAddressIdByRoom.get("gbId").toString());
		}
		if (gbId != null) {
			GroupBuilding groupBuilding = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
			if (groupBuilding.getBaiduLocatLat() != null && groupBuilding.getBaiduLocatLng() != null) {
				location = new Location();
				location.setLat(groupBuilding.getBaiduLocatLat());
				location.setLng(groupBuilding.getBaiduLocatLng());
			} else {
				Map<String, Object> addressName = queryGroupBuildingInfoById(gbId);
				String city = addressName.get("cityName") + "";
				String detailAddr = addressName.get("blockName") + "" + addressName.get("gbName");
				try {
					CoordinateJsonResponse.CoordinateResult locationByName =
							BaseFetchUtil.getLocationByName(sysParamManager.getSysParaValue(SysParamKey.BAIDU_LBS_AK), city, detailAddr);
					groupBuilding.setBaiduLocatLat(locationByName.getLocation().getLat());
					groupBuilding.setBaiduLocatLng(locationByName.getLocation().getLng());
					groupBuilding.setBaiduPrecise(Long.valueOf(locationByName.getPrecise()));
					groupBuilding.setBaiduLevel(locationByName.getLevel());
					groupBuildingBaseService.updateGroupBuilding(groupBuilding);
					location = locationByName.getLocation();
				} catch (Exception e) {
					System.out.println("查询经纬度错误");
				}
			}
		}
		return location;
	}

	@Override
	public BigInteger getGroupBuildingByLocationCondition(Map<String, Object> paramMap) {
		return groupBuildingDao.getGroupBuildingByLocationCondition(paramMap);
	}
	
	
	/**
	 * 根据省市区查询省市区的信息（ blockName, cityName, provinceName）
	 * @param gbId
	 * @return
	 */
	@Override
	public Map<String, Object> queryAddressInfo_By_City_Province_Name(Map paramMap){
		return groupBuildingDao.queryAddressInfo_By_City_Province_Name(paramMap);
	}

	@Override
	public List<SimpleGbInfo> getNearbyGbs(BigDecimal lng, BigDecimal lat) {
		return groupBuildingDao.getNearbyGbs(lng, lat);
	}

	@Override
	public List<SimpleGbInfo> getRecentGbs(BigInteger userId) {
		SimpleGbInfo defaultGbInfo = groupBuildingDao.getDefaultGbInfo(userId);
		List<SimpleGbInfo> recentGbs = groupBuildingDao.getRecentGbs(userId);
		if (defaultGbInfo == null) {
			return recentGbs;
		}
		List<SimpleGbInfo> resList = new ArrayList<>();
		resList.add(defaultGbInfo);
		if (!DataUtil.isEmpty(recentGbs)) {
			for (SimpleGbInfo recentGb : recentGbs) {
				if (resList.size() == 3) {
					break;
				}
				if (recentGb.getGbId().compareTo(defaultGbInfo.getGbId()) != 0) {
					resList.add(recentGb);
				}
			}
		}
		return resList;
	}
}

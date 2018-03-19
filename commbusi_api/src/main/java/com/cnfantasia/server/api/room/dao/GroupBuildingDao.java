package com.cnfantasia.server.api.room.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.room.entity.BuildingAndRealRoomListEntity;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.SimpleGbInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
/**
 * 描述:(小区信息) 具体业务Dao层实现
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingDao extends AbstractBaseDao implements IGroupBuildingDao{
//	@Override
//	public List<GroupBuildingEntity> selectGroupBuildingDimBySearchKey(String searchKey,BigInteger cityId,String cityName,BigInteger userId) {
//		Map<String,Object> qryMap = new HashMap<String, Object>();
//		qryMap.put("searchKey", searchKey);
//		qryMap.put("cityId", cityId);
//		qryMap.put("cityName", cityName);
//		qryMap.put("userId", userId);
//		return sqlSession.selectList("groupBuilding.select_GroupBuildingDim_BySearchKey", qryMap);
//	}

	@Override
	public List<GroupBuildingEntity> selectGroupBuildingDimBySearchKey(String searchKey,GbQueryAddressParam addressParam, PageModel pageModel,BigInteger userId) {
		BigInteger cityId = addressParam.getCityId();
		BigInteger blockId = addressParam.getBlockId();
		String cityName = addressParam.getCityName();
		
		Map<String,Object> qryMap = new HashMap<String, Object>();
		qryMap.put("searchKey", searchKey);
		qryMap.put("cityId", cityId);
		qryMap.put("blockId", blockId);
		qryMap.put("cityName", cityName);
		qryMap.put("userId", userId);
		String pageSqlKey = "groupBuilding.select_GroupBuildingDim_BySearchKey_WithPage";
		String countSqlKey = "groupBuilding.select_GroupBuildingDim_BySearchKey_Count";
		List<GroupBuildingEntity> resList = PageQueryUtil.selectPageList(sqlSession, qryMap, pageModel, pageSqlKey, countSqlKey);
		return resList;
	}

	@Override
	public List<BuildingAndRealRoomListEntity> selectBuildingAndRoomList(BigInteger groupBuildId) {
		List<BuildingAndRealRoomListEntity> resList = sqlSession.selectList("groupBuilding.select_BuildingAndRoomList_ByGroupBuildingId", groupBuildId);
		return resList;
	}
	
	@Override
	public GroupBuildingEntity selectGroupBuildingEntityById(BigInteger groupBuildingId,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildingId", groupBuildingId);
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("groupBuilding.select_GroupBuildingEntity_ById", tmpMap);
	}
	
	/**
	 * 根据小区id查询小区信息（gbName, blockName, cityName, provinceName）
	 * @param gbId
	 * @return
	 */
	@Override
	public Map<String, Object> queryGroupBuildingInfoById(BigInteger gbId){
		return sqlSession.selectOne("groupBuilding.select_groupBuilding_info_by_id", gbId);
	}
	
	/**
	 * 根据省市区查询省市区的信息（ blockName, cityName, provinceName）
	 * @param gbId
	 * @return
	 */
	@Override
	public Map<String, Object> queryAddressInfo_By_City_Province_Name(Map paramMap){
		return sqlSession.selectOne("groupBuilding.select_groupBuilding_info_by_block_city_province", paramMap);
	}

	@Override
	public List<SimpleGbInfo> getNearbyGbs(BigDecimal lng, BigDecimal lat) {
		Map<String, BigDecimal> params = new HashMap<>();
		params.put("lng", lng);
		params.put("lat", lat);
		return sqlSession.selectList("groupBuilding.getNearbyGbs", params);
	}

	@Override
	public List<SimpleGbInfo> getRecentGbs(BigInteger userId) {
		return sqlSession.selectList("groupBuilding.getRecentGbs", userId);
	}

	@Override
	public SimpleGbInfo getDefaultGbInfo(BigInteger userId) {
		return sqlSession.selectOne("groupBuilding.getDefaultGbInfo", userId);
	}

	/**
	 * 根据市、区、小区名称查询小区是否存在，并返回其id
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectGroupBuildingByAddress(Map<String, Object> map) {
		return sqlSession.selectList("groupBuilding.select_group_building_by_address", map);
	}

	@Override
	public int updateGroupBuildingBillAvgBacth(List<Map<String, Object>> bgAvgList) {
		return sqlSession.update("groupBuilding.updateGroupBuildingBillAvgBacth", bgAvgList);
	}

	@Override
	public BigInteger getGroupBuildingByLocationCondition(Map<String, Object> paramMap) {
		BigInteger gbId = null;
		gbId = sqlSession.selectOne("groupBuilding.getGroupBuildingByLocationCondtion1", paramMap);
		
	  	if(gbId == null){
			gbId = sqlSession.selectOne("groupBuilding.getGroupBuildingByLocationCondtion2", paramMap);
		}
		if(gbId == null){
			gbId = sqlSession.selectOne("groupBuilding.getGroupBuildingByLocationCondtion3", paramMap);
		}
		
		if(gbId == null) //如果前面都 没有找到小区，给默认的小区，即解放区实验小区
			gbId = new BigInteger("-1");
		
		return gbId;
	}
}

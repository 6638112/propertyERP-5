package com.cnfantasia.server.ms.groupBuilding.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domainbase.groupBuilding.dao.GroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.payBill.entity.PrintConfigList;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class GroupBuildingDao extends GroupBuildingBaseDao implements IGroupBuildingDao {

	@Override
	public List<GroupBuildingSimpleViewEntity> selectGroupBuildingByOmsUser(Map<String, Object> paramMap) {
		return sqlSession.selectList("groupBuilding.select_groupBuilding_forAddSupply", paramMap);
	}

	@Override
	public List<GroupBuildingSimpleViewEntity> selectGroupBuildingBySupply(Map<String, Object> paramMap) {
		return sqlSession.selectList("groupBuilding.select_groupBuilding_forSupplyService", paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("groupBuilding.select_groupBuilding_forList", paramMap);
	}

	@Override
	public int selectGroupBuildingForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "groupBuilding.select_groupBuilding_forList", paramMap);
	}

	@Override
	public GroupBuildingSimpleEntity selectGroupBuildingById(BigInteger id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", id);
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		return sqlSession.selectOne("groupBuilding.select_groupBuilding_byId", paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingForDialogList(Map<String, Object> paramMap) {
		return sqlSession.selectList("groupBuilding.select_groupBuilding_forDialogList", paramMap);
	}

	@Override
	public Long queryGroupbuildingIsExists(Map<String, Object> paramMap) {
		return sqlSession.selectOne("groupBuilding.select_groupBuilding_isExists", paramMap);
	}

	@Override
	public List<GroupBuildingSimpleEntity> selectGroupBuildingForCPList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("groupBuilding.select_groupBuilding_forCPList", paramMap);
	}

	@Override
	public int selectGroupBuilding4CPForCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "groupBuilding.select_groupBuilding_forCPList", paramMap);
	}

	@Override
	public GroupBuildingSimpleEntity selectGroupBuildingByGbrId(BigInteger id) {
		return sqlSession.selectOne("groupBuilding.select_groupBuilding_byGbrId", id);
	}
	
	/**
	 * 查询短信通知的所有手机号码
	 * 
	 * @param msgType
	 * @param gbId
	 * @return
	 */
	@Override
	public List<String> queryMobiles(String msgType, String gbId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("msgType", msgType);
		paramMap.put("gbId", gbId);
		
		return sqlSession.selectList("groupBuilding.select_mobiles", paramMap);
	}


	@Override
	public List<Map<String, Object>> getBuildingListByNameAndCityId(String name, BigInteger cityId, PageModel pageModel) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("cityId", cityId);
		if (pageModel != null) {
			param.put("begin", pageModel.getBegin());
			param.put("length", pageModel.getLength());
		}
		return sqlSession.selectList("groupBuilding.getBuildingListByNameAndCityId", param);
	}

	@Override
	public List<Map<String, Object>> getBuildingListByNameAndBlockId(String name, BigInteger blockId, List<BigInteger> gbIdList) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("blockId", blockId);
		param.put("gbIdList", gbIdList);
		return sqlSession.selectList("groupBuilding.getBuildingListByNameAndBlockId", param);
	}

	/**
	 * 根据小区名称及楼下店供应商服务范围查询小区
	 * 
	 * @param gbName
	 * @param merchantId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> searchByNameAndMerchantServiceArea(String gbName, BigInteger merchantId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gbName", gbName);
		param.put("merchantId", merchantId);
		return sqlSession.selectList("groupBuilding.searchByNameAndMerchantServiceArea", param);
	}
	
	@Override
	public List<Map<String, Object>> getBuildingListBySeqIdList(List<BigInteger> ids) {
		return sqlSession.selectList("groupBuilding.getBuildingListBySeqIdList", ids);
	}

	@Override
	public List<Map<String, Object>> getBuildingListForSelected(Map<String, Object> paramMap) {
		return sqlSession.selectList("groupBuilding.select_groupBuilding_forSelectList", paramMap);
	}

	@Override
	public List<BigInteger> selectGroupBuildingRealRoomList(BigInteger gbId) {
		return sqlSession.selectList("groupBuilding.select_groupBuilding_realRoom", gbId);
	}

	@Override
	public List<Map<String, Object>> selectBillTypeListByGbId(BigInteger gbId, String paytimeType, Integer propertyPeriodType, String isMeterReading) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gbId", gbId);
		param.put("paytimeType", paytimeType);
		param.put("propertyPeriodType", propertyPeriodType);
		param.put("isMeterReading", isMeterReading);
		return sqlSession.selectList("groupBuilding.select_groupBuilding_billType_by_gbId", param);
	}

	@Override
	public List<GroupBuilding> getgbListByGbIds(Map<String, Object> param) {
		return sqlSession.selectList("groupBuilding.getgbListByGbIds", param);
	}


	@Override
	public int updateGroupBuilding02(GroupBuilding groupBuilding) {
		return sqlSession.update("groupBuilding.update_groupBuilding", groupBuilding);
	}

	@Override
	public Integer isHasEmptyProprietorByGbId(BigInteger gbId) {
		return sqlSession.selectOne("groupBuilding.select_isHas_empty_proprietor_byGbId", gbId);
	}
	
	@Override
	public Integer selectProprietorByGbIdCount(BigInteger gbId) {
		return sqlSession.selectOne("groupBuilding.select_proprietor_byGbId_count", gbId);
	}

	@Override
	public int updateHistoryManagementRevenueData(BigInteger gbId, BigInteger managementId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("gbId", gbId);
		param.put("managementId", managementId);
		return sqlSession.update("groupBuilding.update_history_management_revenue_data", param);
	}

	@Override
	public Integer getBuildingAndRoomCountByGbId(BigInteger gbId) {
		return sqlSession.selectOne("groupBuilding.getBuildingAndRommCountByGbId", gbId);
	}
	
	@Override
	public List<PrintConfigList> getGbListForPrint(Map<String, Object> paramMap){
		return sqlSession.selectList("groupBuilding.getGbsForPrint", paramMap);
	}
	
	@Override
	public Integer getGbCountForPrint(Map<String, Object> paramMap){
		return sqlSession.selectOne("groupBuilding.getGbCountForPrint", paramMap);
	}

	@Override
	public int getBuildingListForSelectedCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession,"groupBuilding.select_groupBuilding_forSelectList", paramMap);
	}

	@Override
	public List<Map<BigInteger, String>> getGroupBuildingList(Map<String, Object> paraMap) {
		return sqlSession.selectList("groupBuilding.getGroupBuildingList", paraMap);
	}
}

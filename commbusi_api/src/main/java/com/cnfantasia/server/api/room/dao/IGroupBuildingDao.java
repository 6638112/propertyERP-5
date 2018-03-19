package com.cnfantasia.server.api.room.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.room.entity.BuildingAndRealRoomListEntity;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.SimpleGbInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
/**
 * 描述:(小区信息) 具体业务Dao层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingDao {
	/**
	 * 根据关键字搜索小区信息列表
	 * @param searchKey
	 * @return
	 */
//	public List<GroupBuildingEntity> selectGroupBuildingDimBySearchKey(String searchKey,BigInteger cityId,String cityName,BigInteger userId);
//	public List<GroupBuildingEntity> selectGroupBuildingDimBySearchKey(String searchKey,BigInteger cityId,BigInteger blockId,String cityName,PageModel pageModel,BigInteger userId);
	List<GroupBuildingEntity> selectGroupBuildingDimBySearchKey(String searchKey, GbQueryAddressParam addressParam, PageModel pageModel, BigInteger userId);
	
	/**
	 * 根据小区查询其下的所有建筑和门牌
	 * @param groupBuildId
	 * @return
	 */
	List<BuildingAndRealRoomListEntity> selectBuildingAndRoomList(BigInteger groupBuildId);
	
	/**
	 * 根据小区Id查询对应的小区信息，包含支持
	 * @param groupBuildingId
	 * @return
	 */
	GroupBuildingEntity selectGroupBuildingEntityById(BigInteger groupBuildingId, BigInteger userId);
	
	/**
	 * 根据小区id查询小区信息（gbName, blockName, cityName, provinceName）
	 * @param gbId
	 * @return
	 */
	Map<String, Object> queryGroupBuildingInfoById(BigInteger gbId);
	
	/**
	 * 根据市、区、小区名称查询小区是否存在，并返回其id
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectGroupBuildingByAddress(Map<String, Object> map);

	/**
	 * 批量更新小区平均账单金额
	 * @param bgAvgList
	 * @return
	 */
	int updateGroupBuildingBillAvgBacth(List<Map<String, Object>> bgAvgList);

	BigInteger getGroupBuildingByLocationCondition(Map<String, Object> paramMap);

	/**
	 * 查询全地址信息
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> queryAddressInfo_By_City_Province_Name(Map paramMap);

	List<SimpleGbInfo> getNearbyGbs(BigDecimal lng, BigDecimal lat);

	List<SimpleGbInfo> getRecentGbs(BigInteger userId);

	SimpleGbInfo getDefaultGbInfo(BigInteger userId);

}

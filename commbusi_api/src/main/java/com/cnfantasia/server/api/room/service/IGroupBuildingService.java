package com.cnfantasia.server.api.room.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.room.entity.BuildingAndRealRoomListEntity;
import com.cnfantasia.server.api.room.entity.GbQueryAddressParam;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.SimpleGbInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
/**
 * 描述:(小区信息) 具体业务Service层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IGroupBuildingService {
	/**
	 * 根据关键字搜索小区信息列表
	 * @param searchKey
	 * @return
	 */
//	public List<GroupBuildingEntity> getGroupBuildingDimBySearchKey(String searchKey,BigInteger cityId,String cityName,BigInteger userId);
//	public List<GroupBuildingEntity> getGroupBuildingDimBySearchKey(String searchKey,BigInteger cityId,BigInteger blockId,String cityName,PageModel pageModel,BigInteger userId);
	public List<GroupBuildingEntity> getGroupBuildingDimBySearchKey(String searchKey,GbQueryAddressParam addressParam,PageModel pageModel,BigInteger userId);
	/**
	 * 根据小区查询其下的所有建筑和门牌
	 * @param groupBuildId
	 * @return
	 */
	public List<BuildingAndRealRoomListEntity> getBuildingAndRoomList(BigInteger groupBuildId);
	
	/**
	 * 根据小区id查询小区信息（gbName, blockName, cityName, provinceName）
	 * @param gbId
	 * @return
	 */
	public Map<String, Object> queryGroupBuildingInfoById(BigInteger gbId);
	
	/**
	 * 根据市、区、小区名称查询小区是否存在，并返回其id
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectGroupBuildingByAddress(Map<String, Object> map);
	
	/**
	 * 批量更新小区平均账单金额
	 * @param bgAvgList
	 */
	public int updateGroupBuildingBillAvgBacth(List<Map<String, Object>> bgAvgList);

	/**
	 * 获取经纬度，房间号传了按房间查，不传按小区查，不存在则返回null
	 * @param gbId 小区ID
	 * @param roomId 房间ID
	 * @return
	 */
	public Location getLocationByGbIdOrRoomId(BigInteger gbId, BigInteger roomId);
//	/**
//	 * 根据小区Id查询对应的小区信息
//	 * @param groupBuildingId
//	 * @return
//	 */
//	public GroupBuildingEntity getGroupBuildingEntityById(BigInteger groupBuildingId);
	
//	/**
//	 * 街坊消息，定时扫描小区表，给未推送物业全面签约的小区推送缴费的博客消息
//	 * */
//	public void autoPushMicroblogForSignGroupbuild();
	
	/**
	 * 根据前端定位返回最符合条件的一个小区Id。<p>
	 * 最“符合”条件小区：1先按全条件过滤查找，能找到则返回；2按行政区里找到签约的小区，能找到则返回；3按市范围找到签约小区；4 其它规则待定，未定义时先返回null
	 * @param paramMap 前端定位信息：省名 provinceName，市名 cityName、行政区名 blockName、小区名 gbName、经度 longitude， 纬度  latitude 
	 * @return
	 */
	public BigInteger getGroupBuildingByLocationCondition(Map<String, Object> paramMap);
	
	Map<String, Object> queryAddressInfo_By_City_Province_Name(Map paramMap);

	/**
	 * 根据经纬度查询附近的小区
	 * @param lng 经度
	 * @param lat 纬度
	 * @return
	 */
	List<SimpleGbInfo> getNearbyGbs(BigDecimal lng, BigDecimal lat);

	List<SimpleGbInfo> getRecentGbs(BigInteger userId);
}

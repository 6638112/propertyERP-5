package com.cnfantasia.server.ms.groupBuilding.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.payBill.entity.PrintConfigList;

public interface IGroupBuildingDao extends IGroupBuildingBaseDao {
	/**
	 * 物业新增商铺查询所属小区
	 * @author huangzj
	 * @param adminId
	 * */
	List<GroupBuildingSimpleViewEntity> selectGroupBuildingByOmsUser(Map<String, Object> paramMap);
	
	/**
	 * 物业新增商铺查询服务小区
	 * @author huangzj
	 * @param adminId
	 * */
	List<GroupBuildingSimpleViewEntity> selectGroupBuildingBySupply(Map<String, Object> paramMap);
	
	/**
	 * 小区管理查询List
	 * @author huangzj
	 * @param paramMap
	 * */
	List<GroupBuildingSimpleEntity> selectGroupBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap);
	
	/**
	 * 小区管理查询Count
	 * @author huangzj
	 * @param paramMap
	 * */
	int selectGroupBuildingForCount(Map<String, Object> paramMap);
	
	/**
	 * 小区管理查询明细
	 * @author huangzj
	 * @param id
	 * */
	GroupBuildingSimpleEntity selectGroupBuildingById(BigInteger id);
	
	/**
	 * 小区管理弹出窗查询List
	 * @author huangzj
	 * @param paramMap
	 * */
	List<GroupBuildingSimpleEntity> selectGroupBuildingForDialogList(Map<String, Object> paramMap);
	
	/**
	 * 添加小区验证小区是否重复(小区名+所属省市区ID)
	 * */
	Long queryGroupbuildingIsExists(Map<String, Object> paramMap);

	/**
	 * 渠道新增小区审核
	 * @param curPageIndex
	 * @param pageSize
	 * @param paramMap
	 * @return
	 */
	List<GroupBuildingSimpleEntity> selectGroupBuildingForCPList(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	int selectGroupBuilding4CPForCount(Map<String, Object> paramMap);

	/**
	 *  根据渠道推荐的注册小区的ID，获取小区信息
	 */
	GroupBuildingSimpleEntity selectGroupBuildingByGbrId(BigInteger id);

	
	/**
	 * 查询短信通知的所有手机号码
	 * 
	 * @param msgType
	 * @param gbId
	 * @return
	 */
	List<String> queryMobiles(String msgType, String gbId);


	List<Map<String, Object>> getBuildingListByNameAndCityId(String name, BigInteger cityId, PageModel pageModel);
	List<Map<String, Object>> getBuildingListByNameAndBlockId(String name, BigInteger blockId, List<BigInteger> gbIdList);

	/**
	 * 根据小区名称及楼下店供应商服务范围查询小区
	 * 
	 * @param gbName
	 * @param merchantId
	 * @return
	 */
	List<Map<String, Object>> searchByNameAndMerchantServiceArea(String gbName, BigInteger merchantId);

	List<Map<String,Object>> getBuildingListBySeqIdList(List<BigInteger> ids);
	
	/**
	 * 查询 当前用户下的小区信息 （下拉列表）
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>>getBuildingListForSelected(Map<String, Object> paramMap);

	/**
	 * 查询 小区下的所有真实门牌
	 * @param gbId
	 * @return
	 */
	List<BigInteger> selectGroupBuildingRealRoomList(BigInteger gbId);
	
	/**
	 * 更新(小区信息)信息
	 * @param groupBuilding
	 * @return
	 */
	int updateGroupBuilding02(GroupBuilding groupBuilding);

	/**
	 * 查询小区下的门牌业主信息未登记的记录数
	 * @param gbId
	 * @return
	 */
	Integer isHasEmptyProprietorByGbId(BigInteger gbId);

	/**
	 * 查询小区下共有多少个门牌业主信息
	 * @param gbId
	 * @return
	 */
	Integer selectProprietorByGbIdCount(BigInteger gbId);

	/**
	 * 管理处历史数据更新
	 * @param gbId
	 * @param managementId
	 */
	int updateHistoryManagementRevenueData(BigInteger gbId, BigInteger managementId);
	
	/**
	 * 查询小区下的缴费类型
	 * @param paytimeType 
	 * @param bgId
	 * @param propertyPeriodType
	 * @param isMeterReading 是否抄表
	 * @return
	 */
	List<Map<String, Object>> selectBillTypeListByGbId(BigInteger bgId, String paytimeType, Integer propertyPeriodType, String isMeterReading);

	List<GroupBuilding> getgbListByGbIds(Map<String, Object> param);

	/**
	 * 根据小区id来查询  小区下楼栋房号的数据（现在为excel导出信息校验所用）
	 * @param gbId
	 * @return
     */
	Integer getBuildingAndRoomCountByGbId(BigInteger gbId);
	
	List<PrintConfigList> getGbListForPrint(Map<String, Object> paramMap);
	
	Integer getGbCountForPrint(Map<String, Object> paramMap);

	int getBuildingListForSelectedCount(Map<String, Object> paramMap);

	List<Map<BigInteger,String>> getGroupBuildingList(Map<String, Object> paraMap);
}

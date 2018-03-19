package com.cnfantasia.server.ms.groupBuilding.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.payBill.entity.PrintConfigList;

public interface IGroupBuildingService extends IGroupBuildingBaseService {
	/**
	 * 物业新增商铺查询所属小区
	 *
	 * @param adminId
	 * @author huangzj
	 */
	List<GroupBuildingSimpleViewEntity> selectGroupBuildingByOmsUser(Map<String, Object> paramMap);

	/**
	 * 物业新增商铺查询服务小区
	 *
	 * @param adminId
	 * @author huangzj
	 */
	List<GroupBuildingSimpleViewEntity> selectGroupBuildingBySupply(Map<String, Object> paramMap);

	/**
	 * 小区管理查询List
	 *
	 * @param paramMap
	 * @author huangzj
	 */
	List<GroupBuildingSimpleEntity> selectGroupBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	/**
	 * 小区管理查询Count
	 *
	 * @param paramMap
	 * @author huangzj
	 */
	int selectGroupBuildingForCount(Map<String, Object> paramMap);

	/**
	 * 小区管理查询明细
	 *
	 * @param id
	 * @author huangzj
	 */
	GroupBuildingSimpleEntity selectGroupBuildingById(BigInteger id);

	/**
	 * 小区管理查询明细
	 *
	 * @param id
	 * @author huangzj
	 */
	int deleteGroupBuildingById(String id);

	/**
	 * 保存小区信息
	 *
	 * @param id
	 * @param gbName
	 * @param blockId
	 * @param addressDetail
	 * @param streetName
	 * @param streetTel
	 * @param neighborName
	 * @param neighborTel
	 * @param payPeriodStart
	 * @param payPeriodEnd
	 * @author huangzj
	 * @param settlementDay 
	 */
	int saveOrUpdateGroupBuilding(String id, String gbName,
								  String blockId, String addressDesc, String managementId, String companyId, String streetName, String streetTel,
								  String neighborName, String neighborTel);

	/**
	 * 保存审核信息
	 *
	 * @param id
	 * @param payPeriodStart
	 * @param payPeriodEnd
	 * @author huangzj
	 */
	int saveAuditGroupBuilding(String id, String payPeriodStart, String payPeriodEnd);

	/**
	 * 保存后台管理编辑的信息
	 *
	 * @param id
	 * @param propertyMonthChange2
	 * @author huangzj
	 */
	int saveAuditEditGroupBuilding(GroupBuilding groupBuilding, GbSoftwareConfig config);

	/**
	 * 小区管理弹出窗查询List
	 *
	 * @param paramMap
	 * @author huangzj
	 */
	List<GroupBuildingSimpleEntity> selectGroupBuildingForDialogList(Map<String, Object> paramMap);

	/**
	 * 添加小区验证小区是否重复(小区名+所属省市区ID)
	 */
	Long queryGroupbuildingIsExists(Map<String, Object> paramMap);

	List<GroupBuildingSimpleEntity> selectGroupBuildingForCPList(int curPageIndex, int pageSize, Map<String, Object> paramMap);

	int selectGroupBuilding4CPForCount(Map<String, Object> paramMap);

	int saveAuditGroupBuilding4CP(String id, String auditStatus, String auditDesc);

	/**
	 * 根据GroupBuildingRegister的ID获取待审核的小区信息
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

	List<Map<String, Object>> getBuildingListBySeqIdList(List<BigInteger> ids);

	List<Map<String, Object>> getBuildingListForSelected(Map<String, Object> paramMap);

	/**
	 * 查询 小区下的所有真实门牌
	 * @param bigInteger 
	 * @return
	 */
	List<BigInteger> selectGroupBuildingRealRoomList(BigInteger bigInteger);

	/**
	 * 根据小区id获取小区下的所有缴费类型 下拉列表
	 * @param valueOf
	 * @param paytimeType 
	 * @param isMeterReading 是否抄表
	 * @return
	 */
	List<Map<String, Object>> selectBillTypeListByGbId(BigInteger valueOf, String paytimeType, String isMeterReading);

	/**
	 * 判断小区下的门牌是否都登记了业主姓名
	 * @param gbId
	 * @return
	 */
	int isHasEmptyProprietorByGbId(BigInteger gbId);

	/**
	 * 更新历史收益数据
	 * @param groupBuilding
	 * @param managementId
	 */
	void updateHistoryManagementRevenueData(GroupBuilding groupBuilding, String managementId);

	/**
	 * 根据小区IdList查询所有小区
	 * @param gbIdList
	 * @return
     */
	List<GroupBuilding> getgbListByGbIds(List<BigInteger> gbIdList, PageModel pageModel);
	
	List<PrintConfigList> getGbListForPrint(Map<String, Object> paramMap);
	
	Integer getGbCountForPrint(Map<String, Object> paramMap);

	/**
	 * 可以进行缴费的小区数量
	 * @param paramMap
	 * @return
     */
	int getBuildingListForSelectedCount(Map<String, Object> paramMap);

	/**
	 * 查询小区列表（带权限的）--为下来选择列表使用
	 * @param paraMap
	 * @return
     */
	List<Map<BigInteger,String>> getGroupBuildingList(Map<String, Object> paraMap);
}

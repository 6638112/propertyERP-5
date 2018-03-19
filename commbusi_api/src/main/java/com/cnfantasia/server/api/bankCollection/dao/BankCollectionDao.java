package com.cnfantasia.server.api.bankCollection.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.bankCollection.entity.BCHistoryDto;
import com.cnfantasia.server.api.bankCollection.entity.BcBankInfoDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoByGbDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoByPPDto;
import com.cnfantasia.server.api.bankCollection.entity.BcPrintDetail;
import com.cnfantasia.server.api.bankCollection.entity.BillCycleAndTypeName;
import com.cnfantasia.server.api.bankCollection.entity.PPBCInfo4List;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.entity.RebackRecordDto;
import com.cnfantasia.server.api.bankCollection.entity.RoomEntity;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity.BcGroupBuildingCycleEntry;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

public class BankCollectionDao extends AbstractBaseDao{

	/**
	 * 查询托收银行维护列表
	 * @param pcId
	 * @return
	 */
	public List<BcBankInfoDto> selectBcBankInfoList(BigInteger pcId){
		return sqlSession.selectList("bankCollection.selectBcBankInfoList", pcId);
	}

	/**
	 * 业主托收信息列表
	 * @param paramMap
	 * @return
     */
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByCondition(Map<String, Object> paramMap) {
		return sqlSession.selectList("bankCollection.getPropertyProprietorBankCollectionInfoByCondition", paramMap);
	}

	public int getPropertyProprietorBankCollectionInfoByConditionCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "bankCollection.getPropertyProprietorBankCollectionInfoByCondition", paramMap);
	}
	/**
	 * 逻辑删除托收时间配置
	 * @param pcId
	 * @return
	 */
	public Integer deleteBankCollectionDateByPcId(BigInteger pcId){
		return sqlSession.selectOne("bankCollection.deleteBankCollectionDateByPcId", pcId);
	}

	/**
	 * 找到所有物业公司托收配置
	 * @author wenfq
	 * @return
	 */
	public List<PropertyCompanyBCInfo> selectAllBankCollectionInfoList() {
		return sqlSession.selectList("bankCollection.selectAllBankCollectionInfoList");
	}

	/**
	 * 传入托收小区ID集，找出其名下所有可缴账期列表
	 * @author wenfq
	 * @param gbIdList
	 * @return
	 */
	public List<BillCycleAndTypeName> selectGBBCListByGbIDList(List<BigInteger> gbIdList) {
		return sqlSession.selectList("bankCollection.selectGBBCListByGbIDList", gbIdList);
	}

	/**
	 * 出盘文件定义列表，经过从左往右排序的
	 * @param bcInfoId 物业配置托收ID
	 * @param isSumFile  0明细文件，1汇总文件
	 * @return
	 */
	public List<BcFileDefine> selectFileDefineList(BigInteger bcInfoId, int isSumFile) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bcInfoId", bcInfoId);
		paramMap.put("isSumFile", isSumFile);
		return sqlSession.selectList("bankCollection.selectFileDefineList", paramMap);
	}

	/**
	 * 查询小区信息
	 * @param gbId
	 * @return
     */
	public List<RoomEntity> queryRoomForList(BigInteger gbId) {
		return sqlSession.selectList("bankCollection.queryRoomForList", gbId);
	}
	/**
	 * 物业公司对应的所有管理处、小区信息
	 * @param pcId
	 * @return
	 */
	public List<BcInfoByGbDto> selectBcInfoByGB(Map<String, Object> paramMap){
		return sqlSession.selectList("bankCollection.selectBcInfoByGB", paramMap);
	}
	

	/**物业公司对应的所有用户托收信息*/
	public List<BcInfoByPPDto> selectBcInfoByPP(Map<String, Object> paramMap){
		return sqlSession.selectList("bankCollection.selectBcInfoByPP", paramMap);
	}
	
	
	
	/**
	 * 按小区托盘重复性校验
	 * @param paramMap
	 * @return
	 */
	public Integer selectRepeatGbCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("bankCollection.selectRepeatGbCount", paramMap);
	}
	
	/**
	 * 按业主托盘重复性校验
	 * @param paramMap
	 * @return
	 */
	public Integer selectRepeatPpCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("bankCollection.selectRepeatPpCount", paramMap);
	}
	
	// TODO:
/*	public Integer lockForsaveBankCollectionInfo(BigInteger pcId){
		return sqlSession.selectOne("bankCollection.lockForsaveBankCollectionInfo", pcId);
	}*/

	/**
	 * 查询回盘信息对应的银行信息
	 * @param bcgroupBuildingCycleId
	 * @return
     */
	public BcFinanceOrg getBcFinanceOrg(BigInteger bcgroupBuildingCycleId) {
		return sqlSession.selectOne("bankCollection.getBcFinanceOrg", bcgroupBuildingCycleId);
	}

	public List<BcFileDefine> selectFileDefineListByFinanceOrgId(BigInteger financeOrgId) {
		return sqlSession.selectList("bankCollection.selectFileDefineListByFinanceOrgId", financeOrgId);
	}
	/**
	 * 根据t_property_company_bank_collection_info表字段逻辑删除t_bc_info_has_t_group_building
	 * @param pcbciId
	 * @return
	 */
	public Integer deleteBcInfoHasTGroupBuildingByPcbciId(BigInteger pcbciId){
		return sqlSession.selectOne("bankCollection.deleteBcInfoHasTGroupBuildingByPcbciId", pcbciId);
	}
	
	/**
	 * 根据t_bc_info_has_t_property_proprietor表字段逻辑删除t_bc_info_has_t_group_building
	 * @param pcbciId
	 * @return
	 */
	public Integer deleteBcInfoHasTPropertyProprietorByPcbciId(BigInteger pcbciId){
		return sqlSession.selectOne("bankCollection.deleteBcInfoHasTPropertyProprietorByPcbciId", pcbciId);
	}
	
	/**
	 * 托收数据列表查询
	 * @param paramMap
	 * @return
	 */
	public List<BCHistoryDto> selectBCHistoryForList(Map<String, Object> paramMap){
		return sqlSession.selectList("bankCollection.selectBCHistoryForList", paramMap);
	}
	
	/**
	 * 托收数据列表条数查询
	 * @param paramMap
	 * @return
	 */
	public Integer selectBCHistoryForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("bankCollection.selectBCHistoryForCount", paramMap);
	}
	
	/**
	 * 按小区确认回盘
	 * @param bgbcId
	 * @return
	 */
	public Integer confirmBcByGB(BigInteger bgbcId){
		return sqlSession.selectOne("bankCollection.confirmBcByGB", bgbcId);
	}
	
	/**
	 * 按业主确认回盘
	 * @param bgbcId
	 * @return
	 */
	public Integer confirmBcByPP(Map<String, Object> paramMap){
		return sqlSession.selectOne("bankCollection.confirmBcByPP", paramMap);
	}
	
	/**
	 * 查看回盘结果列表
	 * @param paramMap
	 * @return
	 */
	public List<RebackRecordDto> selectRebackRecordForList(Map<String, Object> paramMap){
		return sqlSession.selectList("bankCollection.selectRebackRecordForList", paramMap);
	}
	
	/**
	 * 查看回盘结果列表数据条数
	 * @param paramMap
	 * @return
	 */
	public Integer selectRebackRecordForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("bankCollection.selectRebackRecordForCount", paramMap);
	}

	public int updatePropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo) {
		return sqlSession.update("bankCollection.update_propertyProprietorBankCollectionInfo", propertyProprietorBankCollectionInfo);
	}

	public int updatePayBillAfterOffer(List<BcOfferRecord> bcOfferRecordList) {
		return sqlSession.update("bankCollection.updatePayBillAfterOffer", bcOfferRecordList);
	}

	public PPBCInfo4List selectPropertyProprietorBankCollectionInfoBySeqId(BigInteger id) {
		return sqlSession.selectOne("bankCollection.select_propertyProprietorBankCollectionInfo_bySeqId",id);
	}
	/**
	 * 出盘账单与业主托收信息:按小区
	 * @author wenfq
	 * @param bcGBCycleEntryAddList 账期
	 * @return
	 */
	public List<PPBankCollectionWithPayBill>  selectPayBllWithBCCycleIdForGBRange(List<BcGroupBuildingCycleEntry> bcGBCycleEntryAddList) {
		return sqlSession.selectList("bankCollection.selectPayBllWithBCCycleIdForGBRange", bcGBCycleEntryAddList);
	}
	
	/**
	 * 出盘账单与业主托收信息:按业主
	 * @author wenfq
	 * @param bcInfoId 物业托收信息id
	 * @param bcGBCycleEntryAddList 账期
	 * @return
	 */
	public List<PPBankCollectionWithPayBill>  selectPayBllWithBCCycleIdForPPRange(BigInteger bcInfoId, List<BcGroupBuildingCycleEntry> bcGBCycleEntryAddList) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("bcInfoId", bcInfoId);
		paramMap.put("bcgbcIdList", bcGBCycleEntryAddList);
		return sqlSession.selectList("bankCollection.selectPayBllWithBCCycleIdForPPRange", paramMap);
	}

	/**
	 * 平安银行回盘信息校验--账单
	 * @param bankNo
	 * @param amount
	 * @param ids
	 * @param bcgroupBuildingCycleId
	 * @return
	 */
	public List<BigInteger> getPayBillCheckForPingAnBankCollection(String bankNo, Long amount, List<BigInteger> ids, BigInteger bcgroupBuildingCycleId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("bankNo", bankNo);
		paraMap.put("amount", amount);
		paraMap.put("ids", ids);
		paraMap.put("bcgroupBuildingCycleId", bcgroupBuildingCycleId);
		return sqlSession.selectList("bankCollection.getPayBillCheckForPingAnBankCollection", paraMap);
	}

	/**
	 * 金融、ICBC
	 * @param roomNo
	 * @param bcgroupBuildingCycleId
	 * @param amount
     * @return
     */
	public List<BigInteger> getPayBillCheckForJinRongAndICBCCollection(String roomNo, BigInteger bcgroupBuildingCycleId, Long amount) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roomNo", roomNo);
		paraMap.put("amount", amount);
		paraMap.put("bcgroupBuildingCycleId", bcgroupBuildingCycleId);
		return sqlSession.selectList("bankCollection.getPayBillCheckForJinRongAndICBCCollection", paraMap);
	}
	
	/*========================================= 打印 start =========================================*/
	/**
	 * 查询银行出盘明细
	 * @param bgbcId
	 * @return
	 */
	public List<BcPrintDetail> getBcPrintDetailByBgbcId(BigInteger bgbcId){
		return sqlSession.selectList("bankCollection.getBcPrintDetailByBgbcId", bgbcId);
	}
	
	/*========================================= 打印 end =========================================*/

	public List<PPBankCollectionWithPayBill> selectUnPaidPayBllWithPayBillList(
			List<PPBankCollectionWithPayBill> bcPayBillList) {
		return sqlSession.selectList("bankCollection.selectUnPaidPayBllWithPayBillList", bcPayBillList);
	}

	/**
	 * 根据账单id查询欠费的账单payBillEntity
	 * @param id
	 * @return
     */
	public List<PayBill> selectUnPaidPayBllWithPayBillId(BigInteger id) {
		return sqlSession.selectList("bankCollection.selectUnPaidPayBllWithPayBillId", id);
	}
}

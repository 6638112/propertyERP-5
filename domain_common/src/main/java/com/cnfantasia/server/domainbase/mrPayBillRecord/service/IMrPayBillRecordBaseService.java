package com.cnfantasia.server.domainbase.mrPayBillRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;

/**
 * 描述:(抄表费收费 账单读表数) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrPayBillRecordBaseService {
	
	/**
	 * 根据条件查询(抄表费收费 账单读表数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MrPayBillRecord> getMrPayBillRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抄表费收费 账单读表数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MrPayBillRecord> getMrPayBillRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抄表费收费 账单读表数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MrPayBillRecord> getMrPayBillRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抄表费收费 账单读表数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MrPayBillRecord> getMrPayBillRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抄表费收费 账单读表数)信息
	 * @param id
	 * @return
	 */
	public MrPayBillRecord getMrPayBillRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表费收费 账单读表数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMrPayBillRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表费收费 账单读表数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMrPayBillRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抄表费收费 账单读表数)新增一条记录
	 * @param mrPayBillRecord
	 * @return
	 */
	public int insertMrPayBillRecord(MrPayBillRecord mrPayBillRecord);
	/**
	 * 批量新增(抄表费收费 账单读表数)
	 * @param mrPayBillRecordList
	 * @return
	 */
	public int insertMrPayBillRecordBatch(List<MrPayBillRecord> mrPayBillRecordList);
	/**
	 * 更新(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecord
	 * @return
	 */
	public int updateMrPayBillRecord(MrPayBillRecord mrPayBillRecord);
	/**
	 * 批量更新(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecordList
	 * @return
	 */
	public int updateMrPayBillRecordBatch(List<MrPayBillRecord> mrPayBillRecordList);
	/**
	 * 根据序列号删除(抄表费收费 账单读表数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMrPayBillRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表费收费 账单读表数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMrPayBillRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表费收费 账单读表数)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrPayBillRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费 账单读表数)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrPayBillRecordBatch(List<java.math.BigInteger> idList);
	
}

package com.cnfantasia.server.domainbase.dredgeBillFollowRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;

/**
 * 描述:(维修订单跟进记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillFollowRecordBaseDao {
	/**
	 * 根据条件查询(维修订单跟进记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<DredgeBillFollowRecord> selectDredgeBillFollowRecordByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(维修订单跟进记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<DredgeBillFollowRecord> selectDredgeBillFollowRecordByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(维修订单跟进记录)信息
	 * @param id
	 * @return
	 */
	DredgeBillFollowRecord selectDredgeBillFollowRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修订单跟进记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectDredgeBillFollowRecordCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(维修订单跟进记录)新增一条记录
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	int insertDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord);
	
	/**
	 * 批量新增(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecordList
	 * @return
	 */
	int insertDredgeBillFollowRecordBatch(List<DredgeBillFollowRecord> dredgeBillFollowRecordList);
	
	/**
	 * 更新(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	int updateDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord);
	
	/**
	 * 批量更新(维修订单跟进记录)信息
	 * @param dredgeBillFollowRecordList
	 * @return
	 */
	int updateDredgeBillFollowRecordBatch(List<DredgeBillFollowRecord> dredgeBillFollowRecordList);
	
	/**
	 * 根据序列号删除(维修订单跟进记录)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteDredgeBillFollowRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(维修订单跟进记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteDredgeBillFollowRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(维修订单跟进记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillFollowRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修订单跟进记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillFollowRecordBatch(List<java.math.BigInteger> idList);
	
	
}

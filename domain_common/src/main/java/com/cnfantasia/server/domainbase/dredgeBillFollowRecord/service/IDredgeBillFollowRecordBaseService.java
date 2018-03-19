package com.cnfantasia.server.domainbase.dredgeBillFollowRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;

/**
 * 描述:(维修订单跟进记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillFollowRecordBaseService {
	
	/**
	 * 根据条件查询(维修订单跟进记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<DredgeBillFollowRecord> getDredgeBillFollowRecordByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(维修订单跟进记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<DredgeBillFollowRecord> getDredgeBillFollowRecordByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(维修订单跟进记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<DredgeBillFollowRecord> getDredgeBillFollowRecordByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(维修订单跟进记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<DredgeBillFollowRecord> getDredgeBillFollowRecordByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(维修订单跟进记录)信息
	 * @param id
	 * @return
	 */
	DredgeBillFollowRecord getDredgeBillFollowRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修订单跟进记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getDredgeBillFollowRecordCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修订单跟进记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getDredgeBillFollowRecordCountDim(Map<String, Object> paramMap);
	/**
	 * 往(维修订单跟进记录)新增一条记录
	 * @param dredgeBillFollowRecord
	 * @return
	 */
	int insertDredgeBillFollowRecord(DredgeBillFollowRecord dredgeBillFollowRecord);
	/**
	 * 批量新增(维修订单跟进记录)
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
	 * 根据序列号批量删除(维修订单跟进记录)信息_逻辑删除
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

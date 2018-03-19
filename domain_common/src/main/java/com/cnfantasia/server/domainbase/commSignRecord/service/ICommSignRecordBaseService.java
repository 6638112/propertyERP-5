package com.cnfantasia.server.domainbase.commSignRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * 描述:(签到记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommSignRecordBaseService {
	
	/**
	 * 根据条件查询(签到记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommSignRecord> getCommSignRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(签到记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommSignRecord> getCommSignRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(签到记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommSignRecord> getCommSignRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(签到记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommSignRecord> getCommSignRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(签到记录)信息
	 * @param id
	 * @return
	 */
	public CommSignRecord getCommSignRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(签到记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommSignRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(签到记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommSignRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(签到记录)新增一条记录
	 * @param commSignRecord
	 * @return
	 */
	public int insertCommSignRecord(CommSignRecord commSignRecord);
	/**
	 * 批量新增(签到记录)
	 * @param commSignRecordList
	 * @return
	 */
	public int insertCommSignRecordBatch(List<CommSignRecord> commSignRecordList);
	/**
	 * 更新(签到记录)信息
	 * @param commSignRecord
	 * @return
	 */
	public int updateCommSignRecord(CommSignRecord commSignRecord);
	/**
	 * 批量更新(签到记录)信息
	 * @param commSignRecordList
	 * @return
	 */
	public int updateCommSignRecordBatch(List<CommSignRecord> commSignRecordList);
	/**
	 * 根据序列号删除(签到记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommSignRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(签到记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommSignRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(签到记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommSignRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(签到记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommSignRecordBatch(List<java.math.BigInteger> idList);
	
}

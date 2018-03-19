package com.cnfantasia.server.domainbase.commSignRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSignRecord.entity.CommSignRecord;

/**
 * 描述:(签到记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommSignRecordBaseDao {
	/**
	 * 根据条件查询(签到记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommSignRecord> selectCommSignRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(签到记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommSignRecord> selectCommSignRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(签到记录)信息
	 * @param id
	 * @return
	 */
	public CommSignRecord selectCommSignRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(签到记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommSignRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(签到记录)新增一条记录
	 * @param commSignRecord
	 * @return
	 */
	public int insertCommSignRecord(CommSignRecord commSignRecord);
	
	/**
	 * 批量新增(签到记录)信息
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
	 * 根据Id 批量删除(签到记录)信息_逻辑删除
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

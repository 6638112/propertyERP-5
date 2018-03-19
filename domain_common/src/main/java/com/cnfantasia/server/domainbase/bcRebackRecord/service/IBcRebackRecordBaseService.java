package com.cnfantasia.server.domainbase.bcRebackRecord.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcRebackRecord.entity.BcRebackRecord;

/**
 * 描述:(回盘记录) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcRebackRecordBaseService {
	
	/**
	 * 根据条件查询(回盘记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcRebackRecord> getBcRebackRecordByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(回盘记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcRebackRecord> getBcRebackRecordByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(回盘记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcRebackRecord> getBcRebackRecordByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(回盘记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcRebackRecord> getBcRebackRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(回盘记录)信息
	 * @param id
	 * @return
	 */
	public BcRebackRecord getBcRebackRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(回盘记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcRebackRecordCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(回盘记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcRebackRecordCountDim(Map<String,Object> paramMap);
	/**
	 * 往(回盘记录)新增一条记录
	 * @param bcRebackRecord
	 * @return
	 */
	public int insertBcRebackRecord(BcRebackRecord bcRebackRecord);
	/**
	 * 批量新增(回盘记录)
	 * @param bcRebackRecordList
	 * @return
	 */
	public int insertBcRebackRecordBatch(List<BcRebackRecord> bcRebackRecordList);
	/**
	 * 更新(回盘记录)信息
	 * @param bcRebackRecord
	 * @return
	 */
	public int updateBcRebackRecord(BcRebackRecord bcRebackRecord);
	/**
	 * 批量更新(回盘记录)信息
	 * @param bcRebackRecordList
	 * @return
	 */
	public int updateBcRebackRecordBatch(List<BcRebackRecord> bcRebackRecordList);
	/**
	 * 根据序列号删除(回盘记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcRebackRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(回盘记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcRebackRecordLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(回盘记录)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcRebackRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(回盘记录)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcRebackRecordBatch(List<java.math.BigInteger> idList);
	
}

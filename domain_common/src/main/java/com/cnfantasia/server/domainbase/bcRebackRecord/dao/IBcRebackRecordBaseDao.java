package com.cnfantasia.server.domainbase.bcRebackRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcRebackRecord.entity.BcRebackRecord;

/**
 * 描述:(回盘记录) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcRebackRecordBaseDao {
	/**
	 * 根据条件查询(回盘记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcRebackRecord> selectBcRebackRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(回盘记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcRebackRecord> selectBcRebackRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(回盘记录)信息
	 * @param id
	 * @return
	 */
	public BcRebackRecord selectBcRebackRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(回盘记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcRebackRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(回盘记录)新增一条记录
	 * @param bcRebackRecord
	 * @return
	 */
	public int insertBcRebackRecord(BcRebackRecord bcRebackRecord);
	
	/**
	 * 批量新增(回盘记录)信息
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
	 * 根据Id 批量删除(回盘记录)信息_逻辑删除
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

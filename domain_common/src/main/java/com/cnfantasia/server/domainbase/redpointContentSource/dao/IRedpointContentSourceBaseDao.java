package com.cnfantasia.server.domainbase.redpointContentSource.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;

/**
 * 描述:(红点来源表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRedpointContentSourceBaseDao {
	/**
	 * 根据条件查询(红点来源表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RedpointContentSource> selectRedpointContentSourceByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(红点来源表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RedpointContentSource> selectRedpointContentSourceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(红点来源表)信息
	 * @param id
	 * @return
	 */
	public RedpointContentSource selectRedpointContentSourceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(红点来源表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRedpointContentSourceCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(红点来源表)新增一条记录
	 * @param redpointContentSource
	 * @return
	 */
	public int insertRedpointContentSource(RedpointContentSource redpointContentSource);
	
	/**
	 * 批量新增(红点来源表)信息
	 * @param redpointContentSourceList
	 * @return
	 */
	public int insertRedpointContentSourceBatch(List<RedpointContentSource> redpointContentSourceList);
	
	/**
	 * 更新(红点来源表)信息
	 * @param redpointContentSource
	 * @return
	 */
	public int updateRedpointContentSource(RedpointContentSource redpointContentSource);
	
	/**
	 * 批量更新(红点来源表)信息
	 * @param redpointContentSourceList
	 * @return
	 */
	public int updateRedpointContentSourceBatch(List<RedpointContentSource> redpointContentSourceList);
	
	/**
	 * 根据序列号删除(红点来源表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRedpointContentSourceLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(红点来源表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRedpointContentSourceLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(红点来源表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRedpointContentSource(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(红点来源表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRedpointContentSourceBatch(List<java.math.BigInteger> idList);
	
	
}

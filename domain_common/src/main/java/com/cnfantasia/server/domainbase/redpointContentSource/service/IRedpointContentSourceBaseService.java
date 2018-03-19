package com.cnfantasia.server.domainbase.redpointContentSource.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;

/**
 * 描述:(红点来源表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRedpointContentSourceBaseService {
	
	/**
	 * 根据条件查询(红点来源表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RedpointContentSource> getRedpointContentSourceByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(红点来源表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RedpointContentSource> getRedpointContentSourceByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(红点来源表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RedpointContentSource> getRedpointContentSourceByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(红点来源表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RedpointContentSource> getRedpointContentSourceByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(红点来源表)信息
	 * @param id
	 * @return
	 */
	public RedpointContentSource getRedpointContentSourceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(红点来源表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRedpointContentSourceCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(红点来源表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRedpointContentSourceCountDim(Map<String,Object> paramMap);
	/**
	 * 往(红点来源表)新增一条记录
	 * @param redpointContentSource
	 * @return
	 */
	public int insertRedpointContentSource(RedpointContentSource redpointContentSource);
	/**
	 * 批量新增(红点来源表)
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
	 * 根据序列号批量删除(红点来源表)信息_逻辑删除
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

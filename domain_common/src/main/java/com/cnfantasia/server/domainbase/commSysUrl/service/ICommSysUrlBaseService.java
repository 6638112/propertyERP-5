package com.cnfantasia.server.domainbase.commSysUrl.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commSysUrl.entity.CommSysUrl;

/**
 * 描述:(基础的url信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommSysUrlBaseService {
	
	/**
	 * 根据条件查询(基础的url信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommSysUrl> getCommSysUrlByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(基础的url信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommSysUrl> getCommSysUrlByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(基础的url信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommSysUrl> getCommSysUrlByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(基础的url信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommSysUrl> getCommSysUrlByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(基础的url信息)信息
	 * @param id
	 * @return
	 */
	public CommSysUrl getCommSysUrlBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(基础的url信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommSysUrlCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(基础的url信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommSysUrlCountDim(Map<String,Object> paramMap);
	/**
	 * 往(基础的url信息)新增一条记录
	 * @param commSysUrl
	 * @return
	 */
	public int insertCommSysUrl(CommSysUrl commSysUrl);
	/**
	 * 批量新增(基础的url信息)
	 * @param commSysUrlList
	 * @return
	 */
	public int insertCommSysUrlBatch(List<CommSysUrl> commSysUrlList);
	/**
	 * 更新(基础的url信息)信息
	 * @param commSysUrl
	 * @return
	 */
	public int updateCommSysUrl(CommSysUrl commSysUrl);
	/**
	 * 批量更新(基础的url信息)信息
	 * @param commSysUrlList
	 * @return
	 */
	public int updateCommSysUrlBatch(List<CommSysUrl> commSysUrlList);
	/**
	 * 根据序列号删除(基础的url信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommSysUrlLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(基础的url信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommSysUrlLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(基础的url信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommSysUrl(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(基础的url信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommSysUrlBatch(List<java.math.BigInteger> idList);
	
}

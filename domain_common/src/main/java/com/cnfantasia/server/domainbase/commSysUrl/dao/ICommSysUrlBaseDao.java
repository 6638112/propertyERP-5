package com.cnfantasia.server.domainbase.commSysUrl.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSysUrl.entity.CommSysUrl;

/**
 * 描述:(基础的url信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommSysUrlBaseDao {
	/**
	 * 根据条件查询(基础的url信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommSysUrl> selectCommSysUrlByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(基础的url信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommSysUrl> selectCommSysUrlByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(基础的url信息)信息
	 * @param id
	 * @return
	 */
	public CommSysUrl selectCommSysUrlBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(基础的url信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommSysUrlCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(基础的url信息)新增一条记录
	 * @param commSysUrl
	 * @return
	 */
	public int insertCommSysUrl(CommSysUrl commSysUrl);
	
	/**
	 * 批量新增(基础的url信息)信息
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
	 * 根据Id 批量删除(基础的url信息)信息_逻辑删除
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

package com.cnfantasia.server.domainbase.ebuyAuth.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyAuth.entity.EbuyAuth;

/**
 * 描述:(认证类别信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyAuthBaseService {
	
	/**
	 * 根据条件查询(认证类别信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyAuth> getEbuyAuthByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(认证类别信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyAuth> getEbuyAuthByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(认证类别信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyAuth> getEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(认证类别信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyAuth> getEbuyAuthByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(认证类别信息)信息
	 * @param id
	 * @return
	 */
	public EbuyAuth getEbuyAuthBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(认证类别信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyAuthCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(认证类别信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyAuthCountDim(Map<String,Object> paramMap);
	/**
	 * 往(认证类别信息)新增一条记录
	 * @param ebuyAuth
	 * @return
	 */
	public int insertEbuyAuth(EbuyAuth ebuyAuth);
	/**
	 * 批量新增(认证类别信息)
	 * @param ebuyAuthList
	 * @return
	 */
	public int insertEbuyAuthBatch(List<EbuyAuth> ebuyAuthList);
	/**
	 * 更新(认证类别信息)信息
	 * @param ebuyAuth
	 * @return
	 */
	public int updateEbuyAuth(EbuyAuth ebuyAuth);
	/**
	 * 批量更新(认证类别信息)信息
	 * @param ebuyAuthList
	 * @return
	 */
	public int updateEbuyAuthBatch(List<EbuyAuth> ebuyAuthList);
	/**
	 * 根据序列号删除(认证类别信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyAuthLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(认证类别信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyAuthLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(认证类别信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyAuth(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(认证类别信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyAuthBatch(List<java.math.BigInteger> idList);
	
}

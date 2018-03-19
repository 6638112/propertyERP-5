package com.cnfantasia.server.domainbase.ebuyAuth.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAuth.entity.EbuyAuth;

/**
 * 描述:(认证类别信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyAuthBaseDao {
	/**
	 * 根据条件查询(认证类别信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyAuth> selectEbuyAuthByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(认证类别信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyAuth> selectEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(认证类别信息)信息
	 * @param id
	 * @return
	 */
	public EbuyAuth selectEbuyAuthBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(认证类别信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyAuthCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(认证类别信息)新增一条记录
	 * @param ebuyAuth
	 * @return
	 */
	public int insertEbuyAuth(EbuyAuth ebuyAuth);
	
	/**
	 * 批量新增(认证类别信息)信息
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
	 * 根据Id 批量删除(认证类别信息)信息_逻辑删除
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

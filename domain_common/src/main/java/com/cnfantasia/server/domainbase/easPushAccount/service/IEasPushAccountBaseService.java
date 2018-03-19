package com.cnfantasia.server.domainbase.easPushAccount.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.easPushAccount.entity.EasPushAccount;

/**
 * 描述:(EAS推送账户信息配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEasPushAccountBaseService {
	
	/**
	 * 根据条件查询(EAS推送账户信息配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EasPushAccount> getEasPushAccountByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(EAS推送账户信息配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EasPushAccount> getEasPushAccountByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(EAS推送账户信息配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EasPushAccount> getEasPushAccountByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(EAS推送账户信息配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EasPushAccount> getEasPushAccountByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(EAS推送账户信息配置表)信息
	 * @param id
	 * @return
	 */
	public EasPushAccount getEasPushAccountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(EAS推送账户信息配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEasPushAccountCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(EAS推送账户信息配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEasPushAccountCountDim(Map<String,Object> paramMap);
	/**
	 * 往(EAS推送账户信息配置表)新增一条记录
	 * @param easPushAccount
	 * @return
	 */
	public int insertEasPushAccount(EasPushAccount easPushAccount);
	/**
	 * 批量新增(EAS推送账户信息配置表)
	 * @param easPushAccountList
	 * @return
	 */
	public int insertEasPushAccountBatch(List<EasPushAccount> easPushAccountList);
	/**
	 * 更新(EAS推送账户信息配置表)信息
	 * @param easPushAccount
	 * @return
	 */
	public int updateEasPushAccount(EasPushAccount easPushAccount);
	/**
	 * 批量更新(EAS推送账户信息配置表)信息
	 * @param easPushAccountList
	 * @return
	 */
	public int updateEasPushAccountBatch(List<EasPushAccount> easPushAccountList);
	/**
	 * 根据序列号删除(EAS推送账户信息配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEasPushAccountLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(EAS推送账户信息配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEasPushAccountLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(EAS推送账户信息配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEasPushAccount(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(EAS推送账户信息配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEasPushAccountBatch(List<java.math.BigInteger> idList);
	
}

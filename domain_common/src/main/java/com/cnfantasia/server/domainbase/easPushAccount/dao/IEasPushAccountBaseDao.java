package com.cnfantasia.server.domainbase.easPushAccount.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.easPushAccount.entity.EasPushAccount;

/**
 * 描述:(EAS推送账户信息配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEasPushAccountBaseDao {
	/**
	 * 根据条件查询(EAS推送账户信息配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EasPushAccount> selectEasPushAccountByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(EAS推送账户信息配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EasPushAccount> selectEasPushAccountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(EAS推送账户信息配置表)信息
	 * @param id
	 * @return
	 */
	public EasPushAccount selectEasPushAccountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(EAS推送账户信息配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEasPushAccountCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(EAS推送账户信息配置表)新增一条记录
	 * @param easPushAccount
	 * @return
	 */
	public int insertEasPushAccount(EasPushAccount easPushAccount);
	
	/**
	 * 批量新增(EAS推送账户信息配置表)信息
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
	 * 根据Id 批量删除(EAS推送账户信息配置表)信息_逻辑删除
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

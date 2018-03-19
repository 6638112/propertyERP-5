package com.cnfantasia.server.domainbase.easPushAccount.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.easPushAccount.dao.IEasPushAccountBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.easPushAccount.entity.EasPushAccount;

/**
 * 描述:(EAS推送账户信息配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EasPushAccountBaseService implements IEasPushAccountBaseService{
	
	private IEasPushAccountBaseDao easPushAccountBaseDao;
	public void setEasPushAccountBaseDao(IEasPushAccountBaseDao easPushAccountBaseDao) {
		this.easPushAccountBaseDao = easPushAccountBaseDao;
	}
	/**
	 * 根据条件查询(EAS推送账户信息配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EasPushAccount> getEasPushAccountByCondition(Map<String,Object> paramMap){
		return easPushAccountBaseDao.selectEasPushAccountByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(EAS推送账户信息配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EasPushAccount> getEasPushAccountByConditionDim(Map<String,Object> paramMap){
		return easPushAccountBaseDao.selectEasPushAccountByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(EAS推送账户信息配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EasPushAccount> getEasPushAccountByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return easPushAccountBaseDao.selectEasPushAccountByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(EAS推送账户信息配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EasPushAccount> getEasPushAccountByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return easPushAccountBaseDao.selectEasPushAccountByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(EAS推送账户信息配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EasPushAccount getEasPushAccountBySeqId(java.math.BigInteger id){
		return easPushAccountBaseDao.selectEasPushAccountBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(EAS推送账户信息配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEasPushAccountCount(Map<String,Object> paramMap){
		return easPushAccountBaseDao.selectEasPushAccountCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(EAS推送账户信息配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEasPushAccountCountDim(Map<String,Object> paramMap){
		return easPushAccountBaseDao.selectEasPushAccountCount(paramMap,true);
	}
	/**
	 * 往(EAS推送账户信息配置表)新增一条记录
	 * @param easPushAccount
	 * @return
	 */
	@Override
	public int insertEasPushAccount(EasPushAccount easPushAccount){
		return easPushAccountBaseDao.insertEasPushAccount(easPushAccount);
	}
	/**
	 * 批量新增(EAS推送账户信息配置表)
	 * @param easPushAccountList
	 * @return
	 */
	@Override
	public int insertEasPushAccountBatch(List<EasPushAccount> easPushAccountList){
		return easPushAccountBaseDao.insertEasPushAccountBatch(easPushAccountList);
	}
	/**
	 * 更新(EAS推送账户信息配置表)信息
	 * @param easPushAccount
	 * @return
	 */
	@Override
	public int updateEasPushAccount(EasPushAccount easPushAccount){
		return easPushAccountBaseDao.updateEasPushAccount(easPushAccount);
	}
	/**
	 * 批量更新(EAS推送账户信息配置表)信息
	 * @param easPushAccountList
	 * @return
	 */
	@Override
	public int updateEasPushAccountBatch(List<EasPushAccount> easPushAccountList){
		return easPushAccountBaseDao.updateEasPushAccountBatch(easPushAccountList);
	}
	/**
	 * 根据序列号删除(EAS推送账户信息配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEasPushAccountLogic(java.math.BigInteger id){
		return easPushAccountBaseDao.deleteEasPushAccountLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(EAS推送账户信息配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEasPushAccountLogicBatch(List<java.math.BigInteger> idList){
		return easPushAccountBaseDao.deleteEasPushAccountLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(EAS推送账户信息配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushAccount(java.math.BigInteger id){
//		return easPushAccountBaseDao.deleteEasPushAccount(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(EAS推送账户信息配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushAccountBatch(List<java.math.BigInteger> idList){
//		return easPushAccountBaseDao.deleteEasPushAccountBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.dao.ILoginNoOldUpgradeDateBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;

/**
 * 描述:(微信升级老数据) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoginNoOldUpgradeDateBaseService implements ILoginNoOldUpgradeDateBaseService{
	
	private ILoginNoOldUpgradeDateBaseDao loginNoOldUpgradeDateBaseDao;
	public void setLoginNoOldUpgradeDateBaseDao(ILoginNoOldUpgradeDateBaseDao loginNoOldUpgradeDateBaseDao) {
		this.loginNoOldUpgradeDateBaseDao = loginNoOldUpgradeDateBaseDao;
	}
	/**
	 * 根据条件查询(微信升级老数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap){
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微信升级老数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByConditionDim(Map<String,Object> paramMap){
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微信升级老数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微信升级老数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微信升级老数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginNoOldUpgradeDate getLoginNoOldUpgradeDateBySeqId(java.math.BigInteger id){
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微信升级老数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginNoOldUpgradeDateCount(Map<String,Object> paramMap){
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微信升级老数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginNoOldUpgradeDateCountDim(Map<String,Object> paramMap){
		return loginNoOldUpgradeDateBaseDao.selectLoginNoOldUpgradeDateCount(paramMap,true);
	}
	/**
	 * 往(微信升级老数据)新增一条记录
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	@Override
	public int insertLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate){
		return loginNoOldUpgradeDateBaseDao.insertLoginNoOldUpgradeDate(loginNoOldUpgradeDate);
	}
	/**
	 * 批量新增(微信升级老数据)
	 * @param loginNoOldUpgradeDateList
	 * @return
	 */
	@Override
	public int insertLoginNoOldUpgradeDateBatch(List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList){
		return loginNoOldUpgradeDateBaseDao.insertLoginNoOldUpgradeDateBatch(loginNoOldUpgradeDateList);
	}
	/**
	 * 更新(微信升级老数据)信息
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	@Override
	public int updateLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate){
		return loginNoOldUpgradeDateBaseDao.updateLoginNoOldUpgradeDate(loginNoOldUpgradeDate);
	}
	/**
	 * 批量更新(微信升级老数据)信息
	 * @param loginNoOldUpgradeDateList
	 * @return
	 */
	@Override
	public int updateLoginNoOldUpgradeDateBatch(List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList){
		return loginNoOldUpgradeDateBaseDao.updateLoginNoOldUpgradeDateBatch(loginNoOldUpgradeDateList);
	}
	/**
	 * 根据序列号删除(微信升级老数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginNoOldUpgradeDateLogic(java.math.BigInteger id){
		return loginNoOldUpgradeDateBaseDao.deleteLoginNoOldUpgradeDateLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微信升级老数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginNoOldUpgradeDateLogicBatch(List<java.math.BigInteger> idList){
		return loginNoOldUpgradeDateBaseDao.deleteLoginNoOldUpgradeDateLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微信升级老数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoOldUpgradeDate(java.math.BigInteger id){
//		return loginNoOldUpgradeDateBaseDao.deleteLoginNoOldUpgradeDate(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微信升级老数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoOldUpgradeDateBatch(List<java.math.BigInteger> idList){
//		return loginNoOldUpgradeDateBaseDao.deleteLoginNoOldUpgradeDateBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.dao.IUserSetRealroomDeductionConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.entity.UserSetRealroomDeductionConfig;

/**
 * 描述:(用户房间划扣配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserSetRealroomDeductionConfigBaseService implements IUserSetRealroomDeductionConfigBaseService{
	
	private IUserSetRealroomDeductionConfigBaseDao userSetRealroomDeductionConfigBaseDao;
	public void setUserSetRealroomDeductionConfigBaseDao(IUserSetRealroomDeductionConfigBaseDao userSetRealroomDeductionConfigBaseDao) {
		this.userSetRealroomDeductionConfigBaseDao = userSetRealroomDeductionConfigBaseDao;
	}
	/**
	 * 根据条件查询(用户房间划扣配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByCondition(Map<String,Object> paramMap){
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户房间划扣配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByConditionDim(Map<String,Object> paramMap){
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户房间划扣配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户房间划扣配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserSetRealroomDeductionConfig> getUserSetRealroomDeductionConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户房间划扣配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserSetRealroomDeductionConfig getUserSetRealroomDeductionConfigBySeqId(java.math.BigInteger id){
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户房间划扣配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserSetRealroomDeductionConfigCount(Map<String,Object> paramMap){
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户房间划扣配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserSetRealroomDeductionConfigCountDim(Map<String,Object> paramMap){
		return userSetRealroomDeductionConfigBaseDao.selectUserSetRealroomDeductionConfigCount(paramMap,true);
	}
	/**
	 * 往(用户房间划扣配置)新增一条记录
	 * @param userSetRealroomDeductionConfig
	 * @return
	 */
	@Override
	public int insertUserSetRealroomDeductionConfig(UserSetRealroomDeductionConfig userSetRealroomDeductionConfig){
		return userSetRealroomDeductionConfigBaseDao.insertUserSetRealroomDeductionConfig(userSetRealroomDeductionConfig);
	}
	/**
	 * 批量新增(用户房间划扣配置)
	 * @param userSetRealroomDeductionConfigList
	 * @return
	 */
	@Override
	public int insertUserSetRealroomDeductionConfigBatch(List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList){
		return userSetRealroomDeductionConfigBaseDao.insertUserSetRealroomDeductionConfigBatch(userSetRealroomDeductionConfigList);
	}
	/**
	 * 更新(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfig
	 * @return
	 */
	@Override
	public int updateUserSetRealroomDeductionConfig(UserSetRealroomDeductionConfig userSetRealroomDeductionConfig){
		return userSetRealroomDeductionConfigBaseDao.updateUserSetRealroomDeductionConfig(userSetRealroomDeductionConfig);
	}
	/**
	 * 批量更新(用户房间划扣配置)信息
	 * @param userSetRealroomDeductionConfigList
	 * @return
	 */
	@Override
	public int updateUserSetRealroomDeductionConfigBatch(List<UserSetRealroomDeductionConfig> userSetRealroomDeductionConfigList){
		return userSetRealroomDeductionConfigBaseDao.updateUserSetRealroomDeductionConfigBatch(userSetRealroomDeductionConfigList);
	}
	/**
	 * 根据序列号删除(用户房间划扣配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserSetRealroomDeductionConfigLogic(java.math.BigInteger id){
		return userSetRealroomDeductionConfigBaseDao.deleteUserSetRealroomDeductionConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户房间划扣配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserSetRealroomDeductionConfigLogicBatch(List<java.math.BigInteger> idList){
		return userSetRealroomDeductionConfigBaseDao.deleteUserSetRealroomDeductionConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户房间划扣配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserSetRealroomDeductionConfig(java.math.BigInteger id){
//		return userSetRealroomDeductionConfigBaseDao.deleteUserSetRealroomDeductionConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户房间划扣配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserSetRealroomDeductionConfigBatch(List<java.math.BigInteger> idList){
//		return userSetRealroomDeductionConfigBaseDao.deleteUserSetRealroomDeductionConfigBatch(idList);
//	}
	
}

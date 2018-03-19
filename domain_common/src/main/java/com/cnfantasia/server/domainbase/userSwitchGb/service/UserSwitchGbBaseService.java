package com.cnfantasia.server.domainbase.userSwitchGb.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userSwitchGb.dao.IUserSwitchGbBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;

/**
 * 描述:(用户切换小区表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserSwitchGbBaseService implements IUserSwitchGbBaseService{
	
	private IUserSwitchGbBaseDao userSwitchGbBaseDao;
	public void setUserSwitchGbBaseDao(IUserSwitchGbBaseDao userSwitchGbBaseDao) {
		this.userSwitchGbBaseDao = userSwitchGbBaseDao;
	}
	/**
	 * 根据条件查询(用户切换小区表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserSwitchGb> getUserSwitchGbByCondition(Map<String,Object> paramMap){
		return userSwitchGbBaseDao.selectUserSwitchGbByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户切换小区表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserSwitchGb> getUserSwitchGbByConditionDim(Map<String,Object> paramMap){
		return userSwitchGbBaseDao.selectUserSwitchGbByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户切换小区表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserSwitchGb> getUserSwitchGbByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userSwitchGbBaseDao.selectUserSwitchGbByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户切换小区表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserSwitchGb> getUserSwitchGbByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userSwitchGbBaseDao.selectUserSwitchGbByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户切换小区表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserSwitchGb getUserSwitchGbBySeqId(java.math.BigInteger id){
		return userSwitchGbBaseDao.selectUserSwitchGbBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户切换小区表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserSwitchGbCount(Map<String,Object> paramMap){
		return userSwitchGbBaseDao.selectUserSwitchGbCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户切换小区表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserSwitchGbCountDim(Map<String,Object> paramMap){
		return userSwitchGbBaseDao.selectUserSwitchGbCount(paramMap,true);
	}
	/**
	 * 往(用户切换小区表)新增一条记录
	 * @param userSwitchGb
	 * @return
	 */
	@Override
	public int insertUserSwitchGb(UserSwitchGb userSwitchGb){
		return userSwitchGbBaseDao.insertUserSwitchGb(userSwitchGb);
	}
	/**
	 * 批量新增(用户切换小区表)
	 * @param userSwitchGbList
	 * @return
	 */
	@Override
	public int insertUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList){
		return userSwitchGbBaseDao.insertUserSwitchGbBatch(userSwitchGbList);
	}
	/**
	 * 更新(用户切换小区表)信息
	 * @param userSwitchGb
	 * @return
	 */
	@Override
	public int updateUserSwitchGb(UserSwitchGb userSwitchGb){
		return userSwitchGbBaseDao.updateUserSwitchGb(userSwitchGb);
	}
	/**
	 * 批量更新(用户切换小区表)信息
	 * @param userSwitchGbList
	 * @return
	 */
	@Override
	public int updateUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList){
		return userSwitchGbBaseDao.updateUserSwitchGbBatch(userSwitchGbList);
	}
	/**
	 * 根据序列号删除(用户切换小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserSwitchGbLogic(java.math.BigInteger id){
		return userSwitchGbBaseDao.deleteUserSwitchGbLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户切换小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserSwitchGbLogicBatch(List<java.math.BigInteger> idList){
		return userSwitchGbBaseDao.deleteUserSwitchGbLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户切换小区表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserSwitchGb(java.math.BigInteger id){
//		return userSwitchGbBaseDao.deleteUserSwitchGb(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户切换小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserSwitchGbBatch(List<java.math.BigInteger> idList){
//		return userSwitchGbBaseDao.deleteUserSwitchGbBatch(idList);
//	}
	
}

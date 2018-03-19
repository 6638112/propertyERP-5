package com.cnfantasia.server.domainbase.userTmp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userTmp.dao.IUserTmpBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * 描述:(临时用户) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserTmpBaseService implements IUserTmpBaseService{
	
	private IUserTmpBaseDao userTmpBaseDao;
	public void setUserTmpBaseDao(IUserTmpBaseDao userTmpBaseDao) {
		this.userTmpBaseDao = userTmpBaseDao;
	}
	/**
	 * 根据条件查询(临时用户)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserTmp> getUserTmpByCondition(Map<String,Object> paramMap){
		return userTmpBaseDao.selectUserTmpByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(临时用户)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserTmp> getUserTmpByConditionDim(Map<String,Object> paramMap){
		return userTmpBaseDao.selectUserTmpByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(临时用户)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserTmp> getUserTmpByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userTmpBaseDao.selectUserTmpByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(临时用户)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserTmp> getUserTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userTmpBaseDao.selectUserTmpByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(临时用户)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserTmp getUserTmpBySeqId(java.math.BigInteger id){
		return userTmpBaseDao.selectUserTmpBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(临时用户)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserTmpCount(Map<String,Object> paramMap){
		return userTmpBaseDao.selectUserTmpCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(临时用户)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserTmpCountDim(Map<String,Object> paramMap){
		return userTmpBaseDao.selectUserTmpCount(paramMap,true);
	}
	/**
	 * 往(临时用户)新增一条记录
	 * @param userTmp
	 * @return
	 */
	@Override
	public int insertUserTmp(UserTmp userTmp){
		return userTmpBaseDao.insertUserTmp(userTmp);
	}
	/**
	 * 批量新增(临时用户)
	 * @param userTmpList
	 * @return
	 */
	@Override
	public int insertUserTmpBatch(List<UserTmp> userTmpList){
		return userTmpBaseDao.insertUserTmpBatch(userTmpList);
	}
	/**
	 * 更新(临时用户)信息
	 * @param userTmp
	 * @return
	 */
	@Override
	public int updateUserTmp(UserTmp userTmp){
		return userTmpBaseDao.updateUserTmp(userTmp);
	}
	/**
	 * 批量更新(临时用户)信息
	 * @param userTmpList
	 * @return
	 */
	@Override
	public int updateUserTmpBatch(List<UserTmp> userTmpList){
		return userTmpBaseDao.updateUserTmpBatch(userTmpList);
	}
	/**
	 * 根据序列号删除(临时用户)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserTmpLogic(java.math.BigInteger id){
		return userTmpBaseDao.deleteUserTmpLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(临时用户)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserTmpLogicBatch(List<java.math.BigInteger> idList){
		return userTmpBaseDao.deleteUserTmpLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(临时用户)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserTmp(java.math.BigInteger id){
//		return userTmpBaseDao.deleteUserTmp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临时用户)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserTmpBatch(List<java.math.BigInteger> idList){
//		return userTmpBaseDao.deleteUserTmpBatch(idList);
//	}
	
}

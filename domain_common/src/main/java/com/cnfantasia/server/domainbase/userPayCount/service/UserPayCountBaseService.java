package com.cnfantasia.server.domainbase.userPayCount.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userPayCount.dao.IUserPayCountBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPayCount.entity.UserPayCount;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserPayCountBaseService implements IUserPayCountBaseService{
	
	private IUserPayCountBaseDao userPayCountBaseDao;
	public void setUserPayCountBaseDao(IUserPayCountBaseDao userPayCountBaseDao) {
		this.userPayCountBaseDao = userPayCountBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserPayCount> getUserPayCountByCondition(Map<String,Object> paramMap){
		return userPayCountBaseDao.selectUserPayCountByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserPayCount> getUserPayCountByConditionDim(Map<String,Object> paramMap){
		return userPayCountBaseDao.selectUserPayCountByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserPayCount> getUserPayCountByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userPayCountBaseDao.selectUserPayCountByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserPayCount> getUserPayCountByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userPayCountBaseDao.selectUserPayCountByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public UserPayCount getUserPayCountBySeqId(java.math.BigInteger id){
		return userPayCountBaseDao.selectUserPayCountBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserPayCountCount(Map<String,Object> paramMap){
		return userPayCountBaseDao.selectUserPayCountCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserPayCountCountDim(Map<String,Object> paramMap){
		return userPayCountBaseDao.selectUserPayCountCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param userPayCount
	 * @return
	 */
	@Override
	public int insertUserPayCount(UserPayCount userPayCount){
		return userPayCountBaseDao.insertUserPayCount(userPayCount);
	}
	/**
	 * 批量新增()
	 * @param userPayCountList
	 * @return
	 */
	@Override
	public int insertUserPayCountBatch(List<UserPayCount> userPayCountList){
		return userPayCountBaseDao.insertUserPayCountBatch(userPayCountList);
	}
	/**
	 * 更新()信息
	 * @param userPayCount
	 * @return
	 */
	@Override
	public int updateUserPayCount(UserPayCount userPayCount){
		return userPayCountBaseDao.updateUserPayCount(userPayCount);
	}
	/**
	 * 批量更新()信息
	 * @param userPayCountList
	 * @return
	 */
	@Override
	public int updateUserPayCountBatch(List<UserPayCount> userPayCountList){
		return userPayCountBaseDao.updateUserPayCountBatch(userPayCountList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserPayCountLogic(java.math.BigInteger id){
		return userPayCountBaseDao.deleteUserPayCountLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserPayCountLogicBatch(List<java.math.BigInteger> idList){
		return userPayCountBaseDao.deleteUserPayCountLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserPayCount(java.math.BigInteger id){
//		return userPayCountBaseDao.deleteUserPayCount(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserPayCountBatch(List<java.math.BigInteger> idList){
//		return userPayCountBaseDao.deleteUserPayCountBatch(idList);
//	}
	
}

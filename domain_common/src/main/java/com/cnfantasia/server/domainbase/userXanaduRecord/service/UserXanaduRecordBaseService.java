package com.cnfantasia.server.domainbase.userXanaduRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.userXanaduRecord.dao.IUserXanaduRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * 描述:(用户世外桃源状态信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class UserXanaduRecordBaseService implements IUserXanaduRecordBaseService{
	
	private IUserXanaduRecordBaseDao userXanaduRecordBaseDao;
	public void setUserXanaduRecordBaseDao(IUserXanaduRecordBaseDao userXanaduRecordBaseDao) {
		this.userXanaduRecordBaseDao = userXanaduRecordBaseDao;
	}
	/**
	 * 根据条件查询(用户世外桃源状态信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserXanaduRecord> getUserXanaduRecordByCondition(Map<String,Object> paramMap){
		return userXanaduRecordBaseDao.selectUserXanaduRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户世外桃源状态信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<UserXanaduRecord> getUserXanaduRecordByConditionDim(Map<String,Object> paramMap){
		return userXanaduRecordBaseDao.selectUserXanaduRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户世外桃源状态信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserXanaduRecord> getUserXanaduRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return userXanaduRecordBaseDao.selectUserXanaduRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户世外桃源状态信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<UserXanaduRecord> getUserXanaduRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return userXanaduRecordBaseDao.selectUserXanaduRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户世外桃源状态信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserXanaduRecord getUserXanaduRecordBySeqId(java.math.BigInteger id){
		return userXanaduRecordBaseDao.selectUserXanaduRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户世外桃源状态信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserXanaduRecordCount(Map<String,Object> paramMap){
		return userXanaduRecordBaseDao.selectUserXanaduRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户世外桃源状态信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getUserXanaduRecordCountDim(Map<String,Object> paramMap){
		return userXanaduRecordBaseDao.selectUserXanaduRecordCount(paramMap,true);
	}
	/**
	 * 往(用户世外桃源状态信息)新增一条记录
	 * @param userXanaduRecord
	 * @return
	 */
	@Override
	public int insertUserXanaduRecord(UserXanaduRecord userXanaduRecord){
		return userXanaduRecordBaseDao.insertUserXanaduRecord(userXanaduRecord);
	}
	/**
	 * 批量新增(用户世外桃源状态信息)
	 * @param userXanaduRecordList
	 * @return
	 */
	@Override
	public int insertUserXanaduRecordBatch(List<UserXanaduRecord> userXanaduRecordList){
		return userXanaduRecordBaseDao.insertUserXanaduRecordBatch(userXanaduRecordList);
	}
	/**
	 * 更新(用户世外桃源状态信息)信息
	 * @param userXanaduRecord
	 * @return
	 */
	@Override
	public int updateUserXanaduRecord(UserXanaduRecord userXanaduRecord){
		return userXanaduRecordBaseDao.updateUserXanaduRecord(userXanaduRecord);
	}
	/**
	 * 批量更新(用户世外桃源状态信息)信息
	 * @param userXanaduRecordList
	 * @return
	 */
	@Override
	public int updateUserXanaduRecordBatch(List<UserXanaduRecord> userXanaduRecordList){
		return userXanaduRecordBaseDao.updateUserXanaduRecordBatch(userXanaduRecordList);
	}
	/**
	 * 根据序列号删除(用户世外桃源状态信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserXanaduRecordLogic(java.math.BigInteger id){
		return userXanaduRecordBaseDao.deleteUserXanaduRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户世外桃源状态信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserXanaduRecordLogicBatch(List<java.math.BigInteger> idList){
		return userXanaduRecordBaseDao.deleteUserXanaduRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户世外桃源状态信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserXanaduRecord(java.math.BigInteger id){
//		return userXanaduRecordBaseDao.deleteUserXanaduRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户世外桃源状态信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserXanaduRecordBatch(List<java.math.BigInteger> idList){
//		return userXanaduRecordBaseDao.deleteUserXanaduRecordBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.familyMsgHasTUser.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.familyMsgHasTUser.dao.IFamilyMsgHasTUserBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgHasTUser.entity.FamilyMsgHasTUser;

/**
 * 描述:(家庭留言艾特的用户列表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FamilyMsgHasTUserBaseService implements IFamilyMsgHasTUserBaseService{
	
	private IFamilyMsgHasTUserBaseDao familyMsgHasTUserBaseDao;
	public void setFamilyMsgHasTUserBaseDao(IFamilyMsgHasTUserBaseDao familyMsgHasTUserBaseDao) {
		this.familyMsgHasTUserBaseDao = familyMsgHasTUserBaseDao;
	}
	/**
	 * 根据条件查询(家庭留言艾特的用户列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByCondition(Map<String,Object> paramMap){
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(家庭留言艾特的用户列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByConditionDim(Map<String,Object> paramMap){
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(家庭留言艾特的用户列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(家庭留言艾特的用户列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(家庭留言艾特的用户列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsgHasTUser getFamilyMsgHasTUserBySeqId(java.math.BigInteger id){
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言艾特的用户列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgHasTUserCount(Map<String,Object> paramMap){
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言艾特的用户列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgHasTUserCountDim(Map<String,Object> paramMap){
		return familyMsgHasTUserBaseDao.selectFamilyMsgHasTUserCount(paramMap,true);
	}
	/**
	 * 往(家庭留言艾特的用户列表)新增一条记录
	 * @param familyMsgHasTUser
	 * @return
	 */
	@Override
	public int insertFamilyMsgHasTUser(FamilyMsgHasTUser familyMsgHasTUser){
		return familyMsgHasTUserBaseDao.insertFamilyMsgHasTUser(familyMsgHasTUser);
	}
	/**
	 * 批量新增(家庭留言艾特的用户列表)
	 * @param familyMsgHasTUserList
	 * @return
	 */
	@Override
	public int insertFamilyMsgHasTUserBatch(List<FamilyMsgHasTUser> familyMsgHasTUserList){
		return familyMsgHasTUserBaseDao.insertFamilyMsgHasTUserBatch(familyMsgHasTUserList);
	}
	/**
	 * 更新(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUser
	 * @return
	 */
	@Override
	public int updateFamilyMsgHasTUser(FamilyMsgHasTUser familyMsgHasTUser){
		return familyMsgHasTUserBaseDao.updateFamilyMsgHasTUser(familyMsgHasTUser);
	}
	/**
	 * 批量更新(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUserList
	 * @return
	 */
	@Override
	public int updateFamilyMsgHasTUserBatch(List<FamilyMsgHasTUser> familyMsgHasTUserList){
		return familyMsgHasTUserBaseDao.updateFamilyMsgHasTUserBatch(familyMsgHasTUserList);
	}
	/**
	 * 根据序列号删除(家庭留言艾特的用户列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgHasTUserLogic(java.math.BigInteger id){
		return familyMsgHasTUserBaseDao.deleteFamilyMsgHasTUserLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(家庭留言艾特的用户列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgHasTUserLogicBatch(List<java.math.BigInteger> idList){
		return familyMsgHasTUserBaseDao.deleteFamilyMsgHasTUserLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言艾特的用户列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgHasTUser(java.math.BigInteger id){
//		return familyMsgHasTUserBaseDao.deleteFamilyMsgHasTUser(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言艾特的用户列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgHasTUserBatch(List<java.math.BigInteger> idList){
//		return familyMsgHasTUserBaseDao.deleteFamilyMsgHasTUserBatch(idList);
//	}
	
}

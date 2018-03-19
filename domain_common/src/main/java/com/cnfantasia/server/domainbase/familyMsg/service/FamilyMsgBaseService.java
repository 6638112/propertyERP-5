package com.cnfantasia.server.domainbase.familyMsg.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.familyMsg.dao.IFamilyMsgBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsg.entity.FamilyMsg;

/**
 * 描述:(家庭留言板) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FamilyMsgBaseService implements IFamilyMsgBaseService{
	
	private IFamilyMsgBaseDao familyMsgBaseDao;
	public void setFamilyMsgBaseDao(IFamilyMsgBaseDao familyMsgBaseDao) {
		this.familyMsgBaseDao = familyMsgBaseDao;
	}
	/**
	 * 根据条件查询(家庭留言板)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsg> getFamilyMsgByCondition(Map<String,Object> paramMap){
		return familyMsgBaseDao.selectFamilyMsgByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(家庭留言板)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsg> getFamilyMsgByConditionDim(Map<String,Object> paramMap){
		return familyMsgBaseDao.selectFamilyMsgByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(家庭留言板)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsg> getFamilyMsgByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgBaseDao.selectFamilyMsgByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(家庭留言板)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsg> getFamilyMsgByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgBaseDao.selectFamilyMsgByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(家庭留言板)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsg getFamilyMsgBySeqId(java.math.BigInteger id){
		return familyMsgBaseDao.selectFamilyMsgBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgCount(Map<String,Object> paramMap){
		return familyMsgBaseDao.selectFamilyMsgCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgCountDim(Map<String,Object> paramMap){
		return familyMsgBaseDao.selectFamilyMsgCount(paramMap,true);
	}
	/**
	 * 往(家庭留言板)新增一条记录
	 * @param familyMsg
	 * @return
	 */
	@Override
	public int insertFamilyMsg(FamilyMsg familyMsg){
		return familyMsgBaseDao.insertFamilyMsg(familyMsg);
	}
	/**
	 * 批量新增(家庭留言板)
	 * @param familyMsgList
	 * @return
	 */
	@Override
	public int insertFamilyMsgBatch(List<FamilyMsg> familyMsgList){
		return familyMsgBaseDao.insertFamilyMsgBatch(familyMsgList);
	}
	/**
	 * 更新(家庭留言板)信息
	 * @param familyMsg
	 * @return
	 */
	@Override
	public int updateFamilyMsg(FamilyMsg familyMsg){
		return familyMsgBaseDao.updateFamilyMsg(familyMsg);
	}
	/**
	 * 批量更新(家庭留言板)信息
	 * @param familyMsgList
	 * @return
	 */
	@Override
	public int updateFamilyMsgBatch(List<FamilyMsg> familyMsgList){
		return familyMsgBaseDao.updateFamilyMsgBatch(familyMsgList);
	}
	/**
	 * 根据序列号删除(家庭留言板)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgLogic(java.math.BigInteger id){
		return familyMsgBaseDao.deleteFamilyMsgLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(家庭留言板)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgLogicBatch(List<java.math.BigInteger> idList){
		return familyMsgBaseDao.deleteFamilyMsgLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言板)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsg(java.math.BigInteger id){
//		return familyMsgBaseDao.deleteFamilyMsg(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgBatch(List<java.math.BigInteger> idList){
//		return familyMsgBaseDao.deleteFamilyMsgBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.familyMsgExtData.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.familyMsgExtData.dao.IFamilyMsgExtDataBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;

/**
 * 描述:(家庭留言板拓展信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FamilyMsgExtDataBaseService implements IFamilyMsgExtDataBaseService{
	
	private IFamilyMsgExtDataBaseDao familyMsgExtDataBaseDao;
	public void setFamilyMsgExtDataBaseDao(IFamilyMsgExtDataBaseDao familyMsgExtDataBaseDao) {
		this.familyMsgExtDataBaseDao = familyMsgExtDataBaseDao;
	}
	/**
	 * 根据条件查询(家庭留言板拓展信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsgExtData> getFamilyMsgExtDataByCondition(Map<String,Object> paramMap){
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(家庭留言板拓展信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsgExtData> getFamilyMsgExtDataByConditionDim(Map<String,Object> paramMap){
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(家庭留言板拓展信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsgExtData> getFamilyMsgExtDataByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(家庭留言板拓展信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsgExtData> getFamilyMsgExtDataByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(家庭留言板拓展信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsgExtData getFamilyMsgExtDataBySeqId(java.math.BigInteger id){
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板拓展信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgExtDataCount(Map<String,Object> paramMap){
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板拓展信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgExtDataCountDim(Map<String,Object> paramMap){
		return familyMsgExtDataBaseDao.selectFamilyMsgExtDataCount(paramMap,true);
	}
	/**
	 * 往(家庭留言板拓展信息)新增一条记录
	 * @param familyMsgExtData
	 * @return
	 */
	@Override
	public int insertFamilyMsgExtData(FamilyMsgExtData familyMsgExtData){
		return familyMsgExtDataBaseDao.insertFamilyMsgExtData(familyMsgExtData);
	}
	/**
	 * 批量新增(家庭留言板拓展信息)
	 * @param familyMsgExtDataList
	 * @return
	 */
	@Override
	public int insertFamilyMsgExtDataBatch(List<FamilyMsgExtData> familyMsgExtDataList){
		return familyMsgExtDataBaseDao.insertFamilyMsgExtDataBatch(familyMsgExtDataList);
	}
	/**
	 * 更新(家庭留言板拓展信息)信息
	 * @param familyMsgExtData
	 * @return
	 */
	@Override
	public int updateFamilyMsgExtData(FamilyMsgExtData familyMsgExtData){
		return familyMsgExtDataBaseDao.updateFamilyMsgExtData(familyMsgExtData);
	}
	/**
	 * 批量更新(家庭留言板拓展信息)信息
	 * @param familyMsgExtDataList
	 * @return
	 */
	@Override
	public int updateFamilyMsgExtDataBatch(List<FamilyMsgExtData> familyMsgExtDataList){
		return familyMsgExtDataBaseDao.updateFamilyMsgExtDataBatch(familyMsgExtDataList);
	}
	/**
	 * 根据序列号删除(家庭留言板拓展信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgExtDataLogic(java.math.BigInteger id){
		return familyMsgExtDataBaseDao.deleteFamilyMsgExtDataLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(家庭留言板拓展信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgExtDataLogicBatch(List<java.math.BigInteger> idList){
		return familyMsgExtDataBaseDao.deleteFamilyMsgExtDataLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言板拓展信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgExtData(java.math.BigInteger id){
//		return familyMsgExtDataBaseDao.deleteFamilyMsgExtData(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板拓展信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgExtDataBatch(List<java.math.BigInteger> idList){
//		return familyMsgExtDataBaseDao.deleteFamilyMsgExtDataBatch(idList);
//	}
	
}

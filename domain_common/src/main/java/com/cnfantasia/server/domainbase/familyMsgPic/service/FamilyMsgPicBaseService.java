package com.cnfantasia.server.domainbase.familyMsgPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.familyMsgPic.dao.IFamilyMsgPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgPic.entity.FamilyMsgPic;

/**
 * 描述:(家庭留言板图片) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FamilyMsgPicBaseService implements IFamilyMsgPicBaseService{
	
	private IFamilyMsgPicBaseDao familyMsgPicBaseDao;
	public void setFamilyMsgPicBaseDao(IFamilyMsgPicBaseDao familyMsgPicBaseDao) {
		this.familyMsgPicBaseDao = familyMsgPicBaseDao;
	}
	/**
	 * 根据条件查询(家庭留言板图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsgPic> getFamilyMsgPicByCondition(Map<String,Object> paramMap){
		return familyMsgPicBaseDao.selectFamilyMsgPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(家庭留言板图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyMsgPic> getFamilyMsgPicByConditionDim(Map<String,Object> paramMap){
		return familyMsgPicBaseDao.selectFamilyMsgPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(家庭留言板图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsgPic> getFamilyMsgPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgPicBaseDao.selectFamilyMsgPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(家庭留言板图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyMsgPic> getFamilyMsgPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return familyMsgPicBaseDao.selectFamilyMsgPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(家庭留言板图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsgPic getFamilyMsgPicBySeqId(java.math.BigInteger id){
		return familyMsgPicBaseDao.selectFamilyMsgPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgPicCount(Map<String,Object> paramMap){
		return familyMsgPicBaseDao.selectFamilyMsgPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyMsgPicCountDim(Map<String,Object> paramMap){
		return familyMsgPicBaseDao.selectFamilyMsgPicCount(paramMap,true);
	}
	/**
	 * 往(家庭留言板图片)新增一条记录
	 * @param familyMsgPic
	 * @return
	 */
	@Override
	public int insertFamilyMsgPic(FamilyMsgPic familyMsgPic){
		return familyMsgPicBaseDao.insertFamilyMsgPic(familyMsgPic);
	}
	/**
	 * 批量新增(家庭留言板图片)
	 * @param familyMsgPicList
	 * @return
	 */
	@Override
	public int insertFamilyMsgPicBatch(List<FamilyMsgPic> familyMsgPicList){
		return familyMsgPicBaseDao.insertFamilyMsgPicBatch(familyMsgPicList);
	}
	/**
	 * 更新(家庭留言板图片)信息
	 * @param familyMsgPic
	 * @return
	 */
	@Override
	public int updateFamilyMsgPic(FamilyMsgPic familyMsgPic){
		return familyMsgPicBaseDao.updateFamilyMsgPic(familyMsgPic);
	}
	/**
	 * 批量更新(家庭留言板图片)信息
	 * @param familyMsgPicList
	 * @return
	 */
	@Override
	public int updateFamilyMsgPicBatch(List<FamilyMsgPic> familyMsgPicList){
		return familyMsgPicBaseDao.updateFamilyMsgPicBatch(familyMsgPicList);
	}
	/**
	 * 根据序列号删除(家庭留言板图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgPicLogic(java.math.BigInteger id){
		return familyMsgPicBaseDao.deleteFamilyMsgPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(家庭留言板图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgPicLogicBatch(List<java.math.BigInteger> idList){
		return familyMsgPicBaseDao.deleteFamilyMsgPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言板图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgPic(java.math.BigInteger id){
//		return familyMsgPicBaseDao.deleteFamilyMsgPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgPicBatch(List<java.math.BigInteger> idList){
//		return familyMsgPicBaseDao.deleteFamilyMsgPicBatch(idList);
//	}
	
}

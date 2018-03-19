package com.cnfantasia.server.domainbase.familyWealthOption.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.familyWealthOption.dao.IFamilyWealthOptionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyWealthOption.entity.FamilyWealthOption;

/**
 * 描述:(家庭财富选项) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class FamilyWealthOptionBaseService implements IFamilyWealthOptionBaseService{
	
	private IFamilyWealthOptionBaseDao familyWealthOptionBaseDao;
	public void setFamilyWealthOptionBaseDao(IFamilyWealthOptionBaseDao familyWealthOptionBaseDao) {
		this.familyWealthOptionBaseDao = familyWealthOptionBaseDao;
	}
	/**
	 * 根据条件查询(家庭财富选项)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyWealthOption> getFamilyWealthOptionByCondition(Map<String,Object> paramMap){
		return familyWealthOptionBaseDao.selectFamilyWealthOptionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(家庭财富选项)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<FamilyWealthOption> getFamilyWealthOptionByConditionDim(Map<String,Object> paramMap){
		return familyWealthOptionBaseDao.selectFamilyWealthOptionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(家庭财富选项)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyWealthOption> getFamilyWealthOptionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return familyWealthOptionBaseDao.selectFamilyWealthOptionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(家庭财富选项)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<FamilyWealthOption> getFamilyWealthOptionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return familyWealthOptionBaseDao.selectFamilyWealthOptionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(家庭财富选项)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyWealthOption getFamilyWealthOptionBySeqId(java.math.BigInteger id){
		return familyWealthOptionBaseDao.selectFamilyWealthOptionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(家庭财富选项)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyWealthOptionCount(Map<String,Object> paramMap){
		return familyWealthOptionBaseDao.selectFamilyWealthOptionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(家庭财富选项)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getFamilyWealthOptionCountDim(Map<String,Object> paramMap){
		return familyWealthOptionBaseDao.selectFamilyWealthOptionCount(paramMap,true);
	}
	/**
	 * 往(家庭财富选项)新增一条记录
	 * @param familyWealthOption
	 * @return
	 */
	@Override
	public int insertFamilyWealthOption(FamilyWealthOption familyWealthOption){
		return familyWealthOptionBaseDao.insertFamilyWealthOption(familyWealthOption);
	}
	/**
	 * 批量新增(家庭财富选项)
	 * @param familyWealthOptionList
	 * @return
	 */
	@Override
	public int insertFamilyWealthOptionBatch(List<FamilyWealthOption> familyWealthOptionList){
		return familyWealthOptionBaseDao.insertFamilyWealthOptionBatch(familyWealthOptionList);
	}
	/**
	 * 更新(家庭财富选项)信息
	 * @param familyWealthOption
	 * @return
	 */
	@Override
	public int updateFamilyWealthOption(FamilyWealthOption familyWealthOption){
		return familyWealthOptionBaseDao.updateFamilyWealthOption(familyWealthOption);
	}
	/**
	 * 批量更新(家庭财富选项)信息
	 * @param familyWealthOptionList
	 * @return
	 */
	@Override
	public int updateFamilyWealthOptionBatch(List<FamilyWealthOption> familyWealthOptionList){
		return familyWealthOptionBaseDao.updateFamilyWealthOptionBatch(familyWealthOptionList);
	}
	/**
	 * 根据序列号删除(家庭财富选项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyWealthOptionLogic(java.math.BigInteger id){
		return familyWealthOptionBaseDao.deleteFamilyWealthOptionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(家庭财富选项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyWealthOptionLogicBatch(List<java.math.BigInteger> idList){
		return familyWealthOptionBaseDao.deleteFamilyWealthOptionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(家庭财富选项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyWealthOption(java.math.BigInteger id){
//		return familyWealthOptionBaseDao.deleteFamilyWealthOption(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭财富选项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyWealthOptionBatch(List<java.math.BigInteger> idList){
//		return familyWealthOptionBaseDao.deleteFamilyWealthOptionBatch(idList);
//	}
	
}

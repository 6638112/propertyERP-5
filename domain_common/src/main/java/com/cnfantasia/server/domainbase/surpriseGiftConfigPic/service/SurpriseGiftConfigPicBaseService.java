package com.cnfantasia.server.domainbase.surpriseGiftConfigPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.surpriseGiftConfigPic.dao.ISurpriseGiftConfigPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * 描述:(意外惊喜配置图标) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SurpriseGiftConfigPicBaseService implements ISurpriseGiftConfigPicBaseService{
	
	private ISurpriseGiftConfigPicBaseDao surpriseGiftConfigPicBaseDao;
	public void setSurpriseGiftConfigPicBaseDao(ISurpriseGiftConfigPicBaseDao surpriseGiftConfigPicBaseDao) {
		this.surpriseGiftConfigPicBaseDao = surpriseGiftConfigPicBaseDao;
	}
	/**
	 * 根据条件查询(意外惊喜配置图标)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByCondition(Map<String,Object> paramMap){
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(意外惊喜配置图标)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByConditionDim(Map<String,Object> paramMap){
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(意外惊喜配置图标)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(意外惊喜配置图标)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SurpriseGiftConfigPic> getSurpriseGiftConfigPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(意外惊喜配置图标)信息
	 * @param id
	 * @return
	 */
	@Override
	public SurpriseGiftConfigPic getSurpriseGiftConfigPicBySeqId(java.math.BigInteger id){
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(意外惊喜配置图标)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSurpriseGiftConfigPicCount(Map<String,Object> paramMap){
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(意外惊喜配置图标)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSurpriseGiftConfigPicCountDim(Map<String,Object> paramMap){
		return surpriseGiftConfigPicBaseDao.selectSurpriseGiftConfigPicCount(paramMap,true);
	}
	/**
	 * 往(意外惊喜配置图标)新增一条记录
	 * @param surpriseGiftConfigPic
	 * @return
	 */
	@Override
	public int insertSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic){
		return surpriseGiftConfigPicBaseDao.insertSurpriseGiftConfigPic(surpriseGiftConfigPic);
	}
	/**
	 * 批量新增(意外惊喜配置图标)
	 * @param surpriseGiftConfigPicList
	 * @return
	 */
	@Override
	public int insertSurpriseGiftConfigPicBatch(List<SurpriseGiftConfigPic> surpriseGiftConfigPicList){
		return surpriseGiftConfigPicBaseDao.insertSurpriseGiftConfigPicBatch(surpriseGiftConfigPicList);
	}
	/**
	 * 更新(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPic
	 * @return
	 */
	@Override
	public int updateSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic){
		return surpriseGiftConfigPicBaseDao.updateSurpriseGiftConfigPic(surpriseGiftConfigPic);
	}
	/**
	 * 批量更新(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPicList
	 * @return
	 */
	@Override
	public int updateSurpriseGiftConfigPicBatch(List<SurpriseGiftConfigPic> surpriseGiftConfigPicList){
		return surpriseGiftConfigPicBaseDao.updateSurpriseGiftConfigPicBatch(surpriseGiftConfigPicList);
	}
	/**
	 * 根据序列号删除(意外惊喜配置图标)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftConfigPicLogic(java.math.BigInteger id){
		return surpriseGiftConfigPicBaseDao.deleteSurpriseGiftConfigPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(意外惊喜配置图标)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftConfigPicLogicBatch(List<java.math.BigInteger> idList){
		return surpriseGiftConfigPicBaseDao.deleteSurpriseGiftConfigPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(意外惊喜配置图标)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftConfigPic(java.math.BigInteger id){
//		return surpriseGiftConfigPicBaseDao.deleteSurpriseGiftConfigPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意外惊喜配置图标)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftConfigPicBatch(List<java.math.BigInteger> idList){
//		return surpriseGiftConfigPicBaseDao.deleteSurpriseGiftConfigPicBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.mrFeeItem.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrFeeItem.dao.IMrFeeItemBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;

/**
 * 描述:(抄表费收费 项配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrFeeItemBaseService implements IMrFeeItemBaseService{
	
	private IMrFeeItemBaseDao mrFeeItemBaseDao;
	public void setMrFeeItemBaseDao(IMrFeeItemBaseDao mrFeeItemBaseDao) {
		this.mrFeeItemBaseDao = mrFeeItemBaseDao;
	}
	/**
	 * 根据条件查询(抄表费收费 项配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrFeeItem> getMrFeeItemByCondition(Map<String,Object> paramMap){
		return mrFeeItemBaseDao.selectMrFeeItemByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表费收费 项配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrFeeItem> getMrFeeItemByConditionDim(Map<String,Object> paramMap){
		return mrFeeItemBaseDao.selectMrFeeItemByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表费收费 项配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrFeeItem> getMrFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrFeeItemBaseDao.selectMrFeeItemByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表费收费 项配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrFeeItem> getMrFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrFeeItemBaseDao.selectMrFeeItemByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表费收费 项配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrFeeItem getMrFeeItemBySeqId(java.math.BigInteger id){
		return mrFeeItemBaseDao.selectMrFeeItemBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费 项配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrFeeItemCount(Map<String,Object> paramMap){
		return mrFeeItemBaseDao.selectMrFeeItemCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费 项配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrFeeItemCountDim(Map<String,Object> paramMap){
		return mrFeeItemBaseDao.selectMrFeeItemCount(paramMap,true);
	}
	/**
	 * 往(抄表费收费 项配置)新增一条记录
	 * @param mrFeeItem
	 * @return
	 */
	@Override
	public int insertMrFeeItem(MrFeeItem mrFeeItem){
		return mrFeeItemBaseDao.insertMrFeeItem(mrFeeItem);
	}
	/**
	 * 批量新增(抄表费收费 项配置)
	 * @param mrFeeItemList
	 * @return
	 */
	@Override
	public int insertMrFeeItemBatch(List<MrFeeItem> mrFeeItemList){
		return mrFeeItemBaseDao.insertMrFeeItemBatch(mrFeeItemList);
	}
	/**
	 * 更新(抄表费收费 项配置)信息
	 * @param mrFeeItem
	 * @return
	 */
	@Override
	public int updateMrFeeItem(MrFeeItem mrFeeItem){
		return mrFeeItemBaseDao.updateMrFeeItem(mrFeeItem);
	}
	/**
	 * 批量更新(抄表费收费 项配置)信息
	 * @param mrFeeItemList
	 * @return
	 */
	@Override
	public int updateMrFeeItemBatch(List<MrFeeItem> mrFeeItemList){
		return mrFeeItemBaseDao.updateMrFeeItemBatch(mrFeeItemList);
	}
	/**
	 * 根据序列号删除(抄表费收费 项配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrFeeItemLogic(java.math.BigInteger id){
		return mrFeeItemBaseDao.deleteMrFeeItemLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表费收费 项配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrFeeItemLogicBatch(List<java.math.BigInteger> idList){
		return mrFeeItemBaseDao.deleteMrFeeItemLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表费收费 项配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrFeeItem(java.math.BigInteger id){
//		return mrFeeItemBaseDao.deleteMrFeeItem(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费 项配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrFeeItemBatch(List<java.math.BigInteger> idList){
//		return mrFeeItemBaseDao.deleteMrFeeItemBatch(idList);
//	}
	
}

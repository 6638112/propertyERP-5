package com.cnfantasia.server.domainbase.livingFeeSp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.livingFeeSp.dao.ILivingFeeSpBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeeSp.entity.LivingFeeSp;

/**
 * 描述:(宽带运营商) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LivingFeeSpBaseService implements ILivingFeeSpBaseService{
	
	private ILivingFeeSpBaseDao livingFeeSpBaseDao;
	public void setLivingFeeSpBaseDao(ILivingFeeSpBaseDao livingFeeSpBaseDao) {
		this.livingFeeSpBaseDao = livingFeeSpBaseDao;
	}
	/**
	 * 根据条件查询(宽带运营商)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LivingFeeSp> getLivingFeeSpByCondition(Map<String,Object> paramMap){
		return livingFeeSpBaseDao.selectLivingFeeSpByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(宽带运营商)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LivingFeeSp> getLivingFeeSpByConditionDim(Map<String,Object> paramMap){
		return livingFeeSpBaseDao.selectLivingFeeSpByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(宽带运营商)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LivingFeeSp> getLivingFeeSpByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return livingFeeSpBaseDao.selectLivingFeeSpByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(宽带运营商)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LivingFeeSp> getLivingFeeSpByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return livingFeeSpBaseDao.selectLivingFeeSpByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(宽带运营商)信息
	 * @param id
	 * @return
	 */
	@Override
	public LivingFeeSp getLivingFeeSpBySeqId(java.math.BigInteger id){
		return livingFeeSpBaseDao.selectLivingFeeSpBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(宽带运营商)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLivingFeeSpCount(Map<String,Object> paramMap){
		return livingFeeSpBaseDao.selectLivingFeeSpCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(宽带运营商)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLivingFeeSpCountDim(Map<String,Object> paramMap){
		return livingFeeSpBaseDao.selectLivingFeeSpCount(paramMap,true);
	}
	/**
	 * 往(宽带运营商)新增一条记录
	 * @param livingFeeSp
	 * @return
	 */
	@Override
	public int insertLivingFeeSp(LivingFeeSp livingFeeSp){
		return livingFeeSpBaseDao.insertLivingFeeSp(livingFeeSp);
	}
	/**
	 * 批量新增(宽带运营商)
	 * @param livingFeeSpList
	 * @return
	 */
	@Override
	public int insertLivingFeeSpBatch(List<LivingFeeSp> livingFeeSpList){
		return livingFeeSpBaseDao.insertLivingFeeSpBatch(livingFeeSpList);
	}
	/**
	 * 更新(宽带运营商)信息
	 * @param livingFeeSp
	 * @return
	 */
	@Override
	public int updateLivingFeeSp(LivingFeeSp livingFeeSp){
		return livingFeeSpBaseDao.updateLivingFeeSp(livingFeeSp);
	}
	/**
	 * 批量更新(宽带运营商)信息
	 * @param livingFeeSpList
	 * @return
	 */
	@Override
	public int updateLivingFeeSpBatch(List<LivingFeeSp> livingFeeSpList){
		return livingFeeSpBaseDao.updateLivingFeeSpBatch(livingFeeSpList);
	}
	/**
	 * 根据序列号删除(宽带运营商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeSpLogic(java.math.BigInteger id){
		return livingFeeSpBaseDao.deleteLivingFeeSpLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(宽带运营商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeSpLogicBatch(List<java.math.BigInteger> idList){
		return livingFeeSpBaseDao.deleteLivingFeeSpLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(宽带运营商)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeSp(java.math.BigInteger id){
//		return livingFeeSpBaseDao.deleteLivingFeeSp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(宽带运营商)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeSpBatch(List<java.math.BigInteger> idList){
//		return livingFeeSpBaseDao.deleteLivingFeeSpBatch(idList);
//	}
	
}

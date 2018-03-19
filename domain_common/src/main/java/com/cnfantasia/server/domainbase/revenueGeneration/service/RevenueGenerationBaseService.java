package com.cnfantasia.server.domainbase.revenueGeneration.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.revenueGeneration.dao.IRevenueGenerationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueGeneration.entity.RevenueGeneration;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RevenueGenerationBaseService implements IRevenueGenerationBaseService{
	
	private IRevenueGenerationBaseDao revenueGenerationBaseDao;
	public void setRevenueGenerationBaseDao(IRevenueGenerationBaseDao revenueGenerationBaseDao) {
		this.revenueGenerationBaseDao = revenueGenerationBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueGeneration> getRevenueGenerationByCondition(Map<String,Object> paramMap){
		return revenueGenerationBaseDao.selectRevenueGenerationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueGeneration> getRevenueGenerationByConditionDim(Map<String,Object> paramMap){
		return revenueGenerationBaseDao.selectRevenueGenerationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueGeneration> getRevenueGenerationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueGenerationBaseDao.selectRevenueGenerationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueGeneration> getRevenueGenerationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueGenerationBaseDao.selectRevenueGenerationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueGeneration getRevenueGenerationBySeqId(java.math.BigInteger id){
		return revenueGenerationBaseDao.selectRevenueGenerationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueGenerationCount(Map<String,Object> paramMap){
		return revenueGenerationBaseDao.selectRevenueGenerationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueGenerationCountDim(Map<String,Object> paramMap){
		return revenueGenerationBaseDao.selectRevenueGenerationCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param revenueGeneration
	 * @return
	 */
	@Override
	public int insertRevenueGeneration(RevenueGeneration revenueGeneration){
		return revenueGenerationBaseDao.insertRevenueGeneration(revenueGeneration);
	}
	/**
	 * 批量新增()
	 * @param revenueGenerationList
	 * @return
	 */
	@Override
	public int insertRevenueGenerationBatch(List<RevenueGeneration> revenueGenerationList){
		return revenueGenerationBaseDao.insertRevenueGenerationBatch(revenueGenerationList);
	}
	/**
	 * 更新()信息
	 * @param revenueGeneration
	 * @return
	 */
	@Override
	public int updateRevenueGeneration(RevenueGeneration revenueGeneration){
		return revenueGenerationBaseDao.updateRevenueGeneration(revenueGeneration);
	}
	/**
	 * 批量更新()信息
	 * @param revenueGenerationList
	 * @return
	 */
	@Override
	public int updateRevenueGenerationBatch(List<RevenueGeneration> revenueGenerationList){
		return revenueGenerationBaseDao.updateRevenueGenerationBatch(revenueGenerationList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteRevenueGenerationLogic(java.math.BigInteger id){
		return revenueGenerationBaseDao.deleteRevenueGenerationLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteRevenueGenerationLogicBatch(List<java.math.BigInteger> idList){
		return revenueGenerationBaseDao.deleteRevenueGenerationLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueGeneration(java.math.BigInteger id){
//		return revenueGenerationBaseDao.deleteRevenueGeneration(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueGenerationBatch(List<java.math.BigInteger> idList){
//		return revenueGenerationBaseDao.deleteRevenueGenerationBatch(idList);
//	}
	
}

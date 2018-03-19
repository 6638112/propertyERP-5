package com.cnfantasia.server.domainbase.support.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.support.dao.ISupportBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.support.entity.Support;

/**
 * 描述:(点赞信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SupportBaseService implements ISupportBaseService{
	
	private ISupportBaseDao supportBaseDao;
	public void setSupportBaseDao(ISupportBaseDao supportBaseDao) {
		this.supportBaseDao = supportBaseDao;
	}
	/**
	 * 根据条件查询(点赞信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Support> getSupportByCondition(Map<String,Object> paramMap){
		return supportBaseDao.selectSupportByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(点赞信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Support> getSupportByConditionDim(Map<String,Object> paramMap){
		return supportBaseDao.selectSupportByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(点赞信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Support> getSupportByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return supportBaseDao.selectSupportByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(点赞信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Support> getSupportByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return supportBaseDao.selectSupportByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(点赞信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public Support getSupportBySeqId(java.math.BigInteger id){
		return supportBaseDao.selectSupportBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(点赞信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSupportCount(Map<String,Object> paramMap){
		return supportBaseDao.selectSupportCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(点赞信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSupportCountDim(Map<String,Object> paramMap){
		return supportBaseDao.selectSupportCount(paramMap,true);
	}
	/**
	 * 往(点赞信息)新增一条记录
	 * @param support
	 * @return
	 */
	@Override
	public int insertSupport(Support support){
		return supportBaseDao.insertSupport(support);
	}
	/**
	 * 批量新增(点赞信息)
	 * @param supportList
	 * @return
	 */
	@Override
	public int insertSupportBatch(List<Support> supportList){
		return supportBaseDao.insertSupportBatch(supportList);
	}
	/**
	 * 更新(点赞信息)信息
	 * @param support
	 * @return
	 */
	@Override
	public int updateSupport(Support support){
		return supportBaseDao.updateSupport(support);
	}
	/**
	 * 批量更新(点赞信息)信息
	 * @param supportList
	 * @return
	 */
	@Override
	public int updateSupportBatch(List<Support> supportList){
		return supportBaseDao.updateSupportBatch(supportList);
	}
	/**
	 * 根据序列号删除(点赞信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSupportLogic(java.math.BigInteger id){
		return supportBaseDao.deleteSupportLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(点赞信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSupportLogicBatch(List<java.math.BigInteger> idList){
		return supportBaseDao.deleteSupportLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(点赞信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSupport(java.math.BigInteger id){
//		return supportBaseDao.deleteSupport(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(点赞信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSupportBatch(List<java.math.BigInteger> idList){
//		return supportBaseDao.deleteSupportBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.selfActivity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.selfActivity.dao.ISelfActivityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;

/**
 * 描述:(运营消息推送配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SelfActivityBaseService implements ISelfActivityBaseService{
	
	private ISelfActivityBaseDao selfActivityBaseDao;
	public void setSelfActivityBaseDao(ISelfActivityBaseDao selfActivityBaseDao) {
		this.selfActivityBaseDao = selfActivityBaseDao;
	}
	/**
	 * 根据条件查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SelfActivity> getSelfActivityByCondition(Map<String,Object> paramMap){
		return selfActivityBaseDao.selectSelfActivityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SelfActivity> getSelfActivityByConditionDim(Map<String,Object> paramMap){
		return selfActivityBaseDao.selectSelfActivityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SelfActivity> getSelfActivityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return selfActivityBaseDao.selectSelfActivityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SelfActivity> getSelfActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return selfActivityBaseDao.selectSelfActivityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public SelfActivity getSelfActivityBySeqId(java.math.BigInteger id){
		return selfActivityBaseDao.selectSelfActivityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSelfActivityCount(Map<String,Object> paramMap){
		return selfActivityBaseDao.selectSelfActivityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSelfActivityCountDim(Map<String,Object> paramMap){
		return selfActivityBaseDao.selectSelfActivityCount(paramMap,true);
	}
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param selfActivity
	 * @return
	 */
	@Override
	public int insertSelfActivity(SelfActivity selfActivity){
		return selfActivityBaseDao.insertSelfActivity(selfActivity);
	}
	/**
	 * 批量新增(运营消息推送配置表)
	 * @param selfActivityList
	 * @return
	 */
	@Override
	public int insertSelfActivityBatch(List<SelfActivity> selfActivityList){
		return selfActivityBaseDao.insertSelfActivityBatch(selfActivityList);
	}
	/**
	 * 更新(运营消息推送配置表)信息
	 * @param selfActivity
	 * @return
	 */
	@Override
	public int updateSelfActivity(SelfActivity selfActivity){
		return selfActivityBaseDao.updateSelfActivity(selfActivity);
	}
	/**
	 * 批量更新(运营消息推送配置表)信息
	 * @param selfActivityList
	 * @return
	 */
	@Override
	public int updateSelfActivityBatch(List<SelfActivity> selfActivityList){
		return selfActivityBaseDao.updateSelfActivityBatch(selfActivityList);
	}
	/**
	 * 根据序列号删除(运营消息推送配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityLogic(java.math.BigInteger id){
		return selfActivityBaseDao.deleteSelfActivityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(运营消息推送配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSelfActivityLogicBatch(List<java.math.BigInteger> idList){
		return selfActivityBaseDao.deleteSelfActivityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(运营消息推送配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivity(java.math.BigInteger id){
//		return selfActivityBaseDao.deleteSelfActivity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运营消息推送配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSelfActivityBatch(List<java.math.BigInteger> idList){
//		return selfActivityBaseDao.deleteSelfActivityBatch(idList);
//	}
	
}

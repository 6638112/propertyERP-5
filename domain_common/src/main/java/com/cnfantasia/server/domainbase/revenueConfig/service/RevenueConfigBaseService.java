package com.cnfantasia.server.domainbase.revenueConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.revenueConfig.dao.IRevenueConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;

/**
 * 描述:(收益规则配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RevenueConfigBaseService implements IRevenueConfigBaseService{
	
	private IRevenueConfigBaseDao revenueConfigBaseDao;
	public void setRevenueConfigBaseDao(IRevenueConfigBaseDao revenueConfigBaseDao) {
		this.revenueConfigBaseDao = revenueConfigBaseDao;
	}
	/**
	 * 根据条件查询(收益规则配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueConfig> getRevenueConfigByCondition(Map<String,Object> paramMap){
		return revenueConfigBaseDao.selectRevenueConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(收益规则配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueConfig> getRevenueConfigByConditionDim(Map<String,Object> paramMap){
		return revenueConfigBaseDao.selectRevenueConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(收益规则配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueConfig> getRevenueConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueConfigBaseDao.selectRevenueConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(收益规则配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueConfig> getRevenueConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueConfigBaseDao.selectRevenueConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(收益规则配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueConfig getRevenueConfigBySeqId(java.math.BigInteger id){
		return revenueConfigBaseDao.selectRevenueConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(收益规则配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueConfigCount(Map<String,Object> paramMap){
		return revenueConfigBaseDao.selectRevenueConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(收益规则配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueConfigCountDim(Map<String,Object> paramMap){
		return revenueConfigBaseDao.selectRevenueConfigCount(paramMap,true);
	}
	/**
	 * 往(收益规则配置表)新增一条记录
	 * @param revenueConfig
	 * @return
	 */
	@Override
	public int insertRevenueConfig(RevenueConfig revenueConfig){
		return revenueConfigBaseDao.insertRevenueConfig(revenueConfig);
	}
	/**
	 * 批量新增(收益规则配置表)
	 * @param revenueConfigList
	 * @return
	 */
	@Override
	public int insertRevenueConfigBatch(List<RevenueConfig> revenueConfigList){
		return revenueConfigBaseDao.insertRevenueConfigBatch(revenueConfigList);
	}
	/**
	 * 更新(收益规则配置表)信息
	 * @param revenueConfig
	 * @return
	 */
	@Override
	public int updateRevenueConfig(RevenueConfig revenueConfig){
		return revenueConfigBaseDao.updateRevenueConfig(revenueConfig);
	}
	/**
	 * 批量更新(收益规则配置表)信息
	 * @param revenueConfigList
	 * @return
	 */
	@Override
	public int updateRevenueConfigBatch(List<RevenueConfig> revenueConfigList){
		return revenueConfigBaseDao.updateRevenueConfigBatch(revenueConfigList);
	}
	/**
	 * 根据序列号删除(收益规则配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueConfigLogic(java.math.BigInteger id){
		return revenueConfigBaseDao.deleteRevenueConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(收益规则配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueConfigLogicBatch(List<java.math.BigInteger> idList){
		return revenueConfigBaseDao.deleteRevenueConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(收益规则配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueConfig(java.math.BigInteger id){
//		return revenueConfigBaseDao.deleteRevenueConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收益规则配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueConfigBatch(List<java.math.BigInteger> idList){
//		return revenueConfigBaseDao.deleteRevenueConfigBatch(idList);
//	}
	
}

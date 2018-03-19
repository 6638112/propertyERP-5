package com.cnfantasia.server.domainbase.pointRule.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.pointRule.dao.IPointRuleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.pointRule.entity.PointRule;

/**
 * 描述:(积分规则) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PointRuleBaseService implements IPointRuleBaseService{
	
	private IPointRuleBaseDao pointRuleBaseDao;
	public void setPointRuleBaseDao(IPointRuleBaseDao pointRuleBaseDao) {
		this.pointRuleBaseDao = pointRuleBaseDao;
	}
	/**
	 * 根据条件查询(积分规则)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointRule> getPointRuleByCondition(Map<String,Object> paramMap){
		return pointRuleBaseDao.selectPointRuleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(积分规则)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PointRule> getPointRuleByConditionDim(Map<String,Object> paramMap){
		return pointRuleBaseDao.selectPointRuleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(积分规则)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointRule> getPointRuleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return pointRuleBaseDao.selectPointRuleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(积分规则)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PointRule> getPointRuleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return pointRuleBaseDao.selectPointRuleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(积分规则)信息
	 * @param id
	 * @return
	 */
	@Override
	public PointRule getPointRuleBySeqId(java.math.BigInteger id){
		return pointRuleBaseDao.selectPointRuleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(积分规则)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointRuleCount(Map<String,Object> paramMap){
		return pointRuleBaseDao.selectPointRuleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(积分规则)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPointRuleCountDim(Map<String,Object> paramMap){
		return pointRuleBaseDao.selectPointRuleCount(paramMap,true);
	}
	/**
	 * 往(积分规则)新增一条记录
	 * @param pointRule
	 * @return
	 */
	@Override
	public int insertPointRule(PointRule pointRule){
		return pointRuleBaseDao.insertPointRule(pointRule);
	}
	/**
	 * 批量新增(积分规则)
	 * @param pointRuleList
	 * @return
	 */
	@Override
	public int insertPointRuleBatch(List<PointRule> pointRuleList){
		return pointRuleBaseDao.insertPointRuleBatch(pointRuleList);
	}
	/**
	 * 更新(积分规则)信息
	 * @param pointRule
	 * @return
	 */
	@Override
	public int updatePointRule(PointRule pointRule){
		return pointRuleBaseDao.updatePointRule(pointRule);
	}
	/**
	 * 批量更新(积分规则)信息
	 * @param pointRuleList
	 * @return
	 */
	@Override
	public int updatePointRuleBatch(List<PointRule> pointRuleList){
		return pointRuleBaseDao.updatePointRuleBatch(pointRuleList);
	}
	/**
	 * 根据序列号删除(积分规则)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePointRuleLogic(java.math.BigInteger id){
		return pointRuleBaseDao.deletePointRuleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(积分规则)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePointRuleLogicBatch(List<java.math.BigInteger> idList){
		return pointRuleBaseDao.deletePointRuleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(积分规则)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePointRule(java.math.BigInteger id){
//		return pointRuleBaseDao.deletePointRule(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(积分规则)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePointRuleBatch(List<java.math.BigInteger> idList){
//		return pointRuleBaseDao.deletePointRuleBatch(idList);
//	}
	
}

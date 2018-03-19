package com.cnfantasia.server.domainbase.selfActivity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;

/**
 * 描述:(运营消息推送配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISelfActivityBaseService {
	
	/**
	 * 根据条件查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SelfActivity> getSelfActivityByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SelfActivity> getSelfActivityByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SelfActivity> getSelfActivityByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(运营消息推送配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SelfActivity> getSelfActivityByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	public SelfActivity getSelfActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSelfActivityCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSelfActivityCountDim(Map<String,Object> paramMap);
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param selfActivity
	 * @return
	 */
	public int insertSelfActivity(SelfActivity selfActivity);
	/**
	 * 批量新增(运营消息推送配置表)
	 * @param selfActivityList
	 * @return
	 */
	public int insertSelfActivityBatch(List<SelfActivity> selfActivityList);
	/**
	 * 更新(运营消息推送配置表)信息
	 * @param selfActivity
	 * @return
	 */
	public int updateSelfActivity(SelfActivity selfActivity);
	/**
	 * 批量更新(运营消息推送配置表)信息
	 * @param selfActivityList
	 * @return
	 */
	public int updateSelfActivityBatch(List<SelfActivity> selfActivityList);
	/**
	 * 根据序列号删除(运营消息推送配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSelfActivityLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(运营消息推送配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSelfActivityLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(运营消息推送配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSelfActivity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(运营消息推送配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSelfActivityBatch(List<java.math.BigInteger> idList);
	
}

package com.cnfantasia.server.domainbase.selfActivity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.selfActivity.entity.SelfActivity;

/**
 * 描述:(运营消息推送配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISelfActivityBaseDao {
	/**
	 * 根据条件查询(运营消息推送配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SelfActivity> selectSelfActivityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(运营消息推送配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<SelfActivity> selectSelfActivityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(运营消息推送配置表)信息
	 * @param id
	 * @return
	 */
	public SelfActivity selectSelfActivityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运营消息推送配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectSelfActivityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(运营消息推送配置表)新增一条记录
	 * @param selfActivity
	 * @return
	 */
	public int insertSelfActivity(SelfActivity selfActivity);
	
	/**
	 * 批量新增(运营消息推送配置表)信息
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
	 * 根据Id 批量删除(运营消息推送配置表)信息_逻辑删除
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

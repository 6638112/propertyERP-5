package com.cnfantasia.server.domainbase.dredgeBillPushTime.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBillPushTime.entity.DredgeBillPushTime;

/**
 * 描述:(疏通工单推送时间记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillPushTimeBaseService {
	
	/**
	 * 根据条件查询(疏通工单推送时间记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillPushTime> getDredgeBillPushTimeByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(疏通工单推送时间记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBillPushTime> getDredgeBillPushTimeByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(疏通工单推送时间记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillPushTime> getDredgeBillPushTimeByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(疏通工单推送时间记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBillPushTime> getDredgeBillPushTimeByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(疏通工单推送时间记录表)信息
	 * @param id
	 * @return
	 */
	public DredgeBillPushTime getDredgeBillPushTimeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通工单推送时间记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillPushTimeCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通工单推送时间记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillPushTimeCountDim(Map<String, Object> paramMap);
	/**
	 * 往(疏通工单推送时间记录表)新增一条记录
	 * @param dredgeBillPushTime
	 * @return
	 */
	public int insertDredgeBillPushTime(DredgeBillPushTime dredgeBillPushTime);
	/**
	 * 批量新增(疏通工单推送时间记录表)
	 * @param dredgeBillPushTimeList
	 * @return
	 */
	public int insertDredgeBillPushTimeBatch(List<DredgeBillPushTime> dredgeBillPushTimeList);
	/**
	 * 更新(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTime
	 * @return
	 */
	public int updateDredgeBillPushTime(DredgeBillPushTime dredgeBillPushTime);
	/**
	 * 批量更新(疏通工单推送时间记录表)信息
	 * @param dredgeBillPushTimeList
	 * @return
	 */
	public int updateDredgeBillPushTimeBatch(List<DredgeBillPushTime> dredgeBillPushTimeList);
	/**
	 * 根据序列号删除(疏通工单推送时间记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteDredgeBillPushTimeLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(疏通工单推送时间记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteDredgeBillPushTimeLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(疏通工单推送时间记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBillPushTime(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通工单推送时间记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillPushTimeBatch(List<java.math.BigInteger> idList);
	
}

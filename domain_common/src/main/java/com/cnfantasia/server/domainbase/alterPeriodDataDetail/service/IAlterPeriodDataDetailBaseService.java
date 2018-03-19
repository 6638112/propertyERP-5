package com.cnfantasia.server.domainbase.alterPeriodDataDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;

/**
 * 描述:(选择周期数据详情) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAlterPeriodDataDetailBaseService {
	
	/**
	 * 根据条件查询(选择周期数据详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(选择周期数据详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(选择周期数据详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(选择周期数据详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(选择周期数据详情)信息
	 * @param id
	 * @return
	 */
	public AlterPeriodDataDetail getAlterPeriodDataDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(选择周期数据详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAlterPeriodDataDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(选择周期数据详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAlterPeriodDataDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(选择周期数据详情)新增一条记录
	 * @param alterPeriodDataDetail
	 * @return
	 */
	public int insertAlterPeriodDataDetail(AlterPeriodDataDetail alterPeriodDataDetail);
	/**
	 * 批量新增(选择周期数据详情)
	 * @param alterPeriodDataDetailList
	 * @return
	 */
	public int insertAlterPeriodDataDetailBatch(List<AlterPeriodDataDetail> alterPeriodDataDetailList);
	/**
	 * 更新(选择周期数据详情)信息
	 * @param alterPeriodDataDetail
	 * @return
	 */
	public int updateAlterPeriodDataDetail(AlterPeriodDataDetail alterPeriodDataDetail);
	/**
	 * 批量更新(选择周期数据详情)信息
	 * @param alterPeriodDataDetailList
	 * @return
	 */
	public int updateAlterPeriodDataDetailBatch(List<AlterPeriodDataDetail> alterPeriodDataDetailList);
	/**
	 * 根据序列号删除(选择周期数据详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAlterPeriodDataDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(选择周期数据详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAlterPeriodDataDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(选择周期数据详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAlterPeriodDataDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(选择周期数据详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAlterPeriodDataDetailBatch(List<java.math.BigInteger> idList);
	
}

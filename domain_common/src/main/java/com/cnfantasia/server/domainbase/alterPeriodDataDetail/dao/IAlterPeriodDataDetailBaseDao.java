package com.cnfantasia.server.domainbase.alterPeriodDataDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;

/**
 * 描述:(选择周期数据详情) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAlterPeriodDataDetailBaseDao {
	/**
	 * 根据条件查询(选择周期数据详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AlterPeriodDataDetail> selectAlterPeriodDataDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(选择周期数据详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AlterPeriodDataDetail> selectAlterPeriodDataDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(选择周期数据详情)信息
	 * @param id
	 * @return
	 */
	public AlterPeriodDataDetail selectAlterPeriodDataDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(选择周期数据详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAlterPeriodDataDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(选择周期数据详情)新增一条记录
	 * @param alterPeriodDataDetail
	 * @return
	 */
	public int insertAlterPeriodDataDetail(AlterPeriodDataDetail alterPeriodDataDetail);
	
	/**
	 * 批量新增(选择周期数据详情)信息
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
	 * 根据Id 批量删除(选择周期数据详情)信息_逻辑删除
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

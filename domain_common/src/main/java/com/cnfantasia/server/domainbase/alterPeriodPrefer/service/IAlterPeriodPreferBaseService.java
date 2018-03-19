package com.cnfantasia.server.domainbase.alterPeriodPrefer.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.alterPeriodPrefer.entity.AlterPeriodPrefer;

/**
 * 描述:(选择周期缴费优惠表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAlterPeriodPreferBaseService {
	
	/**
	 * 根据条件查询(选择周期缴费优惠表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterPeriodPrefer> getAlterPeriodPreferByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(选择周期缴费优惠表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<AlterPeriodPrefer> getAlterPeriodPreferByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(选择周期缴费优惠表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterPeriodPrefer> getAlterPeriodPreferByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(选择周期缴费优惠表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<AlterPeriodPrefer> getAlterPeriodPreferByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(选择周期缴费优惠表)信息
	 * @param id
	 * @return
	 */
	AlterPeriodPrefer getAlterPeriodPreferBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(选择周期缴费优惠表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getAlterPeriodPreferCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(选择周期缴费优惠表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getAlterPeriodPreferCountDim(Map<String, Object> paramMap);
	/**
	 * 往(选择周期缴费优惠表)新增一条记录
	 * @param alterPeriodPrefer
	 * @return
	 */
	int insertAlterPeriodPrefer(AlterPeriodPrefer alterPeriodPrefer);
	/**
	 * 批量新增(选择周期缴费优惠表)
	 * @param alterPeriodPreferList
	 * @return
	 */
	int insertAlterPeriodPreferBatch(List<AlterPeriodPrefer> alterPeriodPreferList);
	/**
	 * 更新(选择周期缴费优惠表)信息
	 * @param alterPeriodPrefer
	 * @return
	 */
	int updateAlterPeriodPrefer(AlterPeriodPrefer alterPeriodPrefer);
	/**
	 * 批量更新(选择周期缴费优惠表)信息
	 * @param alterPeriodPreferList
	 * @return
	 */
	int updateAlterPeriodPreferBatch(List<AlterPeriodPrefer> alterPeriodPreferList);
	/**
	 * 根据序列号删除(选择周期缴费优惠表)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteAlterPeriodPreferLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(选择周期缴费优惠表)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteAlterPeriodPreferLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(选择周期缴费优惠表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAlterPeriodPrefer(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(选择周期缴费优惠表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAlterPeriodPreferBatch(List<java.math.BigInteger> idList);
	
}

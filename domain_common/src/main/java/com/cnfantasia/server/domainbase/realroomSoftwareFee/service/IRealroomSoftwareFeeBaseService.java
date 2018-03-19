package com.cnfantasia.server.domainbase.realroomSoftwareFee.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.realroomSoftwareFee.entity.RealroomSoftwareFee;

/**
 * 描述:(查询的物业账单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealroomSoftwareFeeBaseService {
	
	/**
	 * 根据条件查询(查询的物业账单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(查询的物业账单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(查询的物业账单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(查询的物业账单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RealroomSoftwareFee> getRealroomSoftwareFeeByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(查询的物业账单)信息
	 * @param id
	 * @return
	 */
	public RealroomSoftwareFee getRealroomSoftwareFeeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(查询的物业账单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRealroomSoftwareFeeCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(查询的物业账单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRealroomSoftwareFeeCountDim(Map<String, Object> paramMap);
	/**
	 * 往(查询的物业账单)新增一条记录
	 * @param realroomSoftwareFee
	 * @return
	 */
	public int insertRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee);
	/**
	 * 批量新增(查询的物业账单)
	 * @param realroomSoftwareFeeList
	 * @return
	 */
	public int insertRealroomSoftwareFeeBatch(List<RealroomSoftwareFee> realroomSoftwareFeeList);
	/**
	 * 更新(查询的物业账单)信息
	 * @param realroomSoftwareFee
	 * @return
	 */
	public int updateRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee);
	/**
	 * 批量更新(查询的物业账单)信息
	 * @param realroomSoftwareFeeList
	 * @return
	 */
	public int updateRealroomSoftwareFeeBatch(List<RealroomSoftwareFee> realroomSoftwareFeeList);
	/**
	 * 根据序列号删除(查询的物业账单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteRealroomSoftwareFeeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(查询的物业账单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteRealroomSoftwareFeeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(查询的物业账单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRealroomSoftwareFee(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(查询的物业账单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRealroomSoftwareFeeBatch(List<java.math.BigInteger> idList);
	
}

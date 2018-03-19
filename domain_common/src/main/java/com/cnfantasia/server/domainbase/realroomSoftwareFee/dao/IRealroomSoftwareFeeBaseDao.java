package com.cnfantasia.server.domainbase.realroomSoftwareFee.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realroomSoftwareFee.entity.RealroomSoftwareFee;

/**
 * 描述:(查询的物业账单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRealroomSoftwareFeeBaseDao {
	/**
	 * 根据条件查询(查询的物业账单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealroomSoftwareFee> selectRealroomSoftwareFeeByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(查询的物业账单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RealroomSoftwareFee> selectRealroomSoftwareFeeByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(查询的物业账单)信息
	 * @param id
	 * @return
	 */
	public RealroomSoftwareFee selectRealroomSoftwareFeeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(查询的物业账单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRealroomSoftwareFeeCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(查询的物业账单)新增一条记录
	 * @param realroomSoftwareFee
	 * @return
	 */
	public int insertRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee);
	
	/**
	 * 批量新增(查询的物业账单)信息
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
	 * 根据Id 批量删除(查询的物业账单)信息_逻辑删除
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

package com.cnfantasia.server.domainbase.dredgeAddress.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeAddress.entity.DredgeAddress;

/**
 * 描述:(维修地址) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeAddressBaseDao {
	/**
	 * 根据条件查询(维修地址)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeAddress> selectDredgeAddressByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(维修地址)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeAddress> selectDredgeAddressByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(维修地址)信息
	 * @param id
	 * @return
	 */
	public DredgeAddress selectDredgeAddressBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修地址)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeAddressCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(维修地址)新增一条记录
	 * @param dredgeAddress
	 * @return
	 */
	public int insertDredgeAddress(DredgeAddress dredgeAddress);
	
	/**
	 * 批量新增(维修地址)信息
	 * @param dredgeAddressList
	 * @return
	 */
	public int insertDredgeAddressBatch(List<DredgeAddress> dredgeAddressList);
	
	/**
	 * 更新(维修地址)信息
	 * @param dredgeAddress
	 * @return
	 */
	public int updateDredgeAddress(DredgeAddress dredgeAddress);
	
	/**
	 * 批量更新(维修地址)信息
	 * @param dredgeAddressList
	 * @return
	 */
	public int updateDredgeAddressBatch(List<DredgeAddress> dredgeAddressList);
	
	/**
	 * 根据序列号删除(维修地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeAddressLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(维修地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeAddressLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(维修地址)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeAddress(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(维修地址)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeAddressBatch(List<java.math.BigInteger> idList);
	
	
}

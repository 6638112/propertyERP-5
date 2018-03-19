package com.cnfantasia.server.domainbase.dredgeAddress.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeAddress.entity.DredgeAddress;

/**
 * 描述:(维修地址) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeAddressBaseService {
	
	/**
	 * 根据条件查询(维修地址)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeAddress> getDredgeAddressByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(维修地址)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeAddress> getDredgeAddressByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(维修地址)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeAddress> getDredgeAddressByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(维修地址)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeAddress> getDredgeAddressByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(维修地址)信息
	 * @param id
	 * @return
	 */
	public DredgeAddress getDredgeAddressBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(维修地址)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeAddressCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(维修地址)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeAddressCountDim(Map<String, Object> paramMap);
	/**
	 * 往(维修地址)新增一条记录
	 * @param dredgeAddress
	 * @return
	 */
	public int insertDredgeAddress(DredgeAddress dredgeAddress);
	/**
	 * 批量新增(维修地址)
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
	 * 根据序列号批量删除(维修地址)信息_逻辑删除
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

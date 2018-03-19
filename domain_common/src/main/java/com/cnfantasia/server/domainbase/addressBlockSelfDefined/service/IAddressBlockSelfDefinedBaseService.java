package com.cnfantasia.server.domainbase.addressBlockSelfDefined.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressBlockSelfDefined.entity.AddressBlockSelfDefined;

/**
 * 描述:(自定义片区) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressBlockSelfDefinedBaseService {
	
	/**
	 * 根据条件查询(自定义片区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(自定义片区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(自定义片区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(自定义片区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(自定义片区)信息
	 * @param id
	 * @return
	 */
	public AddressBlockSelfDefined getAddressBlockSelfDefinedBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(自定义片区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressBlockSelfDefinedCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(自定义片区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressBlockSelfDefinedCountDim(Map<String,Object> paramMap);
	/**
	 * 往(自定义片区)新增一条记录
	 * @param addressBlockSelfDefined
	 * @return
	 */
	public int insertAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined);
	/**
	 * 批量新增(自定义片区)
	 * @param addressBlockSelfDefinedList
	 * @return
	 */
	public int insertAddressBlockSelfDefinedBatch(List<AddressBlockSelfDefined> addressBlockSelfDefinedList);
	/**
	 * 更新(自定义片区)信息
	 * @param addressBlockSelfDefined
	 * @return
	 */
	public int updateAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined);
	/**
	 * 批量更新(自定义片区)信息
	 * @param addressBlockSelfDefinedList
	 * @return
	 */
	public int updateAddressBlockSelfDefinedBatch(List<AddressBlockSelfDefined> addressBlockSelfDefinedList);
	/**
	 * 根据序列号删除(自定义片区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAddressBlockSelfDefinedLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(自定义片区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAddressBlockSelfDefinedLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(自定义片区)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAddressBlockSelfDefined(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(自定义片区)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAddressBlockSelfDefinedBatch(List<java.math.BigInteger> idList);
	
}

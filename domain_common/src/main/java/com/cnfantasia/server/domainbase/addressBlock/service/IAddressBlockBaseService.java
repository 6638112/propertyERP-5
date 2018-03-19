package com.cnfantasia.server.domainbase.addressBlock.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;

/**
 * 描述:(区) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressBlockBaseService {
	
	/**
	 * 根据条件查询(区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressBlock> getAddressBlockByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressBlock> getAddressBlockByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressBlock> getAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressBlock> getAddressBlockByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(区)信息
	 * @param id
	 * @return
	 */
	public AddressBlock getAddressBlockBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressBlockCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressBlockCountDim(Map<String,Object> paramMap);
	/**
	 * 往(区)新增一条记录
	 * @param addressBlock
	 * @return
	 */
	public int insertAddressBlock(AddressBlock addressBlock);
	/**
	 * 批量新增(区)
	 * @param addressBlockList
	 * @return
	 */
	public int insertAddressBlockBatch(List<AddressBlock> addressBlockList);
	/**
	 * 更新(区)信息
	 * @param addressBlock
	 * @return
	 */
	public int updateAddressBlock(AddressBlock addressBlock);
	/**
	 * 批量更新(区)信息
	 * @param addressBlockList
	 * @return
	 */
	public int updateAddressBlockBatch(List<AddressBlock> addressBlockList);
	/**
	 * 根据序列号删除(区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAddressBlockLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAddressBlockLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(区)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAddressBlock(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(区)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAddressBlockBatch(List<java.math.BigInteger> idList);
	
}

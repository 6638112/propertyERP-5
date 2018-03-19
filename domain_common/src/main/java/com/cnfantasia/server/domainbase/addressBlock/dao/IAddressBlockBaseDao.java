package com.cnfantasia.server.domainbase.addressBlock.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;

/**
 * 描述:(区) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressBlockBaseDao {
	/**
	 * 根据条件查询(区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressBlock> selectAddressBlockByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressBlock> selectAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(区)信息
	 * @param id
	 * @return
	 */
	public AddressBlock selectAddressBlockBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAddressBlockCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(区)新增一条记录
	 * @param addressBlock
	 * @return
	 */
	public int insertAddressBlock(AddressBlock addressBlock);
	
	/**
	 * 批量新增(区)信息
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
	 * 根据Id 批量删除(区)信息_逻辑删除
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

package com.cnfantasia.server.domainbase.addressBlockSelfDefined.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressBlockSelfDefined.entity.AddressBlockSelfDefined;

/**
 * 描述:(自定义片区) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressBlockSelfDefinedBaseDao {
	/**
	 * 根据条件查询(自定义片区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressBlockSelfDefined> selectAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(自定义片区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressBlockSelfDefined> selectAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(自定义片区)信息
	 * @param id
	 * @return
	 */
	public AddressBlockSelfDefined selectAddressBlockSelfDefinedBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(自定义片区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAddressBlockSelfDefinedCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(自定义片区)新增一条记录
	 * @param addressBlockSelfDefined
	 * @return
	 */
	public int insertAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined);
	
	/**
	 * 批量新增(自定义片区)信息
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
	 * 根据Id 批量删除(自定义片区)信息_逻辑删除
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

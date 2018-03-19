package com.cnfantasia.server.domainbase.ebuyDeliveryAddress.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;

/**
 * 描述:(收货地址) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryAddressBaseService {
	
	/**
	 * 根据条件查询(收货地址)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(收货地址)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(收货地址)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(收货地址)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(收货地址)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryAddress getEbuyDeliveryAddressBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(收货地址)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryAddressCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(收货地址)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryAddressCountDim(Map<String,Object> paramMap);
	/**
	 * 往(收货地址)新增一条记录
	 * @param ebuyDeliveryAddress
	 * @return
	 */
	public int insertEbuyDeliveryAddress(EbuyDeliveryAddress ebuyDeliveryAddress);
	/**
	 * 批量新增(收货地址)
	 * @param ebuyDeliveryAddressList
	 * @return
	 */
	public int insertEbuyDeliveryAddressBatch(List<EbuyDeliveryAddress> ebuyDeliveryAddressList);
	/**
	 * 更新(收货地址)信息
	 * @param ebuyDeliveryAddress
	 * @return
	 */
	public int updateEbuyDeliveryAddress(EbuyDeliveryAddress ebuyDeliveryAddress);
	/**
	 * 批量更新(收货地址)信息
	 * @param ebuyDeliveryAddressList
	 * @return
	 */
	public int updateEbuyDeliveryAddressBatch(List<EbuyDeliveryAddress> ebuyDeliveryAddressList);
	/**
	 * 根据序列号删除(收货地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyDeliveryAddressLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(收货地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyDeliveryAddressLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(收货地址)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryAddress(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(收货地址)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryAddressBatch(List<java.math.BigInteger> idList);
	
}

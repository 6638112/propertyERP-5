package com.cnfantasia.server.domainbase.ebuyHandleAddress.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;

/**
 * 描述:(手动输入的收货地址) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyHandleAddressBaseDao {
	/**
	 * 根据条件查询(手动输入的收货地址)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyHandleAddress> selectEbuyHandleAddressByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(手动输入的收货地址)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyHandleAddress> selectEbuyHandleAddressByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(手动输入的收货地址)信息
	 * @param id
	 * @return
	 */
	public EbuyHandleAddress selectEbuyHandleAddressBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(手动输入的收货地址)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyHandleAddressCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(手动输入的收货地址)新增一条记录
	 * @param ebuyHandleAddress
	 * @return
	 */
	public int insertEbuyHandleAddress(EbuyHandleAddress ebuyHandleAddress);
	
	/**
	 * 批量新增(手动输入的收货地址)信息
	 * @param ebuyHandleAddressList
	 * @return
	 */
	public int insertEbuyHandleAddressBatch(List<EbuyHandleAddress> ebuyHandleAddressList);
	
	/**
	 * 更新(手动输入的收货地址)信息
	 * @param ebuyHandleAddress
	 * @return
	 */
	public int updateEbuyHandleAddress(EbuyHandleAddress ebuyHandleAddress);
	
	/**
	 * 批量更新(手动输入的收货地址)信息
	 * @param ebuyHandleAddressList
	 * @return
	 */
	public int updateEbuyHandleAddressBatch(List<EbuyHandleAddress> ebuyHandleAddressList);
	
	/**
	 * 根据序列号删除(手动输入的收货地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyHandleAddressLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(手动输入的收货地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyHandleAddressLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(手动输入的收货地址)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyHandleAddress(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(手动输入的收货地址)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyHandleAddressBatch(List<java.math.BigInteger> idList);
	
	
}

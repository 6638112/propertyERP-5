package com.cnfantasia.server.domainbase.addressCountry.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressCountry.entity.AddressCountry;

/**
 * 描述:(国家表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressCountryBaseService {
	
	/**
	 * 根据条件查询(国家表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressCountry> getAddressCountryByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(国家表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressCountry> getAddressCountryByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(国家表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressCountry> getAddressCountryByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(国家表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressCountry> getAddressCountryByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(国家表)信息
	 * @param id
	 * @return
	 */
	public AddressCountry getAddressCountryBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(国家表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressCountryCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(国家表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressCountryCountDim(Map<String,Object> paramMap);
	/**
	 * 往(国家表)新增一条记录
	 * @param addressCountry
	 * @return
	 */
	public int insertAddressCountry(AddressCountry addressCountry);
	/**
	 * 批量新增(国家表)
	 * @param addressCountryList
	 * @return
	 */
	public int insertAddressCountryBatch(List<AddressCountry> addressCountryList);
	/**
	 * 更新(国家表)信息
	 * @param addressCountry
	 * @return
	 */
	public int updateAddressCountry(AddressCountry addressCountry);
	/**
	 * 批量更新(国家表)信息
	 * @param addressCountryList
	 * @return
	 */
	public int updateAddressCountryBatch(List<AddressCountry> addressCountryList);
	/**
	 * 根据序列号删除(国家表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAddressCountryLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(国家表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAddressCountryLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(国家表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAddressCountry(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(国家表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAddressCountryBatch(List<java.math.BigInteger> idList);
	
}

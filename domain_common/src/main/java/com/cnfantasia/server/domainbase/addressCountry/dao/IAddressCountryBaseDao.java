package com.cnfantasia.server.domainbase.addressCountry.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCountry.entity.AddressCountry;

/**
 * 描述:(国家表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressCountryBaseDao {
	/**
	 * 根据条件查询(国家表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressCountry> selectAddressCountryByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(国家表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressCountry> selectAddressCountryByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(国家表)信息
	 * @param id
	 * @return
	 */
	public AddressCountry selectAddressCountryBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(国家表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAddressCountryCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(国家表)新增一条记录
	 * @param addressCountry
	 * @return
	 */
	public int insertAddressCountry(AddressCountry addressCountry);
	
	/**
	 * 批量新增(国家表)信息
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
	 * 根据Id 批量删除(国家表)信息_逻辑删除
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

package com.cnfantasia.server.domainbase.addressCity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;

/**
 * 描述:(市) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressCityBaseService {
	
	/**
	 * 根据条件查询(市)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressCity> getAddressCityByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(市)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressCity> getAddressCityByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(市)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressCity> getAddressCityByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(市)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressCity> getAddressCityByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(市)信息
	 * @param id
	 * @return
	 */
	public AddressCity getAddressCityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(市)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressCityCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(市)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressCityCountDim(Map<String,Object> paramMap);
	/**
	 * 往(市)新增一条记录
	 * @param addressCity
	 * @return
	 */
	public int insertAddressCity(AddressCity addressCity);
	/**
	 * 批量新增(市)
	 * @param addressCityList
	 * @return
	 */
	public int insertAddressCityBatch(List<AddressCity> addressCityList);
	/**
	 * 更新(市)信息
	 * @param addressCity
	 * @return
	 */
	public int updateAddressCity(AddressCity addressCity);
	/**
	 * 批量更新(市)信息
	 * @param addressCityList
	 * @return
	 */
	public int updateAddressCityBatch(List<AddressCity> addressCityList);
	/**
	 * 根据序列号删除(市)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAddressCityLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(市)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAddressCityLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(市)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAddressCity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(市)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAddressCityBatch(List<java.math.BigInteger> idList);
	
}

package com.cnfantasia.server.domainbase.addressCity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;

/**
 * 描述:(市) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressCityBaseDao {
	/**
	 * 根据条件查询(市)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressCity> selectAddressCityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(市)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressCity> selectAddressCityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(市)信息
	 * @param id
	 * @return
	 */
	public AddressCity selectAddressCityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(市)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAddressCityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(市)新增一条记录
	 * @param addressCity
	 * @return
	 */
	public int insertAddressCity(AddressCity addressCity);
	
	/**
	 * 批量新增(市)信息
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
	 * 根据Id 批量删除(市)信息_逻辑删除
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

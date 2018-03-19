package com.cnfantasia.server.domainbase.addressProvince.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

/**
 * 描述:(省) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressProvinceBaseDao {
	/**
	 * 根据条件查询(省)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressProvince> selectAddressProvinceByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(省)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressProvince> selectAddressProvinceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(省)信息
	 * @param id
	 * @return
	 */
	public AddressProvince selectAddressProvinceBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(省)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAddressProvinceCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(省)新增一条记录
	 * @param addressProvince
	 * @return
	 */
	public int insertAddressProvince(AddressProvince addressProvince);
	
	/**
	 * 批量新增(省)信息
	 * @param addressProvinceList
	 * @return
	 */
	public int insertAddressProvinceBatch(List<AddressProvince> addressProvinceList);
	
	/**
	 * 更新(省)信息
	 * @param addressProvince
	 * @return
	 */
	public int updateAddressProvince(AddressProvince addressProvince);
	
	/**
	 * 批量更新(省)信息
	 * @param addressProvinceList
	 * @return
	 */
	public int updateAddressProvinceBatch(List<AddressProvince> addressProvinceList);
	
	/**
	 * 根据序列号删除(省)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAddressProvinceLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(省)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAddressProvinceLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(省)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAddressProvince(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(省)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAddressProvinceBatch(List<java.math.BigInteger> idList);
	
	
}

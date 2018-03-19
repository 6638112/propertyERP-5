package com.cnfantasia.server.domainbase.addressCityHot.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.addressCityHot.entity.AddressCityHot;

/**
 * 描述:(热门城市) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressCityHotBaseService {
	
	/**
	 * 根据条件查询(热门城市)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressCityHot> getAddressCityHotByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(热门城市)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<AddressCityHot> getAddressCityHotByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(热门城市)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressCityHot> getAddressCityHotByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(热门城市)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<AddressCityHot> getAddressCityHotByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(热门城市)信息
	 * @param id
	 * @return
	 */
	public AddressCityHot getAddressCityHotBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(热门城市)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressCityHotCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(热门城市)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getAddressCityHotCountDim(Map<String,Object> paramMap);
	/**
	 * 往(热门城市)新增一条记录
	 * @param addressCityHot
	 * @return
	 */
	public int insertAddressCityHot(AddressCityHot addressCityHot);
	/**
	 * 批量新增(热门城市)
	 * @param addressCityHotList
	 * @return
	 */
	public int insertAddressCityHotBatch(List<AddressCityHot> addressCityHotList);
	/**
	 * 更新(热门城市)信息
	 * @param addressCityHot
	 * @return
	 */
	public int updateAddressCityHot(AddressCityHot addressCityHot);
	/**
	 * 批量更新(热门城市)信息
	 * @param addressCityHotList
	 * @return
	 */
	public int updateAddressCityHotBatch(List<AddressCityHot> addressCityHotList);
	/**
	 * 根据序列号删除(热门城市)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteAddressCityHotLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(热门城市)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteAddressCityHotLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(热门城市)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteAddressCityHot(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(热门城市)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteAddressCityHotBatch(List<java.math.BigInteger> idList);
	
}

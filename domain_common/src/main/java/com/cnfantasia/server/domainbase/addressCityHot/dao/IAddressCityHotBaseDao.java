package com.cnfantasia.server.domainbase.addressCityHot.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCityHot.entity.AddressCityHot;

/**
 * 描述:(热门城市) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IAddressCityHotBaseDao {
	/**
	 * 根据条件查询(热门城市)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressCityHot> selectAddressCityHotByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(热门城市)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<AddressCityHot> selectAddressCityHotByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(热门城市)信息
	 * @param id
	 * @return
	 */
	public AddressCityHot selectAddressCityHotBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(热门城市)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectAddressCityHotCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(热门城市)新增一条记录
	 * @param addressCityHot
	 * @return
	 */
	public int insertAddressCityHot(AddressCityHot addressCityHot);
	
	/**
	 * 批量新增(热门城市)信息
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
	 * 根据Id 批量删除(热门城市)信息_逻辑删除
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

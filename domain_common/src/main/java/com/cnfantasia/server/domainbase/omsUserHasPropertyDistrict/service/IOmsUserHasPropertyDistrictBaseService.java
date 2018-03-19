package com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.entity.OmsUserHasPropertyDistrict;

/**
 * 描述:(用户和片区关联表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserHasPropertyDistrictBaseService {
	
	/**
	 * 根据条件查询(用户和片区关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户和片区关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户和片区关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户和片区关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUserHasPropertyDistrict> getOmsUserHasPropertyDistrictByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户和片区关联表)信息
	 * @param id
	 * @return
	 */
	public OmsUserHasPropertyDistrict getOmsUserHasPropertyDistrictBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户和片区关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserHasPropertyDistrictCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户和片区关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserHasPropertyDistrictCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户和片区关联表)新增一条记录
	 * @param omsUserHasPropertyDistrict
	 * @return
	 */
	public int insertOmsUserHasPropertyDistrict(OmsUserHasPropertyDistrict omsUserHasPropertyDistrict);
	/**
	 * 批量新增(用户和片区关联表)
	 * @param omsUserHasPropertyDistrictList
	 * @return
	 */
	public int insertOmsUserHasPropertyDistrictBatch(List<OmsUserHasPropertyDistrict> omsUserHasPropertyDistrictList);
	/**
	 * 更新(用户和片区关联表)信息
	 * @param omsUserHasPropertyDistrict
	 * @return
	 */
	public int updateOmsUserHasPropertyDistrict(OmsUserHasPropertyDistrict omsUserHasPropertyDistrict);
	/**
	 * 批量更新(用户和片区关联表)信息
	 * @param omsUserHasPropertyDistrictList
	 * @return
	 */
	public int updateOmsUserHasPropertyDistrictBatch(List<OmsUserHasPropertyDistrict> omsUserHasPropertyDistrictList);
	/**
	 * 根据序列号删除(用户和片区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsUserHasPropertyDistrictLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户和片区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsUserHasPropertyDistrictLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户和片区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsUserHasPropertyDistrict(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户和片区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsUserHasPropertyDistrictBatch(List<java.math.BigInteger> idList);
	
}

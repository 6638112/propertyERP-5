package com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.entity.OmsUserHasPropertyDistrict;

/**
 * 描述:(用户和片区关联表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserHasPropertyDistrictBaseDao {
	/**
	 * 根据条件查询(用户和片区关联表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserHasPropertyDistrict> selectOmsUserHasPropertyDistrictByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户和片区关联表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUserHasPropertyDistrict> selectOmsUserHasPropertyDistrictByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户和片区关联表)信息
	 * @param id
	 * @return
	 */
	public OmsUserHasPropertyDistrict selectOmsUserHasPropertyDistrictBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户和片区关联表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsUserHasPropertyDistrictCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户和片区关联表)新增一条记录
	 * @param omsUserHasPropertyDistrict
	 * @return
	 */
	public int insertOmsUserHasPropertyDistrict(OmsUserHasPropertyDistrict omsUserHasPropertyDistrict);
	
	/**
	 * 批量新增(用户和片区关联表)信息
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
	 * 根据Id 批量删除(用户和片区关联表)信息_逻辑删除
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

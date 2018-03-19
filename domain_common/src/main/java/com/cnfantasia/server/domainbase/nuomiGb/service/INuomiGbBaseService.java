package com.cnfantasia.server.domainbase.nuomiGb.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.nuomiGb.entity.NuomiGb;

/**
 * 描述:(百度糯米对接小区信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface INuomiGbBaseService {
	
	/**
	 * 根据条件查询(百度糯米对接小区信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<NuomiGb> getNuomiGbByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(百度糯米对接小区信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<NuomiGb> getNuomiGbByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(百度糯米对接小区信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<NuomiGb> getNuomiGbByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(百度糯米对接小区信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<NuomiGb> getNuomiGbByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(百度糯米对接小区信息)信息
	 * @param id
	 * @return
	 */
	public NuomiGb getNuomiGbBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(百度糯米对接小区信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getNuomiGbCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(百度糯米对接小区信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getNuomiGbCountDim(Map<String,Object> paramMap);
	/**
	 * 往(百度糯米对接小区信息)新增一条记录
	 * @param nuomiGb
	 * @return
	 */
	public int insertNuomiGb(NuomiGb nuomiGb);
	/**
	 * 批量新增(百度糯米对接小区信息)
	 * @param nuomiGbList
	 * @return
	 */
	public int insertNuomiGbBatch(List<NuomiGb> nuomiGbList);
	/**
	 * 更新(百度糯米对接小区信息)信息
	 * @param nuomiGb
	 * @return
	 */
	public int updateNuomiGb(NuomiGb nuomiGb);
	/**
	 * 批量更新(百度糯米对接小区信息)信息
	 * @param nuomiGbList
	 * @return
	 */
	public int updateNuomiGbBatch(List<NuomiGb> nuomiGbList);
	/**
	 * 根据序列号删除(百度糯米对接小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteNuomiGbLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(百度糯米对接小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteNuomiGbLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(百度糯米对接小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteNuomiGb(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(百度糯米对接小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteNuomiGbBatch(List<java.math.BigInteger> idList);
	
}

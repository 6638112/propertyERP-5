package com.cnfantasia.server.domainbase.nuomiGb.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.nuomiGb.entity.NuomiGb;

/**
 * 描述:(百度糯米对接小区信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface INuomiGbBaseDao {
	/**
	 * 根据条件查询(百度糯米对接小区信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<NuomiGb> selectNuomiGbByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(百度糯米对接小区信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<NuomiGb> selectNuomiGbByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(百度糯米对接小区信息)信息
	 * @param id
	 * @return
	 */
	public NuomiGb selectNuomiGbBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(百度糯米对接小区信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectNuomiGbCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(百度糯米对接小区信息)新增一条记录
	 * @param nuomiGb
	 * @return
	 */
	public int insertNuomiGb(NuomiGb nuomiGb);
	
	/**
	 * 批量新增(百度糯米对接小区信息)信息
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
	 * 根据Id 批量删除(百度糯米对接小区信息)信息_逻辑删除
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

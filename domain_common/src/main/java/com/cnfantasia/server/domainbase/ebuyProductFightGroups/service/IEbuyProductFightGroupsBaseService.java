package com.cnfantasia.server.domainbase.ebuyProductFightGroups.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;

/**
 * 描述:(拼购商品规则表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductFightGroupsBaseService {
	
	/**
	 * 根据条件查询(拼购商品规则表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(拼购商品规则表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(拼购商品规则表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(拼购商品规则表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductFightGroups> getEbuyProductFightGroupsByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(拼购商品规则表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductFightGroups getEbuyProductFightGroupsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼购商品规则表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductFightGroupsCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(拼购商品规则表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductFightGroupsCountDim(Map<String,Object> paramMap);
	/**
	 * 往(拼购商品规则表)新增一条记录
	 * @param ebuyProductFightGroups
	 * @return
	 */
	public int insertEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups);
	/**
	 * 批量新增(拼购商品规则表)
	 * @param ebuyProductFightGroupsList
	 * @return
	 */
	public int insertEbuyProductFightGroupsBatch(List<EbuyProductFightGroups> ebuyProductFightGroupsList);
	/**
	 * 更新(拼购商品规则表)信息
	 * @param ebuyProductFightGroups
	 * @return
	 */
	public int updateEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups);
	/**
	 * 批量更新(拼购商品规则表)信息
	 * @param ebuyProductFightGroupsList
	 * @return
	 */
	public int updateEbuyProductFightGroupsBatch(List<EbuyProductFightGroups> ebuyProductFightGroupsList);
	/**
	 * 根据序列号删除(拼购商品规则表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductFightGroupsLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(拼购商品规则表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductFightGroupsLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(拼购商品规则表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductFightGroups(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼购商品规则表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductFightGroupsBatch(List<java.math.BigInteger> idList);
	
}

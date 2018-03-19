package com.cnfantasia.server.domainbase.ebuyProductFightGroups.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;

/**
 * 描述:(拼购商品规则表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductFightGroupsBaseDao {
	/**
	 * 根据条件查询(拼购商品规则表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductFightGroups> selectEbuyProductFightGroupsByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(拼购商品规则表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductFightGroups> selectEbuyProductFightGroupsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(拼购商品规则表)信息
	 * @param id
	 * @return
	 */
	public EbuyProductFightGroups selectEbuyProductFightGroupsBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼购商品规则表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductFightGroupsCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(拼购商品规则表)新增一条记录
	 * @param ebuyProductFightGroups
	 * @return
	 */
	public int insertEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups);
	
	/**
	 * 批量新增(拼购商品规则表)信息
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
	 * 根据Id 批量删除(拼购商品规则表)信息_逻辑删除
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

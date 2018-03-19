package com.cnfantasia.server.domainbase.mrFeeItem.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;

/**
 * 描述:(抄表费收费 项配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrFeeItemBaseService {
	
	/**
	 * 根据条件查询(抄表费收费 项配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MrFeeItem> getMrFeeItemByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抄表费收费 项配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MrFeeItem> getMrFeeItemByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抄表费收费 项配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MrFeeItem> getMrFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抄表费收费 项配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MrFeeItem> getMrFeeItemByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抄表费收费 项配置)信息
	 * @param id
	 * @return
	 */
	public MrFeeItem getMrFeeItemBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表费收费 项配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMrFeeItemCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表费收费 项配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMrFeeItemCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抄表费收费 项配置)新增一条记录
	 * @param mrFeeItem
	 * @return
	 */
	public int insertMrFeeItem(MrFeeItem mrFeeItem);
	/**
	 * 批量新增(抄表费收费 项配置)
	 * @param mrFeeItemList
	 * @return
	 */
	public int insertMrFeeItemBatch(List<MrFeeItem> mrFeeItemList);
	/**
	 * 更新(抄表费收费 项配置)信息
	 * @param mrFeeItem
	 * @return
	 */
	public int updateMrFeeItem(MrFeeItem mrFeeItem);
	/**
	 * 批量更新(抄表费收费 项配置)信息
	 * @param mrFeeItemList
	 * @return
	 */
	public int updateMrFeeItemBatch(List<MrFeeItem> mrFeeItemList);
	/**
	 * 根据序列号删除(抄表费收费 项配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMrFeeItemLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表费收费 项配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMrFeeItemLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表费收费 项配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrFeeItem(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费 项配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrFeeItemBatch(List<java.math.BigInteger> idList);
	
}

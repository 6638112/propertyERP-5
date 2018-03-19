package com.cnfantasia.server.domainbase.dredgeBankCard.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBankCard.entity.DredgeBankCard;

/**
 * 描述:(用户绑定的银行卡信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBankCardBaseService {
	
	/**
	 * 根据条件查询(用户绑定的银行卡信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBankCard> getDredgeBankCardByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户绑定的银行卡信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBankCard> getDredgeBankCardByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户绑定的银行卡信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBankCard> getDredgeBankCardByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户绑定的银行卡信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBankCard> getDredgeBankCardByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户绑定的银行卡信息)信息
	 * @param id
	 * @return
	 */
	public DredgeBankCard getDredgeBankCardBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户绑定的银行卡信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBankCardCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户绑定的银行卡信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBankCardCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户绑定的银行卡信息)新增一条记录
	 * @param dredgeBankCard
	 * @return
	 */
	public int insertDredgeBankCard(DredgeBankCard dredgeBankCard);
	/**
	 * 批量新增(用户绑定的银行卡信息)
	 * @param dredgeBankCardList
	 * @return
	 */
	public int insertDredgeBankCardBatch(List<DredgeBankCard> dredgeBankCardList);
	/**
	 * 更新(用户绑定的银行卡信息)信息
	 * @param dredgeBankCard
	 * @return
	 */
	public int updateDredgeBankCard(DredgeBankCard dredgeBankCard);
	/**
	 * 批量更新(用户绑定的银行卡信息)信息
	 * @param dredgeBankCardList
	 * @return
	 */
	public int updateDredgeBankCardBatch(List<DredgeBankCard> dredgeBankCardList);
	/**
	 * 根据序列号删除(用户绑定的银行卡信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteDredgeBankCardLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(用户绑定的银行卡信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteDredgeBankCardLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(用户绑定的银行卡信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBankCard(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户绑定的银行卡信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBankCardBatch(List<java.math.BigInteger> idList);
	
}

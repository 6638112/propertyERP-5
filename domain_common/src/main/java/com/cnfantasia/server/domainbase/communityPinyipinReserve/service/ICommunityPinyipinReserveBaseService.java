package com.cnfantasia.server.domainbase.communityPinyipinReserve.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * 描述:(拼单预定信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityPinyipinReserveBaseService {
	
	/**
	 * 根据条件查询(拼单预定信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(拼单预定信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(拼单预定信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(拼单预定信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityPinyipinReserve> getCommunityPinyipinReserveByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(拼单预定信息)信息
	 * @param id
	 * @return
	 */
	public CommunityPinyipinReserve getCommunityPinyipinReserveBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼单预定信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityPinyipinReserveCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(拼单预定信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityPinyipinReserveCountDim(Map<String,Object> paramMap);
	/**
	 * 往(拼单预定信息)新增一条记录
	 * @param communityPinyipinReserve
	 * @return
	 */
	public int insertCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve);
	/**
	 * 批量新增(拼单预定信息)
	 * @param communityPinyipinReserveList
	 * @return
	 */
	public int insertCommunityPinyipinReserveBatch(List<CommunityPinyipinReserve> communityPinyipinReserveList);
	/**
	 * 更新(拼单预定信息)信息
	 * @param communityPinyipinReserve
	 * @return
	 */
	public int updateCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve);
	/**
	 * 批量更新(拼单预定信息)信息
	 * @param communityPinyipinReserveList
	 * @return
	 */
	public int updateCommunityPinyipinReserveBatch(List<CommunityPinyipinReserve> communityPinyipinReserveList);
	/**
	 * 根据序列号删除(拼单预定信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityPinyipinReserveLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(拼单预定信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityPinyipinReserveLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(拼单预定信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityPinyipinReserve(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼单预定信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityPinyipinReserveBatch(List<java.math.BigInteger> idList);
	
}

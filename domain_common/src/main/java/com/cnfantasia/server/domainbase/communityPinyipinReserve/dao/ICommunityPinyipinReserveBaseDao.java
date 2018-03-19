package com.cnfantasia.server.domainbase.communityPinyipinReserve.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinReserve.entity.CommunityPinyipinReserve;

/**
 * 描述:(拼单预定信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityPinyipinReserveBaseDao {
	/**
	 * 根据条件查询(拼单预定信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityPinyipinReserve> selectCommunityPinyipinReserveByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(拼单预定信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityPinyipinReserve> selectCommunityPinyipinReserveByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(拼单预定信息)信息
	 * @param id
	 * @return
	 */
	public CommunityPinyipinReserve selectCommunityPinyipinReserveBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼单预定信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityPinyipinReserveCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(拼单预定信息)新增一条记录
	 * @param communityPinyipinReserve
	 * @return
	 */
	public int insertCommunityPinyipinReserve(CommunityPinyipinReserve communityPinyipinReserve);
	
	/**
	 * 批量新增(拼单预定信息)信息
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
	 * 根据Id 批量删除(拼单预定信息)信息_逻辑删除
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

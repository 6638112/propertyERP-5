package com.cnfantasia.server.domainbase.eatMenuRecommend.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.eatMenuRecommend.entity.EatMenuRecommend;

/**
 * 描述:(推荐菜谱) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEatMenuRecommendBaseService {
	
	/**
	 * 根据条件查询(推荐菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EatMenuRecommend> getEatMenuRecommendByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(推荐菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EatMenuRecommend> getEatMenuRecommendByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(推荐菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EatMenuRecommend> getEatMenuRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(推荐菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EatMenuRecommend> getEatMenuRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(推荐菜谱)信息
	 * @param id
	 * @return
	 */
	public EatMenuRecommend getEatMenuRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(推荐菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEatMenuRecommendCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(推荐菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEatMenuRecommendCountDim(Map<String,Object> paramMap);
	/**
	 * 往(推荐菜谱)新增一条记录
	 * @param eatMenuRecommend
	 * @return
	 */
	public int insertEatMenuRecommend(EatMenuRecommend eatMenuRecommend);
	/**
	 * 批量新增(推荐菜谱)
	 * @param eatMenuRecommendList
	 * @return
	 */
	public int insertEatMenuRecommendBatch(List<EatMenuRecommend> eatMenuRecommendList);
	/**
	 * 更新(推荐菜谱)信息
	 * @param eatMenuRecommend
	 * @return
	 */
	public int updateEatMenuRecommend(EatMenuRecommend eatMenuRecommend);
	/**
	 * 批量更新(推荐菜谱)信息
	 * @param eatMenuRecommendList
	 * @return
	 */
	public int updateEatMenuRecommendBatch(List<EatMenuRecommend> eatMenuRecommendList);
	/**
	 * 根据序列号删除(推荐菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEatMenuRecommendLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(推荐菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEatMenuRecommendLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(推荐菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEatMenuRecommend(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(推荐菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEatMenuRecommendBatch(List<java.math.BigInteger> idList);
	
}

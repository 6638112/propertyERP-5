package com.cnfantasia.server.domainbase.eatMenuRecommend.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.eatMenuRecommend.entity.EatMenuRecommend;

/**
 * 描述:(推荐菜谱) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEatMenuRecommendBaseDao {
	/**
	 * 根据条件查询(推荐菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EatMenuRecommend> selectEatMenuRecommendByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(推荐菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EatMenuRecommend> selectEatMenuRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(推荐菜谱)信息
	 * @param id
	 * @return
	 */
	public EatMenuRecommend selectEatMenuRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(推荐菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEatMenuRecommendCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(推荐菜谱)新增一条记录
	 * @param eatMenuRecommend
	 * @return
	 */
	public int insertEatMenuRecommend(EatMenuRecommend eatMenuRecommend);
	
	/**
	 * 批量新增(推荐菜谱)信息
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
	 * 根据Id 批量删除(推荐菜谱)信息_逻辑删除
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

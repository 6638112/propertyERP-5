package com.cnfantasia.server.domainbase.ebuyProductRecommend.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductRecommend.entity.EbuyProductRecommend;

/**
 * 描述:(推荐的商品) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductRecommendBaseDao {
	/**
	 * 根据条件查询(推荐的商品)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductRecommend> selectEbuyProductRecommendByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(推荐的商品)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyProductRecommend> selectEbuyProductRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(推荐的商品)信息
	 * @param id
	 * @return
	 */
	public EbuyProductRecommend selectEbuyProductRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(推荐的商品)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyProductRecommendCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(推荐的商品)新增一条记录
	 * @param ebuyProductRecommend
	 * @return
	 */
	public int insertEbuyProductRecommend(EbuyProductRecommend ebuyProductRecommend);
	
	/**
	 * 批量新增(推荐的商品)信息
	 * @param ebuyProductRecommendList
	 * @return
	 */
	public int insertEbuyProductRecommendBatch(List<EbuyProductRecommend> ebuyProductRecommendList);
	
	/**
	 * 更新(推荐的商品)信息
	 * @param ebuyProductRecommend
	 * @return
	 */
	public int updateEbuyProductRecommend(EbuyProductRecommend ebuyProductRecommend);
	
	/**
	 * 批量更新(推荐的商品)信息
	 * @param ebuyProductRecommendList
	 * @return
	 */
	public int updateEbuyProductRecommendBatch(List<EbuyProductRecommend> ebuyProductRecommendList);
	
	/**
	 * 根据序列号删除(推荐的商品)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyProductRecommendLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(推荐的商品)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyProductRecommendLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(推荐的商品)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyProductRecommend(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(推荐的商品)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyProductRecommendBatch(List<java.math.BigInteger> idList);
	
	
}

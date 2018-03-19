package com.cnfantasia.server.domainbase.ebuyProductRecommend.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyProductRecommend.entity.EbuyProductRecommend;

/**
 * 描述:(推荐的商品) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyProductRecommendBaseService {
	
	/**
	 * 根据条件查询(推荐的商品)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductRecommend> getEbuyProductRecommendByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(推荐的商品)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyProductRecommend> getEbuyProductRecommendByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(推荐的商品)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductRecommend> getEbuyProductRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(推荐的商品)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyProductRecommend> getEbuyProductRecommendByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(推荐的商品)信息
	 * @param id
	 * @return
	 */
	public EbuyProductRecommend getEbuyProductRecommendBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(推荐的商品)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductRecommendCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(推荐的商品)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyProductRecommendCountDim(Map<String,Object> paramMap);
	/**
	 * 往(推荐的商品)新增一条记录
	 * @param ebuyProductRecommend
	 * @return
	 */
	public int insertEbuyProductRecommend(EbuyProductRecommend ebuyProductRecommend);
	/**
	 * 批量新增(推荐的商品)
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
	 * 根据序列号批量删除(推荐的商品)信息_逻辑删除
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

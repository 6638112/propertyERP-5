package com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;

/**
 * 描述:(运单备注) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryOrderCommentBaseService {
	
	/**
	 * 根据条件查询(运单备注)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(运单备注)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(运单备注)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(运单备注)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(运单备注)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryOrderComment getEbuyDeliveryOrderCommentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运单备注)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryOrderCommentCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(运单备注)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyDeliveryOrderCommentCountDim(Map<String,Object> paramMap);
	/**
	 * 往(运单备注)新增一条记录
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	public int insertEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment);
	/**
	 * 批量新增(运单备注)
	 * @param ebuyDeliveryOrderCommentList
	 * @return
	 */
	public int insertEbuyDeliveryOrderCommentBatch(List<EbuyDeliveryOrderComment> ebuyDeliveryOrderCommentList);
	/**
	 * 更新(运单备注)信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	public int updateEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment);
	/**
	 * 批量更新(运单备注)信息
	 * @param ebuyDeliveryOrderCommentList
	 * @return
	 */
	public int updateEbuyDeliveryOrderCommentBatch(List<EbuyDeliveryOrderComment> ebuyDeliveryOrderCommentList);
	/**
	 * 根据序列号删除(运单备注)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteEbuyDeliveryOrderCommentLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(运单备注)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteEbuyDeliveryOrderCommentLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(运单备注)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrderComment(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(运单备注)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrderCommentBatch(List<java.math.BigInteger> idList);
	
}

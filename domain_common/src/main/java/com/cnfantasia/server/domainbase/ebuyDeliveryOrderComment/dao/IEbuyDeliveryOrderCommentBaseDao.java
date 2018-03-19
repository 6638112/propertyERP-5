package com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;

/**
 * 描述:(运单备注) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryOrderCommentBaseDao {
	/**
	 * 根据条件查询(运单备注)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(运单备注)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(运单备注)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryOrderComment selectEbuyDeliveryOrderCommentBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(运单备注)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyDeliveryOrderCommentCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(运单备注)新增一条记录
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	public int insertEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment);
	
	/**
	 * 批量新增(运单备注)信息
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
	 * 根据Id 批量删除(运单备注)信息_逻辑删除
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

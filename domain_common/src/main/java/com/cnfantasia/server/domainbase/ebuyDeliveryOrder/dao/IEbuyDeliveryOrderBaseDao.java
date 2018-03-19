package com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;

/**
 * 描述:(送货单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyDeliveryOrderBaseDao {
	/**
	 * 根据条件查询(送货单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryOrder> selectEbuyDeliveryOrderByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(送货单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyDeliveryOrder> selectEbuyDeliveryOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(送货单)信息
	 * @param id
	 * @return
	 */
	public EbuyDeliveryOrder selectEbuyDeliveryOrderBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(送货单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyDeliveryOrderCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(送货单)新增一条记录
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	public int insertEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder);
	
	/**
	 * 批量新增(送货单)信息
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	public int insertEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList);
	
	/**
	 * 更新(送货单)信息
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	public int updateEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder);
	
	/**
	 * 批量更新(送货单)信息
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	public int updateEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList);
	
	/**
	 * 根据序列号删除(送货单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyDeliveryOrderLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(送货单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyDeliveryOrderLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(送货单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrder(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(送货单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyDeliveryOrderBatch(List<java.math.BigInteger> idList);
	
	/**
	 * 新增一条订单备注信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	public int insertEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment);
	
	/**
	 * 查询该订单所有备注信息
	 * @param paramMap
	 * @param isDim
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,boolean isDim);
}

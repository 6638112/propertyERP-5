/**
 * 
 */
package com.cnfantasia.server.ms.ebuy.service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;
import com.cnfantasia.server.ms.ebuy.entity.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:55:08
 */
public interface IEbuyorderService {
	
	public List<OrderDetailBean> queryOrderDetailByPage(PageModel model,Map<String, Object> params) throws BusinessRuntimeException;
	public List<OrderDetailBean> queryOrderDetailAll(Map<String, Object> params) throws BusinessRuntimeException;
	
	/**
	 *  syl--add--2014-6-12 11:32:28
	 * 	录入物流信息，并更改订单状态为已发货
	 * @param orderId
	 * @param logisticsName
	 * @param logisticseCode
	 */
	public void setLogisticsInfo(BigInteger orderId,String logisticsName,String logisticseCode);
	
	
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params) throws BusinessRuntimeException;
	public int selectOrderCount(Map<String, Object> params) throws BusinessRuntimeException;
	public List<OrderBean> selectOrderListByPage(Map<String, Object> params) throws BusinessRuntimeException;
	public OrderDetailsBean selectOrderDetail(Map<String, Object> params);
	public void saveExpressInfo(EbuyDeliveryOrder ebuyDeliveryOrder);
	List<OrderExportBean> getOrderForExport(Map<String, Object> params);
	
	/**
	 * 新增一条订单备注信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	public int saveEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment);
	
	/**
	 * 查询该订单所有备注信息
	 * @param paramMap
	 * @param isDim
	 * @return
	 */
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,boolean isDim);

}

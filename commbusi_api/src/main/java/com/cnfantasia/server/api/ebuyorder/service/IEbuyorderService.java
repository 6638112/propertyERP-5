/**
 * 
 */
package com.cnfantasia.server.api.ebuyorder.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuyorder.entity.OrderBuyInfo;
import com.cnfantasia.server.api.ebuyorder.entity.OrderDetailBean;
import com.cnfantasia.server.api.ebuyorder.entity.ReportBean;
import com.cnfantasia.server.api.payment.entity.SqPayBtRequest;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:55:08
 */
public interface IEbuyorderService {
	
	public List<OrderDetailBean> queryOrderDetailByPage(PageModel model,Map<String, Object> params) throws BusinessRuntimeException;
	
	/**
	 *  syl--add--2014-6-12 11:32:28
	 * 	录入物流信息，并更改订单状态为已发货
	 * @param orderId
	 * @param logisticsName
	 * @param logisticseCode
	 */
	public void setLogisticsInfo(BigInteger orderId,String logisticsName,String logisticseCode);
	
	
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params) throws BusinessRuntimeException;

	public List<OrderBuyInfo> getOrderBuyInfoByOrderId(BigInteger orderId);
	
	/**
	 * 根据orderId获取双乾支付补贴请求所需额外参数
	 * 
	 * @param orderId
	 * @return
	 */
	public SqPayBtRequest selectSqPayBtRequestByOrderId(BigInteger orderId);

	public List<OrderBuyInfo> getZiTiOrderBuyInfoByOrderId(BigInteger orderId);

    OrderBuyInfo getMidAutumnOrderBuyInfoByOrderId(BigInteger orderId);
}

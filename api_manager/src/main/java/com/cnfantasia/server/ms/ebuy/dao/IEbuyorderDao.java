/**
 * 
 */
package com.cnfantasia.server.ms.ebuy.dao;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.ms.ebuy.entity.*;

import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:17:52
 */
public interface IEbuyorderDao {
	
	public List<OrderDetailBean> selectOrderDetailByPage(PageModel pageModel,Map<String, Object> params);
	public List<OrderDetailBean> selectOrderDetailAll(Map<String, Object> params);
	
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params);
	public int selectOrderCount(Map<String, Object> params);
	public List<OrderBean> selectOrderListByPage(Map<String, Object> params);
	public OrderDetailsBean selectOrderDetail(Map<String, Object> params);
	boolean isPartDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder);
	List<OrderExportBean> getOrderForExport(Map<String, Object> params);

}

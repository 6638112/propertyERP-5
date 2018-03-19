/**
 * 
 */
package com.cnfantasia.server.api.ebuyorder.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuyorder.dao.IEbuyorderDao;
import com.cnfantasia.server.api.ebuyorder.entity.OrderBuyInfo;
import com.cnfantasia.server.api.ebuyorder.entity.OrderDetailBean;
import com.cnfantasia.server.api.ebuyorder.entity.ReportBean;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.entity.SqPayBtRequest;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.service.IEbuyDeliveryOrderProductBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:55:34
 */
public class EbuyorderService implements IEbuyorderService {
	
	private IEbuyorderDao ebuyorderDao;
	public void setEbuyorderDao(IEbuyorderDao ebuyorderDao) {
		this.ebuyorderDao = ebuyorderDao;
	}
	
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}
	private IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService;
	public void setEbuyDeliveryOrderProductBaseService(IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService) {
		this.ebuyDeliveryOrderProductBaseService = ebuyDeliveryOrderProductBaseService;
	}

	public List<OrderDetailBean> queryOrderDetailByPage(PageModel model, Map<String, Object> params) throws BusinessRuntimeException{
		return ebuyorderDao.selectOrderDetailByPage(model, params);
	}
	
	@Override
	public void setLogisticsInfo(BigInteger orderId, String logisticsName, String logisticseCode) {
//		{//查询当前订单状态是否为代付款
//			EbuyOrder ebuyOrderRqy = new EbuyOrder();
//			ebuyOrderRqy.setId(orderId);
//			ebuyOrderRqy.setStatus(DictConstants.EbuyOrder_Status.DaiFuKuan);
//			int count = ebuyOrderBaseDao.selectEbuyOrderCount(MapConverter.toMap(ebuyOrderRqy), false);
//			if(count<=0){
//				throw new BusinessRuntimeException("EbuyorderService.setLogisticsInfo.selectEbuyOrderCount.isZero");
//			}
//		}
		{//更改物流信息
			EbuyOrder ebuyOrderUpdLogis = new EbuyOrder();
			ebuyOrderUpdLogis.setId(orderId);
			ebuyOrderUpdLogis.setLogisticsecode(logisticseCode);
			ebuyOrderUpdLogis.setLogisticsname(logisticsName);
			ebuyOrderUpdLogis.setStatus(EbuyDict.EbuyOrder_Status.DaiShouHuo);//TODO 临时改为待收货
			int res = ebuyOrderBaseDao.updateEbuyOrder(ebuyOrderUpdLogis);
			if(res<=0){
				throw new BusinessRuntimeException("EbuyorderService.setLogisticsInfo.updateEbuyOrder.failed");
			}
		}
	}

	@Override
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params) throws BusinessRuntimeException {
		return ebuyorderDao.selectOrderItemsForReport(params);
	}

	@Override
	public List<OrderBuyInfo> getOrderBuyInfoByOrderId(BigInteger orderId) {
		return ebuyorderDao.getOrderBuyInfoByOrderId(orderId);
	}
	
	/**
	 * 根据orderId获取双乾支付补贴请求所需额外参数
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public SqPayBtRequest selectSqPayBtRequestByOrderId(BigInteger orderId){
		return ebuyorderDao.selectSqPayBtRequestByOrderId(orderId);
	}

	@Override
	public List<OrderBuyInfo> getZiTiOrderBuyInfoByOrderId(BigInteger orderId) {
		return ebuyorderDao.getZiTiOrderBuyInfoByOrderId(orderId);
	}

	@Override
	public OrderBuyInfo getMidAutumnOrderBuyInfoByOrderId(BigInteger orderId) {
		return ebuyorderDao.getMidAutumnOrderBuyInfoByOrderId(orderId);
	}
}

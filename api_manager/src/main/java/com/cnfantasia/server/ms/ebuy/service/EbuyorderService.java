/**
 * 
 */
package com.cnfantasia.server.ms.ebuy.service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao.IEbuyDeliveryOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.service.IEbuyDeliveryOrderProductBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.ms.ebuy.constant.EbuyDict;
import com.cnfantasia.server.ms.ebuy.constant.EbuyDict.EbuyOrder_Status;
import com.cnfantasia.server.ms.ebuy.dao.IEbuyorderDao;
import com.cnfantasia.server.ms.ebuy.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:55:34
 */
public class EbuyorderService implements IEbuyorderService {
	
	private IEbuyorderDao ebuyorderDao;
	private IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao;
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	
	public void setEbuyDeliveryOrderBaseDao(IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao) {
		this.ebuyDeliveryOrderBaseDao = ebuyDeliveryOrderBaseDao;
	}
	public void setEbuyorderDao(IEbuyorderDao ebuyorderDao) {
		this.ebuyorderDao = ebuyorderDao;
	}
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}
	public void setEbuyDeliveryOrderProductBaseService(IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService) {
		IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService1 = ebuyDeliveryOrderProductBaseService;
	}

	@Override
	@Deprecated
	public List<OrderDetailBean> queryOrderDetailByPage(PageModel model,Map<String, Object> params) throws BusinessRuntimeException{
		return ebuyorderDao.selectOrderDetailByPage(model, params);
	}
	
	@Override
	public int selectOrderCount(Map<String, Object> params) throws BusinessRuntimeException {
		return ebuyorderDao.selectOrderCount(params);
	}
	
	@Override
	public List<OrderBean> selectOrderListByPage(Map<String, Object> params) throws BusinessRuntimeException {
		return ebuyorderDao.selectOrderListByPage(params);
	}
	
	@Override
	public OrderDetailsBean selectOrderDetail(Map<String, Object> params) {
		return ebuyorderDao.selectOrderDetail(params);
	}
	
	@Override
	public List<OrderDetailBean> queryOrderDetailAll(Map<String, Object> params) throws BusinessRuntimeException{
		return ebuyorderDao.selectOrderDetailAll(params);
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
	@Deprecated
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params) throws BusinessRuntimeException {
		return ebuyorderDao.selectOrderItemsForReport(params);
	}
	
	@Transactional
	@Override
	public void saveExpressInfo(EbuyDeliveryOrder ebuyDeliveryOrder) {
		ebuyDeliveryOrderBaseDao.updateEbuyDeliveryOrder(ebuyDeliveryOrder);
		boolean isPartDelivery = ebuyorderDao.isPartDeliveryOrder(ebuyDeliveryOrder);
		
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setId(ebuyDeliveryOrder.gettEbuyOrderFId());
		if(isPartDelivery) {
			ebuyOrder.setDelivStatus(2); //部分发货
		} else {
			ebuyOrder.setDelivStatus(3); //全部发货
			ebuyOrder.setStatus(EbuyOrder_Status.DaiShouHuo); //订单状态改成待收货
		}
		ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
	}
	
	@Override
	public List<OrderExportBean> getOrderForExport (Map<String, Object> params) {
		return ebuyorderDao.getOrderForExport(params);
	}
	
	/**
	 * 新增一条订单备注信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	@Transactional
	@Override
	public int saveEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment){
		return ebuyDeliveryOrderBaseDao.insertEbuyDeliveryOrderComment(ebuyDeliveryOrderComment);
	}
	
	/**
	 * 查询该订单所有备注信息
	 * @param paramMap
	 * @param isDim
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,boolean isDim){
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderCommentByCondition(paramMap, isDim);
	}
}

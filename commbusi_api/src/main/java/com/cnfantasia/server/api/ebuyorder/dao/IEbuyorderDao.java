/**
 * 
 */
package com.cnfantasia.server.api.ebuyorder.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.ebuyorder.entity.OrderBuyInfo;
import com.cnfantasia.server.api.ebuyorder.entity.OrderDetailBean;
import com.cnfantasia.server.api.ebuyorder.entity.ReportBean;
import com.cnfantasia.server.api.payment.entity.SqPayBtPrePayDto;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:17:52
 */
public interface IEbuyorderDao {
	
	public List<OrderDetailBean> selectOrderDetailByPage(PageModel pageModel,Map<String, Object> params);

	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params);

	public List<OrderBuyInfo> getOrderBuyInfoByOrderId(BigInteger orderId);
	
	/**
	 * 根据orderId获取双乾支付补贴请求所需额外参数
	 * 
	 * @param orderId
	 * @return
	 */
	public SqPayBtPrePayDto selectSqPayBtRequestByOrderId(BigInteger orderId);
	
	/**
	 * 是否第一次消费
	 * 
	 * @param userId
	 * @return
	 */
	public boolean isFirstConsumed(BigInteger userId);
	
	/**
	 * 物业账单是否已缴费
	 * 
	 * @param orderId
	 * @return
	 */
	public boolean isWyPaid(BigInteger orderId);

	public List<OrderBuyInfo> getZiTiOrderBuyInfoByOrderId(BigInteger orderId);

	OrderBuyInfo getMidAutumnOrderBuyInfoByOrderId(BigInteger orderId);

	/**
	 * 查询订单所包含的供应商
	 * @author wenfq 2017-09-19
	 *
	 * @param paramMap
	 * @return 订单所包含的供应商
	 */
	public List<BigInteger> selectMerchantIdListByOrderId(Map<String, Object> paramMap);
}

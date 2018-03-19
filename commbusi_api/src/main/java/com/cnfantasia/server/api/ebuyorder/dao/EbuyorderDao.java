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
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:25:26
 */
public class EbuyorderDao extends AbstractBaseDao  implements IEbuyorderDao {

	@Override
	public List<OrderDetailBean> selectOrderDetailByPage(PageModel pageModel,Map<String, Object> params) {
		return PageQueryUtil.selectPageList(sqlSession, params, pageModel, "ebuyorder.selectOrderDetailByPage", "ebuyorder.countOrderDetail");
	}

	@Override
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params) {
		return sqlSession.selectList("ebuyorder.selectOrderItemsForReport",params);
	}

	@Override
	public List<OrderBuyInfo> getOrderBuyInfoByOrderId(BigInteger orderId) {
		return sqlSession.selectList("ebuyorder.getOrderBuyInfoByOrderId", orderId);
	}
	
	/**
	 * 根据orderId获取双乾支付补贴请求所需额外参数
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public SqPayBtPrePayDto selectSqPayBtRequestByOrderId(BigInteger orderId){
		return sqlSession.selectOne("ebuyorder.select_SqPayBtRequest_with_orderId", orderId);
	}
	
	/**
	 * 是否第一次消费
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public boolean isFirstConsumed(BigInteger userId){
		int count = sqlSession.selectOne("ebuyorder.select_first_consume_count", userId);
		return count==0;
	}
	
	/**
	 * 物业账单是否已缴费
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public boolean isWyPaid(BigInteger orderId){
		int count = sqlSession.selectOne("ebuyorder.is_wy_paid", orderId);
		return count>0;
	}

	@Override
	public List<OrderBuyInfo> getZiTiOrderBuyInfoByOrderId(BigInteger orderId) {
		return sqlSession.selectList("ebuyorder.getZiTiOrderBuyInfoByOrderId", orderId);
	}

	@Override
	public OrderBuyInfo getMidAutumnOrderBuyInfoByOrderId(BigInteger orderId) {
		return sqlSession.selectOne("ebuyorder.getMidAutumnOrderBuyInfoByOrderId", orderId);
	}

	@Override
	public List<BigInteger> selectMerchantIdListByOrderId(Map<String, Object> paramMap){
		return sqlSession.selectList("ebuyorder.selectMerchantIdListByOrderId", paramMap);
	}
}

/**
 * 
 */
package com.cnfantasia.server.ms.ebuy.dao;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.ms.ebuy.entity.*;

import java.util.List;
import java.util.Map;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:25:26
 */
public class EbuyorderDao extends AbstractBaseDao  implements IEbuyorderDao {

	@Override
	@Deprecated
	public List<OrderDetailBean> selectOrderDetailByPage(PageModel pageModel,Map<String, Object> params) {
		return PageQueryUtil.selectPageList(sqlSession, params, pageModel, "ebuyorder.selectOrderDetailByPage", "ebuyorder.countOrderDetail");
	}
	
	@Override
	public int selectOrderCount(Map<String, Object> params) {
		return sqlSession.selectOne("ebuyorder.getOrderListCount", params);
	}
	
	@Override
	public List<OrderBean> selectOrderListByPage(Map<String, Object> params) {
		return sqlSession.selectList("ebuyorder.getOrderListByPage", params);
	}
	
	@Override
	public OrderDetailsBean selectOrderDetail(Map<String, Object> params) {
		return sqlSession.selectOne("ebuyorder.getOrderDetail", params);
	}
	
	@Override
	public List<OrderDetailBean> selectOrderDetailAll(Map<String, Object> params) {
		return sqlSession.selectList("ebuyorder.selectOrderDetailAll",params);
	}

	@Override
	@Deprecated
	public List<ReportBean> selectOrderItemsForReport(Map<String, Object> params) {
		return sqlSession.selectList("ebuyorder.selectOrderItemsForReport",params);
	}
	
	@Override
	public boolean isPartDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder) {
		int count = sqlSession.selectOne("ebuyorder.getEbuyDeliveryOrderCount", ebuyDeliveryOrder);
		return (count > 0 ? true : false);
	}
	
	@Override
	public List<OrderExportBean> getOrderForExport (Map<String, Object> params) {
		return sqlSession.selectList("ebuyorder.getOrderForExport",params);
	}

}

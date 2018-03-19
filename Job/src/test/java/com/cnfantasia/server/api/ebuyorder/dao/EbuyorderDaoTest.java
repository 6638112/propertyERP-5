/**
 * 
 */
package com.cnfantasia.server.api.ebuyorder.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.BaseTest;
import com.cnfantasia.server.api.ebuyorder.entity.OrderDetailBean;
import com.cnfantasia.server.api.ebuyorder.entity.ReportBean;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月7日下午4:31:02
 */
public class EbuyorderDaoTest extends BaseTest {
	
	IEbuyorderDao ebuyorderDao;
	
//	@Test
	public void selectOrderDetailByPage() {
		Integer page = 1;
		Integer pageNum = 6;
		PageModel pageModel = new PageModel((page-1)*pageNum, pageNum);
		ebuyorderDao = (IEbuyorderDao)ctx.getBean("ebuyorderDao");
		Map<String, Object> params = new HashMap<String, Object>();
		List<OrderDetailBean> orderDetailBeans = ebuyorderDao.selectOrderDetailByPage(pageModel, params);
		for(OrderDetailBean bean:orderDetailBeans){
			System.out.println(bean.getOrderNo()+" "+bean.getHuaId());
		}
	}
	
//	@Test
	public void selectOrderItemsForReport(){
		ebuyorderDao = (IEbuyorderDao)ctx.getBean("ebuyorderDao");
		Map<String, Object> params = new HashMap<String, Object>();
		List<ReportBean> reportBeans = ebuyorderDao.selectOrderItemsForReport(params);
		System.out.println(reportBeans.size());
	}

}

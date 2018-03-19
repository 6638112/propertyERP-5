package com.cnfantasia.server.ms.payBill.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;

public interface IPayBillDao extends IPayBillBaseDao {

	/**
	 * 整理待导入的数据
	 * 
	 * @param payBills
	 *            从Excel中解析过来的数据
	 * @return 返回给用户结果信息，哪些行可导，哪些行不能导入
	 */
	public String vierfyImportPayBill(List<PayBillWithFeeDetailEntity> payBills);

	/**
	 * 找出待导出的数据
	 * 
	 * @param payBillIdList
	 * @return
	 */
	List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList);

	/**
	 * 查看单个账单详情
	 * 
	 * @param id
	 *            物业账单ID
	 */
	PayBillWithFeeDetailEntity getPayBillForDetail(BigInteger id);

	/**
	 * 查看单个账单支付详情2
	 * 
	 * @param id
	 *            物业账单ID
	 */
	List<HashMap<String, Object>> select_payBill_with_payRecord(String id);

	/**
	 * 手功标记物业账单为已缴
	 * 
	 * @param id
	 * @return
	 */
	public int markByManual(String id);

	/**
	 * 根据用户ID查找缴费单的总记录数
	 * 
	 * @param paramMap
	 * @return
	 */
	int getPayBill_byUserId_forCount(Map<String, Object> paramMap);

	/**
	 * 根据用户ID查找缴费单
	 * 
	 * @param curPageIndex
	 *            第几页
	 * @param pageSize
	 *            每页的大小
	 * @param paramMap
	 *            请求参数
	 * @return
	 */
	List<PayBillEntity> getPayBill_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
}

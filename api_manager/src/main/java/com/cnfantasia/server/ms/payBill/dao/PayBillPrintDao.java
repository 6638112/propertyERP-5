package com.cnfantasia.server.ms.payBill.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.mybatis.MapResultHandler;
import com.cnfantasia.server.ms.payBill.entity.CommPrintKey;
import com.cnfantasia.server.ms.payBill.entity.PrintTemplateEntity;
import com.cnfantasia.server.ms.payBill.entity.TemplatePrintKey;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

public class PayBillPrintDao extends AbstractBaseDao{
	
	/**
	 * 有模板，账单打印（根据gbId）
	 * @param gbbcId
	 * @param feeType
	 * @param code
	 * @return
	 */
	public List<TemplatePrintKey> selectByGbIdWithTemplate(BigInteger gbbcId, String feeType, String code){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbbcId", gbbcId);
		paramMap.put("feeType", feeType);
		paramMap.put("code", code);
				
		return sqlSession.selectList("payBillPrint.selectByGbIdWithTemplate", paramMap);
	}
	
	/**
	 * 无模板，账单打印（根据账单gbId）
	 * @param gbbcId
	 * @return
	 */
	public List<CommPrintKey> selectByGbIdWithNoTemplate(BigInteger gbbcId, String feeType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbbcId", gbbcId);
		paramMap.put("feeType", feeType);
		
		return sqlSession.selectList("payBillPrint.selectByGbIdWithNoTemplate", paramMap);
	}
	
	//====================
	
	/**
	 *  有模板，账单打印（根据账单id）
	 * @param payBillIdList
	 * @param feeType
	 * @param code
	 * @return
	 */
	public List<TemplatePrintKey> selectByBillIdWithTemplate(List<BigInteger> payBillIdList, String feeType, String code){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payBillIdList", payBillIdList);
		paramMap.put("feeType", feeType);
		paramMap.put("code", code);
		
		return sqlSession.selectList("payBillPrint.selectByBillIdWithTemplate", paramMap);
	}
	
	/**
	 * 无模板，账单打印（根据账单id）
	 * @param payBillIdList
	 * @return
	 */
	public List<CommPrintKey> selectByBillIdWithNoTemplate(List<BigInteger> payBillIdList, String feeType){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("payBillIdList", payBillIdList);
		paramMap.put("feeType", feeType);
		
		return sqlSession.selectList("payBillPrint.selectByBillIdWithNoTemplate", paramMap);
	}
	
	/**
	 * 根据小区id查询打印模板
	 * 
	 * @param gbId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, PrintTemplateEntity> selectPrintTemplateByGbId(BigInteger gbId){
		MapResultHandler mapResultHandler = new MapResultHandler();
		sqlSession.select("payBillPrint.selectPrintTemplateByGbId", gbId, mapResultHandler);
		return mapResultHandler.getMappedResults();
	}
	
	/**
	 * 将小区模板设置为f_service_state=1
	 * @param gbId
	 * @return
	 */
	public Integer updatePrintTemplateStopService(BigInteger gbId){
		return sqlSession.update("payBillPrint.updatePrintTemplateStopService", gbId);
	}
}

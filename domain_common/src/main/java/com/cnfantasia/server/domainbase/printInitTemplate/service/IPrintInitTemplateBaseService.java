package com.cnfantasia.server.domainbase.printInitTemplate.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.printInitTemplate.entity.PrintInitTemplate;

/**
 * 描述:(账单打印模板初始化表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrintInitTemplateBaseService {
	
	/**
	 * 根据条件查询(账单打印模板初始化表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrintInitTemplate> getPrintInitTemplateByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(账单打印模板初始化表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrintInitTemplate> getPrintInitTemplateByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(账单打印模板初始化表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrintInitTemplate> getPrintInitTemplateByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(账单打印模板初始化表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrintInitTemplate> getPrintInitTemplateByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(账单打印模板初始化表)信息
	 * @param id
	 * @return
	 */
	public PrintInitTemplate getPrintInitTemplateBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单打印模板初始化表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrintInitTemplateCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(账单打印模板初始化表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrintInitTemplateCountDim(Map<String,Object> paramMap);
	/**
	 * 往(账单打印模板初始化表)新增一条记录
	 * @param printInitTemplate
	 * @return
	 */
	public int insertPrintInitTemplate(PrintInitTemplate printInitTemplate);
	/**
	 * 批量新增(账单打印模板初始化表)
	 * @param printInitTemplateList
	 * @return
	 */
	public int insertPrintInitTemplateBatch(List<PrintInitTemplate> printInitTemplateList);
	/**
	 * 更新(账单打印模板初始化表)信息
	 * @param printInitTemplate
	 * @return
	 */
	public int updatePrintInitTemplate(PrintInitTemplate printInitTemplate);
	/**
	 * 批量更新(账单打印模板初始化表)信息
	 * @param printInitTemplateList
	 * @return
	 */
	public int updatePrintInitTemplateBatch(List<PrintInitTemplate> printInitTemplateList);
	/**
	 * 根据序列号删除(账单打印模板初始化表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrintInitTemplateLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(账单打印模板初始化表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrintInitTemplateLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(账单打印模板初始化表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrintInitTemplate(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(账单打印模板初始化表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrintInitTemplateBatch(List<java.math.BigInteger> idList);
	
}

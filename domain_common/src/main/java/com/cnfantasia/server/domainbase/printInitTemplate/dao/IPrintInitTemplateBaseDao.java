package com.cnfantasia.server.domainbase.printInitTemplate.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.printInitTemplate.entity.PrintInitTemplate;

/**
 * 描述:(账单打印模板初始化表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrintInitTemplateBaseDao {
	/**
	 * 根据条件查询(账单打印模板初始化表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrintInitTemplate> selectPrintInitTemplateByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(账单打印模板初始化表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrintInitTemplate> selectPrintInitTemplateByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(账单打印模板初始化表)信息
	 * @param id
	 * @return
	 */
	public PrintInitTemplate selectPrintInitTemplateBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(账单打印模板初始化表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrintInitTemplateCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(账单打印模板初始化表)新增一条记录
	 * @param printInitTemplate
	 * @return
	 */
	public int insertPrintInitTemplate(PrintInitTemplate printInitTemplate);
	
	/**
	 * 批量新增(账单打印模板初始化表)信息
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
	 * 根据Id 批量删除(账单打印模板初始化表)信息_逻辑删除
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

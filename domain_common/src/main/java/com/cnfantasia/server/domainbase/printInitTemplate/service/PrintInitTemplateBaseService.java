package com.cnfantasia.server.domainbase.printInitTemplate.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.printInitTemplate.dao.IPrintInitTemplateBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.printInitTemplate.entity.PrintInitTemplate;

/**
 * 描述:(账单打印模板初始化表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrintInitTemplateBaseService implements IPrintInitTemplateBaseService{
	
	private IPrintInitTemplateBaseDao printInitTemplateBaseDao;
	public void setPrintInitTemplateBaseDao(IPrintInitTemplateBaseDao printInitTemplateBaseDao) {
		this.printInitTemplateBaseDao = printInitTemplateBaseDao;
	}
	/**
	 * 根据条件查询(账单打印模板初始化表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrintInitTemplate> getPrintInitTemplateByCondition(Map<String,Object> paramMap){
		return printInitTemplateBaseDao.selectPrintInitTemplateByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(账单打印模板初始化表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrintInitTemplate> getPrintInitTemplateByConditionDim(Map<String,Object> paramMap){
		return printInitTemplateBaseDao.selectPrintInitTemplateByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(账单打印模板初始化表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrintInitTemplate> getPrintInitTemplateByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return printInitTemplateBaseDao.selectPrintInitTemplateByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(账单打印模板初始化表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrintInitTemplate> getPrintInitTemplateByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return printInitTemplateBaseDao.selectPrintInitTemplateByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(账单打印模板初始化表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrintInitTemplate getPrintInitTemplateBySeqId(java.math.BigInteger id){
		return printInitTemplateBaseDao.selectPrintInitTemplateBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(账单打印模板初始化表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrintInitTemplateCount(Map<String,Object> paramMap){
		return printInitTemplateBaseDao.selectPrintInitTemplateCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(账单打印模板初始化表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrintInitTemplateCountDim(Map<String,Object> paramMap){
		return printInitTemplateBaseDao.selectPrintInitTemplateCount(paramMap,true);
	}
	/**
	 * 往(账单打印模板初始化表)新增一条记录
	 * @param printInitTemplate
	 * @return
	 */
	@Override
	public int insertPrintInitTemplate(PrintInitTemplate printInitTemplate){
		return printInitTemplateBaseDao.insertPrintInitTemplate(printInitTemplate);
	}
	/**
	 * 批量新增(账单打印模板初始化表)
	 * @param printInitTemplateList
	 * @return
	 */
	@Override
	public int insertPrintInitTemplateBatch(List<PrintInitTemplate> printInitTemplateList){
		return printInitTemplateBaseDao.insertPrintInitTemplateBatch(printInitTemplateList);
	}
	/**
	 * 更新(账单打印模板初始化表)信息
	 * @param printInitTemplate
	 * @return
	 */
	@Override
	public int updatePrintInitTemplate(PrintInitTemplate printInitTemplate){
		return printInitTemplateBaseDao.updatePrintInitTemplate(printInitTemplate);
	}
	/**
	 * 批量更新(账单打印模板初始化表)信息
	 * @param printInitTemplateList
	 * @return
	 */
	@Override
	public int updatePrintInitTemplateBatch(List<PrintInitTemplate> printInitTemplateList){
		return printInitTemplateBaseDao.updatePrintInitTemplateBatch(printInitTemplateList);
	}
	/**
	 * 根据序列号删除(账单打印模板初始化表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrintInitTemplateLogic(java.math.BigInteger id){
		return printInitTemplateBaseDao.deletePrintInitTemplateLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(账单打印模板初始化表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrintInitTemplateLogicBatch(List<java.math.BigInteger> idList){
		return printInitTemplateBaseDao.deletePrintInitTemplateLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(账单打印模板初始化表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrintInitTemplate(java.math.BigInteger id){
//		return printInitTemplateBaseDao.deletePrintInitTemplate(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单打印模板初始化表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrintInitTemplateBatch(List<java.math.BigInteger> idList){
//		return printInitTemplateBaseDao.deletePrintInitTemplateBatch(idList);
//	}
	
}

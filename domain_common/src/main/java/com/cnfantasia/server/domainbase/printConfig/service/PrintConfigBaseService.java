package com.cnfantasia.server.domainbase.printConfig.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.printConfig.dao.IPrintConfigBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig;

/**
 * 描述:(物业账单打印小区模板配置表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class PrintConfigBaseService implements IPrintConfigBaseService{
	
	private IPrintConfigBaseDao printConfigBaseDao;
	public void setPrintConfigBaseDao(IPrintConfigBaseDao printConfigBaseDao) {
		this.printConfigBaseDao = printConfigBaseDao;
	}
	/**
	 * 根据条件查询(物业账单打印小区模板配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrintConfig> getPrintConfigByCondition(Map<String,Object> paramMap){
		return printConfigBaseDao.selectPrintConfigByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业账单打印小区模板配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<PrintConfig> getPrintConfigByConditionDim(Map<String,Object> paramMap){
		return printConfigBaseDao.selectPrintConfigByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业账单打印小区模板配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrintConfig> getPrintConfigByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return printConfigBaseDao.selectPrintConfigByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业账单打印小区模板配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<PrintConfig> getPrintConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return printConfigBaseDao.selectPrintConfigByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业账单打印小区模板配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrintConfig getPrintConfigBySeqId(java.math.BigInteger id){
		return printConfigBaseDao.selectPrintConfigBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业账单打印小区模板配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrintConfigCount(Map<String,Object> paramMap){
		return printConfigBaseDao.selectPrintConfigCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业账单打印小区模板配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getPrintConfigCountDim(Map<String,Object> paramMap){
		return printConfigBaseDao.selectPrintConfigCount(paramMap,true);
	}
	/**
	 * 往(物业账单打印小区模板配置表)新增一条记录
	 * @param printConfig
	 * @return
	 */
	@Override
	public int insertPrintConfig(PrintConfig printConfig){
		return printConfigBaseDao.insertPrintConfig(printConfig);
	}
	/**
	 * 批量新增(物业账单打印小区模板配置表)
	 * @param printConfigList
	 * @return
	 */
	@Override
	public int insertPrintConfigBatch(List<PrintConfig> printConfigList){
		return printConfigBaseDao.insertPrintConfigBatch(printConfigList);
	}
	/**
	 * 更新(物业账单打印小区模板配置表)信息
	 * @param printConfig
	 * @return
	 */
	@Override
	public int updatePrintConfig(PrintConfig printConfig){
		return printConfigBaseDao.updatePrintConfig(printConfig);
	}
	/**
	 * 批量更新(物业账单打印小区模板配置表)信息
	 * @param printConfigList
	 * @return
	 */
	@Override
	public int updatePrintConfigBatch(List<PrintConfig> printConfigList){
		return printConfigBaseDao.updatePrintConfigBatch(printConfigList);
	}
	/**
	 * 根据序列号删除(物业账单打印小区模板配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrintConfigLogic(java.math.BigInteger id){
		return printConfigBaseDao.deletePrintConfigLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业账单打印小区模板配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrintConfigLogicBatch(List<java.math.BigInteger> idList){
		return printConfigBaseDao.deletePrintConfigLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业账单打印小区模板配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrintConfig(java.math.BigInteger id){
//		return printConfigBaseDao.deletePrintConfig(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业账单打印小区模板配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrintConfigBatch(List<java.math.BigInteger> idList){
//		return printConfigBaseDao.deletePrintConfigBatch(idList);
//	}
	
}

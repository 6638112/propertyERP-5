package com.cnfantasia.server.domainbase.printConfig.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig;

/**
 * 描述:(物业账单打印小区模板配置表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrintConfigBaseService {
	
	/**
	 * 根据条件查询(物业账单打印小区模板配置表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrintConfig> getPrintConfigByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(物业账单打印小区模板配置表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<PrintConfig> getPrintConfigByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(物业账单打印小区模板配置表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrintConfig> getPrintConfigByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(物业账单打印小区模板配置表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<PrintConfig> getPrintConfigByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(物业账单打印小区模板配置表)信息
	 * @param id
	 * @return
	 */
	public PrintConfig getPrintConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业账单打印小区模板配置表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getPrintConfigCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(物业账单打印小区模板配置表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getPrintConfigCountDim(Map<String,Object> paramMap);
	/**
	 * 往(物业账单打印小区模板配置表)新增一条记录
	 * @param printConfig
	 * @return
	 */
	public int insertPrintConfig(PrintConfig printConfig);
	/**
	 * 批量新增(物业账单打印小区模板配置表)
	 * @param printConfigList
	 * @return
	 */
	public int insertPrintConfigBatch(List<PrintConfig> printConfigList);
	/**
	 * 更新(物业账单打印小区模板配置表)信息
	 * @param printConfig
	 * @return
	 */
	public int updatePrintConfig(PrintConfig printConfig);
	/**
	 * 批量更新(物业账单打印小区模板配置表)信息
	 * @param printConfigList
	 * @return
	 */
	public int updatePrintConfigBatch(List<PrintConfig> printConfigList);
	/**
	 * 根据序列号删除(物业账单打印小区模板配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deletePrintConfigLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(物业账单打印小区模板配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deletePrintConfigLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(物业账单打印小区模板配置表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deletePrintConfig(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(物业账单打印小区模板配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deletePrintConfigBatch(List<java.math.BigInteger> idList);
	
}

package com.cnfantasia.server.domainbase.printConfig.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig;

/**
 * 描述:(物业账单打印小区模板配置表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IPrintConfigBaseDao {
	/**
	 * 根据条件查询(物业账单打印小区模板配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrintConfig> selectPrintConfigByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(物业账单打印小区模板配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<PrintConfig> selectPrintConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(物业账单打印小区模板配置表)信息
	 * @param id
	 * @return
	 */
	public PrintConfig selectPrintConfigBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(物业账单打印小区模板配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectPrintConfigCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(物业账单打印小区模板配置表)新增一条记录
	 * @param printConfig
	 * @return
	 */
	public int insertPrintConfig(PrintConfig printConfig);
	
	/**
	 * 批量新增(物业账单打印小区模板配置表)信息
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
	 * 根据Id 批量删除(物业账单打印小区模板配置表)信息_逻辑删除
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

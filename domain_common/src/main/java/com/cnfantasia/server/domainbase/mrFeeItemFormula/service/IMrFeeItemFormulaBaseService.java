package com.cnfantasia.server.domainbase.mrFeeItemFormula.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;

/**
 * 描述:(抄表费收费项阶梯计算配置) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrFeeItemFormulaBaseService {
	
	/**
	 * 根据条件查询(抄表费收费项阶梯计算配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MrFeeItemFormula> getMrFeeItemFormulaByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(抄表费收费项阶梯计算配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MrFeeItemFormula> getMrFeeItemFormulaByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(抄表费收费项阶梯计算配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MrFeeItemFormula> getMrFeeItemFormulaByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(抄表费收费项阶梯计算配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MrFeeItemFormula> getMrFeeItemFormulaByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(抄表费收费项阶梯计算配置)信息
	 * @param id
	 * @return
	 */
	public MrFeeItemFormula getMrFeeItemFormulaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表费收费项阶梯计算配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMrFeeItemFormulaCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(抄表费收费项阶梯计算配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMrFeeItemFormulaCountDim(Map<String,Object> paramMap);
	/**
	 * 往(抄表费收费项阶梯计算配置)新增一条记录
	 * @param mrFeeItemFormula
	 * @return
	 */
	public int insertMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula);
	/**
	 * 批量新增(抄表费收费项阶梯计算配置)
	 * @param mrFeeItemFormulaList
	 * @return
	 */
	public int insertMrFeeItemFormulaBatch(List<MrFeeItemFormula> mrFeeItemFormulaList);
	/**
	 * 更新(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormula
	 * @return
	 */
	public int updateMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula);
	/**
	 * 批量更新(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormulaList
	 * @return
	 */
	public int updateMrFeeItemFormulaBatch(List<MrFeeItemFormula> mrFeeItemFormulaList);
	/**
	 * 根据序列号删除(抄表费收费项阶梯计算配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMrFeeItemFormulaLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(抄表费收费项阶梯计算配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMrFeeItemFormulaLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(抄表费收费项阶梯计算配置)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMrFeeItemFormula(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费项阶梯计算配置)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMrFeeItemFormulaBatch(List<java.math.BigInteger> idList);
	
}

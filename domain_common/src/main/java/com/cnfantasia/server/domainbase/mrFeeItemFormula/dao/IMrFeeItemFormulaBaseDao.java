package com.cnfantasia.server.domainbase.mrFeeItemFormula.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;

/**
 * 描述:(抄表费收费项阶梯计算配置) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMrFeeItemFormulaBaseDao {
	/**
	 * 根据条件查询(抄表费收费项阶梯计算配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MrFeeItemFormula> selectMrFeeItemFormulaByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(抄表费收费项阶梯计算配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MrFeeItemFormula> selectMrFeeItemFormulaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(抄表费收费项阶梯计算配置)信息
	 * @param id
	 * @return
	 */
	public MrFeeItemFormula selectMrFeeItemFormulaBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(抄表费收费项阶梯计算配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMrFeeItemFormulaCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(抄表费收费项阶梯计算配置)新增一条记录
	 * @param mrFeeItemFormula
	 * @return
	 */
	public int insertMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula);
	
	/**
	 * 批量新增(抄表费收费项阶梯计算配置)信息
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
	 * 根据Id 批量删除(抄表费收费项阶梯计算配置)信息_逻辑删除
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

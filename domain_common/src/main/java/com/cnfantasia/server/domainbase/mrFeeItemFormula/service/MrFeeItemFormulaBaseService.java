package com.cnfantasia.server.domainbase.mrFeeItemFormula.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrFeeItemFormula.dao.IMrFeeItemFormulaBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;

/**
 * 描述:(抄表费收费项阶梯计算配置) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrFeeItemFormulaBaseService implements IMrFeeItemFormulaBaseService{
	
	private IMrFeeItemFormulaBaseDao mrFeeItemFormulaBaseDao;
	public void setMrFeeItemFormulaBaseDao(IMrFeeItemFormulaBaseDao mrFeeItemFormulaBaseDao) {
		this.mrFeeItemFormulaBaseDao = mrFeeItemFormulaBaseDao;
	}
	/**
	 * 根据条件查询(抄表费收费项阶梯计算配置)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrFeeItemFormula> getMrFeeItemFormulaByCondition(Map<String,Object> paramMap){
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表费收费项阶梯计算配置)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrFeeItemFormula> getMrFeeItemFormulaByConditionDim(Map<String,Object> paramMap){
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表费收费项阶梯计算配置)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrFeeItemFormula> getMrFeeItemFormulaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表费收费项阶梯计算配置)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrFeeItemFormula> getMrFeeItemFormulaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表费收费项阶梯计算配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrFeeItemFormula getMrFeeItemFormulaBySeqId(java.math.BigInteger id){
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费项阶梯计算配置)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrFeeItemFormulaCount(Map<String,Object> paramMap){
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费项阶梯计算配置)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrFeeItemFormulaCountDim(Map<String,Object> paramMap){
		return mrFeeItemFormulaBaseDao.selectMrFeeItemFormulaCount(paramMap,true);
	}
	/**
	 * 往(抄表费收费项阶梯计算配置)新增一条记录
	 * @param mrFeeItemFormula
	 * @return
	 */
	@Override
	public int insertMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula){
		return mrFeeItemFormulaBaseDao.insertMrFeeItemFormula(mrFeeItemFormula);
	}
	/**
	 * 批量新增(抄表费收费项阶梯计算配置)
	 * @param mrFeeItemFormulaList
	 * @return
	 */
	@Override
	public int insertMrFeeItemFormulaBatch(List<MrFeeItemFormula> mrFeeItemFormulaList){
		return mrFeeItemFormulaBaseDao.insertMrFeeItemFormulaBatch(mrFeeItemFormulaList);
	}
	/**
	 * 更新(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormula
	 * @return
	 */
	@Override
	public int updateMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula){
		return mrFeeItemFormulaBaseDao.updateMrFeeItemFormula(mrFeeItemFormula);
	}
	/**
	 * 批量更新(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormulaList
	 * @return
	 */
	@Override
	public int updateMrFeeItemFormulaBatch(List<MrFeeItemFormula> mrFeeItemFormulaList){
		return mrFeeItemFormulaBaseDao.updateMrFeeItemFormulaBatch(mrFeeItemFormulaList);
	}
	/**
	 * 根据序列号删除(抄表费收费项阶梯计算配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrFeeItemFormulaLogic(java.math.BigInteger id){
		return mrFeeItemFormulaBaseDao.deleteMrFeeItemFormulaLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表费收费项阶梯计算配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrFeeItemFormulaLogicBatch(List<java.math.BigInteger> idList){
		return mrFeeItemFormulaBaseDao.deleteMrFeeItemFormulaLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表费收费项阶梯计算配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrFeeItemFormula(java.math.BigInteger id){
//		return mrFeeItemFormulaBaseDao.deleteMrFeeItemFormula(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费项阶梯计算配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrFeeItemFormulaBatch(List<java.math.BigInteger> idList){
//		return mrFeeItemFormulaBaseDao.deleteMrFeeItemFormulaBatch(idList);
//	}
	
}

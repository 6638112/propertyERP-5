package com.cnfantasia.server.domainbase.ebuyProductParameters.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductParameters.dao.IEbuyProductParametersBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;

/**
 * 描述:(产品参数) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductParametersBaseService implements IEbuyProductParametersBaseService{
	
	private IEbuyProductParametersBaseDao ebuyProductParametersBaseDao;
	public void setEbuyProductParametersBaseDao(IEbuyProductParametersBaseDao ebuyProductParametersBaseDao) {
		this.ebuyProductParametersBaseDao = ebuyProductParametersBaseDao;
	}
	/**
	 * 根据条件查询(产品参数)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductParameters> getEbuyProductParametersByCondition(Map<String,Object> paramMap){
		return ebuyProductParametersBaseDao.selectEbuyProductParametersByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(产品参数)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductParameters> getEbuyProductParametersByConditionDim(Map<String,Object> paramMap){
		return ebuyProductParametersBaseDao.selectEbuyProductParametersByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(产品参数)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductParameters> getEbuyProductParametersByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductParametersBaseDao.selectEbuyProductParametersByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(产品参数)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductParameters> getEbuyProductParametersByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductParametersBaseDao.selectEbuyProductParametersByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(产品参数)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductParameters getEbuyProductParametersBySeqId(java.math.BigInteger id){
		return ebuyProductParametersBaseDao.selectEbuyProductParametersBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(产品参数)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductParametersCount(Map<String,Object> paramMap){
		return ebuyProductParametersBaseDao.selectEbuyProductParametersCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(产品参数)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductParametersCountDim(Map<String,Object> paramMap){
		return ebuyProductParametersBaseDao.selectEbuyProductParametersCount(paramMap,true);
	}
	/**
	 * 往(产品参数)新增一条记录
	 * @param ebuyProductParameters
	 * @return
	 */
	@Override
	public int insertEbuyProductParameters(EbuyProductParameters ebuyProductParameters){
		return ebuyProductParametersBaseDao.insertEbuyProductParameters(ebuyProductParameters);
	}
	/**
	 * 批量新增(产品参数)
	 * @param ebuyProductParametersList
	 * @return
	 */
	@Override
	public int insertEbuyProductParametersBatch(List<EbuyProductParameters> ebuyProductParametersList){
		return ebuyProductParametersBaseDao.insertEbuyProductParametersBatch(ebuyProductParametersList);
	}
	/**
	 * 更新(产品参数)信息
	 * @param ebuyProductParameters
	 * @return
	 */
	@Override
	public int updateEbuyProductParameters(EbuyProductParameters ebuyProductParameters){
		return ebuyProductParametersBaseDao.updateEbuyProductParameters(ebuyProductParameters);
	}
	/**
	 * 批量更新(产品参数)信息
	 * @param ebuyProductParametersList
	 * @return
	 */
	@Override
	public int updateEbuyProductParametersBatch(List<EbuyProductParameters> ebuyProductParametersList){
		return ebuyProductParametersBaseDao.updateEbuyProductParametersBatch(ebuyProductParametersList);
	}
	/**
	 * 根据序列号删除(产品参数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductParametersLogic(java.math.BigInteger id){
		return ebuyProductParametersBaseDao.deleteEbuyProductParametersLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(产品参数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductParametersLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductParametersBaseDao.deleteEbuyProductParametersLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(产品参数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductParameters(java.math.BigInteger id){
//		return ebuyProductParametersBaseDao.deleteEbuyProductParameters(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品参数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductParametersBatch(List<java.math.BigInteger> idList){
//		return ebuyProductParametersBaseDao.deleteEbuyProductParametersBatch(idList);
//	}
	
}

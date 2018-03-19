package com.cnfantasia.server.domainbase.ebuyProductParametersTemp.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductParametersTemp.dao.IEbuyProductParametersTempBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;

/**
 * 描述:(产品参数，临时存储从外部抓取的数据) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductParametersTempBaseService implements IEbuyProductParametersTempBaseService{
	
	private IEbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao;
	public void setEbuyProductParametersTempBaseDao(IEbuyProductParametersTempBaseDao ebuyProductParametersTempBaseDao) {
		this.ebuyProductParametersTempBaseDao = ebuyProductParametersTempBaseDao;
	}
	/**
	 * 根据条件查询(产品参数，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByCondition(Map<String,Object> paramMap){
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(产品参数，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByConditionDim(Map<String,Object> paramMap){
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(产品参数，临时存储从外部抓取的数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(产品参数，临时存储从外部抓取的数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductParametersTemp> getEbuyProductParametersTempByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(产品参数，临时存储从外部抓取的数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductParametersTemp getEbuyProductParametersTempBySeqId(java.math.BigInteger id){
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(产品参数，临时存储从外部抓取的数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductParametersTempCount(Map<String,Object> paramMap){
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(产品参数，临时存储从外部抓取的数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductParametersTempCountDim(Map<String,Object> paramMap){
		return ebuyProductParametersTempBaseDao.selectEbuyProductParametersTempCount(paramMap,true);
	}
	/**
	 * 往(产品参数，临时存储从外部抓取的数据)新增一条记录
	 * @param ebuyProductParametersTemp
	 * @return
	 */
	@Override
	public int insertEbuyProductParametersTemp(EbuyProductParametersTemp ebuyProductParametersTemp){
		return ebuyProductParametersTempBaseDao.insertEbuyProductParametersTemp(ebuyProductParametersTemp);
	}
	/**
	 * 批量新增(产品参数，临时存储从外部抓取的数据)
	 * @param ebuyProductParametersTempList
	 * @return
	 */
	@Override
	public int insertEbuyProductParametersTempBatch(List<EbuyProductParametersTemp> ebuyProductParametersTempList){
		return ebuyProductParametersTempBaseDao.insertEbuyProductParametersTempBatch(ebuyProductParametersTempList);
	}
	/**
	 * 更新(产品参数，临时存储从外部抓取的数据)信息
	 * @param ebuyProductParametersTemp
	 * @return
	 */
	@Override
	public int updateEbuyProductParametersTemp(EbuyProductParametersTemp ebuyProductParametersTemp){
		return ebuyProductParametersTempBaseDao.updateEbuyProductParametersTemp(ebuyProductParametersTemp);
	}
	/**
	 * 批量更新(产品参数，临时存储从外部抓取的数据)信息
	 * @param ebuyProductParametersTempList
	 * @return
	 */
	@Override
	public int updateEbuyProductParametersTempBatch(List<EbuyProductParametersTemp> ebuyProductParametersTempList){
		return ebuyProductParametersTempBaseDao.updateEbuyProductParametersTempBatch(ebuyProductParametersTempList);
	}
	/**
	 * 根据序列号删除(产品参数，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductParametersTempLogic(java.math.BigInteger id){
		return ebuyProductParametersTempBaseDao.deleteEbuyProductParametersTempLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(产品参数，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductParametersTempLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductParametersTempBaseDao.deleteEbuyProductParametersTempLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(产品参数，临时存储从外部抓取的数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductParametersTemp(java.math.BigInteger id){
//		return ebuyProductParametersTempBaseDao.deleteEbuyProductParametersTemp(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品参数，临时存储从外部抓取的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductParametersTempBatch(List<java.math.BigInteger> idList){
//		return ebuyProductParametersTempBaseDao.deleteEbuyProductParametersTempBatch(idList);
//	}
	
}

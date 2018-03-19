package com.cnfantasia.server.domainbase.ebuyExpressCompany.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyExpressCompany.dao.IEbuyExpressCompanyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;

/**
 * 描述:(快递公司) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyExpressCompanyBaseService implements IEbuyExpressCompanyBaseService{
	
	private IEbuyExpressCompanyBaseDao ebuyExpressCompanyBaseDao;
	public void setEbuyExpressCompanyBaseDao(IEbuyExpressCompanyBaseDao ebuyExpressCompanyBaseDao) {
		this.ebuyExpressCompanyBaseDao = ebuyExpressCompanyBaseDao;
	}
	/**
	 * 根据条件查询(快递公司)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyExpressCompany> getEbuyExpressCompanyByCondition(Map<String,Object> paramMap){
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(快递公司)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyExpressCompany> getEbuyExpressCompanyByConditionDim(Map<String,Object> paramMap){
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(快递公司)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyExpressCompany> getEbuyExpressCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(快递公司)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyExpressCompany> getEbuyExpressCompanyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(快递公司)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyExpressCompany getEbuyExpressCompanyBySeqId(java.math.BigInteger id){
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(快递公司)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyExpressCompanyCount(Map<String,Object> paramMap){
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(快递公司)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyExpressCompanyCountDim(Map<String,Object> paramMap){
		return ebuyExpressCompanyBaseDao.selectEbuyExpressCompanyCount(paramMap,true);
	}
	/**
	 * 往(快递公司)新增一条记录
	 * @param ebuyExpressCompany
	 * @return
	 */
	@Override
	public int insertEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany){
		return ebuyExpressCompanyBaseDao.insertEbuyExpressCompany(ebuyExpressCompany);
	}
	/**
	 * 批量新增(快递公司)
	 * @param ebuyExpressCompanyList
	 * @return
	 */
	@Override
	public int insertEbuyExpressCompanyBatch(List<EbuyExpressCompany> ebuyExpressCompanyList){
		return ebuyExpressCompanyBaseDao.insertEbuyExpressCompanyBatch(ebuyExpressCompanyList);
	}
	/**
	 * 更新(快递公司)信息
	 * @param ebuyExpressCompany
	 * @return
	 */
	@Override
	public int updateEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany){
		return ebuyExpressCompanyBaseDao.updateEbuyExpressCompany(ebuyExpressCompany);
	}
	/**
	 * 批量更新(快递公司)信息
	 * @param ebuyExpressCompanyList
	 * @return
	 */
	@Override
	public int updateEbuyExpressCompanyBatch(List<EbuyExpressCompany> ebuyExpressCompanyList){
		return ebuyExpressCompanyBaseDao.updateEbuyExpressCompanyBatch(ebuyExpressCompanyList);
	}
	/**
	 * 根据序列号删除(快递公司)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyExpressCompanyLogic(java.math.BigInteger id){
		return ebuyExpressCompanyBaseDao.deleteEbuyExpressCompanyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(快递公司)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyExpressCompanyLogicBatch(List<java.math.BigInteger> idList){
		return ebuyExpressCompanyBaseDao.deleteEbuyExpressCompanyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(快递公司)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyExpressCompany(java.math.BigInteger id){
//		return ebuyExpressCompanyBaseDao.deleteEbuyExpressCompany(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(快递公司)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyExpressCompanyBatch(List<java.math.BigInteger> idList){
//		return ebuyExpressCompanyBaseDao.deleteEbuyExpressCompanyBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.dao.IEbuyHomeTypeHasProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;

/**
 * 描述:(首页分类名称关联产品分类) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyHomeTypeHasProductBaseService implements IEbuyHomeTypeHasProductBaseService{
	
	private IEbuyHomeTypeHasProductBaseDao ebuyHomeTypeHasProductBaseDao;
	public void setEbuyHomeTypeHasProductBaseDao(IEbuyHomeTypeHasProductBaseDao ebuyHomeTypeHasProductBaseDao) {
		this.ebuyHomeTypeHasProductBaseDao = ebuyHomeTypeHasProductBaseDao;
	}
	/**
	 * 根据条件查询(首页分类名称关联产品分类)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap){
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(首页分类名称关联产品分类)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByConditionDim(Map<String,Object> paramMap){
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(首页分类名称关联产品分类)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(首页分类名称关联产品分类)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyHomeTypeHasProduct> getEbuyHomeTypeHasProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(首页分类名称关联产品分类)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyHomeTypeHasProduct getEbuyHomeTypeHasProductBySeqId(java.math.BigInteger id){
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(首页分类名称关联产品分类)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyHomeTypeHasProductCount(Map<String,Object> paramMap){
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(首页分类名称关联产品分类)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyHomeTypeHasProductCountDim(Map<String,Object> paramMap){
		return ebuyHomeTypeHasProductBaseDao.selectEbuyHomeTypeHasProductCount(paramMap,true);
	}
	/**
	 * 往(首页分类名称关联产品分类)新增一条记录
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	@Override
	public int insertEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct){
		return ebuyHomeTypeHasProductBaseDao.insertEbuyHomeTypeHasProduct(ebuyHomeTypeHasProduct);
	}
	/**
	 * 批量新增(首页分类名称关联产品分类)
	 * @param ebuyHomeTypeHasProductList
	 * @return
	 */
	@Override
	public int insertEbuyHomeTypeHasProductBatch(List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProductList){
		return ebuyHomeTypeHasProductBaseDao.insertEbuyHomeTypeHasProductBatch(ebuyHomeTypeHasProductList);
	}
	/**
	 * 更新(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	@Override
	public int updateEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct){
		return ebuyHomeTypeHasProductBaseDao.updateEbuyHomeTypeHasProduct(ebuyHomeTypeHasProduct);
	}
	/**
	 * 批量更新(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProductList
	 * @return
	 */
	@Override
	public int updateEbuyHomeTypeHasProductBatch(List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProductList){
		return ebuyHomeTypeHasProductBaseDao.updateEbuyHomeTypeHasProductBatch(ebuyHomeTypeHasProductList);
	}
	/**
	 * 根据序列号删除(首页分类名称关联产品分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeHasProductLogic(java.math.BigInteger id){
		return ebuyHomeTypeHasProductBaseDao.deleteEbuyHomeTypeHasProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(首页分类名称关联产品分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeHasProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyHomeTypeHasProductBaseDao.deleteEbuyHomeTypeHasProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(首页分类名称关联产品分类)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeTypeHasProduct(java.math.BigInteger id){
//		return ebuyHomeTypeHasProductBaseDao.deleteEbuyHomeTypeHasProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页分类名称关联产品分类)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeTypeHasProductBatch(List<java.math.BigInteger> idList){
//		return ebuyHomeTypeHasProductBaseDao.deleteEbuyHomeTypeHasProductBatch(idList);
//	}
	
}

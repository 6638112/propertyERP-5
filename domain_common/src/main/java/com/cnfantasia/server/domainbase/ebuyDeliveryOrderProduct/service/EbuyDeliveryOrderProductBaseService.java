package com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.dao.IEbuyDeliveryOrderProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;

/**
 * 描述:(供应商配送包含的商品信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyDeliveryOrderProductBaseService implements IEbuyDeliveryOrderProductBaseService{
	
	private IEbuyDeliveryOrderProductBaseDao ebuyDeliveryOrderProductBaseDao;
	public void setEbuyDeliveryOrderProductBaseDao(IEbuyDeliveryOrderProductBaseDao ebuyDeliveryOrderProductBaseDao) {
		this.ebuyDeliveryOrderProductBaseDao = ebuyDeliveryOrderProductBaseDao;
	}
	/**
	 * 根据条件查询(供应商配送包含的商品信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap){
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商配送包含的商品信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByConditionDim(Map<String,Object> paramMap){
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商配送包含的商品信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商配送包含的商品信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderProduct> getEbuyDeliveryOrderProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商配送包含的商品信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryOrderProduct getEbuyDeliveryOrderProductBySeqId(java.math.BigInteger id){
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商配送包含的商品信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryOrderProductCount(Map<String,Object> paramMap){
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商配送包含的商品信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryOrderProductCountDim(Map<String,Object> paramMap){
		return ebuyDeliveryOrderProductBaseDao.selectEbuyDeliveryOrderProductCount(paramMap,true);
	}
	/**
	 * 往(供应商配送包含的商品信息)新增一条记录
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct){
		return ebuyDeliveryOrderProductBaseDao.insertEbuyDeliveryOrderProduct(ebuyDeliveryOrderProduct);
	}
	/**
	 * 批量新增(供应商配送包含的商品信息)
	 * @param ebuyDeliveryOrderProductList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderProductBatch(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList){
		return ebuyDeliveryOrderProductBaseDao.insertEbuyDeliveryOrderProductBatch(ebuyDeliveryOrderProductList);
	}
	/**
	 * 更新(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct){
		return ebuyDeliveryOrderProductBaseDao.updateEbuyDeliveryOrderProduct(ebuyDeliveryOrderProduct);
	}
	/**
	 * 批量更新(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProductList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderProductBatch(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList){
		return ebuyDeliveryOrderProductBaseDao.updateEbuyDeliveryOrderProductBatch(ebuyDeliveryOrderProductList);
	}
	/**
	 * 根据序列号删除(供应商配送包含的商品信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderProductLogic(java.math.BigInteger id){
		return ebuyDeliveryOrderProductBaseDao.deleteEbuyDeliveryOrderProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商配送包含的商品信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyDeliveryOrderProductBaseDao.deleteEbuyDeliveryOrderProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商配送包含的商品信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderProduct(java.math.BigInteger id){
//		return ebuyDeliveryOrderProductBaseDao.deleteEbuyDeliveryOrderProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商配送包含的商品信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderProductBatch(List<java.math.BigInteger> idList){
//		return ebuyDeliveryOrderProductBaseDao.deleteEbuyDeliveryOrderProductBatch(idList);
//	}
	
}

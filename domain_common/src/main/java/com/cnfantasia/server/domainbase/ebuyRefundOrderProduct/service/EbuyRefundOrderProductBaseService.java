package com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.dao.IEbuyRefundOrderProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * 描述:(退货订单信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyRefundOrderProductBaseService implements IEbuyRefundOrderProductBaseService{
	
	private IEbuyRefundOrderProductBaseDao ebuyRefundOrderProductBaseDao;
	public void setEbuyRefundOrderProductBaseDao(IEbuyRefundOrderProductBaseDao ebuyRefundOrderProductBaseDao) {
		this.ebuyRefundOrderProductBaseDao = ebuyRefundOrderProductBaseDao;
	}
	/**
	 * 根据条件查询(退货订单信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByCondition(Map<String,Object> paramMap){
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(退货订单信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByConditionDim(Map<String,Object> paramMap){
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(退货订单信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(退货订单信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyRefundOrderProduct> getEbuyRefundOrderProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(退货订单信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyRefundOrderProduct getEbuyRefundOrderProductBySeqId(java.math.BigInteger id){
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(退货订单信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyRefundOrderProductCount(Map<String,Object> paramMap){
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(退货订单信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyRefundOrderProductCountDim(Map<String,Object> paramMap){
		return ebuyRefundOrderProductBaseDao.selectEbuyRefundOrderProductCount(paramMap,true);
	}
	/**
	 * 往(退货订单信息)新增一条记录
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct){
		return ebuyRefundOrderProductBaseDao.insertEbuyRefundOrderProduct(ebuyRefundOrderProduct);
	}
	/**
	 * 批量新增(退货订单信息)
	 * @param ebuyRefundOrderProductList
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrderProductBatch(List<EbuyRefundOrderProduct> ebuyRefundOrderProductList){
		return ebuyRefundOrderProductBaseDao.insertEbuyRefundOrderProductBatch(ebuyRefundOrderProductList);
	}
	/**
	 * 更新(退货订单信息)信息
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct){
		return ebuyRefundOrderProductBaseDao.updateEbuyRefundOrderProduct(ebuyRefundOrderProduct);
	}
	/**
	 * 批量更新(退货订单信息)信息
	 * @param ebuyRefundOrderProductList
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrderProductBatch(List<EbuyRefundOrderProduct> ebuyRefundOrderProductList){
		return ebuyRefundOrderProductBaseDao.updateEbuyRefundOrderProductBatch(ebuyRefundOrderProductList);
	}
	/**
	 * 根据序列号删除(退货订单信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderProductLogic(java.math.BigInteger id){
		return ebuyRefundOrderProductBaseDao.deleteEbuyRefundOrderProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(退货订单信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyRefundOrderProductBaseDao.deleteEbuyRefundOrderProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(退货订单信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrderProduct(java.math.BigInteger id){
//		return ebuyRefundOrderProductBaseDao.deleteEbuyRefundOrderProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(退货订单信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrderProductBatch(List<java.math.BigInteger> idList){
//		return ebuyRefundOrderProductBaseDao.deleteEbuyRefundOrderProductBatch(idList);
//	}
	
}

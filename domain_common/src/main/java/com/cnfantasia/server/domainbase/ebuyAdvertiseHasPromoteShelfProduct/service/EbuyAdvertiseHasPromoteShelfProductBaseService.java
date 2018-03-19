package com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.dao.IEbuyAdvertiseHasPromoteShelfProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.entity.EbuyAdvertiseHasPromoteShelfProduct;

/**
 * 描述:(商品推广广告对应商品表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyAdvertiseHasPromoteShelfProductBaseService implements IEbuyAdvertiseHasPromoteShelfProductBaseService{
	
	private IEbuyAdvertiseHasPromoteShelfProductBaseDao ebuyAdvertiseHasPromoteShelfProductBaseDao;
	public void setEbuyAdvertiseHasPromoteShelfProductBaseDao(IEbuyAdvertiseHasPromoteShelfProductBaseDao ebuyAdvertiseHasPromoteShelfProductBaseDao) {
		this.ebuyAdvertiseHasPromoteShelfProductBaseDao = ebuyAdvertiseHasPromoteShelfProductBaseDao;
	}
	/**
	 * 根据条件查询(商品推广广告对应商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyAdvertiseHasPromoteShelfProduct> getEbuyAdvertiseHasPromoteShelfProductByCondition(Map<String,Object> paramMap){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品推广广告对应商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyAdvertiseHasPromoteShelfProduct> getEbuyAdvertiseHasPromoteShelfProductByConditionDim(Map<String,Object> paramMap){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品推广广告对应商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyAdvertiseHasPromoteShelfProduct> getEbuyAdvertiseHasPromoteShelfProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品推广广告对应商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyAdvertiseHasPromoteShelfProduct> getEbuyAdvertiseHasPromoteShelfProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品推广广告对应商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyAdvertiseHasPromoteShelfProduct getEbuyAdvertiseHasPromoteShelfProductBySeqId(java.math.BigInteger id){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品推广广告对应商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyAdvertiseHasPromoteShelfProductCount(Map<String,Object> paramMap){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品推广广告对应商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyAdvertiseHasPromoteShelfProductCountDim(Map<String,Object> paramMap){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.selectEbuyAdvertiseHasPromoteShelfProductCount(paramMap,true);
	}
	/**
	 * 往(商品推广广告对应商品表)新增一条记录
	 * @param ebuyAdvertiseHasPromoteShelfProduct
	 * @return
	 */
	@Override
	public int insertEbuyAdvertiseHasPromoteShelfProduct(EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.insertEbuyAdvertiseHasPromoteShelfProduct(ebuyAdvertiseHasPromoteShelfProduct);
	}
	/**
	 * 批量新增(商品推广广告对应商品表)
	 * @param ebuyAdvertiseHasPromoteShelfProductList
	 * @return
	 */
	@Override
	public int insertEbuyAdvertiseHasPromoteShelfProductBatch(List<EbuyAdvertiseHasPromoteShelfProduct> ebuyAdvertiseHasPromoteShelfProductList){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.insertEbuyAdvertiseHasPromoteShelfProductBatch(ebuyAdvertiseHasPromoteShelfProductList);
	}
	/**
	 * 更新(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProduct
	 * @return
	 */
	@Override
	public int updateEbuyAdvertiseHasPromoteShelfProduct(EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.updateEbuyAdvertiseHasPromoteShelfProduct(ebuyAdvertiseHasPromoteShelfProduct);
	}
	/**
	 * 批量更新(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProductList
	 * @return
	 */
	@Override
	public int updateEbuyAdvertiseHasPromoteShelfProductBatch(List<EbuyAdvertiseHasPromoteShelfProduct> ebuyAdvertiseHasPromoteShelfProductList){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.updateEbuyAdvertiseHasPromoteShelfProductBatch(ebuyAdvertiseHasPromoteShelfProductList);
	}
	/**
	 * 根据序列号删除(商品推广广告对应商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseHasPromoteShelfProductLogic(java.math.BigInteger id){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.deleteEbuyAdvertiseHasPromoteShelfProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品推广广告对应商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseHasPromoteShelfProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyAdvertiseHasPromoteShelfProductBaseDao.deleteEbuyAdvertiseHasPromoteShelfProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品推广广告对应商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertiseHasPromoteShelfProduct(java.math.BigInteger id){
//		return ebuyAdvertiseHasPromoteShelfProductBaseDao.deleteEbuyAdvertiseHasPromoteShelfProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品推广广告对应商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertiseHasPromoteShelfProductBatch(List<java.math.BigInteger> idList){
//		return ebuyAdvertiseHasPromoteShelfProductBaseDao.deleteEbuyAdvertiseHasPromoteShelfProductBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.ebuyProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProduct.dao.IEbuyProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * 描述:(商品表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductBaseService implements IEbuyProductBaseService{
	
	private IEbuyProductBaseDao ebuyProductBaseDao;
	public void setEbuyProductBaseDao(IEbuyProductBaseDao ebuyProductBaseDao) {
		this.ebuyProductBaseDao = ebuyProductBaseDao;
	}
	/**
	 * 根据条件查询(商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap){
		return ebuyProductBaseDao.selectEbuyProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProduct> getEbuyProductByConditionDim(Map<String,Object> paramMap){
		return ebuyProductBaseDao.selectEbuyProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductBaseDao.selectEbuyProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProduct> getEbuyProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductBaseDao.selectEbuyProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProduct getEbuyProductBySeqId(java.math.BigInteger id){
		return ebuyProductBaseDao.selectEbuyProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductCount(Map<String,Object> paramMap){
		return ebuyProductBaseDao.selectEbuyProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductCountDim(Map<String,Object> paramMap){
		return ebuyProductBaseDao.selectEbuyProductCount(paramMap,true);
	}
	/**
	 * 往(商品表)新增一条记录
	 * @param ebuyProduct
	 * @return
	 */
	@Override
	public int insertEbuyProduct(EbuyProduct ebuyProduct){
		return ebuyProductBaseDao.insertEbuyProduct(ebuyProduct);
	}
	/**
	 * 批量新增(商品表)
	 * @param ebuyProductList
	 * @return
	 */
	@Override
	public int insertEbuyProductBatch(List<EbuyProduct> ebuyProductList){
		return ebuyProductBaseDao.insertEbuyProductBatch(ebuyProductList);
	}
	/**
	 * 更新(商品表)信息
	 * @param ebuyProduct
	 * @return
	 */
	@Override
	public int updateEbuyProduct(EbuyProduct ebuyProduct){
		return ebuyProductBaseDao.updateEbuyProduct(ebuyProduct);
	}
	/**
	 * 批量更新(商品表)信息
	 * @param ebuyProductList
	 * @return
	 */
	@Override
	public int updateEbuyProductBatch(List<EbuyProduct> ebuyProductList){
		return ebuyProductBaseDao.updateEbuyProductBatch(ebuyProductList);
	}
	/**
	 * 根据序列号删除(商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductLogic(java.math.BigInteger id){
		return ebuyProductBaseDao.deleteEbuyProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductBaseDao.deleteEbuyProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProduct(java.math.BigInteger id){
//		return ebuyProductBaseDao.deleteEbuyProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductBatch(List<java.math.BigInteger> idList){
//		return ebuyProductBaseDao.deleteEbuyProductBatch(idList);
//	}
	
}

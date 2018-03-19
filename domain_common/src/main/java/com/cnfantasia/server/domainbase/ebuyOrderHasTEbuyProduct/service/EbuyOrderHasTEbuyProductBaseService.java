package com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.dao.IEbuyOrderHasTEbuyProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;

/**
 * 描述:(订单商品关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderHasTEbuyProductBaseService implements IEbuyOrderHasTEbuyProductBaseService{
	
	private IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao;
	public void setEbuyOrderHasTEbuyProductBaseDao(IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao) {
		this.ebuyOrderHasTEbuyProductBaseDao = ebuyOrderHasTEbuyProductBaseDao;
	}
	/**
	 * 根据条件查询(订单商品关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap){
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(订单商品关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(订单商品关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(订单商品关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTEbuyProduct> getEbuyOrderHasTEbuyProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(订单商品关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderHasTEbuyProduct getEbuyOrderHasTEbuyProductBySeqId(java.math.BigInteger id){
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(订单商品关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderHasTEbuyProductCount(Map<String,Object> paramMap){
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(订单商品关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderHasTEbuyProductCountDim(Map<String,Object> paramMap){
		return ebuyOrderHasTEbuyProductBaseDao.selectEbuyOrderHasTEbuyProductCount(paramMap,true);
	}
	/**
	 * 往(订单商品关系)新增一条记录
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct){
		return ebuyOrderHasTEbuyProductBaseDao.insertEbuyOrderHasTEbuyProduct(ebuyOrderHasTEbuyProduct);
	}
	/**
	 * 批量新增(订单商品关系)
	 * @param ebuyOrderHasTEbuyProductList
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasTEbuyProductBatch(List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductList){
		return ebuyOrderHasTEbuyProductBaseDao.insertEbuyOrderHasTEbuyProductBatch(ebuyOrderHasTEbuyProductList);
	}
	/**
	 * 更新(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct){
		return ebuyOrderHasTEbuyProductBaseDao.updateEbuyOrderHasTEbuyProduct(ebuyOrderHasTEbuyProduct);
	}
	/**
	 * 批量更新(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProductList
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasTEbuyProductBatch(List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductList){
		return ebuyOrderHasTEbuyProductBaseDao.updateEbuyOrderHasTEbuyProductBatch(ebuyOrderHasTEbuyProductList);
	}
	/**
	 * 根据序列号删除(订单商品关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasTEbuyProductLogic(java.math.BigInteger id){
		return ebuyOrderHasTEbuyProductBaseDao.deleteEbuyOrderHasTEbuyProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(订单商品关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasTEbuyProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderHasTEbuyProductBaseDao.deleteEbuyOrderHasTEbuyProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(订单商品关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasTEbuyProduct(java.math.BigInteger id){
//		return ebuyOrderHasTEbuyProductBaseDao.deleteEbuyOrderHasTEbuyProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单商品关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasTEbuyProductBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderHasTEbuyProductBaseDao.deleteEbuyOrderHasTEbuyProductBatch(idList);
//	}
	
}

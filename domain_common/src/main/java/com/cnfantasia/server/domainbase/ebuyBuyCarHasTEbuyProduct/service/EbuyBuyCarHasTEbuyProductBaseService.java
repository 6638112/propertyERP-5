package com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.dao.IEbuyBuyCarHasTEbuyProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyBuyCarHasTEbuyProductBaseService implements IEbuyBuyCarHasTEbuyProductBaseService{
	
	private IEbuyBuyCarHasTEbuyProductBaseDao ebuyBuyCarHasTEbuyProductBaseDao;
	public void setEbuyBuyCarHasTEbuyProductBaseDao(IEbuyBuyCarHasTEbuyProductBaseDao ebuyBuyCarHasTEbuyProductBaseDao) {
		this.ebuyBuyCarHasTEbuyProductBaseDao = ebuyBuyCarHasTEbuyProductBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap){
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByConditionDim(Map<String,Object> paramMap){
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> getEbuyBuyCarHasTEbuyProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyBuyCarHasTEbuyProduct getEbuyBuyCarHasTEbuyProductBySeqId(java.math.BigInteger id){
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyBuyCarHasTEbuyProductCount(Map<String,Object> paramMap){
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyBuyCarHasTEbuyProductCountDim(Map<String,Object> paramMap){
		return ebuyBuyCarHasTEbuyProductBaseDao.selectEbuyBuyCarHasTEbuyProductCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	@Override
	public int insertEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct){
		return ebuyBuyCarHasTEbuyProductBaseDao.insertEbuyBuyCarHasTEbuyProduct(ebuyBuyCarHasTEbuyProduct);
	}
	/**
	 * 批量新增()
	 * @param ebuyBuyCarHasTEbuyProductList
	 * @return
	 */
	@Override
	public int insertEbuyBuyCarHasTEbuyProductBatch(List<EbuyBuyCarHasTEbuyProduct> ebuyBuyCarHasTEbuyProductList){
		return ebuyBuyCarHasTEbuyProductBaseDao.insertEbuyBuyCarHasTEbuyProductBatch(ebuyBuyCarHasTEbuyProductList);
	}
	/**
	 * 更新()信息
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	@Override
	public int updateEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct){
		return ebuyBuyCarHasTEbuyProductBaseDao.updateEbuyBuyCarHasTEbuyProduct(ebuyBuyCarHasTEbuyProduct);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyBuyCarHasTEbuyProductList
	 * @return
	 */
	@Override
	public int updateEbuyBuyCarHasTEbuyProductBatch(List<EbuyBuyCarHasTEbuyProduct> ebuyBuyCarHasTEbuyProductList){
		return ebuyBuyCarHasTEbuyProductBaseDao.updateEbuyBuyCarHasTEbuyProductBatch(ebuyBuyCarHasTEbuyProductList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarHasTEbuyProductLogic(java.math.BigInteger id){
		return ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarHasTEbuyProductLogicBatch(List<java.math.BigInteger> idList){
		return ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCarHasTEbuyProduct(java.math.BigInteger id){
//		return ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCarHasTEbuyProductBatch(List<java.math.BigInteger> idList){
//		return ebuyBuyCarHasTEbuyProductBaseDao.deleteEbuyBuyCarHasTEbuyProductBatch(idList);
//	}
	
}

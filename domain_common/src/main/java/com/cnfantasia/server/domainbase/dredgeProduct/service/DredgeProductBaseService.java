package com.cnfantasia.server.domainbase.dredgeProduct.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeProduct.dao.IDredgeProductBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;

/**
 * 描述:(维修服务商品表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeProductBaseService implements IDredgeProductBaseService{
	
	private IDredgeProductBaseDao dredgeProductBaseDao;
	public void setDredgeProductBaseDao(IDredgeProductBaseDao dredgeProductBaseDao) {
		this.dredgeProductBaseDao = dredgeProductBaseDao;
	}
	/**
	 * 根据条件查询(维修服务商品表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeProduct> getDredgeProductByCondition(Map<String,Object> paramMap){
		return dredgeProductBaseDao.selectDredgeProductByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修服务商品表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeProduct> getDredgeProductByConditionDim(Map<String,Object> paramMap){
		return dredgeProductBaseDao.selectDredgeProductByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修服务商品表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeProduct> getDredgeProductByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeProductBaseDao.selectDredgeProductByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修服务商品表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeProduct> getDredgeProductByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeProductBaseDao.selectDredgeProductByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修服务商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeProduct getDredgeProductBySeqId(java.math.BigInteger id){
		return dredgeProductBaseDao.selectDredgeProductBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeProductCount(Map<String,Object> paramMap){
		return dredgeProductBaseDao.selectDredgeProductCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeProductCountDim(Map<String,Object> paramMap){
		return dredgeProductBaseDao.selectDredgeProductCount(paramMap,true);
	}
	/**
	 * 往(维修服务商品表)新增一条记录
	 * @param dredgeProduct
	 * @return
	 */
	@Override
	public int insertDredgeProduct(DredgeProduct dredgeProduct){
		return dredgeProductBaseDao.insertDredgeProduct(dredgeProduct);
	}
	/**
	 * 批量新增(维修服务商品表)
	 * @param dredgeProductList
	 * @return
	 */
	@Override
	public int insertDredgeProductBatch(List<DredgeProduct> dredgeProductList){
		return dredgeProductBaseDao.insertDredgeProductBatch(dredgeProductList);
	}
	/**
	 * 更新(维修服务商品表)信息
	 * @param dredgeProduct
	 * @return
	 */
	@Override
	public int updateDredgeProduct(DredgeProduct dredgeProduct){
		return dredgeProductBaseDao.updateDredgeProduct(dredgeProduct);
	}
	/**
	 * 批量更新(维修服务商品表)信息
	 * @param dredgeProductList
	 * @return
	 */
	@Override
	public int updateDredgeProductBatch(List<DredgeProduct> dredgeProductList){
		return dredgeProductBaseDao.updateDredgeProductBatch(dredgeProductList);
	}
	/**
	 * 根据序列号删除(维修服务商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductLogic(java.math.BigInteger id){
		return dredgeProductBaseDao.deleteDredgeProductLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修服务商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductLogicBatch(List<java.math.BigInteger> idList){
		return dredgeProductBaseDao.deleteDredgeProductLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修服务商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProduct(java.math.BigInteger id){
//		return dredgeProductBaseDao.deleteDredgeProduct(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProductBatch(List<java.math.BigInteger> idList){
//		return dredgeProductBaseDao.deleteDredgeProductBatch(idList);
//	}
	
}

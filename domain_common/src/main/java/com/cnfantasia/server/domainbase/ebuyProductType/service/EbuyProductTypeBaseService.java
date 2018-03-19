package com.cnfantasia.server.domainbase.ebuyProductType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductType.dao.IEbuyProductTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;

/**
 * 描述:(商品类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductTypeBaseService implements IEbuyProductTypeBaseService{
	
	private IEbuyProductTypeBaseDao ebuyProductTypeBaseDao;
	public void setEbuyProductTypeBaseDao(IEbuyProductTypeBaseDao ebuyProductTypeBaseDao) {
		this.ebuyProductTypeBaseDao = ebuyProductTypeBaseDao;
	}
	/**
	 * 根据条件查询(商品类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductType> getEbuyProductTypeByCondition(Map<String,Object> paramMap){
		return ebuyProductTypeBaseDao.selectEbuyProductTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductType> getEbuyProductTypeByConditionDim(Map<String,Object> paramMap){
		return ebuyProductTypeBaseDao.selectEbuyProductTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductType> getEbuyProductTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductTypeBaseDao.selectEbuyProductTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductType> getEbuyProductTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductTypeBaseDao.selectEbuyProductTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductType getEbuyProductTypeBySeqId(java.math.BigInteger id){
		return ebuyProductTypeBaseDao.selectEbuyProductTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductTypeCount(Map<String,Object> paramMap){
		return ebuyProductTypeBaseDao.selectEbuyProductTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductTypeCountDim(Map<String,Object> paramMap){
		return ebuyProductTypeBaseDao.selectEbuyProductTypeCount(paramMap,true);
	}
	/**
	 * 往(商品类别)新增一条记录
	 * @param ebuyProductType
	 * @return
	 */
	@Override
	public int insertEbuyProductType(EbuyProductType ebuyProductType){
		return ebuyProductTypeBaseDao.insertEbuyProductType(ebuyProductType);
	}
	/**
	 * 批量新增(商品类别)
	 * @param ebuyProductTypeList
	 * @return
	 */
	@Override
	public int insertEbuyProductTypeBatch(List<EbuyProductType> ebuyProductTypeList){
		return ebuyProductTypeBaseDao.insertEbuyProductTypeBatch(ebuyProductTypeList);
	}
	/**
	 * 更新(商品类别)信息
	 * @param ebuyProductType
	 * @return
	 */
	@Override
	public int updateEbuyProductType(EbuyProductType ebuyProductType){
		return ebuyProductTypeBaseDao.updateEbuyProductType(ebuyProductType);
	}
	/**
	 * 批量更新(商品类别)信息
	 * @param ebuyProductTypeList
	 * @return
	 */
	@Override
	public int updateEbuyProductTypeBatch(List<EbuyProductType> ebuyProductTypeList){
		return ebuyProductTypeBaseDao.updateEbuyProductTypeBatch(ebuyProductTypeList);
	}
	/**
	 * 根据序列号删除(商品类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTypeLogic(java.math.BigInteger id){
		return ebuyProductTypeBaseDao.deleteEbuyProductTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTypeLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductTypeBaseDao.deleteEbuyProductTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductType(java.math.BigInteger id){
//		return ebuyProductTypeBaseDao.deleteEbuyProductType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductTypeBatch(List<java.math.BigInteger> idList){
//		return ebuyProductTypeBaseDao.deleteEbuyProductTypeBatch(idList);
//	}
	
}

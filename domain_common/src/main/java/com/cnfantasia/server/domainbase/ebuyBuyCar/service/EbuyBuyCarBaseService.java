package com.cnfantasia.server.domainbase.ebuyBuyCar.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyBuyCar.dao.IEbuyBuyCarBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyBuyCar.entity.EbuyBuyCar;

/**
 * 描述:(购物车) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyBuyCarBaseService implements IEbuyBuyCarBaseService{
	
	private IEbuyBuyCarBaseDao ebuyBuyCarBaseDao;
	public void setEbuyBuyCarBaseDao(IEbuyBuyCarBaseDao ebuyBuyCarBaseDao) {
		this.ebuyBuyCarBaseDao = ebuyBuyCarBaseDao;
	}
	/**
	 * 根据条件查询(购物车)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyBuyCar> getEbuyBuyCarByCondition(Map<String,Object> paramMap){
		return ebuyBuyCarBaseDao.selectEbuyBuyCarByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(购物车)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyBuyCar> getEbuyBuyCarByConditionDim(Map<String,Object> paramMap){
		return ebuyBuyCarBaseDao.selectEbuyBuyCarByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(购物车)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyBuyCar> getEbuyBuyCarByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyBuyCarBaseDao.selectEbuyBuyCarByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(购物车)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyBuyCar> getEbuyBuyCarByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyBuyCarBaseDao.selectEbuyBuyCarByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(购物车)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyBuyCar getEbuyBuyCarBySeqId(java.math.BigInteger id){
		return ebuyBuyCarBaseDao.selectEbuyBuyCarBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(购物车)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyBuyCarCount(Map<String,Object> paramMap){
		return ebuyBuyCarBaseDao.selectEbuyBuyCarCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(购物车)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyBuyCarCountDim(Map<String,Object> paramMap){
		return ebuyBuyCarBaseDao.selectEbuyBuyCarCount(paramMap,true);
	}
	/**
	 * 往(购物车)新增一条记录
	 * @param ebuyBuyCar
	 * @return
	 */
	@Override
	public int insertEbuyBuyCar(EbuyBuyCar ebuyBuyCar){
		return ebuyBuyCarBaseDao.insertEbuyBuyCar(ebuyBuyCar);
	}
	/**
	 * 批量新增(购物车)
	 * @param ebuyBuyCarList
	 * @return
	 */
	@Override
	public int insertEbuyBuyCarBatch(List<EbuyBuyCar> ebuyBuyCarList){
		return ebuyBuyCarBaseDao.insertEbuyBuyCarBatch(ebuyBuyCarList);
	}
	/**
	 * 更新(购物车)信息
	 * @param ebuyBuyCar
	 * @return
	 */
	@Override
	public int updateEbuyBuyCar(EbuyBuyCar ebuyBuyCar){
		return ebuyBuyCarBaseDao.updateEbuyBuyCar(ebuyBuyCar);
	}
	/**
	 * 批量更新(购物车)信息
	 * @param ebuyBuyCarList
	 * @return
	 */
	@Override
	public int updateEbuyBuyCarBatch(List<EbuyBuyCar> ebuyBuyCarList){
		return ebuyBuyCarBaseDao.updateEbuyBuyCarBatch(ebuyBuyCarList);
	}
	/**
	 * 根据序列号删除(购物车)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarLogic(java.math.BigInteger id){
		return ebuyBuyCarBaseDao.deleteEbuyBuyCarLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(购物车)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarLogicBatch(List<java.math.BigInteger> idList){
		return ebuyBuyCarBaseDao.deleteEbuyBuyCarLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(购物车)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCar(java.math.BigInteger id){
//		return ebuyBuyCarBaseDao.deleteEbuyBuyCar(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(购物车)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCarBatch(List<java.math.BigInteger> idList){
//		return ebuyBuyCarBaseDao.deleteEbuyBuyCarBatch(idList);
//	}
	
}

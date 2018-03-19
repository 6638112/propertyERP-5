package com.cnfantasia.server.domainbase.ebuyProductConversionCode.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductConversionCode.dao.IEbuyProductConversionCodeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * 描述:(商品兑换码表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductConversionCodeBaseService implements IEbuyProductConversionCodeBaseService{
	
	private IEbuyProductConversionCodeBaseDao ebuyProductConversionCodeBaseDao;
	public void setEbuyProductConversionCodeBaseDao(IEbuyProductConversionCodeBaseDao ebuyProductConversionCodeBaseDao) {
		this.ebuyProductConversionCodeBaseDao = ebuyProductConversionCodeBaseDao;
	}
	/**
	 * 根据条件查询(商品兑换码表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByCondition(Map<String,Object> paramMap){
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品兑换码表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByConditionDim(Map<String,Object> paramMap){
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品兑换码表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品兑换码表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductConversionCode> getEbuyProductConversionCodeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品兑换码表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductConversionCode getEbuyProductConversionCodeBySeqId(java.math.BigInteger id){
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品兑换码表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductConversionCodeCount(Map<String,Object> paramMap){
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品兑换码表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductConversionCodeCountDim(Map<String,Object> paramMap){
		return ebuyProductConversionCodeBaseDao.selectEbuyProductConversionCodeCount(paramMap,true);
	}
	/**
	 * 往(商品兑换码表)新增一条记录
	 * @param ebuyProductConversionCode
	 * @return
	 */
	@Override
	public int insertEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode){
		return ebuyProductConversionCodeBaseDao.insertEbuyProductConversionCode(ebuyProductConversionCode);
	}
	/**
	 * 批量新增(商品兑换码表)
	 * @param ebuyProductConversionCodeList
	 * @return
	 */
	@Override
	public int insertEbuyProductConversionCodeBatch(List<EbuyProductConversionCode> ebuyProductConversionCodeList){
		return ebuyProductConversionCodeBaseDao.insertEbuyProductConversionCodeBatch(ebuyProductConversionCodeList);
	}
	/**
	 * 更新(商品兑换码表)信息
	 * @param ebuyProductConversionCode
	 * @return
	 */
	@Override
	public int updateEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode){
		return ebuyProductConversionCodeBaseDao.updateEbuyProductConversionCode(ebuyProductConversionCode);
	}
	/**
	 * 批量更新(商品兑换码表)信息
	 * @param ebuyProductConversionCodeList
	 * @return
	 */
	@Override
	public int updateEbuyProductConversionCodeBatch(List<EbuyProductConversionCode> ebuyProductConversionCodeList){
		return ebuyProductConversionCodeBaseDao.updateEbuyProductConversionCodeBatch(ebuyProductConversionCodeList);
	}
	/**
	 * 根据序列号删除(商品兑换码表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductConversionCodeLogic(java.math.BigInteger id){
		return ebuyProductConversionCodeBaseDao.deleteEbuyProductConversionCodeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品兑换码表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductConversionCodeLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductConversionCodeBaseDao.deleteEbuyProductConversionCodeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品兑换码表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductConversionCode(java.math.BigInteger id){
//		return ebuyProductConversionCodeBaseDao.deleteEbuyProductConversionCode(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品兑换码表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductConversionCodeBatch(List<java.math.BigInteger> idList){
//		return ebuyProductConversionCodeBaseDao.deleteEbuyProductConversionCodeBatch(idList);
//	}
	
}

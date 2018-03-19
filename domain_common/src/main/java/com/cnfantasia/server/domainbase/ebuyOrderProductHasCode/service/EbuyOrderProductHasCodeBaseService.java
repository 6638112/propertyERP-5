package com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.dao.IEbuyOrderProductHasCodeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderProductHasCode.entity.EbuyOrderProductHasCode;

/**
 * 描述:(商品订单关系所包含的兑换码) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderProductHasCodeBaseService implements IEbuyOrderProductHasCodeBaseService{
	
	private IEbuyOrderProductHasCodeBaseDao ebuyOrderProductHasCodeBaseDao;
	public void setEbuyOrderProductHasCodeBaseDao(IEbuyOrderProductHasCodeBaseDao ebuyOrderProductHasCodeBaseDao) {
		this.ebuyOrderProductHasCodeBaseDao = ebuyOrderProductHasCodeBaseDao;
	}
	/**
	 * 根据条件查询(商品订单关系所包含的兑换码)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByCondition(Map<String,Object> paramMap){
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(商品订单关系所包含的兑换码)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(商品订单关系所包含的兑换码)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(商品订单关系所包含的兑换码)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderProductHasCode> getEbuyOrderProductHasCodeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(商品订单关系所包含的兑换码)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderProductHasCode getEbuyOrderProductHasCodeBySeqId(java.math.BigInteger id){
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(商品订单关系所包含的兑换码)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderProductHasCodeCount(Map<String,Object> paramMap){
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(商品订单关系所包含的兑换码)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderProductHasCodeCountDim(Map<String,Object> paramMap){
		return ebuyOrderProductHasCodeBaseDao.selectEbuyOrderProductHasCodeCount(paramMap,true);
	}
	/**
	 * 往(商品订单关系所包含的兑换码)新增一条记录
	 * @param ebuyOrderProductHasCode
	 * @return
	 */
	@Override
	public int insertEbuyOrderProductHasCode(EbuyOrderProductHasCode ebuyOrderProductHasCode){
		return ebuyOrderProductHasCodeBaseDao.insertEbuyOrderProductHasCode(ebuyOrderProductHasCode);
	}
	/**
	 * 批量新增(商品订单关系所包含的兑换码)
	 * @param ebuyOrderProductHasCodeList
	 * @return
	 */
	@Override
	public int insertEbuyOrderProductHasCodeBatch(List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList){
		return ebuyOrderProductHasCodeBaseDao.insertEbuyOrderProductHasCodeBatch(ebuyOrderProductHasCodeList);
	}
	/**
	 * 更新(商品订单关系所包含的兑换码)信息
	 * @param ebuyOrderProductHasCode
	 * @return
	 */
	@Override
	public int updateEbuyOrderProductHasCode(EbuyOrderProductHasCode ebuyOrderProductHasCode){
		return ebuyOrderProductHasCodeBaseDao.updateEbuyOrderProductHasCode(ebuyOrderProductHasCode);
	}
	/**
	 * 批量更新(商品订单关系所包含的兑换码)信息
	 * @param ebuyOrderProductHasCodeList
	 * @return
	 */
	@Override
	public int updateEbuyOrderProductHasCodeBatch(List<EbuyOrderProductHasCode> ebuyOrderProductHasCodeList){
		return ebuyOrderProductHasCodeBaseDao.updateEbuyOrderProductHasCodeBatch(ebuyOrderProductHasCodeList);
	}
	/**
	 * 根据序列号删除(商品订单关系所包含的兑换码)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderProductHasCodeLogic(java.math.BigInteger id){
		return ebuyOrderProductHasCodeBaseDao.deleteEbuyOrderProductHasCodeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(商品订单关系所包含的兑换码)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderProductHasCodeLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderProductHasCodeBaseDao.deleteEbuyOrderProductHasCodeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(商品订单关系所包含的兑换码)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderProductHasCode(java.math.BigInteger id){
//		return ebuyOrderProductHasCodeBaseDao.deleteEbuyOrderProductHasCode(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品订单关系所包含的兑换码)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderProductHasCodeBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderProductHasCodeBaseDao.deleteEbuyOrderProductHasCodeBatch(idList);
//	}
	
}

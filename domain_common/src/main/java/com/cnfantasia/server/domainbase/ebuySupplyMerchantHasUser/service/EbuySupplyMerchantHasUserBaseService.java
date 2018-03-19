package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.dao.IEbuySupplyMerchantHasUserBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.entity.EbuySupplyMerchantHasUser;

/**
 * 描述:(用户供应商关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuySupplyMerchantHasUserBaseService implements IEbuySupplyMerchantHasUserBaseService{
	
	private IEbuySupplyMerchantHasUserBaseDao ebuySupplyMerchantHasUserBaseDao;
	public void setEbuySupplyMerchantHasUserBaseDao(IEbuySupplyMerchantHasUserBaseDao ebuySupplyMerchantHasUserBaseDao) {
		this.ebuySupplyMerchantHasUserBaseDao = ebuySupplyMerchantHasUserBaseDao;
	}
	/**
	 * 根据条件查询(用户供应商关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户供应商关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByConditionDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户供应商关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户供应商关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasUser> getEbuySupplyMerchantHasUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户供应商关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantHasUser getEbuySupplyMerchantHasUserBySeqId(java.math.BigInteger id){
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户供应商关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantHasUserCount(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户供应商关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantHasUserCountDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasUserBaseDao.selectEbuySupplyMerchantHasUserCount(paramMap,true);
	}
	/**
	 * 往(用户供应商关系表)新增一条记录
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser){
		return ebuySupplyMerchantHasUserBaseDao.insertEbuySupplyMerchantHasUser(ebuySupplyMerchantHasUser);
	}
	/**
	 * 批量新增(用户供应商关系表)
	 * @param ebuySupplyMerchantHasUserList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasUserBatch(List<EbuySupplyMerchantHasUser> ebuySupplyMerchantHasUserList){
		return ebuySupplyMerchantHasUserBaseDao.insertEbuySupplyMerchantHasUserBatch(ebuySupplyMerchantHasUserList);
	}
	/**
	 * 更新(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser){
		return ebuySupplyMerchantHasUserBaseDao.updateEbuySupplyMerchantHasUser(ebuySupplyMerchantHasUser);
	}
	/**
	 * 批量更新(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUserList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasUserBatch(List<EbuySupplyMerchantHasUser> ebuySupplyMerchantHasUserList){
		return ebuySupplyMerchantHasUserBaseDao.updateEbuySupplyMerchantHasUserBatch(ebuySupplyMerchantHasUserList);
	}
	/**
	 * 根据序列号删除(用户供应商关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasUserLogic(java.math.BigInteger id){
		return ebuySupplyMerchantHasUserBaseDao.deleteEbuySupplyMerchantHasUserLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户供应商关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasUserLogicBatch(List<java.math.BigInteger> idList){
		return ebuySupplyMerchantHasUserBaseDao.deleteEbuySupplyMerchantHasUserLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户供应商关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasUser(java.math.BigInteger id){
//		return ebuySupplyMerchantHasUserBaseDao.deleteEbuySupplyMerchantHasUser(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户供应商关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasUserBatch(List<java.math.BigInteger> idList){
//		return ebuySupplyMerchantHasUserBaseDao.deleteEbuySupplyMerchantHasUserBatch(idList);
//	}
	
}

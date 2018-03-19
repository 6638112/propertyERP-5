package com.cnfantasia.server.domainbase.basedataBank.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.basedataBank.dao.IBasedataBankBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.basedataBank.entity.BasedataBank;

/**
 * 描述:(银行基础资料) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BasedataBankBaseService implements IBasedataBankBaseService{
	
	private IBasedataBankBaseDao basedataBankBaseDao;
	public void setBasedataBankBaseDao(IBasedataBankBaseDao basedataBankBaseDao) {
		this.basedataBankBaseDao = basedataBankBaseDao;
	}
	/**
	 * 根据条件查询(银行基础资料)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BasedataBank> getBasedataBankByCondition(Map<String,Object> paramMap){
		return basedataBankBaseDao.selectBasedataBankByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(银行基础资料)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BasedataBank> getBasedataBankByConditionDim(Map<String,Object> paramMap){
		return basedataBankBaseDao.selectBasedataBankByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(银行基础资料)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BasedataBank> getBasedataBankByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return basedataBankBaseDao.selectBasedataBankByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(银行基础资料)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BasedataBank> getBasedataBankByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return basedataBankBaseDao.selectBasedataBankByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(银行基础资料)信息
	 * @param id
	 * @return
	 */
	@Override
	public BasedataBank getBasedataBankBySeqId(java.math.BigInteger id){
		return basedataBankBaseDao.selectBasedataBankBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(银行基础资料)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBasedataBankCount(Map<String,Object> paramMap){
		return basedataBankBaseDao.selectBasedataBankCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(银行基础资料)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBasedataBankCountDim(Map<String,Object> paramMap){
		return basedataBankBaseDao.selectBasedataBankCount(paramMap,true);
	}
	/**
	 * 往(银行基础资料)新增一条记录
	 * @param basedataBank
	 * @return
	 */
	@Override
	public int insertBasedataBank(BasedataBank basedataBank){
		return basedataBankBaseDao.insertBasedataBank(basedataBank);
	}
	/**
	 * 批量新增(银行基础资料)
	 * @param basedataBankList
	 * @return
	 */
	@Override
	public int insertBasedataBankBatch(List<BasedataBank> basedataBankList){
		return basedataBankBaseDao.insertBasedataBankBatch(basedataBankList);
	}
	/**
	 * 更新(银行基础资料)信息
	 * @param basedataBank
	 * @return
	 */
	@Override
	public int updateBasedataBank(BasedataBank basedataBank){
		return basedataBankBaseDao.updateBasedataBank(basedataBank);
	}
	/**
	 * 批量更新(银行基础资料)信息
	 * @param basedataBankList
	 * @return
	 */
	@Override
	public int updateBasedataBankBatch(List<BasedataBank> basedataBankList){
		return basedataBankBaseDao.updateBasedataBankBatch(basedataBankList);
	}
	/**
	 * 根据序列号删除(银行基础资料)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteBasedataBankLogic(java.math.BigInteger id){
		return basedataBankBaseDao.deleteBasedataBankLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(银行基础资料)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteBasedataBankLogicBatch(List<java.math.BigInteger> idList){
		return basedataBankBaseDao.deleteBasedataBankLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(银行基础资料)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBasedataBank(java.math.BigInteger id){
//		return basedataBankBaseDao.deleteBasedataBank(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(银行基础资料)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBasedataBankBatch(List<java.math.BigInteger> idList){
//		return basedataBankBaseDao.deleteBasedataBankBatch(idList);
//	}
	
}

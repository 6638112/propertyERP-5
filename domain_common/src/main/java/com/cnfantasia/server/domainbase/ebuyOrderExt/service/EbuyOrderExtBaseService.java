package com.cnfantasia.server.domainbase.ebuyOrderExt.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrderExt.dao.IEbuyOrderExtBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderExt.entity.EbuyOrderExt;

/**
 * 描述:(订单扩展表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderExtBaseService implements IEbuyOrderExtBaseService{
	
	private IEbuyOrderExtBaseDao ebuyOrderExtBaseDao;
	public void setEbuyOrderExtBaseDao(IEbuyOrderExtBaseDao ebuyOrderExtBaseDao) {
		this.ebuyOrderExtBaseDao = ebuyOrderExtBaseDao;
	}
	/**
	 * 根据条件查询(订单扩展表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderExt> getEbuyOrderExtByCondition(Map<String,Object> paramMap){
		return ebuyOrderExtBaseDao.selectEbuyOrderExtByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(订单扩展表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrderExt> getEbuyOrderExtByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderExtBaseDao.selectEbuyOrderExtByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(订单扩展表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderExt> getEbuyOrderExtByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderExtBaseDao.selectEbuyOrderExtByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(订单扩展表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrderExt> getEbuyOrderExtByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderExtBaseDao.selectEbuyOrderExtByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(订单扩展表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderExt getEbuyOrderExtBySeqId(java.math.BigInteger id){
		return ebuyOrderExtBaseDao.selectEbuyOrderExtBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(订单扩展表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderExtCount(Map<String,Object> paramMap){
		return ebuyOrderExtBaseDao.selectEbuyOrderExtCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(订单扩展表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderExtCountDim(Map<String,Object> paramMap){
		return ebuyOrderExtBaseDao.selectEbuyOrderExtCount(paramMap,true);
	}
	/**
	 * 往(订单扩展表)新增一条记录
	 * @param ebuyOrderExt
	 * @return
	 */
	@Override
	public int insertEbuyOrderExt(EbuyOrderExt ebuyOrderExt){
		return ebuyOrderExtBaseDao.insertEbuyOrderExt(ebuyOrderExt);
	}
	/**
	 * 批量新增(订单扩展表)
	 * @param ebuyOrderExtList
	 * @return
	 */
	@Override
	public int insertEbuyOrderExtBatch(List<EbuyOrderExt> ebuyOrderExtList){
		return ebuyOrderExtBaseDao.insertEbuyOrderExtBatch(ebuyOrderExtList);
	}
	/**
	 * 更新(订单扩展表)信息
	 * @param ebuyOrderExt
	 * @return
	 */
	@Override
	public int updateEbuyOrderExt(EbuyOrderExt ebuyOrderExt){
		return ebuyOrderExtBaseDao.updateEbuyOrderExt(ebuyOrderExt);
	}
	/**
	 * 批量更新(订单扩展表)信息
	 * @param ebuyOrderExtList
	 * @return
	 */
	@Override
	public int updateEbuyOrderExtBatch(List<EbuyOrderExt> ebuyOrderExtList){
		return ebuyOrderExtBaseDao.updateEbuyOrderExtBatch(ebuyOrderExtList);
	}
	/**
	 * 根据序列号删除(订单扩展表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderExtLogic(java.math.BigInteger id){
		return ebuyOrderExtBaseDao.deleteEbuyOrderExtLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(订单扩展表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderExtLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderExtBaseDao.deleteEbuyOrderExtLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(订单扩展表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderExt(java.math.BigInteger id){
//		return ebuyOrderExtBaseDao.deleteEbuyOrderExt(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单扩展表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderExtBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderExtBaseDao.deleteEbuyOrderExtBatch(idList);
//	}
	
}

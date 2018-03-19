package com.cnfantasia.server.domainbase.ebuyOrder.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * 描述:(订单表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyOrderBaseService implements IEbuyOrderBaseService{
	
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}
	/**
	 * 根据条件查询(订单表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrder> getEbuyOrderByCondition(Map<String,Object> paramMap){
		return ebuyOrderBaseDao.selectEbuyOrderByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(订单表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyOrder> getEbuyOrderByConditionDim(Map<String,Object> paramMap){
		return ebuyOrderBaseDao.selectEbuyOrderByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(订单表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrder> getEbuyOrderByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderBaseDao.selectEbuyOrderByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(订单表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyOrder> getEbuyOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyOrderBaseDao.selectEbuyOrderByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(订单表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrder getEbuyOrderBySeqId(java.math.BigInteger id){
		return ebuyOrderBaseDao.selectEbuyOrderBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(订单表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderCount(Map<String,Object> paramMap){
		return ebuyOrderBaseDao.selectEbuyOrderCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(订单表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyOrderCountDim(Map<String,Object> paramMap){
		return ebuyOrderBaseDao.selectEbuyOrderCount(paramMap,true);
	}
	/**
	 * 往(订单表)新增一条记录
	 * @param ebuyOrder
	 * @return
	 */
	@Override
	public int insertEbuyOrder(EbuyOrder ebuyOrder){
		return ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);
	}
	/**
	 * 批量新增(订单表)
	 * @param ebuyOrderList
	 * @return
	 */
	@Override
	public int insertEbuyOrderBatch(List<EbuyOrder> ebuyOrderList){
		return ebuyOrderBaseDao.insertEbuyOrderBatch(ebuyOrderList);
	}
	/**
	 * 更新(订单表)信息
	 * @param ebuyOrder
	 * @return
	 */
	@Override
	public int updateEbuyOrder(EbuyOrder ebuyOrder){
		return ebuyOrderBaseDao.updateEbuyOrder(ebuyOrder);
	}
	/**
	 * 批量更新(订单表)信息
	 * @param ebuyOrderList
	 * @return
	 */
	@Override
	public int updateEbuyOrderBatch(List<EbuyOrder> ebuyOrderList){
		return ebuyOrderBaseDao.updateEbuyOrderBatch(ebuyOrderList);
	}
	/**
	 * 根据序列号删除(订单表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderLogic(java.math.BigInteger id){
		return ebuyOrderBaseDao.deleteEbuyOrderLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(订单表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderLogicBatch(List<java.math.BigInteger> idList){
		return ebuyOrderBaseDao.deleteEbuyOrderLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(订单表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrder(java.math.BigInteger id){
//		return ebuyOrderBaseDao.deleteEbuyOrder(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderBatch(List<java.math.BigInteger> idList){
//		return ebuyOrderBaseDao.deleteEbuyOrderBatch(idList);
//	}
	
}

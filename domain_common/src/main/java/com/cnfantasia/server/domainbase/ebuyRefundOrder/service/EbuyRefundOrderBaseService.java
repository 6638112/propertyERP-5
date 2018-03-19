package com.cnfantasia.server.domainbase.ebuyRefundOrder.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyRefundOrder.dao.IEbuyRefundOrderBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;

/**
 * 描述:(退货订单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyRefundOrderBaseService implements IEbuyRefundOrderBaseService{
	
	private IEbuyRefundOrderBaseDao ebuyRefundOrderBaseDao;
	public void setEbuyRefundOrderBaseDao(IEbuyRefundOrderBaseDao ebuyRefundOrderBaseDao) {
		this.ebuyRefundOrderBaseDao = ebuyRefundOrderBaseDao;
	}
	/**
	 * 根据条件查询(退货订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyRefundOrder> getEbuyRefundOrderByCondition(Map<String,Object> paramMap){
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(退货订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyRefundOrder> getEbuyRefundOrderByConditionDim(Map<String,Object> paramMap){
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(退货订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyRefundOrder> getEbuyRefundOrderByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(退货订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyRefundOrder> getEbuyRefundOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(退货订单)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyRefundOrder getEbuyRefundOrderBySeqId(java.math.BigInteger id){
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(退货订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyRefundOrderCount(Map<String,Object> paramMap){
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(退货订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyRefundOrderCountDim(Map<String,Object> paramMap){
		return ebuyRefundOrderBaseDao.selectEbuyRefundOrderCount(paramMap,true);
	}
	/**
	 * 往(退货订单)新增一条记录
	 * @param ebuyRefundOrder
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrder(EbuyRefundOrder ebuyRefundOrder){
		return ebuyRefundOrderBaseDao.insertEbuyRefundOrder(ebuyRefundOrder);
	}
	/**
	 * 批量新增(退货订单)
	 * @param ebuyRefundOrderList
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrderBatch(List<EbuyRefundOrder> ebuyRefundOrderList){
		return ebuyRefundOrderBaseDao.insertEbuyRefundOrderBatch(ebuyRefundOrderList);
	}
	/**
	 * 更新(退货订单)信息
	 * @param ebuyRefundOrder
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrder(EbuyRefundOrder ebuyRefundOrder){
		return ebuyRefundOrderBaseDao.updateEbuyRefundOrder(ebuyRefundOrder);
	}
	/**
	 * 批量更新(退货订单)信息
	 * @param ebuyRefundOrderList
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrderBatch(List<EbuyRefundOrder> ebuyRefundOrderList){
		return ebuyRefundOrderBaseDao.updateEbuyRefundOrderBatch(ebuyRefundOrderList);
	}
	/**
	 * 根据序列号删除(退货订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderLogic(java.math.BigInteger id){
		return ebuyRefundOrderBaseDao.deleteEbuyRefundOrderLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(退货订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderLogicBatch(List<java.math.BigInteger> idList){
		return ebuyRefundOrderBaseDao.deleteEbuyRefundOrderLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(退货订单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrder(java.math.BigInteger id){
//		return ebuyRefundOrderBaseDao.deleteEbuyRefundOrder(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(退货订单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrderBatch(List<java.math.BigInteger> idList){
//		return ebuyRefundOrderBaseDao.deleteEbuyRefundOrderBatch(idList);
//	}
	
}

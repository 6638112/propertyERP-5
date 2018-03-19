package com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao.IEbuyDeliveryOrderBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;

/**
 * 描述:(送货单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyDeliveryOrderBaseService implements IEbuyDeliveryOrderBaseService{
	
	private IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao;
	public void setEbuyDeliveryOrderBaseDao(IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao) {
		this.ebuyDeliveryOrderBaseDao = ebuyDeliveryOrderBaseDao;
	}
	/**
	 * 根据条件查询(送货单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByCondition(Map<String,Object> paramMap){
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(送货单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByConditionDim(Map<String,Object> paramMap){
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(送货单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(送货单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrder> getEbuyDeliveryOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(送货单)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryOrder getEbuyDeliveryOrderBySeqId(java.math.BigInteger id){
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(送货单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryOrderCount(Map<String,Object> paramMap){
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(送货单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryOrderCountDim(Map<String,Object> paramMap){
		return ebuyDeliveryOrderBaseDao.selectEbuyDeliveryOrderCount(paramMap,true);
	}
	/**
	 * 往(送货单)新增一条记录
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder){
		return ebuyDeliveryOrderBaseDao.insertEbuyDeliveryOrder(ebuyDeliveryOrder);
	}
	/**
	 * 批量新增(送货单)
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList){
		return ebuyDeliveryOrderBaseDao.insertEbuyDeliveryOrderBatch(ebuyDeliveryOrderList);
	}
	/**
	 * 更新(送货单)信息
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder){
		return ebuyDeliveryOrderBaseDao.updateEbuyDeliveryOrder(ebuyDeliveryOrder);
	}
	/**
	 * 批量更新(送货单)信息
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList){
		return ebuyDeliveryOrderBaseDao.updateEbuyDeliveryOrderBatch(ebuyDeliveryOrderList);
	}
	/**
	 * 根据序列号删除(送货单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderLogic(java.math.BigInteger id){
		return ebuyDeliveryOrderBaseDao.deleteEbuyDeliveryOrderLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(送货单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderLogicBatch(List<java.math.BigInteger> idList){
		return ebuyDeliveryOrderBaseDao.deleteEbuyDeliveryOrderLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(送货单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrder(java.math.BigInteger id){
//		return ebuyDeliveryOrderBaseDao.deleteEbuyDeliveryOrder(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(送货单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderBatch(List<java.math.BigInteger> idList){
//		return ebuyDeliveryOrderBaseDao.deleteEbuyDeliveryOrderBatch(idList);
//	}
	
}

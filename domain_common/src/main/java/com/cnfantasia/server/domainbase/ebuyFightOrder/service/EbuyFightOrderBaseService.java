package com.cnfantasia.server.domainbase.ebuyFightOrder.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyFightOrder.dao.IEbuyFightOrderBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;

/**
 * 描述:(拼购详情订单) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyFightOrderBaseService implements IEbuyFightOrderBaseService{
	
	private IEbuyFightOrderBaseDao ebuyFightOrderBaseDao;
	public void setEbuyFightOrderBaseDao(IEbuyFightOrderBaseDao ebuyFightOrderBaseDao) {
		this.ebuyFightOrderBaseDao = ebuyFightOrderBaseDao;
	}
	/**
	 * 根据条件查询(拼购详情订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyFightOrder> getEbuyFightOrderByCondition(Map<String,Object> paramMap){
		return ebuyFightOrderBaseDao.selectEbuyFightOrderByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(拼购详情订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyFightOrder> getEbuyFightOrderByConditionDim(Map<String,Object> paramMap){
		return ebuyFightOrderBaseDao.selectEbuyFightOrderByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(拼购详情订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyFightOrder> getEbuyFightOrderByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyFightOrderBaseDao.selectEbuyFightOrderByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(拼购详情订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyFightOrder> getEbuyFightOrderByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyFightOrderBaseDao.selectEbuyFightOrderByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(拼购详情订单)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyFightOrder getEbuyFightOrderBySeqId(java.math.BigInteger id){
		return ebuyFightOrderBaseDao.selectEbuyFightOrderBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(拼购详情订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyFightOrderCount(Map<String,Object> paramMap){
		return ebuyFightOrderBaseDao.selectEbuyFightOrderCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(拼购详情订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyFightOrderCountDim(Map<String,Object> paramMap){
		return ebuyFightOrderBaseDao.selectEbuyFightOrderCount(paramMap,true);
	}
	/**
	 * 往(拼购详情订单)新增一条记录
	 * @param ebuyFightOrder
	 * @return
	 */
	@Override
	public int insertEbuyFightOrder(EbuyFightOrder ebuyFightOrder){
		return ebuyFightOrderBaseDao.insertEbuyFightOrder(ebuyFightOrder);
	}
	/**
	 * 批量新增(拼购详情订单)
	 * @param ebuyFightOrderList
	 * @return
	 */
	@Override
	public int insertEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList){
		return ebuyFightOrderBaseDao.insertEbuyFightOrderBatch(ebuyFightOrderList);
	}
	/**
	 * 更新(拼购详情订单)信息
	 * @param ebuyFightOrder
	 * @return
	 */
	@Override
	public int updateEbuyFightOrder(EbuyFightOrder ebuyFightOrder){
		return ebuyFightOrderBaseDao.updateEbuyFightOrder(ebuyFightOrder);
	}
	/**
	 * 批量更新(拼购详情订单)信息
	 * @param ebuyFightOrderList
	 * @return
	 */
	@Override
	public int updateEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList){
		return ebuyFightOrderBaseDao.updateEbuyFightOrderBatch(ebuyFightOrderList);
	}
	/**
	 * 根据序列号删除(拼购详情订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightOrderLogic(java.math.BigInteger id){
		return ebuyFightOrderBaseDao.deleteEbuyFightOrderLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(拼购详情订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightOrderLogicBatch(List<java.math.BigInteger> idList){
		return ebuyFightOrderBaseDao.deleteEbuyFightOrderLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(拼购详情订单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightOrder(java.math.BigInteger id){
//		return ebuyFightOrderBaseDao.deleteEbuyFightOrder(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼购详情订单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightOrderBatch(List<java.math.BigInteger> idList){
//		return ebuyFightOrderBaseDao.deleteEbuyFightOrderBatch(idList);
//	}
	
}

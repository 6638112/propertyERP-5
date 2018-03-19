package com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.dao.IEbuyDeliveryOrderCommentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;

/**
 * 描述:(运单备注) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyDeliveryOrderCommentBaseService implements IEbuyDeliveryOrderCommentBaseService{
	
	private IEbuyDeliveryOrderCommentBaseDao ebuyDeliveryOrderCommentBaseDao;
	public void setEbuyDeliveryOrderCommentBaseDao(IEbuyDeliveryOrderCommentBaseDao ebuyDeliveryOrderCommentBaseDao) {
		this.ebuyDeliveryOrderCommentBaseDao = ebuyDeliveryOrderCommentBaseDao;
	}
	/**
	 * 根据条件查询(运单备注)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap){
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(运单备注)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByConditionDim(Map<String,Object> paramMap){
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(运单备注)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(运单备注)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> getEbuyDeliveryOrderCommentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(运单备注)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryOrderComment getEbuyDeliveryOrderCommentBySeqId(java.math.BigInteger id){
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(运单备注)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryOrderCommentCount(Map<String,Object> paramMap){
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(运单备注)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryOrderCommentCountDim(Map<String,Object> paramMap){
		return ebuyDeliveryOrderCommentBaseDao.selectEbuyDeliveryOrderCommentCount(paramMap,true);
	}
	/**
	 * 往(运单备注)新增一条记录
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment){
		return ebuyDeliveryOrderCommentBaseDao.insertEbuyDeliveryOrderComment(ebuyDeliveryOrderComment);
	}
	/**
	 * 批量新增(运单备注)
	 * @param ebuyDeliveryOrderCommentList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderCommentBatch(List<EbuyDeliveryOrderComment> ebuyDeliveryOrderCommentList){
		return ebuyDeliveryOrderCommentBaseDao.insertEbuyDeliveryOrderCommentBatch(ebuyDeliveryOrderCommentList);
	}
	/**
	 * 更新(运单备注)信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment){
		return ebuyDeliveryOrderCommentBaseDao.updateEbuyDeliveryOrderComment(ebuyDeliveryOrderComment);
	}
	/**
	 * 批量更新(运单备注)信息
	 * @param ebuyDeliveryOrderCommentList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderCommentBatch(List<EbuyDeliveryOrderComment> ebuyDeliveryOrderCommentList){
		return ebuyDeliveryOrderCommentBaseDao.updateEbuyDeliveryOrderCommentBatch(ebuyDeliveryOrderCommentList);
	}
	/**
	 * 根据序列号删除(运单备注)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyDeliveryOrderCommentLogic(java.math.BigInteger id){
		return ebuyDeliveryOrderCommentBaseDao.deleteEbuyDeliveryOrderCommentLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(运单备注)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyDeliveryOrderCommentLogicBatch(List<java.math.BigInteger> idList){
		return ebuyDeliveryOrderCommentBaseDao.deleteEbuyDeliveryOrderCommentLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(运单备注)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderComment(java.math.BigInteger id){
//		return ebuyDeliveryOrderCommentBaseDao.deleteEbuyDeliveryOrderComment(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运单备注)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderCommentBatch(List<java.math.BigInteger> idList){
//		return ebuyDeliveryOrderCommentBaseDao.deleteEbuyDeliveryOrderCommentBatch(idList);
//	}
	
}

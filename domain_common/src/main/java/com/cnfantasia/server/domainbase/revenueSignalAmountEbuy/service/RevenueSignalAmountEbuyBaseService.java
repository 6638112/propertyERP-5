package com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.dao.IRevenueSignalAmountEbuyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;

/**
 * 描述:(楼下店收益明细补充表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RevenueSignalAmountEbuyBaseService implements IRevenueSignalAmountEbuyBaseService{
	
	private IRevenueSignalAmountEbuyBaseDao revenueSignalAmountEbuyBaseDao;
	public void setRevenueSignalAmountEbuyBaseDao(IRevenueSignalAmountEbuyBaseDao revenueSignalAmountEbuyBaseDao) {
		this.revenueSignalAmountEbuyBaseDao = revenueSignalAmountEbuyBaseDao;
	}
	/**
	 * 根据条件查询(楼下店收益明细补充表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap){
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(楼下店收益明细补充表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByConditionDim(Map<String,Object> paramMap){
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(楼下店收益明细补充表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(楼下店收益明细补充表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RevenueSignalAmountEbuy> getRevenueSignalAmountEbuyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(楼下店收益明细补充表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueSignalAmountEbuy getRevenueSignalAmountEbuyBySeqId(java.math.BigInteger id){
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(楼下店收益明细补充表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueSignalAmountEbuyCount(Map<String,Object> paramMap){
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(楼下店收益明细补充表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRevenueSignalAmountEbuyCountDim(Map<String,Object> paramMap){
		return revenueSignalAmountEbuyBaseDao.selectRevenueSignalAmountEbuyCount(paramMap,true);
	}
	/**
	 * 往(楼下店收益明细补充表)新增一条记录
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy){
		return revenueSignalAmountEbuyBaseDao.insertRevenueSignalAmountEbuy(revenueSignalAmountEbuy);
	}
	/**
	 * 批量新增(楼下店收益明细补充表)
	 * @param revenueSignalAmountEbuyList
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmountEbuyBatch(List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList){
		return revenueSignalAmountEbuyBaseDao.insertRevenueSignalAmountEbuyBatch(revenueSignalAmountEbuyList);
	}
	/**
	 * 更新(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy){
		return revenueSignalAmountEbuyBaseDao.updateRevenueSignalAmountEbuy(revenueSignalAmountEbuy);
	}
	/**
	 * 批量更新(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuyList
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmountEbuyBatch(List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList){
		return revenueSignalAmountEbuyBaseDao.updateRevenueSignalAmountEbuyBatch(revenueSignalAmountEbuyList);
	}
	/**
	 * 根据序列号删除(楼下店收益明细补充表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountEbuyLogic(java.math.BigInteger id){
		return revenueSignalAmountEbuyBaseDao.deleteRevenueSignalAmountEbuyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(楼下店收益明细补充表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountEbuyLogicBatch(List<java.math.BigInteger> idList){
		return revenueSignalAmountEbuyBaseDao.deleteRevenueSignalAmountEbuyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(楼下店收益明细补充表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmountEbuy(java.math.BigInteger id){
//		return revenueSignalAmountEbuyBaseDao.deleteRevenueSignalAmountEbuy(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(楼下店收益明细补充表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmountEbuyBatch(List<java.math.BigInteger> idList){
//		return revenueSignalAmountEbuyBaseDao.deleteRevenueSignalAmountEbuyBatch(idList);
//	}
	
}

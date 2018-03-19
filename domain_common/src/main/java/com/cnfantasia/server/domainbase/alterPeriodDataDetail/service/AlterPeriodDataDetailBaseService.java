package com.cnfantasia.server.domainbase.alterPeriodDataDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.alterPeriodDataDetail.dao.IAlterPeriodDataDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;

/**
 * 描述:(选择周期数据详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AlterPeriodDataDetailBaseService implements IAlterPeriodDataDetailBaseService{
	
	private IAlterPeriodDataDetailBaseDao alterPeriodDataDetailBaseDao;
	public void setAlterPeriodDataDetailBaseDao(IAlterPeriodDataDetailBaseDao alterPeriodDataDetailBaseDao) {
		this.alterPeriodDataDetailBaseDao = alterPeriodDataDetailBaseDao;
	}
	/**
	 * 根据条件查询(选择周期数据详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByCondition(Map<String,Object> paramMap){
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(选择周期数据详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByConditionDim(Map<String,Object> paramMap){
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(选择周期数据详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(选择周期数据详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodDataDetail> getAlterPeriodDataDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(选择周期数据详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterPeriodDataDetail getAlterPeriodDataDetailBySeqId(java.math.BigInteger id){
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(选择周期数据详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodDataDetailCount(Map<String,Object> paramMap){
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(选择周期数据详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodDataDetailCountDim(Map<String,Object> paramMap){
		return alterPeriodDataDetailBaseDao.selectAlterPeriodDataDetailCount(paramMap,true);
	}
	/**
	 * 往(选择周期数据详情)新增一条记录
	 * @param alterPeriodDataDetail
	 * @return
	 */
	@Override
	public int insertAlterPeriodDataDetail(AlterPeriodDataDetail alterPeriodDataDetail){
		return alterPeriodDataDetailBaseDao.insertAlterPeriodDataDetail(alterPeriodDataDetail);
	}
	/**
	 * 批量新增(选择周期数据详情)
	 * @param alterPeriodDataDetailList
	 * @return
	 */
	@Override
	public int insertAlterPeriodDataDetailBatch(List<AlterPeriodDataDetail> alterPeriodDataDetailList){
		return alterPeriodDataDetailBaseDao.insertAlterPeriodDataDetailBatch(alterPeriodDataDetailList);
	}
	/**
	 * 更新(选择周期数据详情)信息
	 * @param alterPeriodDataDetail
	 * @return
	 */
	@Override
	public int updateAlterPeriodDataDetail(AlterPeriodDataDetail alterPeriodDataDetail){
		return alterPeriodDataDetailBaseDao.updateAlterPeriodDataDetail(alterPeriodDataDetail);
	}
	/**
	 * 批量更新(选择周期数据详情)信息
	 * @param alterPeriodDataDetailList
	 * @return
	 */
	@Override
	public int updateAlterPeriodDataDetailBatch(List<AlterPeriodDataDetail> alterPeriodDataDetailList){
		return alterPeriodDataDetailBaseDao.updateAlterPeriodDataDetailBatch(alterPeriodDataDetailList);
	}
	/**
	 * 根据序列号删除(选择周期数据详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodDataDetailLogic(java.math.BigInteger id){
		return alterPeriodDataDetailBaseDao.deleteAlterPeriodDataDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(选择周期数据详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodDataDetailLogicBatch(List<java.math.BigInteger> idList){
		return alterPeriodDataDetailBaseDao.deleteAlterPeriodDataDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(选择周期数据详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodDataDetail(java.math.BigInteger id){
//		return alterPeriodDataDetailBaseDao.deleteAlterPeriodDataDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(选择周期数据详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodDataDetailBatch(List<java.math.BigInteger> idList){
//		return alterPeriodDataDetailBaseDao.deleteAlterPeriodDataDetailBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.alterPeriodPrefer.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.alterPeriodPrefer.dao.IAlterPeriodPreferBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodPrefer.entity.AlterPeriodPrefer;

/**
 * 描述:(选择周期缴费优惠表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AlterPeriodPreferBaseService implements IAlterPeriodPreferBaseService{
	
	private IAlterPeriodPreferBaseDao alterPeriodPreferBaseDao;
	public void setAlterPeriodPreferBaseDao(IAlterPeriodPreferBaseDao alterPeriodPreferBaseDao) {
		this.alterPeriodPreferBaseDao = alterPeriodPreferBaseDao;
	}
	/**
	 * 根据条件查询(选择周期缴费优惠表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodPrefer> getAlterPeriodPreferByCondition(Map<String,Object> paramMap){
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(选择周期缴费优惠表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AlterPeriodPrefer> getAlterPeriodPreferByConditionDim(Map<String,Object> paramMap){
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(选择周期缴费优惠表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodPrefer> getAlterPeriodPreferByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(选择周期缴费优惠表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AlterPeriodPrefer> getAlterPeriodPreferByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(选择周期缴费优惠表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterPeriodPrefer getAlterPeriodPreferBySeqId(java.math.BigInteger id){
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(选择周期缴费优惠表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodPreferCount(Map<String,Object> paramMap){
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(选择周期缴费优惠表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAlterPeriodPreferCountDim(Map<String,Object> paramMap){
		return alterPeriodPreferBaseDao.selectAlterPeriodPreferCount(paramMap,true);
	}
	/**
	 * 往(选择周期缴费优惠表)新增一条记录
	 * @param alterPeriodPrefer
	 * @return
	 */
	@Override
	public int insertAlterPeriodPrefer(AlterPeriodPrefer alterPeriodPrefer){
		return alterPeriodPreferBaseDao.insertAlterPeriodPrefer(alterPeriodPrefer);
	}
	/**
	 * 批量新增(选择周期缴费优惠表)
	 * @param alterPeriodPreferList
	 * @return
	 */
	@Override
	public int insertAlterPeriodPreferBatch(List<AlterPeriodPrefer> alterPeriodPreferList){
		return alterPeriodPreferBaseDao.insertAlterPeriodPreferBatch(alterPeriodPreferList);
	}
	/**
	 * 更新(选择周期缴费优惠表)信息
	 * @param alterPeriodPrefer
	 * @return
	 */
	@Override
	public int updateAlterPeriodPrefer(AlterPeriodPrefer alterPeriodPrefer){
		return alterPeriodPreferBaseDao.updateAlterPeriodPrefer(alterPeriodPrefer);
	}
	/**
	 * 批量更新(选择周期缴费优惠表)信息
	 * @param alterPeriodPreferList
	 * @return
	 */
	@Override
	public int updateAlterPeriodPreferBatch(List<AlterPeriodPrefer> alterPeriodPreferList){
		return alterPeriodPreferBaseDao.updateAlterPeriodPreferBatch(alterPeriodPreferList);
	}
	/**
	 * 根据序列号删除(选择周期缴费优惠表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodPreferLogic(java.math.BigInteger id){
		return alterPeriodPreferBaseDao.deleteAlterPeriodPreferLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(选择周期缴费优惠表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodPreferLogicBatch(List<java.math.BigInteger> idList){
		return alterPeriodPreferBaseDao.deleteAlterPeriodPreferLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(选择周期缴费优惠表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodPrefer(java.math.BigInteger id){
//		return alterPeriodPreferBaseDao.deleteAlterPeriodPrefer(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(选择周期缴费优惠表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodPreferBatch(List<java.math.BigInteger> idList){
//		return alterPeriodPreferBaseDao.deleteAlterPeriodPreferBatch(idList);
//	}
	
}

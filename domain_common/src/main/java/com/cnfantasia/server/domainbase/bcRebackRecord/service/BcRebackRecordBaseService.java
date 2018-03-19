package com.cnfantasia.server.domainbase.bcRebackRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcRebackRecord.dao.IBcRebackRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcRebackRecord.entity.BcRebackRecord;

/**
 * 描述:(回盘记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcRebackRecordBaseService implements IBcRebackRecordBaseService{
	
	private IBcRebackRecordBaseDao bcRebackRecordBaseDao;
	public void setBcRebackRecordBaseDao(IBcRebackRecordBaseDao bcRebackRecordBaseDao) {
		this.bcRebackRecordBaseDao = bcRebackRecordBaseDao;
	}
	/**
	 * 根据条件查询(回盘记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcRebackRecord> getBcRebackRecordByCondition(Map<String,Object> paramMap){
		return bcRebackRecordBaseDao.selectBcRebackRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(回盘记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcRebackRecord> getBcRebackRecordByConditionDim(Map<String,Object> paramMap){
		return bcRebackRecordBaseDao.selectBcRebackRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(回盘记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcRebackRecord> getBcRebackRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcRebackRecordBaseDao.selectBcRebackRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(回盘记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcRebackRecord> getBcRebackRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcRebackRecordBaseDao.selectBcRebackRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(回盘记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcRebackRecord getBcRebackRecordBySeqId(java.math.BigInteger id){
		return bcRebackRecordBaseDao.selectBcRebackRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(回盘记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcRebackRecordCount(Map<String,Object> paramMap){
		return bcRebackRecordBaseDao.selectBcRebackRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(回盘记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcRebackRecordCountDim(Map<String,Object> paramMap){
		return bcRebackRecordBaseDao.selectBcRebackRecordCount(paramMap,true);
	}
	/**
	 * 往(回盘记录)新增一条记录
	 * @param bcRebackRecord
	 * @return
	 */
	@Override
	public int insertBcRebackRecord(BcRebackRecord bcRebackRecord){
		return bcRebackRecordBaseDao.insertBcRebackRecord(bcRebackRecord);
	}
	/**
	 * 批量新增(回盘记录)
	 * @param bcRebackRecordList
	 * @return
	 */
	@Override
	public int insertBcRebackRecordBatch(List<BcRebackRecord> bcRebackRecordList){
		return bcRebackRecordBaseDao.insertBcRebackRecordBatch(bcRebackRecordList);
	}
	/**
	 * 更新(回盘记录)信息
	 * @param bcRebackRecord
	 * @return
	 */
	@Override
	public int updateBcRebackRecord(BcRebackRecord bcRebackRecord){
		return bcRebackRecordBaseDao.updateBcRebackRecord(bcRebackRecord);
	}
	/**
	 * 批量更新(回盘记录)信息
	 * @param bcRebackRecordList
	 * @return
	 */
	@Override
	public int updateBcRebackRecordBatch(List<BcRebackRecord> bcRebackRecordList){
		return bcRebackRecordBaseDao.updateBcRebackRecordBatch(bcRebackRecordList);
	}
	/**
	 * 根据序列号删除(回盘记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcRebackRecordLogic(java.math.BigInteger id){
		return bcRebackRecordBaseDao.deleteBcRebackRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(回盘记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcRebackRecordLogicBatch(List<java.math.BigInteger> idList){
		return bcRebackRecordBaseDao.deleteBcRebackRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(回盘记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcRebackRecord(java.math.BigInteger id){
//		return bcRebackRecordBaseDao.deleteBcRebackRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(回盘记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcRebackRecordBatch(List<java.math.BigInteger> idList){
//		return bcRebackRecordBaseDao.deleteBcRebackRecordBatch(idList);
//	}
	
}

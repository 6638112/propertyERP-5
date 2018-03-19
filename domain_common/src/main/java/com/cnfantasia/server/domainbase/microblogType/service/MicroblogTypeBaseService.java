package com.cnfantasia.server.domainbase.microblogType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogType.dao.IMicroblogTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * 描述:(微博类别) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogTypeBaseService implements IMicroblogTypeBaseService{
	
	private IMicroblogTypeBaseDao microblogTypeBaseDao;
	public void setMicroblogTypeBaseDao(IMicroblogTypeBaseDao microblogTypeBaseDao) {
		this.microblogTypeBaseDao = microblogTypeBaseDao;
	}
	/**
	 * 根据条件查询(微博类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogType> getMicroblogTypeByCondition(Map<String,Object> paramMap){
		return microblogTypeBaseDao.selectMicroblogTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微博类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogType> getMicroblogTypeByConditionDim(Map<String,Object> paramMap){
		return microblogTypeBaseDao.selectMicroblogTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微博类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogType> getMicroblogTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogTypeBaseDao.selectMicroblogTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微博类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogType> getMicroblogTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogTypeBaseDao.selectMicroblogTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微博类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogType getMicroblogTypeBySeqId(java.math.BigInteger id){
		return microblogTypeBaseDao.selectMicroblogTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微博类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogTypeCount(Map<String,Object> paramMap){
		return microblogTypeBaseDao.selectMicroblogTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微博类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogTypeCountDim(Map<String,Object> paramMap){
		return microblogTypeBaseDao.selectMicroblogTypeCount(paramMap,true);
	}
	/**
	 * 往(微博类别)新增一条记录
	 * @param microblogType
	 * @return
	 */
	@Override
	public int insertMicroblogType(MicroblogType microblogType){
		return microblogTypeBaseDao.insertMicroblogType(microblogType);
	}
	/**
	 * 批量新增(微博类别)
	 * @param microblogTypeList
	 * @return
	 */
	@Override
	public int insertMicroblogTypeBatch(List<MicroblogType> microblogTypeList){
		return microblogTypeBaseDao.insertMicroblogTypeBatch(microblogTypeList);
	}
	/**
	 * 更新(微博类别)信息
	 * @param microblogType
	 * @return
	 */
	@Override
	public int updateMicroblogType(MicroblogType microblogType){
		return microblogTypeBaseDao.updateMicroblogType(microblogType);
	}
	/**
	 * 批量更新(微博类别)信息
	 * @param microblogTypeList
	 * @return
	 */
	@Override
	public int updateMicroblogTypeBatch(List<MicroblogType> microblogTypeList){
		return microblogTypeBaseDao.updateMicroblogTypeBatch(microblogTypeList);
	}
	/**
	 * 根据序列号删除(微博类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogTypeLogic(java.math.BigInteger id){
		return microblogTypeBaseDao.deleteMicroblogTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微博类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogTypeLogicBatch(List<java.math.BigInteger> idList){
		return microblogTypeBaseDao.deleteMicroblogTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微博类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogType(java.math.BigInteger id){
//		return microblogTypeBaseDao.deleteMicroblogType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogTypeBatch(List<java.math.BigInteger> idList){
//		return microblogTypeBaseDao.deleteMicroblogTypeBatch(idList);
//	}
	
}

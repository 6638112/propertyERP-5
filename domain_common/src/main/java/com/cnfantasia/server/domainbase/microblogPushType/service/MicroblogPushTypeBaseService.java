package com.cnfantasia.server.domainbase.microblogPushType.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogPushType.dao.IMicroblogPushTypeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;

/**
 * 描述:(街坊消息推送类别表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogPushTypeBaseService implements IMicroblogPushTypeBaseService{
	
	private IMicroblogPushTypeBaseDao microblogPushTypeBaseDao;
	public void setMicroblogPushTypeBaseDao(IMicroblogPushTypeBaseDao microblogPushTypeBaseDao) {
		this.microblogPushTypeBaseDao = microblogPushTypeBaseDao;
	}
	/**
	 * 根据条件查询(街坊消息推送类别表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogPushType> getMicroblogPushTypeByCondition(Map<String,Object> paramMap){
		return microblogPushTypeBaseDao.selectMicroblogPushTypeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(街坊消息推送类别表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogPushType> getMicroblogPushTypeByConditionDim(Map<String,Object> paramMap){
		return microblogPushTypeBaseDao.selectMicroblogPushTypeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(街坊消息推送类别表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogPushType> getMicroblogPushTypeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogPushTypeBaseDao.selectMicroblogPushTypeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(街坊消息推送类别表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogPushType> getMicroblogPushTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogPushTypeBaseDao.selectMicroblogPushTypeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(街坊消息推送类别表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogPushType getMicroblogPushTypeBySeqId(java.math.BigInteger id){
		return microblogPushTypeBaseDao.selectMicroblogPushTypeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(街坊消息推送类别表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogPushTypeCount(Map<String,Object> paramMap){
		return microblogPushTypeBaseDao.selectMicroblogPushTypeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(街坊消息推送类别表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogPushTypeCountDim(Map<String,Object> paramMap){
		return microblogPushTypeBaseDao.selectMicroblogPushTypeCount(paramMap,true);
	}
	/**
	 * 往(街坊消息推送类别表)新增一条记录
	 * @param microblogPushType
	 * @return
	 */
	@Override
	public int insertMicroblogPushType(MicroblogPushType microblogPushType){
		return microblogPushTypeBaseDao.insertMicroblogPushType(microblogPushType);
	}
	/**
	 * 批量新增(街坊消息推送类别表)
	 * @param microblogPushTypeList
	 * @return
	 */
	@Override
	public int insertMicroblogPushTypeBatch(List<MicroblogPushType> microblogPushTypeList){
		return microblogPushTypeBaseDao.insertMicroblogPushTypeBatch(microblogPushTypeList);
	}
	/**
	 * 更新(街坊消息推送类别表)信息
	 * @param microblogPushType
	 * @return
	 */
	@Override
	public int updateMicroblogPushType(MicroblogPushType microblogPushType){
		return microblogPushTypeBaseDao.updateMicroblogPushType(microblogPushType);
	}
	/**
	 * 批量更新(街坊消息推送类别表)信息
	 * @param microblogPushTypeList
	 * @return
	 */
	@Override
	public int updateMicroblogPushTypeBatch(List<MicroblogPushType> microblogPushTypeList){
		return microblogPushTypeBaseDao.updateMicroblogPushTypeBatch(microblogPushTypeList);
	}
	/**
	 * 根据序列号删除(街坊消息推送类别表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPushTypeLogic(java.math.BigInteger id){
		return microblogPushTypeBaseDao.deleteMicroblogPushTypeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(街坊消息推送类别表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPushTypeLogicBatch(List<java.math.BigInteger> idList){
		return microblogPushTypeBaseDao.deleteMicroblogPushTypeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(街坊消息推送类别表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPushType(java.math.BigInteger id){
//		return microblogPushTypeBaseDao.deleteMicroblogPushType(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(街坊消息推送类别表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPushTypeBatch(List<java.math.BigInteger> idList){
//		return microblogPushTypeBaseDao.deleteMicroblogPushTypeBatch(idList);
//	}
	
}

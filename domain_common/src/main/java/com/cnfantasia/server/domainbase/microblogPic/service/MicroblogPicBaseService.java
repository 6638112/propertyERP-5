package com.cnfantasia.server.domainbase.microblogPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogPic.dao.IMicroblogPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogPic.entity.MicroblogPic;

/**
 * 描述:(微博图片信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogPicBaseService implements IMicroblogPicBaseService{
	
	private IMicroblogPicBaseDao microblogPicBaseDao;
	public void setMicroblogPicBaseDao(IMicroblogPicBaseDao microblogPicBaseDao) {
		this.microblogPicBaseDao = microblogPicBaseDao;
	}
	/**
	 * 根据条件查询(微博图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogPic> getMicroblogPicByCondition(Map<String,Object> paramMap){
		return microblogPicBaseDao.selectMicroblogPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微博图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogPic> getMicroblogPicByConditionDim(Map<String,Object> paramMap){
		return microblogPicBaseDao.selectMicroblogPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微博图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogPic> getMicroblogPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogPicBaseDao.selectMicroblogPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微博图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogPic> getMicroblogPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogPicBaseDao.selectMicroblogPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微博图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogPic getMicroblogPicBySeqId(java.math.BigInteger id){
		return microblogPicBaseDao.selectMicroblogPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微博图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogPicCount(Map<String,Object> paramMap){
		return microblogPicBaseDao.selectMicroblogPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微博图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogPicCountDim(Map<String,Object> paramMap){
		return microblogPicBaseDao.selectMicroblogPicCount(paramMap,true);
	}
	/**
	 * 往(微博图片信息)新增一条记录
	 * @param microblogPic
	 * @return
	 */
	@Override
	public int insertMicroblogPic(MicroblogPic microblogPic){
		return microblogPicBaseDao.insertMicroblogPic(microblogPic);
	}
	/**
	 * 批量新增(微博图片信息)
	 * @param microblogPicList
	 * @return
	 */
	@Override
	public int insertMicroblogPicBatch(List<MicroblogPic> microblogPicList){
		return microblogPicBaseDao.insertMicroblogPicBatch(microblogPicList);
	}
	/**
	 * 更新(微博图片信息)信息
	 * @param microblogPic
	 * @return
	 */
	@Override
	public int updateMicroblogPic(MicroblogPic microblogPic){
		return microblogPicBaseDao.updateMicroblogPic(microblogPic);
	}
	/**
	 * 批量更新(微博图片信息)信息
	 * @param microblogPicList
	 * @return
	 */
	@Override
	public int updateMicroblogPicBatch(List<MicroblogPic> microblogPicList){
		return microblogPicBaseDao.updateMicroblogPicBatch(microblogPicList);
	}
	/**
	 * 根据序列号删除(微博图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPicLogic(java.math.BigInteger id){
		return microblogPicBaseDao.deleteMicroblogPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微博图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPicLogicBatch(List<java.math.BigInteger> idList){
		return microblogPicBaseDao.deleteMicroblogPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微博图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPic(java.math.BigInteger id){
//		return microblogPicBaseDao.deleteMicroblogPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPicBatch(List<java.math.BigInteger> idList){
//		return microblogPicBaseDao.deleteMicroblogPicBatch(idList);
//	}
	
}

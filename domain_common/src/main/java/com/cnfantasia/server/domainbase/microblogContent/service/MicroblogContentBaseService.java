package com.cnfantasia.server.domainbase.microblogContent.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.microblogContent.dao.IMicroblogContentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;

/**
 * 描述:(微博信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MicroblogContentBaseService implements IMicroblogContentBaseService{
	
	private IMicroblogContentBaseDao microblogContentBaseDao;
	public void setMicroblogContentBaseDao(IMicroblogContentBaseDao microblogContentBaseDao) {
		this.microblogContentBaseDao = microblogContentBaseDao;
	}
	/**
	 * 根据条件查询(微博信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogContent> getMicroblogContentByCondition(Map<String,Object> paramMap){
		return microblogContentBaseDao.selectMicroblogContentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(微博信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MicroblogContent> getMicroblogContentByConditionDim(Map<String,Object> paramMap){
		return microblogContentBaseDao.selectMicroblogContentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(微博信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogContent> getMicroblogContentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogContentBaseDao.selectMicroblogContentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(微博信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MicroblogContent> getMicroblogContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return microblogContentBaseDao.selectMicroblogContentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(微博信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogContent getMicroblogContentBySeqId(java.math.BigInteger id){
		return microblogContentBaseDao.selectMicroblogContentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(微博信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogContentCount(Map<String,Object> paramMap){
		return microblogContentBaseDao.selectMicroblogContentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(微博信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMicroblogContentCountDim(Map<String,Object> paramMap){
		return microblogContentBaseDao.selectMicroblogContentCount(paramMap,true);
	}
	/**
	 * 往(微博信息表)新增一条记录
	 * @param microblogContent
	 * @return
	 */
	@Override
	public int insertMicroblogContent(MicroblogContent microblogContent){
		return microblogContentBaseDao.insertMicroblogContent(microblogContent);
	}
	/**
	 * 批量新增(微博信息表)
	 * @param microblogContentList
	 * @return
	 */
	@Override
	public int insertMicroblogContentBatch(List<MicroblogContent> microblogContentList){
		return microblogContentBaseDao.insertMicroblogContentBatch(microblogContentList);
	}
	/**
	 * 更新(微博信息表)信息
	 * @param microblogContent
	 * @return
	 */
	@Override
	public int updateMicroblogContent(MicroblogContent microblogContent){
		return microblogContentBaseDao.updateMicroblogContent(microblogContent);
	}
	/**
	 * 批量更新(微博信息表)信息
	 * @param microblogContentList
	 * @return
	 */
	@Override
	public int updateMicroblogContentBatch(List<MicroblogContent> microblogContentList){
		return microblogContentBaseDao.updateMicroblogContentBatch(microblogContentList);
	}
	/**
	 * 根据序列号删除(微博信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogContentLogic(java.math.BigInteger id){
		return microblogContentBaseDao.deleteMicroblogContentLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(微博信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogContentLogicBatch(List<java.math.BigInteger> idList){
		return microblogContentBaseDao.deleteMicroblogContentLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(微博信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogContent(java.math.BigInteger id){
//		return microblogContentBaseDao.deleteMicroblogContent(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogContentBatch(List<java.math.BigInteger> idList){
//		return microblogContentBaseDao.deleteMicroblogContentBatch(idList);
//	}
	
}

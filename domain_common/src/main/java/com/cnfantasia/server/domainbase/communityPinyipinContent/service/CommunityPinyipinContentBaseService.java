package com.cnfantasia.server.domainbase.communityPinyipinContent.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communityPinyipinContent.dao.ICommunityPinyipinContentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;

/**
 * 描述:(拼一拼内容表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunityPinyipinContentBaseService implements ICommunityPinyipinContentBaseService{
	
	private ICommunityPinyipinContentBaseDao communityPinyipinContentBaseDao;
	public void setCommunityPinyipinContentBaseDao(ICommunityPinyipinContentBaseDao communityPinyipinContentBaseDao) {
		this.communityPinyipinContentBaseDao = communityPinyipinContentBaseDao;
	}
	/**
	 * 根据条件查询(拼一拼内容表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByCondition(Map<String,Object> paramMap){
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(拼一拼内容表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByConditionDim(Map<String,Object> paramMap){
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(拼一拼内容表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(拼一拼内容表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunityPinyipinContent> getCommunityPinyipinContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(拼一拼内容表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityPinyipinContent getCommunityPinyipinContentBySeqId(java.math.BigInteger id){
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(拼一拼内容表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityPinyipinContentCount(Map<String,Object> paramMap){
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(拼一拼内容表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunityPinyipinContentCountDim(Map<String,Object> paramMap){
		return communityPinyipinContentBaseDao.selectCommunityPinyipinContentCount(paramMap,true);
	}
	/**
	 * 往(拼一拼内容表)新增一条记录
	 * @param communityPinyipinContent
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinContent(CommunityPinyipinContent communityPinyipinContent){
		return communityPinyipinContentBaseDao.insertCommunityPinyipinContent(communityPinyipinContent);
	}
	/**
	 * 批量新增(拼一拼内容表)
	 * @param communityPinyipinContentList
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinContentBatch(List<CommunityPinyipinContent> communityPinyipinContentList){
		return communityPinyipinContentBaseDao.insertCommunityPinyipinContentBatch(communityPinyipinContentList);
	}
	/**
	 * 更新(拼一拼内容表)信息
	 * @param communityPinyipinContent
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinContent(CommunityPinyipinContent communityPinyipinContent){
		return communityPinyipinContentBaseDao.updateCommunityPinyipinContent(communityPinyipinContent);
	}
	/**
	 * 批量更新(拼一拼内容表)信息
	 * @param communityPinyipinContentList
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinContentBatch(List<CommunityPinyipinContent> communityPinyipinContentList){
		return communityPinyipinContentBaseDao.updateCommunityPinyipinContentBatch(communityPinyipinContentList);
	}
	/**
	 * 根据序列号删除(拼一拼内容表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinContentLogic(java.math.BigInteger id){
		return communityPinyipinContentBaseDao.deleteCommunityPinyipinContentLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(拼一拼内容表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinContentLogicBatch(List<java.math.BigInteger> idList){
		return communityPinyipinContentBaseDao.deleteCommunityPinyipinContentLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(拼一拼内容表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinContent(java.math.BigInteger id){
//		return communityPinyipinContentBaseDao.deleteCommunityPinyipinContent(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼一拼内容表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinContentBatch(List<java.math.BigInteger> idList){
//		return communityPinyipinContentBaseDao.deleteCommunityPinyipinContentBatch(idList);
//	}
	
}

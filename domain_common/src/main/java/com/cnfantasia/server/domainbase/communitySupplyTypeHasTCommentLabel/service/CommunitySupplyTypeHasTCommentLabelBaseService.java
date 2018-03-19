package com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.dao.ICommunitySupplyTypeHasTCommentLabelBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.entity.CommunitySupplyTypeHasTCommentLabel;

/**
 * 描述:(社区商家类别所包含的评论标签) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommunitySupplyTypeHasTCommentLabelBaseService implements ICommunitySupplyTypeHasTCommentLabelBaseService{
	
	private ICommunitySupplyTypeHasTCommentLabelBaseDao communitySupplyTypeHasTCommentLabelBaseDao;
	public void setCommunitySupplyTypeHasTCommentLabelBaseDao(ICommunitySupplyTypeHasTCommentLabelBaseDao communitySupplyTypeHasTCommentLabelBaseDao) {
		this.communitySupplyTypeHasTCommentLabelBaseDao = communitySupplyTypeHasTCommentLabelBaseDao;
	}
	/**
	 * 根据条件查询(社区商家类别所包含的评论标签)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentLabel> getCommunitySupplyTypeHasTCommentLabelByCondition(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(社区商家类别所包含的评论标签)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentLabel> getCommunitySupplyTypeHasTCommentLabelByConditionDim(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(社区商家类别所包含的评论标签)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentLabel> getCommunitySupplyTypeHasTCommentLabelByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(社区商家类别所包含的评论标签)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentLabel> getCommunitySupplyTypeHasTCommentLabelByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(社区商家类别所包含的评论标签)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeHasTCommentLabel getCommunitySupplyTypeHasTCommentLabelBySeqId(java.math.BigInteger id){
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家类别所包含的评论标签)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeHasTCommentLabelCount(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(社区商家类别所包含的评论标签)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommunitySupplyTypeHasTCommentLabelCountDim(Map<String,Object> paramMap){
		return communitySupplyTypeHasTCommentLabelBaseDao.selectCommunitySupplyTypeHasTCommentLabelCount(paramMap,true);
	}
	/**
	 * 往(社区商家类别所包含的评论标签)新增一条记录
	 * @param communitySupplyTypeHasTCommentLabel
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentLabel(CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel){
		return communitySupplyTypeHasTCommentLabelBaseDao.insertCommunitySupplyTypeHasTCommentLabel(communitySupplyTypeHasTCommentLabel);
	}
	/**
	 * 批量新增(社区商家类别所包含的评论标签)
	 * @param communitySupplyTypeHasTCommentLabelList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentLabelBatch(List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList){
		return communitySupplyTypeHasTCommentLabelBaseDao.insertCommunitySupplyTypeHasTCommentLabelBatch(communitySupplyTypeHasTCommentLabelList);
	}
	/**
	 * 更新(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabel
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentLabel(CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel){
		return communitySupplyTypeHasTCommentLabelBaseDao.updateCommunitySupplyTypeHasTCommentLabel(communitySupplyTypeHasTCommentLabel);
	}
	/**
	 * 批量更新(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabelList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentLabelBatch(List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList){
		return communitySupplyTypeHasTCommentLabelBaseDao.updateCommunitySupplyTypeHasTCommentLabelBatch(communitySupplyTypeHasTCommentLabelList);
	}
	/**
	 * 根据序列号删除(社区商家类别所包含的评论标签)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentLabelLogic(java.math.BigInteger id){
		return communitySupplyTypeHasTCommentLabelBaseDao.deleteCommunitySupplyTypeHasTCommentLabelLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(社区商家类别所包含的评论标签)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentLabelLogicBatch(List<java.math.BigInteger> idList){
		return communitySupplyTypeHasTCommentLabelBaseDao.deleteCommunitySupplyTypeHasTCommentLabelLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家类别所包含的评论标签)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentLabel(java.math.BigInteger id){
//		return communitySupplyTypeHasTCommentLabelBaseDao.deleteCommunitySupplyTypeHasTCommentLabel(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家类别所包含的评论标签)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentLabelBatch(List<java.math.BigInteger> idList){
//		return communitySupplyTypeHasTCommentLabelBaseDao.deleteCommunitySupplyTypeHasTCommentLabelBatch(idList);
//	}
	
}

package com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.entity.CommunitySupplyTypeHasTCommentLabel;

/**
 * 描述:(社区商家类别所包含的评论标签) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeHasTCommentLabelBaseDao {
	/**
	 * 根据条件查询(社区商家类别所包含的评论标签)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeHasTCommentLabel> selectCommunitySupplyTypeHasTCommentLabelByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区商家类别所包含的评论标签)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeHasTCommentLabel> selectCommunitySupplyTypeHasTCommentLabelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区商家类别所包含的评论标签)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTypeHasTCommentLabel selectCommunitySupplyTypeHasTCommentLabelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家类别所包含的评论标签)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyTypeHasTCommentLabelCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区商家类别所包含的评论标签)新增一条记录
	 * @param communitySupplyTypeHasTCommentLabel
	 * @return
	 */
	public int insertCommunitySupplyTypeHasTCommentLabel(CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel);
	
	/**
	 * 批量新增(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabelList
	 * @return
	 */
	public int insertCommunitySupplyTypeHasTCommentLabelBatch(List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList);
	
	/**
	 * 更新(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabel
	 * @return
	 */
	public int updateCommunitySupplyTypeHasTCommentLabel(CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel);
	
	/**
	 * 批量更新(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabelList
	 * @return
	 */
	public int updateCommunitySupplyTypeHasTCommentLabelBatch(List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList);
	
	/**
	 * 根据序列号删除(社区商家类别所包含的评论标签)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeHasTCommentLabelLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(社区商家类别所包含的评论标签)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeHasTCommentLabelLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(社区商家类别所包含的评论标签)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeHasTCommentLabel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区商家类别所包含的评论标签)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeHasTCommentLabelBatch(List<java.math.BigInteger> idList);
	
	
}

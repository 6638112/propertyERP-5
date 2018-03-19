package com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentLabel.entity.CommunitySupplyTypeHasTCommentLabel;

/**
 * 描述:(社区商家类别所包含的评论标签) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyTypeHasTCommentLabelBaseDao extends AbstractBaseDao implements ICommunitySupplyTypeHasTCommentLabelBaseDao{
	/**
	 * 根据条件查询(社区商家类别所包含的评论标签)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentLabel> selectCommunitySupplyTypeHasTCommentLabelByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyTypeHasTCommentLabelBase.select_communitySupplyTypeHasTCommentLabel",paramMap);
	}
	/**
	 * 按条件分页查询(社区商家类别所包含的评论标签)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentLabel> selectCommunitySupplyTypeHasTCommentLabelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyTypeHasTCommentLabelCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyTypeHasTCommentLabel> resMap= sqlSession.selectList("communitySupplyTypeHasTCommentLabelBase.select_communitySupplyTypeHasTCommentLabel_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区商家类别所包含的评论标签)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeHasTCommentLabel selectCommunitySupplyTypeHasTCommentLabelBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyTypeHasTCommentLabelBase.select_communitySupplyTypeHasTCommentLabel_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家类别所包含的评论标签)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyTypeHasTCommentLabelCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyTypeHasTCommentLabelBase.select_communitySupplyTypeHasTCommentLabel_count", paramMap);
	}
	/**
	 * 往(社区商家类别所包含的评论标签)新增一条记录
	 * @param communitySupplyTypeHasTCommentLabel
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentLabel(CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel){
		return sqlSession.insert("communitySupplyTypeHasTCommentLabelBase.insert_communitySupplyTypeHasTCommentLabel",communitySupplyTypeHasTCommentLabel);
	}
	/**
	 * 批量新增(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabelList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentLabelBatch(List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList) {
		return sqlSession.insert("communitySupplyTypeHasTCommentLabelBase.insert_communitySupplyTypeHasTCommentLabel_Batch",communitySupplyTypeHasTCommentLabelList);
	}
	
	/**
	 * 更新(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabel
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentLabel(CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel){
		return sqlSession.update("communitySupplyTypeHasTCommentLabelBase.update_communitySupplyTypeHasTCommentLabel", communitySupplyTypeHasTCommentLabel);
	}
	/**
	 * 批量更新(社区商家类别所包含的评论标签)信息
	 * @param communitySupplyTypeHasTCommentLabelList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentLabelBatch(List<CommunitySupplyTypeHasTCommentLabel> communitySupplyTypeHasTCommentLabelList) {
		return sqlSession.update("communitySupplyTypeHasTCommentLabelBase.update_communitySupplyTypeHasTCommentLabel_Batch", communitySupplyTypeHasTCommentLabelList);
	}
	
	/**
	 * 根据序列号删除(社区商家类别所包含的评论标签)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentLabelLogic(java.math.BigInteger id){
		CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel = new CommunitySupplyTypeHasTCommentLabel();
		communitySupplyTypeHasTCommentLabel.setId(id);
		communitySupplyTypeHasTCommentLabel.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyTypeHasTCommentLabelBase.delete_communitySupplyTypeHasTCommentLabel_Logic",communitySupplyTypeHasTCommentLabel);
	}
	
	/**
	 * 根据Id 批量删除(社区商家类别所包含的评论标签)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentLabelLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyTypeHasTCommentLabel> delList = new java.util.ArrayList<CommunitySupplyTypeHasTCommentLabel>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyTypeHasTCommentLabel communitySupplyTypeHasTCommentLabel = new CommunitySupplyTypeHasTCommentLabel();
			communitySupplyTypeHasTCommentLabel.setId(id);
			communitySupplyTypeHasTCommentLabel.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyTypeHasTCommentLabel);
		}
		return sqlSession.update("communitySupplyTypeHasTCommentLabelBase.delete_communitySupplyTypeHasTCommentLabel_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家类别所包含的评论标签)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentLabel(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyTypeHasTCommentLabelBase.delete_communitySupplyTypeHasTCommentLabel", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家类别所包含的评论标签)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentLabelBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyTypeHasTCommentLabelBase.delete_communitySupplyTypeHasTCommentLabel_Batch", idList);
//	}
	
	
}

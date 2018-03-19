package com.cnfantasia.server.domainbase.channelPartnerRecommend.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelPartnerRecommend.entity.ChannelPartnerRecommend;

/**
 * 描述:(渠道合伙人的推荐) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ChannelPartnerRecommendBaseDao extends AbstractBaseDao implements IChannelPartnerRecommendBaseDao{
	/**
	 * 根据条件查询(渠道合伙人的推荐)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ChannelPartnerRecommend> selectChannelPartnerRecommendByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("channelPartnerRecommendBase.select_channelPartnerRecommend",paramMap);
	}
	/**
	 * 按条件分页查询(渠道合伙人的推荐)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ChannelPartnerRecommend> selectChannelPartnerRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectChannelPartnerRecommendCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ChannelPartnerRecommend> resMap= sqlSession.selectList("channelPartnerRecommendBase.select_channelPartnerRecommend_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(渠道合伙人的推荐)信息
	 * @param id
	 * @return
	 */
	@Override
	public ChannelPartnerRecommend selectChannelPartnerRecommendBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("channelPartnerRecommendBase.select_channelPartnerRecommend_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(渠道合伙人的推荐)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectChannelPartnerRecommendCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("channelPartnerRecommendBase.select_channelPartnerRecommend_count", paramMap);
	}
	/**
	 * 往(渠道合伙人的推荐)新增一条记录
	 * @param channelPartnerRecommend
	 * @return
	 */
	@Override
	public int insertChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend){
		return sqlSession.insert("channelPartnerRecommendBase.insert_channelPartnerRecommend",channelPartnerRecommend);
	}
	/**
	 * 批量新增(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommendList
	 * @return
	 */
	@Override
	public int insertChannelPartnerRecommendBatch(List<ChannelPartnerRecommend> channelPartnerRecommendList) {
		return sqlSession.insert("channelPartnerRecommendBase.insert_channelPartnerRecommend_Batch",channelPartnerRecommendList);
	}
	
	/**
	 * 更新(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommend
	 * @return
	 */
	@Override
	public int updateChannelPartnerRecommend(ChannelPartnerRecommend channelPartnerRecommend){
		return sqlSession.update("channelPartnerRecommendBase.update_channelPartnerRecommend", channelPartnerRecommend);
	}
	/**
	 * 批量更新(渠道合伙人的推荐)信息
	 * @param channelPartnerRecommendList
	 * @return
	 */
	@Override
	public int updateChannelPartnerRecommendBatch(List<ChannelPartnerRecommend> channelPartnerRecommendList) {
		return sqlSession.update("channelPartnerRecommendBase.update_channelPartnerRecommend_Batch", channelPartnerRecommendList);
	}
	
	/**
	 * 根据序列号删除(渠道合伙人的推荐)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteChannelPartnerRecommendLogic(java.math.BigInteger id){
		ChannelPartnerRecommend channelPartnerRecommend = new ChannelPartnerRecommend();
		channelPartnerRecommend.setId(id);
		channelPartnerRecommend.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("channelPartnerRecommendBase.delete_channelPartnerRecommend_Logic",channelPartnerRecommend);
	}
	
	/**
	 * 根据Id 批量删除(渠道合伙人的推荐)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteChannelPartnerRecommendLogicBatch(List<java.math.BigInteger> idList) {
		List<ChannelPartnerRecommend> delList = new java.util.ArrayList<ChannelPartnerRecommend>();
		for(java.math.BigInteger id:idList){
			ChannelPartnerRecommend channelPartnerRecommend = new ChannelPartnerRecommend();
			channelPartnerRecommend.setId(id);
			channelPartnerRecommend.setSys0DelState(RecordState_DELETED);
			delList.add(channelPartnerRecommend);
		}
		return sqlSession.update("channelPartnerRecommendBase.delete_channelPartnerRecommend_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(渠道合伙人的推荐)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteChannelPartnerRecommend(java.math.BigInteger id){
//		return sqlSession.delete("channelPartnerRecommendBase.delete_channelPartnerRecommend", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(渠道合伙人的推荐)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteChannelPartnerRecommendBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("channelPartnerRecommendBase.delete_channelPartnerRecommend_Batch", idList);
//	}
	
	
}

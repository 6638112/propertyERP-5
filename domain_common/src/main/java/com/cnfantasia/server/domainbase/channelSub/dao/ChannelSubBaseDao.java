package com.cnfantasia.server.domainbase.channelSub.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.channelSub.entity.ChannelSub;

/**
 * 描述:(子渠道表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ChannelSubBaseDao extends AbstractBaseDao implements IChannelSubBaseDao{
	/**
	 * 根据条件查询(子渠道表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ChannelSub> selectChannelSubByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("channelSubBase.select_channelSub",paramMap);
	}
	/**
	 * 按条件分页查询(子渠道表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ChannelSub> selectChannelSubByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectChannelSubCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ChannelSub> resMap= sqlSession.selectList("channelSubBase.select_channelSub_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(子渠道表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ChannelSub selectChannelSubBySeqId(java.lang.Long id){
		return sqlSession.selectOne("channelSubBase.select_channelSub_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(子渠道表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectChannelSubCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("channelSubBase.select_channelSub_count", paramMap);
	}
	/**
	 * 往(子渠道表)新增一条记录
	 * @param channelSub
	 * @return
	 */
	@Override
	public int insertChannelSub(ChannelSub channelSub){
		return sqlSession.insert("channelSubBase.insert_channelSub",channelSub);
	}
	/**
	 * 批量新增(子渠道表)信息
	 * @param channelSubList
	 * @return
	 */
	@Override
	public int insertChannelSubBatch(List<ChannelSub> channelSubList) {
		return sqlSession.insert("channelSubBase.insert_channelSub_Batch",channelSubList);
	}
	
	/**
	 * 更新(子渠道表)信息
	 * @param channelSub
	 * @return
	 */
	@Override
	public int updateChannelSub(ChannelSub channelSub){
		return sqlSession.update("channelSubBase.update_channelSub", channelSub);
	}
	/**
	 * 批量更新(子渠道表)信息
	 * @param channelSubList
	 * @return
	 */
	@Override
	public int updateChannelSubBatch(List<ChannelSub> channelSubList) {
		return sqlSession.update("channelSubBase.update_channelSub_Batch", channelSubList);
	}
	
	/**
	 * 根据序列号删除(子渠道表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteChannelSubLogic(java.lang.Long id){
		ChannelSub channelSub = new ChannelSub();
		channelSub.setId(id);
		channelSub.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("channelSubBase.delete_channelSub_Logic",channelSub);
	}
	
	/**
	 * 根据Id 批量删除(子渠道表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteChannelSubLogicBatch(List<java.lang.Long> idList) {
		List<ChannelSub> delList = new java.util.ArrayList<ChannelSub>();
		for(java.lang.Long id:idList){
			ChannelSub channelSub = new ChannelSub();
			channelSub.setId(id);
			channelSub.setSys0DelState(RecordState_DELETED);
			delList.add(channelSub);
		}
		return sqlSession.update("channelSubBase.delete_channelSub_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(子渠道表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteChannelSub(java.lang.Long id){
//		return sqlSession.delete("channelSubBase.delete_channelSub", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(子渠道表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteChannelSubBatch(List<java.lang.Long> idList) {
//		return sqlSession.delete("channelSubBase.delete_channelSub_Batch", idList);
//	}
	
	
}

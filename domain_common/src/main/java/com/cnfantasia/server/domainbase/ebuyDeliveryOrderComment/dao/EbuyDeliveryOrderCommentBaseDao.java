package com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;

/**
 * 描述:(运单备注) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyDeliveryOrderCommentBaseDao extends AbstractBaseDao implements IEbuyDeliveryOrderCommentBaseDao{
	/**
	 * 根据条件查询(运单备注)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryOrderCommentBase.select_ebuyDeliveryOrderComment",paramMap);
	}
	/**
	 * 按条件分页查询(运单备注)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyDeliveryOrderCommentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyDeliveryOrderComment> resMap= sqlSession.selectList("ebuyDeliveryOrderCommentBase.select_ebuyDeliveryOrderComment_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(运单备注)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryOrderComment selectEbuyDeliveryOrderCommentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyDeliveryOrderCommentBase.select_ebuyDeliveryOrderComment_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(运单备注)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyDeliveryOrderCommentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyDeliveryOrderCommentBase.select_ebuyDeliveryOrderComment_count", paramMap);
	}
	/**
	 * 往(运单备注)新增一条记录
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment){
		return sqlSession.insert("ebuyDeliveryOrderCommentBase.insert_ebuyDeliveryOrderComment",ebuyDeliveryOrderComment);
	}
	/**
	 * 批量新增(运单备注)信息
	 * @param ebuyDeliveryOrderCommentList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderCommentBatch(List<EbuyDeliveryOrderComment> ebuyDeliveryOrderCommentList) {
		return sqlSession.insert("ebuyDeliveryOrderCommentBase.insert_ebuyDeliveryOrderComment_Batch",ebuyDeliveryOrderCommentList);
	}
	
	/**
	 * 更新(运单备注)信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment){
		return sqlSession.update("ebuyDeliveryOrderCommentBase.update_ebuyDeliveryOrderComment", ebuyDeliveryOrderComment);
	}
	/**
	 * 批量更新(运单备注)信息
	 * @param ebuyDeliveryOrderCommentList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderCommentBatch(List<EbuyDeliveryOrderComment> ebuyDeliveryOrderCommentList) {
		return sqlSession.update("ebuyDeliveryOrderCommentBase.update_ebuyDeliveryOrderComment_Batch", ebuyDeliveryOrderCommentList);
	}
	
	/**
	 * 根据序列号删除(运单备注)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyDeliveryOrderCommentLogic(java.math.BigInteger id){
		EbuyDeliveryOrderComment ebuyDeliveryOrderComment = new EbuyDeliveryOrderComment();
		ebuyDeliveryOrderComment.setId(id);
		ebuyDeliveryOrderComment.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyDeliveryOrderCommentBase.delete_ebuyDeliveryOrderComment_Logic",ebuyDeliveryOrderComment);
	}
	 */
	/**
	 * 根据Id 批量删除(运单备注)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyDeliveryOrderCommentLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyDeliveryOrderComment> delList = new java.util.ArrayList<EbuyDeliveryOrderComment>();
		for(java.math.BigInteger id:idList){
			EbuyDeliveryOrderComment ebuyDeliveryOrderComment = new EbuyDeliveryOrderComment();
			ebuyDeliveryOrderComment.setId(id);
			ebuyDeliveryOrderComment.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyDeliveryOrderComment);
		}
		return sqlSession.update("ebuyDeliveryOrderCommentBase.delete_ebuyDeliveryOrderComment_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(运单备注)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderComment(java.math.BigInteger id){
//		return sqlSession.delete("ebuyDeliveryOrderCommentBase.delete_ebuyDeliveryOrderComment", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运单备注)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderCommentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyDeliveryOrderCommentBase.delete_ebuyDeliveryOrderComment_Batch", idList);
//	}
	
	
}

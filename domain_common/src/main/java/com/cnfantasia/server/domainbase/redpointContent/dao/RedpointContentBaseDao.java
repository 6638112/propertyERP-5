package com.cnfantasia.server.domainbase.redpointContent.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;

/**
 * 描述:(红点信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RedpointContentBaseDao extends AbstractBaseDao implements IRedpointContentBaseDao{
	/**
	 * 根据条件查询(红点信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RedpointContent> selectRedpointContentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("redpointContentBase.select_redpointContent",paramMap);
	}
	/**
	 * 按条件分页查询(红点信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RedpointContent> selectRedpointContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRedpointContentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RedpointContent> resMap= sqlSession.selectList("redpointContentBase.select_redpointContent_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(红点信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RedpointContent selectRedpointContentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("redpointContentBase.select_redpointContent_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(红点信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRedpointContentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("redpointContentBase.select_redpointContent_count", paramMap);
	}
	/**
	 * 往(红点信息表)新增一条记录
	 * @param redpointContent
	 * @return
	 */
	@Override
	public int insertRedpointContent(RedpointContent redpointContent){
		return sqlSession.insert("redpointContentBase.insert_redpointContent",redpointContent);
	}
	/**
	 * 批量新增(红点信息表)信息
	 * @param redpointContentList
	 * @return
	 */
	@Override
	public int insertRedpointContentBatch(List<RedpointContent> redpointContentList) {
		return sqlSession.insert("redpointContentBase.insert_redpointContent_Batch",redpointContentList);
	}
	
	/**
	 * 更新(红点信息表)信息
	 * @param redpointContent
	 * @return
	 */
	@Override
	public int updateRedpointContent(RedpointContent redpointContent){
		return sqlSession.update("redpointContentBase.update_redpointContent", redpointContent);
	}
	/**
	 * 批量更新(红点信息表)信息
	 * @param redpointContentList
	 * @return
	 */
	@Override
	public int updateRedpointContentBatch(List<RedpointContent> redpointContentList) {
		return sqlSession.update("redpointContentBase.update_redpointContent_Batch", redpointContentList);
	}
	
	/**
	 * 根据序列号删除(红点信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentLogic(java.math.BigInteger id){
		RedpointContent redpointContent = new RedpointContent();
		redpointContent.setId(id);
		redpointContent.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("redpointContentBase.delete_redpointContent_Logic",redpointContent);
	}
	
	/**
	 * 根据Id 批量删除(红点信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentLogicBatch(List<java.math.BigInteger> idList) {
		List<RedpointContent> delList = new java.util.ArrayList<RedpointContent>();
		for(java.math.BigInteger id:idList){
			RedpointContent redpointContent = new RedpointContent();
			redpointContent.setId(id);
			redpointContent.setSys0DelState(RecordState_DELETED);
			delList.add(redpointContent);
		}
		return sqlSession.update("redpointContentBase.delete_redpointContent_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(红点信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContent(java.math.BigInteger id){
//		return sqlSession.delete("redpointContentBase.delete_redpointContent", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(红点信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("redpointContentBase.delete_redpointContent_Batch", idList);
//	}
	
	
}

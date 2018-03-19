package com.cnfantasia.server.domainbase.support.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.support.entity.Support;

/**
 * 描述:(点赞信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SupportBaseDao extends AbstractBaseDao implements ISupportBaseDao{
	/**
	 * 根据条件查询(点赞信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Support> selectSupportByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("supportBase.select_support",paramMap);
	}
	/**
	 * 按条件分页查询(点赞信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Support> selectSupportByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSupportCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Support> resMap= sqlSession.selectList("supportBase.select_support_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(点赞信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public Support selectSupportBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("supportBase.select_support_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(点赞信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSupportCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("supportBase.select_support_count", paramMap);
	}
	/**
	 * 往(点赞信息)新增一条记录
	 * @param support
	 * @return
	 */
	@Override
	public int insertSupport(Support support){
		return sqlSession.insert("supportBase.insert_support",support);
	}
	/**
	 * 批量新增(点赞信息)信息
	 * @param supportList
	 * @return
	 */
	@Override
	public int insertSupportBatch(List<Support> supportList) {
		return sqlSession.insert("supportBase.insert_support_Batch",supportList);
	}
	
	/**
	 * 更新(点赞信息)信息
	 * @param support
	 * @return
	 */
	@Override
	public int updateSupport(Support support){
		return sqlSession.update("supportBase.update_support", support);
	}
	/**
	 * 批量更新(点赞信息)信息
	 * @param supportList
	 * @return
	 */
	@Override
	public int updateSupportBatch(List<Support> supportList) {
		return sqlSession.update("supportBase.update_support_Batch", supportList);
	}
	
	/**
	 * 根据序列号删除(点赞信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSupportLogic(java.math.BigInteger id){
		Support support = new Support();
		support.setId(id);
		support.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("supportBase.delete_support_Logic",support);
	}
	
	/**
	 * 根据Id 批量删除(点赞信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSupportLogicBatch(List<java.math.BigInteger> idList) {
		List<Support> delList = new java.util.ArrayList<Support>();
		for(java.math.BigInteger id:idList){
			Support support = new Support();
			support.setId(id);
			support.setSys0DelState(RecordState_DELETED);
			delList.add(support);
		}
		return sqlSession.update("supportBase.delete_support_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(点赞信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSupport(java.math.BigInteger id){
//		return sqlSession.delete("supportBase.delete_support", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(点赞信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSupportBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("supportBase.delete_support_Batch", idList);
//	}
	
	
}

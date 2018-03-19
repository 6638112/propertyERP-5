package com.cnfantasia.server.domainbase.redpointContentSource.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointContentSource.entity.RedpointContentSource;

/**
 * 描述:(红点来源表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RedpointContentSourceBaseDao extends AbstractBaseDao implements IRedpointContentSourceBaseDao{
	/**
	 * 根据条件查询(红点来源表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RedpointContentSource> selectRedpointContentSourceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("redpointContentSourceBase.select_redpointContentSource",paramMap);
	}
	/**
	 * 按条件分页查询(红点来源表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RedpointContentSource> selectRedpointContentSourceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRedpointContentSourceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RedpointContentSource> resMap= sqlSession.selectList("redpointContentSourceBase.select_redpointContentSource_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(红点来源表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RedpointContentSource selectRedpointContentSourceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("redpointContentSourceBase.select_redpointContentSource_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(红点来源表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRedpointContentSourceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("redpointContentSourceBase.select_redpointContentSource_count", paramMap);
	}
	/**
	 * 往(红点来源表)新增一条记录
	 * @param redpointContentSource
	 * @return
	 */
	@Override
	public int insertRedpointContentSource(RedpointContentSource redpointContentSource){
		return sqlSession.insert("redpointContentSourceBase.insert_redpointContentSource",redpointContentSource);
	}
	/**
	 * 批量新增(红点来源表)信息
	 * @param redpointContentSourceList
	 * @return
	 */
	@Override
	public int insertRedpointContentSourceBatch(List<RedpointContentSource> redpointContentSourceList) {
		return sqlSession.insert("redpointContentSourceBase.insert_redpointContentSource_Batch",redpointContentSourceList);
	}
	
	/**
	 * 更新(红点来源表)信息
	 * @param redpointContentSource
	 * @return
	 */
	@Override
	public int updateRedpointContentSource(RedpointContentSource redpointContentSource){
		return sqlSession.update("redpointContentSourceBase.update_redpointContentSource", redpointContentSource);
	}
	/**
	 * 批量更新(红点来源表)信息
	 * @param redpointContentSourceList
	 * @return
	 */
	@Override
	public int updateRedpointContentSourceBatch(List<RedpointContentSource> redpointContentSourceList) {
		return sqlSession.update("redpointContentSourceBase.update_redpointContentSource_Batch", redpointContentSourceList);
	}
	
	/**
	 * 根据序列号删除(红点来源表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentSourceLogic(java.math.BigInteger id){
		RedpointContentSource redpointContentSource = new RedpointContentSource();
		redpointContentSource.setId(id);
		redpointContentSource.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("redpointContentSourceBase.delete_redpointContentSource_Logic",redpointContentSource);
	}
	
	/**
	 * 根据Id 批量删除(红点来源表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentSourceLogicBatch(List<java.math.BigInteger> idList) {
		List<RedpointContentSource> delList = new java.util.ArrayList<RedpointContentSource>();
		for(java.math.BigInteger id:idList){
			RedpointContentSource redpointContentSource = new RedpointContentSource();
			redpointContentSource.setId(id);
			redpointContentSource.setSys0DelState(RecordState_DELETED);
			delList.add(redpointContentSource);
		}
		return sqlSession.update("redpointContentSourceBase.delete_redpointContentSource_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(红点来源表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContentSource(java.math.BigInteger id){
//		return sqlSession.delete("redpointContentSourceBase.delete_redpointContentSource", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(红点来源表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContentSourceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("redpointContentSourceBase.delete_redpointContentSource_Batch", idList);
//	}
	
	
}

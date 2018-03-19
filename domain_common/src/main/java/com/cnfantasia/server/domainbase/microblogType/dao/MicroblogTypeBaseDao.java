package com.cnfantasia.server.domainbase.microblogType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogType.entity.MicroblogType;

/**
 * 描述:(微博类别) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MicroblogTypeBaseDao extends AbstractBaseDao implements IMicroblogTypeBaseDao{
	/**
	 * 根据条件查询(微博类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogType> selectMicroblogTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("microblogTypeBase.select_microblogType",paramMap);
	}
	/**
	 * 按条件分页查询(微博类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogType> selectMicroblogTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMicroblogTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MicroblogType> resMap= sqlSession.selectList("microblogTypeBase.select_microblogType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微博类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogType selectMicroblogTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("microblogTypeBase.select_microblogType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微博类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMicroblogTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("microblogTypeBase.select_microblogType_count", paramMap);
	}
	/**
	 * 往(微博类别)新增一条记录
	 * @param microblogType
	 * @return
	 */
	@Override
	public int insertMicroblogType(MicroblogType microblogType){
		return sqlSession.insert("microblogTypeBase.insert_microblogType",microblogType);
	}
	/**
	 * 批量新增(微博类别)信息
	 * @param microblogTypeList
	 * @return
	 */
	@Override
	public int insertMicroblogTypeBatch(List<MicroblogType> microblogTypeList) {
		return sqlSession.insert("microblogTypeBase.insert_microblogType_Batch",microblogTypeList);
	}
	
	/**
	 * 更新(微博类别)信息
	 * @param microblogType
	 * @return
	 */
	@Override
	public int updateMicroblogType(MicroblogType microblogType){
		return sqlSession.update("microblogTypeBase.update_microblogType", microblogType);
	}
	/**
	 * 批量更新(微博类别)信息
	 * @param microblogTypeList
	 * @return
	 */
	@Override
	public int updateMicroblogTypeBatch(List<MicroblogType> microblogTypeList) {
		return sqlSession.update("microblogTypeBase.update_microblogType_Batch", microblogTypeList);
	}
	
	/**
	 * 根据序列号删除(微博类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogTypeLogic(java.math.BigInteger id){
		MicroblogType microblogType = new MicroblogType();
		microblogType.setId(id);
		microblogType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("microblogTypeBase.delete_microblogType_Logic",microblogType);
	}
	
	/**
	 * 根据Id 批量删除(微博类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<MicroblogType> delList = new java.util.ArrayList<MicroblogType>();
		for(java.math.BigInteger id:idList){
			MicroblogType microblogType = new MicroblogType();
			microblogType.setId(id);
			microblogType.setSys0DelState(RecordState_DELETED);
			delList.add(microblogType);
		}
		return sqlSession.update("microblogTypeBase.delete_microblogType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微博类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogType(java.math.BigInteger id){
//		return sqlSession.delete("microblogTypeBase.delete_microblogType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("microblogTypeBase.delete_microblogType_Batch", idList);
//	}
	
	
}

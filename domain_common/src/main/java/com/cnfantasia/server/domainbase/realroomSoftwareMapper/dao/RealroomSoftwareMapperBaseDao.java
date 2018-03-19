package com.cnfantasia.server.domainbase.realroomSoftwareMapper.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realroomSoftwareMapper.entity.RealroomSoftwareMapper;

/**
 * 描述:(房间与物业软件映射) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RealroomSoftwareMapperBaseDao extends AbstractBaseDao implements IRealroomSoftwareMapperBaseDao{
	/**
	 * 根据条件查询(房间与物业软件映射)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealroomSoftwareMapper> selectRealroomSoftwareMapperByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("realroomSoftwareMapperBase.select_realroomSoftwareMapper",paramMap);
	}
	/**
	 * 按条件分页查询(房间与物业软件映射)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealroomSoftwareMapper> selectRealroomSoftwareMapperByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRealroomSoftwareMapperCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RealroomSoftwareMapper> resMap= sqlSession.selectList("realroomSoftwareMapperBase.select_realroomSoftwareMapper_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(房间与物业软件映射)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealroomSoftwareMapper selectRealroomSoftwareMapperBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("realroomSoftwareMapperBase.select_realroomSoftwareMapper_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(房间与物业软件映射)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRealroomSoftwareMapperCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("realroomSoftwareMapperBase.select_realroomSoftwareMapper_count", paramMap);
	}
	/**
	 * 往(房间与物业软件映射)新增一条记录
	 * @param realroomSoftwareMapper
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper){
		return sqlSession.insert("realroomSoftwareMapperBase.insert_realroomSoftwareMapper",realroomSoftwareMapper);
	}
	/**
	 * 批量新增(房间与物业软件映射)信息
	 * @param realroomSoftwareMapperList
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareMapperBatch(List<RealroomSoftwareMapper> realroomSoftwareMapperList) {
		return sqlSession.insert("realroomSoftwareMapperBase.insert_realroomSoftwareMapper_Batch",realroomSoftwareMapperList);
	}
	
	/**
	 * 更新(房间与物业软件映射)信息
	 * @param realroomSoftwareMapper
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareMapper(RealroomSoftwareMapper realroomSoftwareMapper){
		return sqlSession.update("realroomSoftwareMapperBase.update_realroomSoftwareMapper", realroomSoftwareMapper);
	}
	/**
	 * 批量更新(房间与物业软件映射)信息
	 * @param realroomSoftwareMapperList
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareMapperBatch(List<RealroomSoftwareMapper> realroomSoftwareMapperList) {
		return sqlSession.update("realroomSoftwareMapperBase.update_realroomSoftwareMapper_Batch", realroomSoftwareMapperList);
	}
	
	/**
	 * 根据序列号删除(房间与物业软件映射)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareMapperLogic(java.math.BigInteger id){
		RealroomSoftwareMapper realroomSoftwareMapper = new RealroomSoftwareMapper();
		realroomSoftwareMapper.setId(id);
		realroomSoftwareMapper.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("realroomSoftwareMapperBase.delete_realroomSoftwareMapper_Logic",realroomSoftwareMapper);
	}
	
	/**
	 * 根据Id 批量删除(房间与物业软件映射)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareMapperLogicBatch(List<java.math.BigInteger> idList) {
		List<RealroomSoftwareMapper> delList = new java.util.ArrayList<RealroomSoftwareMapper>();
		for(java.math.BigInteger id:idList){
			RealroomSoftwareMapper realroomSoftwareMapper = new RealroomSoftwareMapper();
			realroomSoftwareMapper.setId(id);
			realroomSoftwareMapper.setSys0DelState(RecordState_DELETED);
			delList.add(realroomSoftwareMapper);
		}
		return sqlSession.update("realroomSoftwareMapperBase.delete_realroomSoftwareMapper_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(房间与物业软件映射)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareMapper(java.math.BigInteger id){
//		return sqlSession.delete("realroomSoftwareMapperBase.delete_realroomSoftwareMapper", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间与物业软件映射)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareMapperBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("realroomSoftwareMapperBase.delete_realroomSoftwareMapper_Batch", idList);
//	}
	
	
}

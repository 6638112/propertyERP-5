package com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoomHasTPropertyLessee.entity.RealRoomHasTPropertyLessee;

/**
 * 描述:(房间租户信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RealRoomHasTPropertyLesseeBaseDao extends AbstractBaseDao implements IRealRoomHasTPropertyLesseeBaseDao{
	/**
	 * 根据条件查询(房间租户信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyLessee> selectRealRoomHasTPropertyLesseeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("realRoomHasTPropertyLesseeBase.select_realRoomHasTPropertyLessee",paramMap);
	}
	/**
	 * 按条件分页查询(房间租户信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealRoomHasTPropertyLessee> selectRealRoomHasTPropertyLesseeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRealRoomHasTPropertyLesseeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RealRoomHasTPropertyLessee> resMap= sqlSession.selectList("realRoomHasTPropertyLesseeBase.select_realRoomHasTPropertyLessee_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(房间租户信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoomHasTPropertyLessee selectRealRoomHasTPropertyLesseeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("realRoomHasTPropertyLesseeBase.select_realRoomHasTPropertyLessee_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(房间租户信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRealRoomHasTPropertyLesseeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("realRoomHasTPropertyLesseeBase.select_realRoomHasTPropertyLessee_count", paramMap);
	}
	/**
	 * 往(房间租户信息表)新增一条记录
	 * @param realRoomHasTPropertyLessee
	 * @return
	 */
	@Override
	public int insertRealRoomHasTPropertyLessee(RealRoomHasTPropertyLessee realRoomHasTPropertyLessee){
		return sqlSession.insert("realRoomHasTPropertyLesseeBase.insert_realRoomHasTPropertyLessee",realRoomHasTPropertyLessee);
	}
	/**
	 * 批量新增(房间租户信息表)信息
	 * @param realRoomHasTPropertyLesseeList
	 * @return
	 */
	@Override
	public int insertRealRoomHasTPropertyLesseeBatch(List<RealRoomHasTPropertyLessee> realRoomHasTPropertyLesseeList) {
		if (realRoomHasTPropertyLesseeList == null || realRoomHasTPropertyLesseeList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = realRoomHasTPropertyLesseeList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<RealRoomHasTPropertyLessee> batchList = realRoomHasTPropertyLesseeList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("realRoomHasTPropertyLesseeBase.insert_realRoomHasTPropertyLessee_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(房间租户信息表)信息
	 * @param realRoomHasTPropertyLessee
	 * @return
	 */
	@Override
	public int updateRealRoomHasTPropertyLessee(RealRoomHasTPropertyLessee realRoomHasTPropertyLessee){
		return sqlSession.update("realRoomHasTPropertyLesseeBase.update_realRoomHasTPropertyLessee", realRoomHasTPropertyLessee);
	}
	/**
	 * 批量更新(房间租户信息表)信息
	 * @param realRoomHasTPropertyLesseeList
	 * @return
	 */
	@Override
	public int updateRealRoomHasTPropertyLesseeBatch(List<RealRoomHasTPropertyLessee> realRoomHasTPropertyLesseeList) {
		if (realRoomHasTPropertyLesseeList == null || realRoomHasTPropertyLesseeList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("realRoomHasTPropertyLesseeBase.update_realRoomHasTPropertyLessee_Batch", realRoomHasTPropertyLesseeList);
	}
	
	/**
	 * 根据序列号删除(房间租户信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasTPropertyLesseeLogic(java.math.BigInteger id){
		RealRoomHasTPropertyLessee realRoomHasTPropertyLessee = new RealRoomHasTPropertyLessee();
		realRoomHasTPropertyLessee.setId(id);
		realRoomHasTPropertyLessee.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("realRoomHasTPropertyLesseeBase.delete_realRoomHasTPropertyLessee_Logic",realRoomHasTPropertyLessee);
	}
	
	/**
	 * 根据Id 批量删除(房间租户信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomHasTPropertyLesseeLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<RealRoomHasTPropertyLessee> delList = new java.util.ArrayList<RealRoomHasTPropertyLessee>();
		for(java.math.BigInteger id:idList){
			RealRoomHasTPropertyLessee realRoomHasTPropertyLessee = new RealRoomHasTPropertyLessee();
			realRoomHasTPropertyLessee.setId(id);
			realRoomHasTPropertyLessee.setSys0DelState(RecordState_DELETED);
			delList.add(realRoomHasTPropertyLessee);
		}
		return sqlSession.update("realRoomHasTPropertyLesseeBase.delete_realRoomHasTPropertyLessee_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(房间租户信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasTPropertyLessee(java.math.BigInteger id){
//		return sqlSession.delete("realRoomHasTPropertyLesseeBase.delete_realRoomHasTPropertyLessee", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间租户信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomHasTPropertyLesseeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("realRoomHasTPropertyLesseeBase.delete_realRoomHasTPropertyLessee_Batch", idList);
//	}
	
	
}

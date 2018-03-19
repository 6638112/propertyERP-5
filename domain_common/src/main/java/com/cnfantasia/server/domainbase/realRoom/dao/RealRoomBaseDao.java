package com.cnfantasia.server.domainbase.realRoom.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;

/**
 * 描述:(真实房间) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RealRoomBaseDao extends AbstractBaseDao implements IRealRoomBaseDao{
	/**
	 * 根据条件查询(真实房间)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealRoom> selectRealRoomByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("realRoomBase.select_realRoom",paramMap);
	}
	/**
	 * 按条件分页查询(真实房间)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealRoom> selectRealRoomByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRealRoomCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RealRoom> resMap= sqlSession.selectList("realRoomBase.select_realRoom_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(真实房间)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealRoom selectRealRoomBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("realRoomBase.select_realRoom_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(真实房间)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRealRoomCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("realRoomBase.select_realRoom_count", paramMap);
	}
	/**
	 * 往(真实房间)新增一条记录
	 * @param realRoom
	 * @return
	 */
	@Override
	public int insertRealRoom(RealRoom realRoom){
		return sqlSession.insert("realRoomBase.insert_realRoom",realRoom);
	}
	/**
	 * 批量新增(真实房间)信息
	 * @param realRoomList
	 * @return
	 */
	@Override
	public int insertRealRoomBatch(List<RealRoom> realRoomList) {
		if (realRoomList == null || realRoomList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = realRoomList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<RealRoom> batchList = realRoomList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("realRoomBase.insert_realRoom_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(真实房间)信息
	 * @param realRoom
	 * @return
	 */
	@Override
	public int updateRealRoom(RealRoom realRoom){
		return sqlSession.update("realRoomBase.update_realRoom", realRoom);
	}
	/**
	 * 批量更新(真实房间)信息
	 * @param realRoomList
	 * @return
	 */
	@Override
	public int updateRealRoomBatch(List<RealRoom> realRoomList) {
		if (realRoomList == null || realRoomList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("realRoomBase.update_realRoom_Batch", realRoomList);
	}
	
	/**
	 * 根据序列号删除(真实房间)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealRoomLogic(java.math.BigInteger id){
		RealRoom realRoom = new RealRoom();
		realRoom.setId(id);
		realRoom.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("realRoomBase.delete_realRoom_Logic",realRoom);
	}
	
	/**
	 * 根据Id 批量删除(真实房间)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealRoomLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<RealRoom> delList = new java.util.ArrayList<RealRoom>();
		for(java.math.BigInteger id:idList){
			RealRoom realRoom = new RealRoom();
			realRoom.setId(id);
			realRoom.setSys0DelState(RecordState_DELETED);
			delList.add(realRoom);
		}
		return sqlSession.update("realRoomBase.delete_realRoom_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(真实房间)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoom(java.math.BigInteger id){
//		return sqlSession.delete("realRoomBase.delete_realRoom", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(真实房间)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealRoomBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("realRoomBase.delete_realRoom_Batch", idList);
//	}
	
	
}

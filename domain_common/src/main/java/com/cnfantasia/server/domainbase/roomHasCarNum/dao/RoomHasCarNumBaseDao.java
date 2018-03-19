package com.cnfantasia.server.domainbase.roomHasCarNum.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;

/**
 * 描述:(房间车牌对应表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RoomHasCarNumBaseDao extends AbstractBaseDao implements IRoomHasCarNumBaseDao{
	/**
	 * 根据条件查询(房间车牌对应表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RoomHasCarNum> selectRoomHasCarNumByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("roomHasCarNumBase.select_roomHasCarNum",paramMap);
	}
	/**
	 * 按条件分页查询(房间车牌对应表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RoomHasCarNum> selectRoomHasCarNumByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRoomHasCarNumCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RoomHasCarNum> resMap= sqlSession.selectList("roomHasCarNumBase.select_roomHasCarNum_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(房间车牌对应表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RoomHasCarNum selectRoomHasCarNumBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("roomHasCarNumBase.select_roomHasCarNum_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(房间车牌对应表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRoomHasCarNumCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("roomHasCarNumBase.select_roomHasCarNum_count", paramMap);
	}
	/**
	 * 往(房间车牌对应表)新增一条记录
	 * @param roomHasCarNum
	 * @return
	 */
	@Override
	public int insertRoomHasCarNum(RoomHasCarNum roomHasCarNum){
		return sqlSession.insert("roomHasCarNumBase.insert_roomHasCarNum",roomHasCarNum);
	}
	/**
	 * 批量新增(房间车牌对应表)信息
	 * @param roomHasCarNumList
	 * @return
	 */
	@Override
	public int insertRoomHasCarNumBatch(List<RoomHasCarNum> roomHasCarNumList) {
		return sqlSession.insert("roomHasCarNumBase.insert_roomHasCarNum_Batch",roomHasCarNumList);
	}
	
	/**
	 * 更新(房间车牌对应表)信息
	 * @param roomHasCarNum
	 * @return
	 */
	@Override
	public int updateRoomHasCarNum(RoomHasCarNum roomHasCarNum){
		return sqlSession.update("roomHasCarNumBase.update_roomHasCarNum", roomHasCarNum);
	}
	/**
	 * 批量更新(房间车牌对应表)信息
	 * @param roomHasCarNumList
	 * @return
	 */
	@Override
	public int updateRoomHasCarNumBatch(List<RoomHasCarNum> roomHasCarNumList) {
		return sqlSession.update("roomHasCarNumBase.update_roomHasCarNum_Batch", roomHasCarNumList);
	}
	
	/**
	 * 根据序列号删除(房间车牌对应表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRoomHasCarNumLogic(java.math.BigInteger id){
		RoomHasCarNum roomHasCarNum = new RoomHasCarNum();
		roomHasCarNum.setId(id);
		roomHasCarNum.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("roomHasCarNumBase.delete_roomHasCarNum_Logic",roomHasCarNum);
	}
	
	/**
	 * 根据Id 批量删除(房间车牌对应表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRoomHasCarNumLogicBatch(List<java.math.BigInteger> idList) {
		List<RoomHasCarNum> delList = new java.util.ArrayList<RoomHasCarNum>();
		for(java.math.BigInteger id:idList){
			RoomHasCarNum roomHasCarNum = new RoomHasCarNum();
			roomHasCarNum.setId(id);
			roomHasCarNum.setSys0DelState(RecordState_DELETED);
			delList.add(roomHasCarNum);
		}
		return sqlSession.update("roomHasCarNumBase.delete_roomHasCarNum_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(房间车牌对应表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoomHasCarNum(java.math.BigInteger id){
//		return sqlSession.delete("roomHasCarNumBase.delete_roomHasCarNum", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间车牌对应表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomHasCarNumBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("roomHasCarNumBase.delete_roomHasCarNum_Batch", idList);
//	}
	
	
}

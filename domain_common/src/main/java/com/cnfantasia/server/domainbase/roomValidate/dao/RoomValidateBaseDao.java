package com.cnfantasia.server.domainbase.roomValidate.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * 描述:(门牌校验信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RoomValidateBaseDao extends AbstractBaseDao implements IRoomValidateBaseDao{
	/**
	 * 根据条件查询(门牌校验信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RoomValidate> selectRoomValidateByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("roomValidateBase.select_roomValidate",paramMap);
	}
	/**
	 * 按条件分页查询(门牌校验信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RoomValidate> selectRoomValidateByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRoomValidateCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RoomValidate> resMap= sqlSession.selectList("roomValidateBase.select_roomValidate_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(门牌校验信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public RoomValidate selectRoomValidateBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("roomValidateBase.select_roomValidate_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(门牌校验信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRoomValidateCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("roomValidateBase.select_roomValidate_count", paramMap);
	}
	/**
	 * 往(门牌校验信息)新增一条记录
	 * @param roomValidate
	 * @return
	 */
	@Override
	public int insertRoomValidate(RoomValidate roomValidate){
		return sqlSession.insert("roomValidateBase.insert_roomValidate",roomValidate);
	}
	/**
	 * 批量新增(门牌校验信息)信息
	 * @param roomValidateList
	 * @return
	 */
	@Override
	public int insertRoomValidateBatch(List<RoomValidate> roomValidateList) {
		return sqlSession.insert("roomValidateBase.insert_roomValidate_Batch",roomValidateList);
	}
	
	/**
	 * 更新(门牌校验信息)信息
	 * @param roomValidate
	 * @return
	 */
	@Override
	public int updateRoomValidate(RoomValidate roomValidate){
		return sqlSession.update("roomValidateBase.update_roomValidate", roomValidate);
	}
	/**
	 * 批量更新(门牌校验信息)信息
	 * @param roomValidateList
	 * @return
	 */
	@Override
	public int updateRoomValidateBatch(List<RoomValidate> roomValidateList) {
		return sqlSession.update("roomValidateBase.update_roomValidate_Batch", roomValidateList);
	}
	
	/**
	 * 根据序列号删除(门牌校验信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRoomValidateLogic(java.math.BigInteger id){
		RoomValidate roomValidate = new RoomValidate();
		roomValidate.setId(id);
		roomValidate.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("roomValidateBase.delete_roomValidate_Logic",roomValidate);
	}
	
	/**
	 * 根据Id 批量删除(门牌校验信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRoomValidateLogicBatch(List<java.math.BigInteger> idList) {
		List<RoomValidate> delList = new java.util.ArrayList<RoomValidate>();
		for(java.math.BigInteger id:idList){
			RoomValidate roomValidate = new RoomValidate();
			roomValidate.setId(id);
			roomValidate.setSys0DelState(RecordState_DELETED);
			delList.add(roomValidate);
		}
		return sqlSession.update("roomValidateBase.delete_roomValidate_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(门牌校验信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoomValidate(java.math.BigInteger id){
//		return sqlSession.delete("roomValidateBase.delete_roomValidate", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌校验信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomValidateBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("roomValidateBase.delete_roomValidate_Batch", idList);
//	}
	
	
}

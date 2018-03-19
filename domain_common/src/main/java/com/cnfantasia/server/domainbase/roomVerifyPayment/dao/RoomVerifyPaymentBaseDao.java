package com.cnfantasia.server.domainbase.roomVerifyPayment.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.roomVerifyPayment.entity.RoomVerifyPayment;

/**
 * 描述:(门牌验证缴费情况查询表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RoomVerifyPaymentBaseDao extends AbstractBaseDao implements IRoomVerifyPaymentBaseDao{
	/**
	 * 根据条件查询(门牌验证缴费情况查询表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> selectRoomVerifyPaymentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("roomVerifyPaymentBase.select_roomVerifyPayment",paramMap);
	}
	/**
	 * 按条件分页查询(门牌验证缴费情况查询表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RoomVerifyPayment> selectRoomVerifyPaymentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRoomVerifyPaymentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RoomVerifyPayment> resMap= sqlSession.selectList("roomVerifyPaymentBase.select_roomVerifyPayment_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(门牌验证缴费情况查询表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RoomVerifyPayment selectRoomVerifyPaymentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("roomVerifyPaymentBase.select_roomVerifyPayment_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(门牌验证缴费情况查询表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRoomVerifyPaymentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("roomVerifyPaymentBase.select_roomVerifyPayment_count", paramMap);
	}
	/**
	 * 往(门牌验证缴费情况查询表)新增一条记录
	 * @param roomVerifyPayment
	 * @return
	 */
	@Override
	public int insertRoomVerifyPayment(RoomVerifyPayment roomVerifyPayment){
		return sqlSession.insert("roomVerifyPaymentBase.insert_roomVerifyPayment",roomVerifyPayment);
	}
	/**
	 * 批量新增(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPaymentList
	 * @return
	 */
	@Override
	public int insertRoomVerifyPaymentBatch(List<RoomVerifyPayment> roomVerifyPaymentList) {
		return sqlSession.insert("roomVerifyPaymentBase.insert_roomVerifyPayment_Batch",roomVerifyPaymentList);
	}
	
	/**
	 * 更新(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPayment
	 * @return
	 */
	@Override
	public int updateRoomVerifyPayment(RoomVerifyPayment roomVerifyPayment){
		return sqlSession.update("roomVerifyPaymentBase.update_roomVerifyPayment", roomVerifyPayment);
	}
	/**
	 * 批量更新(门牌验证缴费情况查询表)信息
	 * @param roomVerifyPaymentList
	 * @return
	 */
	@Override
	public int updateRoomVerifyPaymentBatch(List<RoomVerifyPayment> roomVerifyPaymentList) {
		return sqlSession.update("roomVerifyPaymentBase.update_roomVerifyPayment_Batch", roomVerifyPaymentList);
	}
	
	/**
	 * 根据序列号删除(门牌验证缴费情况查询表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteRoomVerifyPaymentLogic(java.math.BigInteger id){
		RoomVerifyPayment roomVerifyPayment = new RoomVerifyPayment();
		roomVerifyPayment.setId(id);
		roomVerifyPayment.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("roomVerifyPaymentBase.delete_roomVerifyPayment_Logic",roomVerifyPayment);
	}
	 */
	/**
	 * 根据Id 批量删除(门牌验证缴费情况查询表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteRoomVerifyPaymentLogicBatch(List<java.math.BigInteger> idList) {
		List<RoomVerifyPayment> delList = new java.util.ArrayList<RoomVerifyPayment>();
		for(java.math.BigInteger id:idList){
			RoomVerifyPayment roomVerifyPayment = new RoomVerifyPayment();
			roomVerifyPayment.setId(id);
			roomVerifyPayment.setSys0DelState(RecordState_DELETED);
			delList.add(roomVerifyPayment);
		}
		return sqlSession.update("roomVerifyPaymentBase.delete_roomVerifyPayment_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(门牌验证缴费情况查询表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRoomVerifyPayment(java.math.BigInteger id){
//		return sqlSession.delete("roomVerifyPaymentBase.delete_roomVerifyPayment", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌验证缴费情况查询表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRoomVerifyPaymentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("roomVerifyPaymentBase.delete_roomVerifyPayment_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.carYhsMsg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carYhsMsg.entity.CarYhsMsg;

/**
 * 描述:(临停车缴费消息发送表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarYhsMsgBaseDao extends AbstractBaseDao implements ICarYhsMsgBaseDao{
	/**
	 * 根据条件查询(临停车缴费消息发送表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarYhsMsg> selectCarYhsMsgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carYhsMsgBase.select_carYhsMsg",paramMap);
	}
	/**
	 * 按条件分页查询(临停车缴费消息发送表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarYhsMsg> selectCarYhsMsgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarYhsMsgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarYhsMsg> resMap= sqlSession.selectList("carYhsMsgBase.select_carYhsMsg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(临停车缴费消息发送表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarYhsMsg selectCarYhsMsgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carYhsMsgBase.select_carYhsMsg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(临停车缴费消息发送表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarYhsMsgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carYhsMsgBase.select_carYhsMsg_count", paramMap);
	}
	/**
	 * 往(临停车缴费消息发送表)新增一条记录
	 * @param carYhsMsg
	 * @return
	 */
	@Override
	public int insertCarYhsMsg(CarYhsMsg carYhsMsg){
		return sqlSession.insert("carYhsMsgBase.insert_carYhsMsg",carYhsMsg);
	}
	/**
	 * 批量新增(临停车缴费消息发送表)信息
	 * @param carYhsMsgList
	 * @return
	 */
	@Override
	public int insertCarYhsMsgBatch(List<CarYhsMsg> carYhsMsgList) {
		return sqlSession.insert("carYhsMsgBase.insert_carYhsMsg_Batch",carYhsMsgList);
	}
	
	/**
	 * 更新(临停车缴费消息发送表)信息
	 * @param carYhsMsg
	 * @return
	 */
	@Override
	public int updateCarYhsMsg(CarYhsMsg carYhsMsg){
		return sqlSession.update("carYhsMsgBase.update_carYhsMsg", carYhsMsg);
	}
	/**
	 * 批量更新(临停车缴费消息发送表)信息
	 * @param carYhsMsgList
	 * @return
	 */
	@Override
	public int updateCarYhsMsgBatch(List<CarYhsMsg> carYhsMsgList) {
		return sqlSession.update("carYhsMsgBase.update_carYhsMsg_Batch", carYhsMsgList);
	}
	
	/**
	 * 根据序列号删除(临停车缴费消息发送表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarYhsMsgLogic(java.math.BigInteger id){
		CarYhsMsg carYhsMsg = new CarYhsMsg();
		carYhsMsg.setId(id);
		carYhsMsg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carYhsMsgBase.delete_carYhsMsg_Logic",carYhsMsg);
	}
	
	/**
	 * 根据Id 批量删除(临停车缴费消息发送表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarYhsMsgLogicBatch(List<java.math.BigInteger> idList) {
		List<CarYhsMsg> delList = new java.util.ArrayList<CarYhsMsg>();
		for(java.math.BigInteger id:idList){
			CarYhsMsg carYhsMsg = new CarYhsMsg();
			carYhsMsg.setId(id);
			carYhsMsg.setSys0DelState(RecordState_DELETED);
			delList.add(carYhsMsg);
		}
		return sqlSession.update("carYhsMsgBase.delete_carYhsMsg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(临停车缴费消息发送表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarYhsMsg(java.math.BigInteger id){
//		return sqlSession.delete("carYhsMsgBase.delete_carYhsMsg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临停车缴费消息发送表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarYhsMsgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carYhsMsgBase.delete_carYhsMsg_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.carXmfMsg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carXmfMsg.entity.CarXmfMsg;

/**
 * 描述:(小蜜蜂临停车缴费通知记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarXmfMsgBaseDao extends AbstractBaseDao implements ICarXmfMsgBaseDao{
	/**
	 * 根据条件查询(小蜜蜂临停车缴费通知记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarXmfMsg> selectCarXmfMsgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carXmfMsgBase.select_carXmfMsg",paramMap);
	}
	/**
	 * 按条件分页查询(小蜜蜂临停车缴费通知记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarXmfMsg> selectCarXmfMsgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarXmfMsgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarXmfMsg> resMap= sqlSession.selectList("carXmfMsgBase.select_carXmfMsg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小蜜蜂临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarXmfMsg selectCarXmfMsgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carXmfMsgBase.select_carXmfMsg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小蜜蜂临停车缴费通知记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarXmfMsgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carXmfMsgBase.select_carXmfMsg_count", paramMap);
	}
	/**
	 * 往(小蜜蜂临停车缴费通知记录)新增一条记录
	 * @param carXmfMsg
	 * @return
	 */
	@Override
	public int insertCarXmfMsg(CarXmfMsg carXmfMsg){
		return sqlSession.insert("carXmfMsgBase.insert_carXmfMsg",carXmfMsg);
	}
	/**
	 * 批量新增(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsgList
	 * @return
	 */
	@Override
	public int insertCarXmfMsgBatch(List<CarXmfMsg> carXmfMsgList) {
		if (carXmfMsgList == null || carXmfMsgList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = carXmfMsgList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<CarXmfMsg> batchList = carXmfMsgList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("carXmfMsgBase.insert_carXmfMsg_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsg
	 * @return
	 */
	@Override
	public int updateCarXmfMsg(CarXmfMsg carXmfMsg){
		return sqlSession.update("carXmfMsgBase.update_carXmfMsg", carXmfMsg);
	}
	/**
	 * 批量更新(小蜜蜂临停车缴费通知记录)信息
	 * @param carXmfMsgList
	 * @return
	 */
	@Override
	public int updateCarXmfMsgBatch(List<CarXmfMsg> carXmfMsgList) {
		if (carXmfMsgList == null || carXmfMsgList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("carXmfMsgBase.update_carXmfMsg_Batch", carXmfMsgList);
	}
	
	/**
	 * 根据序列号删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarXmfMsgLogic(java.math.BigInteger id){
		CarXmfMsg carXmfMsg = new CarXmfMsg();
		carXmfMsg.setId(id);
		carXmfMsg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carXmfMsgBase.delete_carXmfMsg_Logic",carXmfMsg);
	}
	
	/**
	 * 根据Id 批量删除(小蜜蜂临停车缴费通知记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarXmfMsgLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<CarXmfMsg> delList = new java.util.ArrayList<CarXmfMsg>();
		for(java.math.BigInteger id:idList){
			CarXmfMsg carXmfMsg = new CarXmfMsg();
			carXmfMsg.setId(id);
			carXmfMsg.setSys0DelState(RecordState_DELETED);
			delList.add(carXmfMsg);
		}
		return sqlSession.update("carXmfMsgBase.delete_carXmfMsg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小蜜蜂临停车缴费通知记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarXmfMsg(java.math.BigInteger id){
//		return sqlSession.delete("carXmfMsgBase.delete_carXmfMsg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小蜜蜂临停车缴费通知记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarXmfMsgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carXmfMsgBase.delete_carXmfMsg_Batch", idList);
//	}
	
	
}

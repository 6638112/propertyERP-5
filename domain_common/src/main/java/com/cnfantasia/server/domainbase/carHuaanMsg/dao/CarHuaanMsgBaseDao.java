package com.cnfantasia.server.domainbase.carHuaanMsg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carHuaanMsg.entity.CarHuaanMsg;

/**
 * 描述:(华安临停车缴费通知记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarHuaanMsgBaseDao extends AbstractBaseDao implements ICarHuaanMsgBaseDao{
	/**
	 * 根据条件查询(华安临停车缴费通知记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarHuaanMsg> selectCarHuaanMsgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carHuaanMsgBase.select_carHuaanMsg",paramMap);
	}
	/**
	 * 按条件分页查询(华安临停车缴费通知记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarHuaanMsg> selectCarHuaanMsgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarHuaanMsgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarHuaanMsg> resMap= sqlSession.selectList("carHuaanMsgBase.select_carHuaanMsg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(华安临停车缴费通知记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarHuaanMsg selectCarHuaanMsgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carHuaanMsgBase.select_carHuaanMsg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(华安临停车缴费通知记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarHuaanMsgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carHuaanMsgBase.select_carHuaanMsg_count", paramMap);
	}
	/**
	 * 往(华安临停车缴费通知记录)新增一条记录
	 * @param carHuaanMsg
	 * @return
	 */
	@Override
	public int insertCarHuaanMsg(CarHuaanMsg carHuaanMsg){
		return sqlSession.insert("carHuaanMsgBase.insert_carHuaanMsg",carHuaanMsg);
	}
	/**
	 * 批量新增(华安临停车缴费通知记录)信息
	 * @param carHuaanMsgList
	 * @return
	 */
	@Override
	public int insertCarHuaanMsgBatch(List<CarHuaanMsg> carHuaanMsgList) {
		if (carHuaanMsgList == null || carHuaanMsgList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = carHuaanMsgList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<CarHuaanMsg> batchList = carHuaanMsgList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("carHuaanMsgBase.insert_carHuaanMsg_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(华安临停车缴费通知记录)信息
	 * @param carHuaanMsg
	 * @return
	 */
	@Override
	public int updateCarHuaanMsg(CarHuaanMsg carHuaanMsg){
		return sqlSession.update("carHuaanMsgBase.update_carHuaanMsg", carHuaanMsg);
	}
	/**
	 * 批量更新(华安临停车缴费通知记录)信息
	 * @param carHuaanMsgList
	 * @return
	 */
	@Override
	public int updateCarHuaanMsgBatch(List<CarHuaanMsg> carHuaanMsgList) {
		if (carHuaanMsgList == null || carHuaanMsgList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("carHuaanMsgBase.update_carHuaanMsg_Batch", carHuaanMsgList);
	}
	
	/**
	 * 根据序列号删除(华安临停车缴费通知记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarHuaanMsgLogic(java.math.BigInteger id){
		CarHuaanMsg carHuaanMsg = new CarHuaanMsg();
		carHuaanMsg.setId(id);
		carHuaanMsg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carHuaanMsgBase.delete_carHuaanMsg_Logic",carHuaanMsg);
	}
	
	/**
	 * 根据Id 批量删除(华安临停车缴费通知记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarHuaanMsgLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<CarHuaanMsg> delList = new java.util.ArrayList<CarHuaanMsg>();
		for(java.math.BigInteger id:idList){
			CarHuaanMsg carHuaanMsg = new CarHuaanMsg();
			carHuaanMsg.setId(id);
			carHuaanMsg.setSys0DelState(RecordState_DELETED);
			delList.add(carHuaanMsg);
		}
		return sqlSession.update("carHuaanMsgBase.delete_carHuaanMsg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(华安临停车缴费通知记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarHuaanMsg(java.math.BigInteger id){
//		return sqlSession.delete("carHuaanMsgBase.delete_carHuaanMsg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(华安临停车缴费通知记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarHuaanMsgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carHuaanMsgBase.delete_carHuaanMsg_Batch", idList);
//	}
	
	
}

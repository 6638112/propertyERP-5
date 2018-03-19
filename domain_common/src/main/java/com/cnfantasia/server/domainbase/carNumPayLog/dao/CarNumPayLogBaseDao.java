package com.cnfantasia.server.domainbase.carNumPayLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;

/**
 * 描述:(月卡车缴费记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarNumPayLogBaseDao extends AbstractBaseDao implements ICarNumPayLogBaseDao{
	/**
	 * 根据条件查询(月卡车缴费记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarNumPayLog> selectCarNumPayLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carNumPayLogBase.select_carNumPayLog",paramMap);
	}
	/**
	 * 按条件分页查询(月卡车缴费记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarNumPayLog> selectCarNumPayLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarNumPayLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarNumPayLog> resMap= sqlSession.selectList("carNumPayLogBase.select_carNumPayLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(月卡车缴费记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarNumPayLog selectCarNumPayLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carNumPayLogBase.select_carNumPayLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(月卡车缴费记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarNumPayLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carNumPayLogBase.select_carNumPayLog_count", paramMap);
	}
	/**
	 * 往(月卡车缴费记录)新增一条记录
	 * @param carNumPayLog
	 * @return
	 */
	@Override
	public int insertCarNumPayLog(CarNumPayLog carNumPayLog){
		return sqlSession.insert("carNumPayLogBase.insert_carNumPayLog",carNumPayLog);
	}
	/**
	 * 批量新增(月卡车缴费记录)信息
	 * @param carNumPayLogList
	 * @return
	 */
	@Override
	public int insertCarNumPayLogBatch(List<CarNumPayLog> carNumPayLogList) {
		return sqlSession.insert("carNumPayLogBase.insert_carNumPayLog_Batch",carNumPayLogList);
	}
	
	/**
	 * 更新(月卡车缴费记录)信息
	 * @param carNumPayLog
	 * @return
	 */
	@Override
	public int updateCarNumPayLog(CarNumPayLog carNumPayLog){
		return sqlSession.update("carNumPayLogBase.update_carNumPayLog", carNumPayLog);
	}
	/**
	 * 批量更新(月卡车缴费记录)信息
	 * @param carNumPayLogList
	 * @return
	 */
	@Override
	public int updateCarNumPayLogBatch(List<? extends CarNumPayLog> carNumPayLogList) {
		return sqlSession.update("carNumPayLogBase.update_carNumPayLog_Batch", carNumPayLogList);
	}
	
	/**
	 * 根据序列号删除(月卡车缴费记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarNumPayLogLogic(java.math.BigInteger id){
		CarNumPayLog carNumPayLog = new CarNumPayLog();
		carNumPayLog.setId(id);
		carNumPayLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carNumPayLogBase.delete_carNumPayLog_Logic",carNumPayLog);
	}
	
	/**
	 * 根据Id 批量删除(月卡车缴费记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarNumPayLogLogicBatch(List<java.math.BigInteger> idList) {
		List<CarNumPayLog> delList = new java.util.ArrayList<CarNumPayLog>();
		for(java.math.BigInteger id:idList){
			CarNumPayLog carNumPayLog = new CarNumPayLog();
			carNumPayLog.setId(id);
			carNumPayLog.setSys0DelState(RecordState_DELETED);
			delList.add(carNumPayLog);
		}
		return sqlSession.update("carNumPayLogBase.delete_carNumPayLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(月卡车缴费记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumPayLog(java.math.BigInteger id){
//		return sqlSession.delete("carNumPayLogBase.delete_carNumPayLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(月卡车缴费记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumPayLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carNumPayLogBase.delete_carNumPayLog_Batch", idList);
//	}
	
	
}

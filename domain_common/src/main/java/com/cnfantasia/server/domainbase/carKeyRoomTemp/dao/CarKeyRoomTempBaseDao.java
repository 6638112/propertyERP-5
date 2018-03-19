package com.cnfantasia.server.domainbase.carKeyRoomTemp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carKeyRoomTemp.entity.CarKeyRoomTemp;

/**
 * 描述:(门牌车牌信息临时表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarKeyRoomTempBaseDao extends AbstractBaseDao implements ICarKeyRoomTempBaseDao{
	/**
	 * 根据条件查询(门牌车牌信息临时表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarKeyRoomTemp> selectCarKeyRoomTempByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carKeyRoomTempBase.select_carKeyRoomTemp",paramMap);
	}
	/**
	 * 按条件分页查询(门牌车牌信息临时表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarKeyRoomTemp> selectCarKeyRoomTempByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarKeyRoomTempCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarKeyRoomTemp> resMap= sqlSession.selectList("carKeyRoomTempBase.select_carKeyRoomTemp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(门牌车牌信息临时表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarKeyRoomTemp selectCarKeyRoomTempBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carKeyRoomTempBase.select_carKeyRoomTemp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(门牌车牌信息临时表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarKeyRoomTempCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carKeyRoomTempBase.select_carKeyRoomTemp_count", paramMap);
	}
	/**
	 * 往(门牌车牌信息临时表)新增一条记录
	 * @param carKeyRoomTemp
	 * @return
	 */
	@Override
	public int insertCarKeyRoomTemp(CarKeyRoomTemp carKeyRoomTemp){
		return sqlSession.insert("carKeyRoomTempBase.insert_carKeyRoomTemp",carKeyRoomTemp);
	}
	/**
	 * 批量新增(门牌车牌信息临时表)信息
	 * @param carKeyRoomTempList
	 * @return
	 */
	@Override
	public int insertCarKeyRoomTempBatch(List<CarKeyRoomTemp> carKeyRoomTempList) {
		return sqlSession.insert("carKeyRoomTempBase.insert_carKeyRoomTemp_Batch",carKeyRoomTempList);
	}
	
	/**
	 * 更新(门牌车牌信息临时表)信息
	 * @param carKeyRoomTemp
	 * @return
	 */
	@Override
	public int updateCarKeyRoomTemp(CarKeyRoomTemp carKeyRoomTemp){
		return sqlSession.update("carKeyRoomTempBase.update_carKeyRoomTemp", carKeyRoomTemp);
	}
	/**
	 * 批量更新(门牌车牌信息临时表)信息
	 * @param carKeyRoomTempList
	 * @return
	 */
	@Override
	public int updateCarKeyRoomTempBatch(List<CarKeyRoomTemp> carKeyRoomTempList) {
		return sqlSession.update("carKeyRoomTempBase.update_carKeyRoomTemp_Batch", carKeyRoomTempList);
	}
	
	/**
	 * 根据序列号删除(门牌车牌信息临时表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarKeyRoomTempLogic(java.math.BigInteger id){
		CarKeyRoomTemp carKeyRoomTemp = new CarKeyRoomTemp();
		carKeyRoomTemp.setId(id);
		carKeyRoomTemp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carKeyRoomTempBase.delete_carKeyRoomTemp_Logic",carKeyRoomTemp);
	}
	
	/**
	 * 根据Id 批量删除(门牌车牌信息临时表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarKeyRoomTempLogicBatch(List<java.math.BigInteger> idList) {
		List<CarKeyRoomTemp> delList = new java.util.ArrayList<CarKeyRoomTemp>();
		for(java.math.BigInteger id:idList){
			CarKeyRoomTemp carKeyRoomTemp = new CarKeyRoomTemp();
			carKeyRoomTemp.setId(id);
			carKeyRoomTemp.setSys0DelState(RecordState_DELETED);
			delList.add(carKeyRoomTemp);
		}
		return sqlSession.update("carKeyRoomTempBase.delete_carKeyRoomTemp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(门牌车牌信息临时表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarKeyRoomTemp(java.math.BigInteger id){
//		return sqlSession.delete("carKeyRoomTempBase.delete_carKeyRoomTemp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(门牌车牌信息临时表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarKeyRoomTempBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carKeyRoomTempBase.delete_carKeyRoomTemp_Batch", idList);
//	}
	
	
}

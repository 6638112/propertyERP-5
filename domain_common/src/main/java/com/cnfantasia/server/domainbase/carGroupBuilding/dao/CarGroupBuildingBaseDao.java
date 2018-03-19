package com.cnfantasia.server.domainbase.carGroupBuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;

/**
 * 描述:(小区车禁配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarGroupBuildingBaseDao extends AbstractBaseDao implements ICarGroupBuildingBaseDao{
	/**
	 * 根据条件查询(小区车禁配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarGroupBuilding> selectCarGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carGroupBuildingBase.select_carGroupBuilding",paramMap);
	}
	/**
	 * 按条件分页查询(小区车禁配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarGroupBuilding> selectCarGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarGroupBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarGroupBuilding> resMap= sqlSession.selectList("carGroupBuildingBase.select_carGroupBuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区车禁配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarGroupBuilding selectCarGroupBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carGroupBuildingBase.select_carGroupBuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区车禁配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarGroupBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carGroupBuildingBase.select_carGroupBuilding_count", paramMap);
	}
	/**
	 * 往(小区车禁配置表)新增一条记录
	 * @param carGroupBuilding
	 * @return
	 */
	@Override
	public int insertCarGroupBuilding(CarGroupBuilding carGroupBuilding){
		return sqlSession.insert("carGroupBuildingBase.insert_carGroupBuilding",carGroupBuilding);
	}
	/**
	 * 批量新增(小区车禁配置表)信息
	 * @param carGroupBuildingList
	 * @return
	 */
	@Override
	public int insertCarGroupBuildingBatch(List<CarGroupBuilding> carGroupBuildingList) {
		return sqlSession.insert("carGroupBuildingBase.insert_carGroupBuilding_Batch",carGroupBuildingList);
	}
	
	/**
	 * 更新(小区车禁配置表)信息
	 * @param carGroupBuilding
	 * @return
	 */
	@Override
	public int updateCarGroupBuilding(CarGroupBuilding carGroupBuilding){
		return sqlSession.update("carGroupBuildingBase.update_carGroupBuilding", carGroupBuilding);
	}
	/**
	 * 批量更新(小区车禁配置表)信息
	 * @param carGroupBuildingList
	 * @return
	 */
	@Override
	public int updateCarGroupBuildingBatch(List<CarGroupBuilding> carGroupBuildingList) {
		return sqlSession.update("carGroupBuildingBase.update_carGroupBuilding_Batch", carGroupBuildingList);
	}
	
	/**
	 * 根据序列号删除(小区车禁配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarGroupBuildingLogic(java.math.BigInteger id){
		CarGroupBuilding carGroupBuilding = new CarGroupBuilding();
		carGroupBuilding.setId(id);
		carGroupBuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carGroupBuildingBase.delete_carGroupBuilding_Logic",carGroupBuilding);
	}
	
	/**
	 * 根据Id 批量删除(小区车禁配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarGroupBuildingLogicBatch(List<java.math.BigInteger> idList) {
		List<CarGroupBuilding> delList = new java.util.ArrayList<CarGroupBuilding>();
		for(java.math.BigInteger id:idList){
			CarGroupBuilding carGroupBuilding = new CarGroupBuilding();
			carGroupBuilding.setId(id);
			carGroupBuilding.setSys0DelState(RecordState_DELETED);
			delList.add(carGroupBuilding);
		}
		return sqlSession.update("carGroupBuildingBase.delete_carGroupBuilding_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区车禁配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarGroupBuilding(java.math.BigInteger id){
//		return sqlSession.delete("carGroupBuildingBase.delete_carGroupBuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区车禁配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarGroupBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carGroupBuildingBase.delete_carGroupBuilding_Batch", idList);
//	}
	
	
}

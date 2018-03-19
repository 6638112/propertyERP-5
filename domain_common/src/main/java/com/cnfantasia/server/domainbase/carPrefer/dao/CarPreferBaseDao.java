package com.cnfantasia.server.domainbase.carPrefer.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carPrefer.entity.CarPrefer;

/**
 * 描述:(车禁随机立减) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarPreferBaseDao extends AbstractBaseDao implements ICarPreferBaseDao{
	/**
	 * 根据条件查询(车禁随机立减)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarPrefer> selectCarPreferByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carPreferBase.select_carPrefer",paramMap);
	}
	/**
	 * 按条件分页查询(车禁随机立减)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarPrefer> selectCarPreferByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarPreferCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarPrefer> resMap= sqlSession.selectList("carPreferBase.select_carPrefer_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(车禁随机立减)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarPrefer selectCarPreferBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carPreferBase.select_carPrefer_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(车禁随机立减)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarPreferCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carPreferBase.select_carPrefer_count", paramMap);
	}
	/**
	 * 往(车禁随机立减)新增一条记录
	 * @param carPrefer
	 * @return
	 */
	@Override
	public int insertCarPrefer(CarPrefer carPrefer){
		return sqlSession.insert("carPreferBase.insert_carPrefer",carPrefer);
	}
	/**
	 * 批量新增(车禁随机立减)信息
	 * @param carPreferList
	 * @return
	 */
	@Override
	public int insertCarPreferBatch(List<CarPrefer> carPreferList) {
		return sqlSession.insert("carPreferBase.insert_carPrefer_Batch",carPreferList);
	}
	
	/**
	 * 更新(车禁随机立减)信息
	 * @param carPrefer
	 * @return
	 */
	@Override
	public int updateCarPrefer(CarPrefer carPrefer){
		return sqlSession.update("carPreferBase.update_carPrefer", carPrefer);
	}
	/**
	 * 批量更新(车禁随机立减)信息
	 * @param carPreferList
	 * @return
	 */
	@Override
	public int updateCarPreferBatch(List<CarPrefer> carPreferList) {
		return sqlSession.update("carPreferBase.update_carPrefer_Batch", carPreferList);
	}
	
	/**
	 * 根据序列号删除(车禁随机立减)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCarPreferLogic(java.math.BigInteger id){
		CarPrefer carPrefer = new CarPrefer();
		carPrefer.setId(id);
		carPrefer.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carPreferBase.delete_carPrefer_Logic",carPrefer);
	}
	 */
	/**
	 * 根据Id 批量删除(车禁随机立减)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCarPreferLogicBatch(List<java.math.BigInteger> idList) {
		List<CarPrefer> delList = new java.util.ArrayList<CarPrefer>();
		for(java.math.BigInteger id:idList){
			CarPrefer carPrefer = new CarPrefer();
			carPrefer.setId(id);
			carPrefer.setSys0DelState(RecordState_DELETED);
			delList.add(carPrefer);
		}
		return sqlSession.update("carPreferBase.delete_carPrefer_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(车禁随机立减)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarPrefer(java.math.BigInteger id){
//		return sqlSession.delete("carPreferBase.delete_carPrefer", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车禁随机立减)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarPreferBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carPreferBase.delete_carPrefer_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.carNumList.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;

/**
 * 描述:(车牌表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarNumListBaseDao extends AbstractBaseDao implements ICarNumListBaseDao{
	/**
	 * 根据条件查询(车牌表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarNumList> selectCarNumListByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carNumListBase.select_carNumList",paramMap);
	}
	/**
	 * 按条件分页查询(车牌表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarNumList> selectCarNumListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarNumListCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarNumList> resMap= sqlSession.selectList("carNumListBase.select_carNumList_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(车牌表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarNumList selectCarNumListBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carNumListBase.select_carNumList_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(车牌表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarNumListCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carNumListBase.select_carNumList_count", paramMap);
	}
	/**
	 * 往(车牌表)新增一条记录
	 * @param carNumList
	 * @return
	 */
	@Override
	public int insertCarNumList(CarNumList carNumList){
		return sqlSession.insert("carNumListBase.insert_carNumList",carNumList);
	}
	/**
	 * 批量新增(车牌表)信息
	 * @param carNumListList
	 * @return
	 */
	@Override
	public int insertCarNumListBatch(List<CarNumList> carNumListList) {
		return sqlSession.insert("carNumListBase.insert_carNumList_Batch",carNumListList);
	}
	
	/**
	 * 更新(车牌表)信息
	 * @param carNumList
	 * @return
	 */
	@Override
	public int updateCarNumList(CarNumList carNumList){
		return sqlSession.update("carNumListBase.update_carNumList", carNumList);
	}
	/**
	 * 批量更新(车牌表)信息
	 * @param carNumListList
	 * @return
	 */
	@Override
	public int updateCarNumListBatch(List<CarNumList> carNumListList) {
		return sqlSession.update("carNumListBase.update_carNumList_Batch", carNumListList);
	}
	
	/**
	 * 根据序列号删除(车牌表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarNumListLogic(java.math.BigInteger id){
		CarNumList carNumList = new CarNumList();
		carNumList.setId(id);
		carNumList.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carNumListBase.delete_carNumList_Logic",carNumList);
	}
	
	/**
	 * 根据Id 批量删除(车牌表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarNumListLogicBatch(List<java.math.BigInteger> idList) {
		List<CarNumList> delList = new java.util.ArrayList<CarNumList>();
		for(java.math.BigInteger id:idList){
			CarNumList carNumList = new CarNumList();
			carNumList.setId(id);
			carNumList.setSys0DelState(RecordState_DELETED);
			delList.add(carNumList);
		}
		return sqlSession.update("carNumListBase.delete_carNumList_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(车牌表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumList(java.math.BigInteger id){
//		return sqlSession.delete("carNumListBase.delete_carNumList", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(车牌表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarNumListBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carNumListBase.delete_carNumList_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.newUserLocation.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.newUserLocation.entity.NewUserLocation;

/**
 * 描述:(新用户第一次打开app定位结果信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class NewUserLocationBaseDao extends AbstractBaseDao implements INewUserLocationBaseDao{
	/**
	 * 根据条件查询(新用户第一次打开app定位结果信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<NewUserLocation> selectNewUserLocationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("newUserLocationBase.select_newUserLocation",paramMap);
	}
	/**
	 * 按条件分页查询(新用户第一次打开app定位结果信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<NewUserLocation> selectNewUserLocationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectNewUserLocationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<NewUserLocation> resMap= sqlSession.selectList("newUserLocationBase.select_newUserLocation_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(新用户第一次打开app定位结果信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public NewUserLocation selectNewUserLocationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("newUserLocationBase.select_newUserLocation_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(新用户第一次打开app定位结果信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectNewUserLocationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("newUserLocationBase.select_newUserLocation_count", paramMap);
	}
	/**
	 * 往(新用户第一次打开app定位结果信息)新增一条记录
	 * @param newUserLocation
	 * @return
	 */
	@Override
	public int insertNewUserLocation(NewUserLocation newUserLocation){
		return sqlSession.insert("newUserLocationBase.insert_newUserLocation",newUserLocation);
	}
	/**
	 * 批量新增(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocationList
	 * @return
	 */
	@Override
	public int insertNewUserLocationBatch(List<NewUserLocation> newUserLocationList) {
		if (newUserLocationList == null || newUserLocationList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = newUserLocationList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<NewUserLocation> batchList = newUserLocationList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("newUserLocationBase.insert_newUserLocation_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocation
	 * @return
	 */
	@Override
	public int updateNewUserLocation(NewUserLocation newUserLocation){
		return sqlSession.update("newUserLocationBase.update_newUserLocation", newUserLocation);
	}
	/**
	 * 批量更新(新用户第一次打开app定位结果信息)信息
	 * @param newUserLocationList
	 * @return
	 */
	@Override
	public int updateNewUserLocationBatch(List<NewUserLocation> newUserLocationList) {
		if (newUserLocationList == null || newUserLocationList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("newUserLocationBase.update_newUserLocation_Batch", newUserLocationList);
	}
	
	/**
	 * 根据序列号删除(新用户第一次打开app定位结果信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteNewUserLocationLogic(java.math.BigInteger id){
		NewUserLocation newUserLocation = new NewUserLocation();
		newUserLocation.setId(id);
		newUserLocation.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("newUserLocationBase.delete_newUserLocation_Logic",newUserLocation);
	}
	
	/**
	 * 根据Id 批量删除(新用户第一次打开app定位结果信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteNewUserLocationLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<NewUserLocation> delList = new java.util.ArrayList<NewUserLocation>();
		for(java.math.BigInteger id:idList){
			NewUserLocation newUserLocation = new NewUserLocation();
			newUserLocation.setId(id);
			newUserLocation.setSys0DelState(RecordState_DELETED);
			delList.add(newUserLocation);
		}
		return sqlSession.update("newUserLocationBase.delete_newUserLocation_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(新用户第一次打开app定位结果信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteNewUserLocation(java.math.BigInteger id){
//		return sqlSession.delete("newUserLocationBase.delete_newUserLocation", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(新用户第一次打开app定位结果信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteNewUserLocationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("newUserLocationBase.delete_newUserLocation_Batch", idList);
//	}
	
	
}

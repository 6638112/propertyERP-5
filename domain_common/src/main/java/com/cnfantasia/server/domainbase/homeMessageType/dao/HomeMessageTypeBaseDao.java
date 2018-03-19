package com.cnfantasia.server.domainbase.homeMessageType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.homeMessageType.entity.HomeMessageType;

/**
 * 描述:(首页消息类型表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class HomeMessageTypeBaseDao extends AbstractBaseDao implements IHomeMessageTypeBaseDao{
	/**
	 * 根据条件查询(首页消息类型表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<HomeMessageType> selectHomeMessageTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("homeMessageTypeBase.select_homeMessageType",paramMap);
	}
	/**
	 * 按条件分页查询(首页消息类型表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<HomeMessageType> selectHomeMessageTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectHomeMessageTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<HomeMessageType> resMap= sqlSession.selectList("homeMessageTypeBase.select_homeMessageType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(首页消息类型表)信息
	 * @param id
	 * @return
	 */
	@Override
	public HomeMessageType selectHomeMessageTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("homeMessageTypeBase.select_homeMessageType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(首页消息类型表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectHomeMessageTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("homeMessageTypeBase.select_homeMessageType_count", paramMap);
	}
	/**
	 * 往(首页消息类型表)新增一条记录
	 * @param homeMessageType
	 * @return
	 */
	@Override
	public int insertHomeMessageType(HomeMessageType homeMessageType){
		return sqlSession.insert("homeMessageTypeBase.insert_homeMessageType",homeMessageType);
	}
	/**
	 * 批量新增(首页消息类型表)信息
	 * @param homeMessageTypeList
	 * @return
	 */
	@Override
	public int insertHomeMessageTypeBatch(List<HomeMessageType> homeMessageTypeList) {
		if (homeMessageTypeList == null || homeMessageTypeList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = homeMessageTypeList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<HomeMessageType> batchList = homeMessageTypeList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("homeMessageTypeBase.insert_homeMessageType_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(首页消息类型表)信息
	 * @param homeMessageType
	 * @return
	 */
	@Override
	public int updateHomeMessageType(HomeMessageType homeMessageType){
		return sqlSession.update("homeMessageTypeBase.update_homeMessageType", homeMessageType);
	}
	/**
	 * 批量更新(首页消息类型表)信息
	 * @param homeMessageTypeList
	 * @return
	 */
	@Override
	public int updateHomeMessageTypeBatch(List<HomeMessageType> homeMessageTypeList) {
		if (homeMessageTypeList == null || homeMessageTypeList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("homeMessageTypeBase.update_homeMessageType_Batch", homeMessageTypeList);
	}
	
	/**
	 * 根据序列号删除(首页消息类型表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteHomeMessageTypeLogic(java.math.BigInteger id){
		HomeMessageType homeMessageType = new HomeMessageType();
		homeMessageType.setId(id);
		homeMessageType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("homeMessageTypeBase.delete_homeMessageType_Logic",homeMessageType);
	}
	
	/**
	 * 根据Id 批量删除(首页消息类型表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteHomeMessageTypeLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<HomeMessageType> delList = new java.util.ArrayList<HomeMessageType>();
		for(java.math.BigInteger id:idList){
			HomeMessageType homeMessageType = new HomeMessageType();
			homeMessageType.setId(id);
			homeMessageType.setSys0DelState(RecordState_DELETED);
			delList.add(homeMessageType);
		}
		return sqlSession.update("homeMessageTypeBase.delete_homeMessageType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(首页消息类型表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteHomeMessageType(java.math.BigInteger id){
//		return sqlSession.delete("homeMessageTypeBase.delete_homeMessageType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页消息类型表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteHomeMessageTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("homeMessageTypeBase.delete_homeMessageType_Batch", idList);
//	}
	
	
}

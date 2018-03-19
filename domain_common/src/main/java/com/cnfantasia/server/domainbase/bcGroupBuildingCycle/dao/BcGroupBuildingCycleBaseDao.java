package com.cnfantasia.server.domainbase.bcGroupBuildingCycle.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;

/**
 * 描述:(托收账期记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcGroupBuildingCycleBaseDao extends AbstractBaseDao implements IBcGroupBuildingCycleBaseDao{
	/**
	 * 根据条件查询(托收账期记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycle> selectBcGroupBuildingCycleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcGroupBuildingCycleBase.select_bcGroupBuildingCycle",paramMap);
	}
	/**
	 * 按条件分页查询(托收账期记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycle> selectBcGroupBuildingCycleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcGroupBuildingCycleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcGroupBuildingCycle> resMap= sqlSession.selectList("bcGroupBuildingCycleBase.select_bcGroupBuildingCycle_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(托收账期记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcGroupBuildingCycle selectBcGroupBuildingCycleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcGroupBuildingCycleBase.select_bcGroupBuildingCycle_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(托收账期记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcGroupBuildingCycleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcGroupBuildingCycleBase.select_bcGroupBuildingCycle_count", paramMap);
	}
	/**
	 * 往(托收账期记录)新增一条记录
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle){
		return sqlSession.insert("bcGroupBuildingCycleBase.insert_bcGroupBuildingCycle",bcGroupBuildingCycle);
	}
	/**
	 * 批量新增(托收账期记录)信息
	 * @param bcGroupBuildingCycleList
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycleBatch(List<BcGroupBuildingCycle> bcGroupBuildingCycleList) {
		if (bcGroupBuildingCycleList == null || bcGroupBuildingCycleList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcGroupBuildingCycleList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcGroupBuildingCycle> batchList = bcGroupBuildingCycleList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcGroupBuildingCycleBase.insert_bcGroupBuildingCycle_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(托收账期记录)信息
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle){
		return sqlSession.update("bcGroupBuildingCycleBase.update_bcGroupBuildingCycle", bcGroupBuildingCycle);
	}
	/**
	 * 批量更新(托收账期记录)信息
	 * @param bcGroupBuildingCycleList
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycleBatch(List<BcGroupBuildingCycle> bcGroupBuildingCycleList) {
		if (bcGroupBuildingCycleList == null || bcGroupBuildingCycleList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcGroupBuildingCycleBase.update_bcGroupBuildingCycle_Batch", bcGroupBuildingCycleList);
	}
	
	/**
	 * 根据序列号删除(托收账期记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleLogic(java.math.BigInteger id){
		BcGroupBuildingCycle bcGroupBuildingCycle = new BcGroupBuildingCycle();
		bcGroupBuildingCycle.setId(id);
		bcGroupBuildingCycle.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcGroupBuildingCycleBase.delete_bcGroupBuildingCycle_Logic",bcGroupBuildingCycle);
	}
	
	/**
	 * 根据Id 批量删除(托收账期记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcGroupBuildingCycle> delList = new java.util.ArrayList<BcGroupBuildingCycle>();
		for(java.math.BigInteger id:idList){
			BcGroupBuildingCycle bcGroupBuildingCycle = new BcGroupBuildingCycle();
			bcGroupBuildingCycle.setId(id);
			bcGroupBuildingCycle.setSys0DelState(RecordState_DELETED);
			delList.add(bcGroupBuildingCycle);
		}
		return sqlSession.update("bcGroupBuildingCycleBase.delete_bcGroupBuildingCycle_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(托收账期记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycle(java.math.BigInteger id){
//		return sqlSession.delete("bcGroupBuildingCycleBase.delete_bcGroupBuildingCycle", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(托收账期记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcGroupBuildingCycleBase.delete_bcGroupBuildingCycle_Batch", idList);
//	}
	
	
}

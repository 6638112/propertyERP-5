package com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity.BcGroupBuildingCycleEntry;

/**
 * 描述:(银行托收账期明细) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcGroupBuildingCycleEntryBaseDao extends AbstractBaseDao implements IBcGroupBuildingCycleEntryBaseDao{
	/**
	 * 根据条件查询(银行托收账期明细)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycleEntry> selectBcGroupBuildingCycleEntryByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcGroupBuildingCycleEntryBase.select_bcGroupBuildingCycleEntry",paramMap);
	}
	/**
	 * 按条件分页查询(银行托收账期明细)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycleEntry> selectBcGroupBuildingCycleEntryByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcGroupBuildingCycleEntryCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcGroupBuildingCycleEntry> resMap= sqlSession.selectList("bcGroupBuildingCycleEntryBase.select_bcGroupBuildingCycleEntry_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(银行托收账期明细)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcGroupBuildingCycleEntry selectBcGroupBuildingCycleEntryBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcGroupBuildingCycleEntryBase.select_bcGroupBuildingCycleEntry_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(银行托收账期明细)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcGroupBuildingCycleEntryCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcGroupBuildingCycleEntryBase.select_bcGroupBuildingCycleEntry_count", paramMap);
	}
	/**
	 * 往(银行托收账期明细)新增一条记录
	 * @param bcGroupBuildingCycleEntry
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry){
		return sqlSession.insert("bcGroupBuildingCycleEntryBase.insert_bcGroupBuildingCycleEntry",bcGroupBuildingCycleEntry);
	}
	/**
	 * 批量新增(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntryList
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycleEntryBatch(List<BcGroupBuildingCycleEntry> bcGroupBuildingCycleEntryList) {
		if (bcGroupBuildingCycleEntryList == null || bcGroupBuildingCycleEntryList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcGroupBuildingCycleEntryList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcGroupBuildingCycleEntry> batchList = bcGroupBuildingCycleEntryList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcGroupBuildingCycleEntryBase.insert_bcGroupBuildingCycleEntry_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntry
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry){
		return sqlSession.update("bcGroupBuildingCycleEntryBase.update_bcGroupBuildingCycleEntry", bcGroupBuildingCycleEntry);
	}
	/**
	 * 批量更新(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntryList
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycleEntryBatch(List<BcGroupBuildingCycleEntry> bcGroupBuildingCycleEntryList) {
		if (bcGroupBuildingCycleEntryList == null || bcGroupBuildingCycleEntryList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcGroupBuildingCycleEntryBase.update_bcGroupBuildingCycleEntry_Batch", bcGroupBuildingCycleEntryList);
	}
	
	/**
	 * 根据序列号删除(银行托收账期明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleEntryLogic(java.math.BigInteger id){
		BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry = new BcGroupBuildingCycleEntry();
		bcGroupBuildingCycleEntry.setId(id);
		bcGroupBuildingCycleEntry.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcGroupBuildingCycleEntryBase.delete_bcGroupBuildingCycleEntry_Logic",bcGroupBuildingCycleEntry);
	}
	
	/**
	 * 根据Id 批量删除(银行托收账期明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleEntryLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcGroupBuildingCycleEntry> delList = new java.util.ArrayList<BcGroupBuildingCycleEntry>();
		for(java.math.BigInteger id:idList){
			BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry = new BcGroupBuildingCycleEntry();
			bcGroupBuildingCycleEntry.setId(id);
			bcGroupBuildingCycleEntry.setSys0DelState(RecordState_DELETED);
			delList.add(bcGroupBuildingCycleEntry);
		}
		return sqlSession.update("bcGroupBuildingCycleEntryBase.delete_bcGroupBuildingCycleEntry_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(银行托收账期明细)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycleEntry(java.math.BigInteger id){
//		return sqlSession.delete("bcGroupBuildingCycleEntryBase.delete_bcGroupBuildingCycleEntry", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(银行托收账期明细)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycleEntryBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcGroupBuildingCycleEntryBase.delete_bcGroupBuildingCycleEntry_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.sourceOfSupply.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.sourceOfSupply.entity.SourceOfSupply;

/**
 * 描述:(供应商的货源地) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SourceOfSupplyBaseDao extends AbstractBaseDao implements ISourceOfSupplyBaseDao{
	/**
	 * 根据条件查询(供应商的货源地)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SourceOfSupply> selectSourceOfSupplyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("sourceOfSupplyBase.select_sourceOfSupply",paramMap);
	}
	/**
	 * 按条件分页查询(供应商的货源地)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SourceOfSupply> selectSourceOfSupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSourceOfSupplyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SourceOfSupply> resMap= sqlSession.selectList("sourceOfSupplyBase.select_sourceOfSupply_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商的货源地)信息
	 * @param id
	 * @return
	 */
	@Override
	public SourceOfSupply selectSourceOfSupplyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("sourceOfSupplyBase.select_sourceOfSupply_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商的货源地)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSourceOfSupplyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("sourceOfSupplyBase.select_sourceOfSupply_count", paramMap);
	}
	/**
	 * 往(供应商的货源地)新增一条记录
	 * @param sourceOfSupply
	 * @return
	 */
	@Override
	public int insertSourceOfSupply(SourceOfSupply sourceOfSupply){
		return sqlSession.insert("sourceOfSupplyBase.insert_sourceOfSupply",sourceOfSupply);
	}
	/**
	 * 批量新增(供应商的货源地)信息
	 * @param sourceOfSupplyList
	 * @return
	 */
	@Override
	public int insertSourceOfSupplyBatch(List<SourceOfSupply> sourceOfSupplyList) {
		if (sourceOfSupplyList == null || sourceOfSupplyList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = sourceOfSupplyList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<SourceOfSupply> batchList = sourceOfSupplyList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("sourceOfSupplyBase.insert_sourceOfSupply_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(供应商的货源地)信息
	 * @param sourceOfSupply
	 * @return
	 */
	@Override
	public int updateSourceOfSupply(SourceOfSupply sourceOfSupply){
		return sqlSession.update("sourceOfSupplyBase.update_sourceOfSupply", sourceOfSupply);
	}
	/**
	 * 批量更新(供应商的货源地)信息
	 * @param sourceOfSupplyList
	 * @return
	 */
	@Override
	public int updateSourceOfSupplyBatch(List<SourceOfSupply> sourceOfSupplyList) {
		if (sourceOfSupplyList == null || sourceOfSupplyList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("sourceOfSupplyBase.update_sourceOfSupply_Batch", sourceOfSupplyList);
	}
	
	/**
	 * 根据序列号删除(供应商的货源地)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSourceOfSupplyLogic(java.math.BigInteger id){
		SourceOfSupply sourceOfSupply = new SourceOfSupply();
		sourceOfSupply.setId(id);
		sourceOfSupply.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("sourceOfSupplyBase.delete_sourceOfSupply_Logic",sourceOfSupply);
	}
	
	/**
	 * 根据Id 批量删除(供应商的货源地)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSourceOfSupplyLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<SourceOfSupply> delList = new java.util.ArrayList<SourceOfSupply>();
		for(java.math.BigInteger id:idList){
			SourceOfSupply sourceOfSupply = new SourceOfSupply();
			sourceOfSupply.setId(id);
			sourceOfSupply.setSys0DelState(RecordState_DELETED);
			delList.add(sourceOfSupply);
		}
		return sqlSession.update("sourceOfSupplyBase.delete_sourceOfSupply_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商的货源地)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSourceOfSupply(java.math.BigInteger id){
//		return sqlSession.delete("sourceOfSupplyBase.delete_sourceOfSupply", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商的货源地)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSourceOfSupplyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("sourceOfSupplyBase.delete_sourceOfSupply_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasProductSpec.entity.DredgeBillHasProductSpec;

/**
 * 描述:(维修服务商品规格表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillHasProductSpecBaseDao extends AbstractBaseDao implements IDredgeBillHasProductSpecBaseDao{
	/**
	 * 根据条件查询(维修服务商品规格表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillHasProductSpec> selectDredgeBillHasProductSpecByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillHasProductSpecBase.select_dredgeBillHasProductSpec",paramMap);
	}
	/**
	 * 按条件分页查询(维修服务商品规格表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillHasProductSpec> selectDredgeBillHasProductSpecByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillHasProductSpecCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillHasProductSpec> resMap= sqlSession.selectList("dredgeBillHasProductSpecBase.select_dredgeBillHasProductSpec_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修服务商品规格表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillHasProductSpec selectDredgeBillHasProductSpecBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillHasProductSpecBase.select_dredgeBillHasProductSpec_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillHasProductSpecCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillHasProductSpecBase.select_dredgeBillHasProductSpec_count", paramMap);
	}
	/**
	 * 往(维修服务商品规格表)新增一条记录
	 * @param dredgeBillHasProductSpec
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProductSpec(DredgeBillHasProductSpec dredgeBillHasProductSpec){
		return sqlSession.insert("dredgeBillHasProductSpecBase.insert_dredgeBillHasProductSpec",dredgeBillHasProductSpec);
	}
	/**
	 * 批量新增(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpecList
	 * @return
	 */
	@Override
	public int insertDredgeBillHasProductSpecBatch(List<DredgeBillHasProductSpec> dredgeBillHasProductSpecList) {
		if (dredgeBillHasProductSpecList == null || dredgeBillHasProductSpecList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dredgeBillHasProductSpecList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DredgeBillHasProductSpec> batchList = dredgeBillHasProductSpecList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dredgeBillHasProductSpecBase.insert_dredgeBillHasProductSpec_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpec
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProductSpec(DredgeBillHasProductSpec dredgeBillHasProductSpec){
		return sqlSession.update("dredgeBillHasProductSpecBase.update_dredgeBillHasProductSpec", dredgeBillHasProductSpec);
	}
	/**
	 * 批量更新(维修服务商品规格表)信息
	 * @param dredgeBillHasProductSpecList
	 * @return
	 */
	@Override
	public int updateDredgeBillHasProductSpecBatch(List<DredgeBillHasProductSpec> dredgeBillHasProductSpecList) {
		if (dredgeBillHasProductSpecList == null || dredgeBillHasProductSpecList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dredgeBillHasProductSpecBase.update_dredgeBillHasProductSpec_Batch", dredgeBillHasProductSpecList);
	}
	
	/**
	 * 根据序列号删除(维修服务商品规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProductSpecLogic(java.math.BigInteger id){
		DredgeBillHasProductSpec dredgeBillHasProductSpec = new DredgeBillHasProductSpec();
		dredgeBillHasProductSpec.setId(id);
		dredgeBillHasProductSpec.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillHasProductSpecBase.delete_dredgeBillHasProductSpec_Logic",dredgeBillHasProductSpec);
	}
	
	/**
	 * 根据Id 批量删除(维修服务商品规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasProductSpecLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DredgeBillHasProductSpec> delList = new java.util.ArrayList<DredgeBillHasProductSpec>();
		for(java.math.BigInteger id:idList){
			DredgeBillHasProductSpec dredgeBillHasProductSpec = new DredgeBillHasProductSpec();
			dredgeBillHasProductSpec.setId(id);
			dredgeBillHasProductSpec.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillHasProductSpec);
		}
		return sqlSession.update("dredgeBillHasProductSpecBase.delete_dredgeBillHasProductSpec_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修服务商品规格表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProductSpec(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillHasProductSpecBase.delete_dredgeBillHasProductSpec", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasProductSpecBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillHasProductSpecBase.delete_dredgeBillHasProductSpec_Batch", idList);
//	}
	
	
}

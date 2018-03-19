package com.cnfantasia.server.domainbase.dredgeProductSpecification.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

/**
 * 描述:(维修服务商品规格表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeProductSpecificationBaseDao extends AbstractBaseDao implements IDredgeProductSpecificationBaseDao{
	/**
	 * 根据条件查询(维修服务商品规格表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeProductSpecification> selectDredgeProductSpecificationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeProductSpecificationBase.select_dredgeProductSpecification",paramMap);
	}
	/**
	 * 按条件分页查询(维修服务商品规格表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeProductSpecification> selectDredgeProductSpecificationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeProductSpecificationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeProductSpecification> resMap= sqlSession.selectList("dredgeProductSpecificationBase.select_dredgeProductSpecification_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修服务商品规格表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeProductSpecification selectDredgeProductSpecificationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeProductSpecificationBase.select_dredgeProductSpecification_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品规格表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeProductSpecificationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeProductSpecificationBase.select_dredgeProductSpecification_count", paramMap);
	}
	/**
	 * 往(维修服务商品规格表)新增一条记录
	 * @param dredgeProductSpecification
	 * @return
	 */
	@Override
	public int insertDredgeProductSpecification(DredgeProductSpecification dredgeProductSpecification){
		return sqlSession.insert("dredgeProductSpecificationBase.insert_dredgeProductSpecification",dredgeProductSpecification);
	}
	/**
	 * 批量新增(维修服务商品规格表)信息
	 * @param dredgeProductSpecificationList
	 * @return
	 */
	@Override
	public int insertDredgeProductSpecificationBatch(List<DredgeProductSpecification> dredgeProductSpecificationList) {
		if (dredgeProductSpecificationList == null || dredgeProductSpecificationList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dredgeProductSpecificationList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DredgeProductSpecification> batchList = dredgeProductSpecificationList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dredgeProductSpecificationBase.insert_dredgeProductSpecification_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(维修服务商品规格表)信息
	 * @param dredgeProductSpecification
	 * @return
	 */
	@Override
	public int updateDredgeProductSpecification(DredgeProductSpecification dredgeProductSpecification){
		return sqlSession.update("dredgeProductSpecificationBase.update_dredgeProductSpecification", dredgeProductSpecification);
	}
	/**
	 * 批量更新(维修服务商品规格表)信息
	 * @param dredgeProductSpecificationList
	 * @return
	 */
	@Override
	public int updateDredgeProductSpecificationBatch(List<DredgeProductSpecification> dredgeProductSpecificationList) {
		if (dredgeProductSpecificationList == null || dredgeProductSpecificationList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dredgeProductSpecificationBase.update_dredgeProductSpecification_Batch", dredgeProductSpecificationList);
	}
	
	/**
	 * 根据序列号删除(维修服务商品规格表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductSpecificationLogic(java.math.BigInteger id){
		DredgeProductSpecification dredgeProductSpecification = new DredgeProductSpecification();
		dredgeProductSpecification.setId(id);
		dredgeProductSpecification.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeProductSpecificationBase.delete_dredgeProductSpecification_Logic",dredgeProductSpecification);
	}
	
	/**
	 * 根据Id 批量删除(维修服务商品规格表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductSpecificationLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DredgeProductSpecification> delList = new java.util.ArrayList<DredgeProductSpecification>();
		for(java.math.BigInteger id:idList){
			DredgeProductSpecification dredgeProductSpecification = new DredgeProductSpecification();
			dredgeProductSpecification.setId(id);
			dredgeProductSpecification.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeProductSpecification);
		}
		return sqlSession.update("dredgeProductSpecificationBase.delete_dredgeProductSpecification_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修服务商品规格表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProductSpecification(java.math.BigInteger id){
//		return sqlSession.delete("dredgeProductSpecificationBase.delete_dredgeProductSpecification", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品规格表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProductSpecificationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeProductSpecificationBase.delete_dredgeProductSpecification_Batch", idList);
//	}
	
	
}

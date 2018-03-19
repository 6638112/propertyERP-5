package com.cnfantasia.server.domainbase.ebuyOrderRelation.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderRelation.entity.EbuyOrderRelation;

/**
 * 描述:(总订单与子订单关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyOrderRelationBaseDao extends AbstractBaseDao implements IEbuyOrderRelationBaseDao{
	/**
	 * 根据条件查询(总订单与子订单关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderRelation> selectEbuyOrderRelationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyOrderRelationBase.select_ebuyOrderRelation",paramMap);
	}
	/**
	 * 按条件分页查询(总订单与子订单关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderRelation> selectEbuyOrderRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyOrderRelationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyOrderRelation> resMap= sqlSession.selectList("ebuyOrderRelationBase.select_ebuyOrderRelation_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(总订单与子订单关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderRelation selectEbuyOrderRelationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyOrderRelationBase.select_ebuyOrderRelation_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(总订单与子订单关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyOrderRelationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyOrderRelationBase.select_ebuyOrderRelation_count", paramMap);
	}
	/**
	 * 往(总订单与子订单关系表)新增一条记录
	 * @param ebuyOrderRelation
	 * @return
	 */
	@Override
	public int insertEbuyOrderRelation(EbuyOrderRelation ebuyOrderRelation){
		return sqlSession.insert("ebuyOrderRelationBase.insert_ebuyOrderRelation",ebuyOrderRelation);
	}
	/**
	 * 批量新增(总订单与子订单关系表)信息
	 * @param ebuyOrderRelationList
	 * @return
	 */
	@Override
	public int insertEbuyOrderRelationBatch(List<EbuyOrderRelation> ebuyOrderRelationList) {
		if (ebuyOrderRelationList == null || ebuyOrderRelationList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = ebuyOrderRelationList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<EbuyOrderRelation> batchList = ebuyOrderRelationList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("ebuyOrderRelationBase.insert_ebuyOrderRelation_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(总订单与子订单关系表)信息
	 * @param ebuyOrderRelation
	 * @return
	 */
	@Override
	public int updateEbuyOrderRelation(EbuyOrderRelation ebuyOrderRelation){
		return sqlSession.update("ebuyOrderRelationBase.update_ebuyOrderRelation", ebuyOrderRelation);
	}
	/**
	 * 批量更新(总订单与子订单关系表)信息
	 * @param ebuyOrderRelationList
	 * @return
	 */
	@Override
	public int updateEbuyOrderRelationBatch(List<EbuyOrderRelation> ebuyOrderRelationList) {
		if (ebuyOrderRelationList == null || ebuyOrderRelationList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("ebuyOrderRelationBase.update_ebuyOrderRelation_Batch", ebuyOrderRelationList);
	}
	
	/**
	 * 根据序列号删除(总订单与子订单关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderRelationLogic(java.math.BigInteger id){
		EbuyOrderRelation ebuyOrderRelation = new EbuyOrderRelation();
		ebuyOrderRelation.setId(id);
		ebuyOrderRelation.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyOrderRelationBase.delete_ebuyOrderRelation_Logic",ebuyOrderRelation);
	}
	
	/**
	 * 根据Id 批量删除(总订单与子订单关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderRelationLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<EbuyOrderRelation> delList = new java.util.ArrayList<EbuyOrderRelation>();
		for(java.math.BigInteger id:idList){
			EbuyOrderRelation ebuyOrderRelation = new EbuyOrderRelation();
			ebuyOrderRelation.setId(id);
			ebuyOrderRelation.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyOrderRelation);
		}
		return sqlSession.update("ebuyOrderRelationBase.delete_ebuyOrderRelation_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(总订单与子订单关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderRelation(java.math.BigInteger id){
//		return sqlSession.delete("ebuyOrderRelationBase.delete_ebuyOrderRelation", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(总订单与子订单关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderRelationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyOrderRelationBase.delete_ebuyOrderRelation_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUserHasTEbuySupplyMerchant.entity.OmsUserHasTEbuySupplyMerchant;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsUserHasTEbuySupplyMerchantBaseDao extends AbstractBaseDao implements IOmsUserHasTEbuySupplyMerchantBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUserHasTEbuySupplyMerchant> selectOmsUserHasTEbuySupplyMerchantByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsUserHasTEbuySupplyMerchantBase.select_omsUserHasTEbuySupplyMerchant",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUserHasTEbuySupplyMerchant> selectOmsUserHasTEbuySupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsUserHasTEbuySupplyMerchantCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsUserHasTEbuySupplyMerchant> resMap= sqlSession.selectList("omsUserHasTEbuySupplyMerchantBase.select_omsUserHasTEbuySupplyMerchant_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserHasTEbuySupplyMerchant selectOmsUserHasTEbuySupplyMerchantBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsUserHasTEbuySupplyMerchantBase.select_omsUserHasTEbuySupplyMerchant_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsUserHasTEbuySupplyMerchantCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsUserHasTEbuySupplyMerchantBase.select_omsUserHasTEbuySupplyMerchant_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	@Override
	public int insertOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant){
		return sqlSession.insert("omsUserHasTEbuySupplyMerchantBase.insert_omsUserHasTEbuySupplyMerchant",omsUserHasTEbuySupplyMerchant);
	}
	/**
	 * 批量新增()信息
	 * @param omsUserHasTEbuySupplyMerchantList
	 * @return
	 */
	@Override
	public int insertOmsUserHasTEbuySupplyMerchantBatch(List<OmsUserHasTEbuySupplyMerchant> omsUserHasTEbuySupplyMerchantList) {
		return sqlSession.insert("omsUserHasTEbuySupplyMerchantBase.insert_omsUserHasTEbuySupplyMerchant_Batch",omsUserHasTEbuySupplyMerchantList);
	}
	
	/**
	 * 更新()信息
	 * @param omsUserHasTEbuySupplyMerchant
	 * @return
	 */
	@Override
	public int updateOmsUserHasTEbuySupplyMerchant(OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant){
		return sqlSession.update("omsUserHasTEbuySupplyMerchantBase.update_omsUserHasTEbuySupplyMerchant", omsUserHasTEbuySupplyMerchant);
	}
	/**
	 * 批量更新()信息
	 * @param omsUserHasTEbuySupplyMerchantList
	 * @return
	 */
	@Override
	public int updateOmsUserHasTEbuySupplyMerchantBatch(List<OmsUserHasTEbuySupplyMerchant> omsUserHasTEbuySupplyMerchantList) {
		return sqlSession.update("omsUserHasTEbuySupplyMerchantBase.update_omsUserHasTEbuySupplyMerchant_Batch", omsUserHasTEbuySupplyMerchantList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTEbuySupplyMerchantLogic(java.math.BigInteger id){
		OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant = new OmsUserHasTEbuySupplyMerchant();
		omsUserHasTEbuySupplyMerchant.setId(id);
		omsUserHasTEbuySupplyMerchant.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsUserHasTEbuySupplyMerchantBase.delete_omsUserHasTEbuySupplyMerchant_Logic",omsUserHasTEbuySupplyMerchant);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTEbuySupplyMerchantLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsUserHasTEbuySupplyMerchant> delList = new java.util.ArrayList<OmsUserHasTEbuySupplyMerchant>();
		for(java.math.BigInteger id:idList){
			OmsUserHasTEbuySupplyMerchant omsUserHasTEbuySupplyMerchant = new OmsUserHasTEbuySupplyMerchant();
			omsUserHasTEbuySupplyMerchant.setId(id);
			omsUserHasTEbuySupplyMerchant.setSys0DelState(RecordState_DELETED);
			delList.add(omsUserHasTEbuySupplyMerchant);
		}
		return sqlSession.update("omsUserHasTEbuySupplyMerchantBase.delete_omsUserHasTEbuySupplyMerchant_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTEbuySupplyMerchant(java.math.BigInteger id){
//		return sqlSession.delete("omsUserHasTEbuySupplyMerchantBase.delete_omsUserHasTEbuySupplyMerchant", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTEbuySupplyMerchantBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsUserHasTEbuySupplyMerchantBase.delete_omsUserHasTEbuySupplyMerchant_Batch", idList);
//	}
	
	
}

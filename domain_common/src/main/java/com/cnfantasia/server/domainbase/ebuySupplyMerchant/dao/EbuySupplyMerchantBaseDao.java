package com.cnfantasia.server.domainbase.ebuySupplyMerchant.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

/**
 * 描述:(供应商) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuySupplyMerchantBaseDao extends AbstractBaseDao implements IEbuySupplyMerchantBaseDao{
	/**
	 * 根据条件查询(供应商)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> selectEbuySupplyMerchantByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuySupplyMerchantBase.select_ebuySupplyMerchant",paramMap);
	}
	/**
	 * 按条件分页查询(供应商)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchant> selectEbuySupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuySupplyMerchantCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuySupplyMerchant> resMap= sqlSession.selectList("ebuySupplyMerchantBase.select_ebuySupplyMerchant_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchant selectEbuySupplyMerchantBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuySupplyMerchantBase.select_ebuySupplyMerchant_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuySupplyMerchantCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuySupplyMerchantBase.select_ebuySupplyMerchant_count", paramMap);
	}
	/**
	 * 往(供应商)新增一条记录
	 * @param ebuySupplyMerchant
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant){
		return sqlSession.insert("ebuySupplyMerchantBase.insert_ebuySupplyMerchant",ebuySupplyMerchant);
	}
	/**
	 * 批量新增(供应商)信息
	 * @param ebuySupplyMerchantList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantBatch(List<EbuySupplyMerchant> ebuySupplyMerchantList) {
		return sqlSession.insert("ebuySupplyMerchantBase.insert_ebuySupplyMerchant_Batch",ebuySupplyMerchantList);
	}
	
	/**
	 * 更新(供应商)信息
	 * @param ebuySupplyMerchant
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant){
		return sqlSession.update("ebuySupplyMerchantBase.update_ebuySupplyMerchant", ebuySupplyMerchant);
	}
	/**
	 * 批量更新(供应商)信息
	 * @param ebuySupplyMerchantList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantBatch(List<EbuySupplyMerchant> ebuySupplyMerchantList) {
		return sqlSession.update("ebuySupplyMerchantBase.update_ebuySupplyMerchant_Batch", ebuySupplyMerchantList);
	}
	
	/**
	 * 根据序列号删除(供应商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLogic(java.math.BigInteger id){
		EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
		ebuySupplyMerchant.setId(id);
		ebuySupplyMerchant.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuySupplyMerchantBase.delete_ebuySupplyMerchant_Logic",ebuySupplyMerchant);
	}
	
	/**
	 * 根据Id 批量删除(供应商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuySupplyMerchant> delList = new java.util.ArrayList<EbuySupplyMerchant>();
		for(java.math.BigInteger id:idList){
			EbuySupplyMerchant ebuySupplyMerchant = new EbuySupplyMerchant();
			ebuySupplyMerchant.setId(id);
			ebuySupplyMerchant.setSys0DelState(RecordState_DELETED);
			delList.add(ebuySupplyMerchant);
		}
		return sqlSession.update("ebuySupplyMerchantBase.delete_ebuySupplyMerchant_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchant(java.math.BigInteger id){
//		return sqlSession.delete("ebuySupplyMerchantBase.delete_ebuySupplyMerchant", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuySupplyMerchantBase.delete_ebuySupplyMerchant_Batch", idList);
//	}
	
	
}

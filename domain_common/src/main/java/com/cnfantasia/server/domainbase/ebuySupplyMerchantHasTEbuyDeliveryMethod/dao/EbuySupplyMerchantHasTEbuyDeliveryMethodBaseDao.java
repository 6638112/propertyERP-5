package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasTEbuyDeliveryMethod.entity.EbuySupplyMerchantHasTEbuyDeliveryMethod;

/**
 * 描述:(供应商支持的配送方式) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao extends AbstractBaseDao implements IEbuySupplyMerchantHasTEbuyDeliveryMethodBaseDao{
	/**
	 * 根据条件查询(供应商支持的配送方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.select_ebuySupplyMerchantHasTEbuyDeliveryMethod",paramMap);
	}
	/**
	 * 按条件分页查询(供应商支持的配送方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasTEbuyDeliveryMethod> selectEbuySupplyMerchantHasTEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuySupplyMerchantHasTEbuyDeliveryMethodCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuySupplyMerchantHasTEbuyDeliveryMethod> resMap= sqlSession.selectList("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.select_ebuySupplyMerchantHasTEbuyDeliveryMethod_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商支持的配送方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantHasTEbuyDeliveryMethod selectEbuySupplyMerchantHasTEbuyDeliveryMethodBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.select_ebuySupplyMerchantHasTEbuyDeliveryMethod_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商支持的配送方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuySupplyMerchantHasTEbuyDeliveryMethodCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.select_ebuySupplyMerchantHasTEbuyDeliveryMethod_count", paramMap);
	}
	/**
	 * 往(供应商支持的配送方式)新增一条记录
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod){
		return sqlSession.insert("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.insert_ebuySupplyMerchantHasTEbuyDeliveryMethod",ebuySupplyMerchantHasTEbuyDeliveryMethod);
	}
	/**
	 * 批量新增(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<EbuySupplyMerchantHasTEbuyDeliveryMethod> ebuySupplyMerchantHasTEbuyDeliveryMethodList) {
		return sqlSession.insert("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.insert_ebuySupplyMerchantHasTEbuyDeliveryMethod_Batch",ebuySupplyMerchantHasTEbuyDeliveryMethodList);
	}
	
	/**
	 * 更新(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethod
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasTEbuyDeliveryMethod(EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod){
		return sqlSession.update("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.update_ebuySupplyMerchantHasTEbuyDeliveryMethod", ebuySupplyMerchantHasTEbuyDeliveryMethod);
	}
	/**
	 * 批量更新(供应商支持的配送方式)信息
	 * @param ebuySupplyMerchantHasTEbuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<EbuySupplyMerchantHasTEbuyDeliveryMethod> ebuySupplyMerchantHasTEbuyDeliveryMethodList) {
		return sqlSession.update("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.update_ebuySupplyMerchantHasTEbuyDeliveryMethod_Batch", ebuySupplyMerchantHasTEbuyDeliveryMethodList);
	}
	
	/**
	 * 根据序列号删除(供应商支持的配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogic(java.math.BigInteger id){
		EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
		ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(id);
		ebuySupplyMerchantHasTEbuyDeliveryMethod.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.delete_ebuySupplyMerchantHasTEbuyDeliveryMethod_Logic",ebuySupplyMerchantHasTEbuyDeliveryMethod);
	}
	
	/**
	 * 根据Id 批量删除(供应商支持的配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuySupplyMerchantHasTEbuyDeliveryMethod> delList = new java.util.ArrayList<EbuySupplyMerchantHasTEbuyDeliveryMethod>();
		for(java.math.BigInteger id:idList){
			EbuySupplyMerchantHasTEbuyDeliveryMethod ebuySupplyMerchantHasTEbuyDeliveryMethod = new EbuySupplyMerchantHasTEbuyDeliveryMethod();
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setId(id);
			ebuySupplyMerchantHasTEbuyDeliveryMethod.setSys0DelState(RecordState_DELETED);
			delList.add(ebuySupplyMerchantHasTEbuyDeliveryMethod);
		}
		return sqlSession.update("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.delete_ebuySupplyMerchantHasTEbuyDeliveryMethod_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商支持的配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethod(java.math.BigInteger id){
//		return sqlSession.delete("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.delete_ebuySupplyMerchantHasTEbuyDeliveryMethod", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商支持的配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasTEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuySupplyMerchantHasTEbuyDeliveryMethodBase.delete_ebuySupplyMerchantHasTEbuyDeliveryMethod_Batch", idList);
//	}
	
	
}

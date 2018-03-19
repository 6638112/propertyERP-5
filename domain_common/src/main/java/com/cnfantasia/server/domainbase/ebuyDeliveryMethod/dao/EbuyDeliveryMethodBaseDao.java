package com.cnfantasia.server.domainbase.ebuyDeliveryMethod.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;

/**
 * 描述:(配送方式) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyDeliveryMethodBaseDao extends AbstractBaseDao implements IEbuyDeliveryMethodBaseDao{
	/**
	 * 根据条件查询(配送方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryMethodBase.select_ebuyDeliveryMethod",paramMap);
	}
	/**
	 * 按条件分页查询(配送方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryMethod> selectEbuyDeliveryMethodByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyDeliveryMethodCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyDeliveryMethod> resMap= sqlSession.selectList("ebuyDeliveryMethodBase.select_ebuyDeliveryMethod_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(配送方式)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryMethod selectEbuyDeliveryMethodBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyDeliveryMethodBase.select_ebuyDeliveryMethod_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(配送方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyDeliveryMethodCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyDeliveryMethodBase.select_ebuyDeliveryMethod_count", paramMap);
	}
	/**
	 * 往(配送方式)新增一条记录
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod){
		return sqlSession.insert("ebuyDeliveryMethodBase.insert_ebuyDeliveryMethod",ebuyDeliveryMethod);
	}
	/**
	 * 批量新增(配送方式)信息
	 * @param ebuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryMethodBatch(List<EbuyDeliveryMethod> ebuyDeliveryMethodList) {
		return sqlSession.insert("ebuyDeliveryMethodBase.insert_ebuyDeliveryMethod_Batch",ebuyDeliveryMethodList);
	}
	
	/**
	 * 更新(配送方式)信息
	 * @param ebuyDeliveryMethod
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryMethod(EbuyDeliveryMethod ebuyDeliveryMethod){
		return sqlSession.update("ebuyDeliveryMethodBase.update_ebuyDeliveryMethod", ebuyDeliveryMethod);
	}
	/**
	 * 批量更新(配送方式)信息
	 * @param ebuyDeliveryMethodList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryMethodBatch(List<EbuyDeliveryMethod> ebuyDeliveryMethodList) {
		return sqlSession.update("ebuyDeliveryMethodBase.update_ebuyDeliveryMethod_Batch", ebuyDeliveryMethodList);
	}
	
	/**
	 * 根据序列号删除(配送方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryMethodLogic(java.math.BigInteger id){
		EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
		ebuyDeliveryMethod.setId(id);
		ebuyDeliveryMethod.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyDeliveryMethodBase.delete_ebuyDeliveryMethod_Logic",ebuyDeliveryMethod);
	}
	
	/**
	 * 根据Id 批量删除(配送方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryMethodLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyDeliveryMethod> delList = new java.util.ArrayList<EbuyDeliveryMethod>();
		for(java.math.BigInteger id:idList){
			EbuyDeliveryMethod ebuyDeliveryMethod = new EbuyDeliveryMethod();
			ebuyDeliveryMethod.setId(id);
			ebuyDeliveryMethod.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyDeliveryMethod);
		}
		return sqlSession.update("ebuyDeliveryMethodBase.delete_ebuyDeliveryMethod_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(配送方式)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryMethod(java.math.BigInteger id){
//		return sqlSession.delete("ebuyDeliveryMethodBase.delete_ebuyDeliveryMethod", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(配送方式)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryMethodBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyDeliveryMethodBase.delete_ebuyDeliveryMethod_Batch", idList);
//	}
	
	
}

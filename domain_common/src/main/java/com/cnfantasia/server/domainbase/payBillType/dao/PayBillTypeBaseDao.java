package com.cnfantasia.server.domainbase.payBillType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 描述:(账单类型基础信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayBillTypeBaseDao extends AbstractBaseDao implements IPayBillTypeBaseDao{
	/**
	 * 根据条件查询(账单类型基础信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillType> selectPayBillTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payBillTypeBase.select_payBillType",paramMap);
	}
	/**
	 * 按条件分页查询(账单类型基础信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillType> selectPayBillTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayBillTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayBillType> resMap= sqlSession.selectList("payBillTypeBase.select_payBillType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(账单类型基础信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillType selectPayBillTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payBillTypeBase.select_payBillType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(账单类型基础信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayBillTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payBillTypeBase.select_payBillType_count", paramMap);
	}
	/**
	 * 往(账单类型基础信息)新增一条记录
	 * @param payBillType
	 * @return
	 */
	@Override
	public int insertPayBillType(PayBillType payBillType){
		return sqlSession.insert("payBillTypeBase.insert_payBillType",payBillType);
	}
	/**
	 * 批量新增(账单类型基础信息)信息
	 * @param payBillTypeList
	 * @return
	 */
	@Override
	public int insertPayBillTypeBatch(List<PayBillType> payBillTypeList) {
		return sqlSession.insert("payBillTypeBase.insert_payBillType_Batch",payBillTypeList);
	}
	
	/**
	 * 更新(账单类型基础信息)信息
	 * @param payBillType
	 * @return
	 */
	@Override
	public int updatePayBillType(PayBillType payBillType){
		return sqlSession.update("payBillTypeBase.update_payBillType", payBillType);
	}
	/**
	 * 批量更新(账单类型基础信息)信息
	 * @param payBillTypeList
	 * @return
	 */
	@Override
	public int updatePayBillTypeBatch(List<PayBillType> payBillTypeList) {
		return sqlSession.update("payBillTypeBase.update_payBillType_Batch", payBillTypeList);
	}
	
	/**
	 * 根据序列号删除(账单类型基础信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillTypeLogic(java.math.BigInteger id){
		PayBillType payBillType = new PayBillType();
		payBillType.setId(id);
		payBillType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payBillTypeBase.delete_payBillType_Logic",payBillType);
	}
	
	/**
	 * 根据Id 批量删除(账单类型基础信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<PayBillType> delList = new java.util.ArrayList<PayBillType>();
		for(java.math.BigInteger id:idList){
			PayBillType payBillType = new PayBillType();
			payBillType.setId(id);
			payBillType.setSys0DelState(RecordState_DELETED);
			delList.add(payBillType);
		}
		return sqlSession.update("payBillTypeBase.delete_payBillType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(账单类型基础信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillType(java.math.BigInteger id){
//		return sqlSession.delete("payBillTypeBase.delete_payBillType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单类型基础信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payBillTypeBase.delete_payBillType_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.payKeyList.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payKeyList.entity.PayKeyList;

/**
 * 描述:(云钥匙付款列表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayKeyListBaseDao extends AbstractBaseDao implements IPayKeyListBaseDao{
	/**
	 * 根据条件查询(云钥匙付款列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayKeyList> selectPayKeyListByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payKeyListBase.select_payKeyList",paramMap);
	}
	/**
	 * 按条件分页查询(云钥匙付款列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayKeyList> selectPayKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayKeyListCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayKeyList> resMap= sqlSession.selectList("payKeyListBase.select_payKeyList_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(云钥匙付款列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayKeyList selectPayKeyListBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payKeyListBase.select_payKeyList_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(云钥匙付款列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayKeyListCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payKeyListBase.select_payKeyList_count", paramMap);
	}
	/**
	 * 往(云钥匙付款列表)新增一条记录
	 * @param payKeyList
	 * @return
	 */
	@Override
	public int insertPayKeyList(PayKeyList payKeyList){
		return sqlSession.insert("payKeyListBase.insert_payKeyList",payKeyList);
	}
	/**
	 * 批量新增(云钥匙付款列表)信息
	 * @param payKeyListList
	 * @return
	 */
	@Override
	public int insertPayKeyListBatch(List<PayKeyList> payKeyListList) {
		return sqlSession.insert("payKeyListBase.insert_payKeyList_Batch",payKeyListList);
	}
	
	/**
	 * 更新(云钥匙付款列表)信息
	 * @param payKeyList
	 * @return
	 */
	@Override
	public int updatePayKeyList(PayKeyList payKeyList){
		return sqlSession.update("payKeyListBase.update_payKeyList", payKeyList);
	}
	/**
	 * 批量更新(云钥匙付款列表)信息
	 * @param payKeyListList
	 * @return
	 */
	@Override
	public int updatePayKeyListBatch(List<PayKeyList> payKeyListList) {
		return sqlSession.update("payKeyListBase.update_payKeyList_Batch", payKeyListList);
	}
	
	/**
	 * 根据序列号删除(云钥匙付款列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayKeyListLogic(java.math.BigInteger id){
		PayKeyList payKeyList = new PayKeyList();
		payKeyList.setId(id);
		payKeyList.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payKeyListBase.delete_payKeyList_Logic",payKeyList);
	}
	
	/**
	 * 根据Id 批量删除(云钥匙付款列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayKeyListLogicBatch(List<java.math.BigInteger> idList) {
		List<PayKeyList> delList = new java.util.ArrayList<PayKeyList>();
		for(java.math.BigInteger id:idList){
			PayKeyList payKeyList = new PayKeyList();
			payKeyList.setId(id);
			payKeyList.setSys0DelState(RecordState_DELETED);
			delList.add(payKeyList);
		}
		return sqlSession.update("payKeyListBase.delete_payKeyList_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(云钥匙付款列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayKeyList(java.math.BigInteger id){
//		return sqlSession.delete("payKeyListBase.delete_payKeyList", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(云钥匙付款列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayKeyListBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payKeyListBase.delete_payKeyList_Batch", idList);
//	}
	
	
}

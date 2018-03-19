package com.cnfantasia.server.api.paybillUserPrefer.dao;

import java.math.BigInteger;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.api.paybillUserPrefer.entity.PaybillUserPrefer;
import com.cnfantasia.server.business.pub.entity.PageModel;


/**
 * 描述:(账单优惠用户关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PaybillUserPreferDao extends AbstractBaseDao implements IPaybillUserPreferDao{
	/**
	 * 根据条件查询(账单优惠用户关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> selectPaybillUserPreferByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("paybillUserPreferBase.select_paybillUserPrefer",paramMap);
	}
	/**
	 * 按条件分页查询(账单优惠用户关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PaybillUserPrefer> selectPaybillUserPreferByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPaybillUserPreferCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PaybillUserPrefer> resMap= sqlSession.selectList("paybillUserPreferBase.select_paybillUserPrefer_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(账单优惠用户关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PaybillUserPrefer selectPaybillUserPreferBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("paybillUserPreferBase.select_paybillUserPrefer_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(账单优惠用户关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPaybillUserPreferCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("paybillUserPrefer.select_paybillUserPrefer_count", paramMap);
	}
	/**
	 * 往(账单优惠用户关系表)新增一条记录
	 * @param paybillUserPrefer
	 * @return
	 */
	@Override
	public int insertPaybillUserPrefer(PaybillUserPrefer paybillUserPrefer){
		return sqlSession.insert("paybillUserPrefer.insert_paybillUserPrefer",paybillUserPrefer);
	}
	/**
	 * 批量新增(账单优惠用户关系表)信息
	 * @param paybillUserPreferList
	 * @return
	 */
	@Override
	public int insertPaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList) {
		return sqlSession.insert("paybillUserPrefer.insert_paybillUserPrefer_Batch",paybillUserPreferList);
	}
	
	/**
	 * 更新(账单优惠用户关系表)信息
	 * @param paybillUserPrefer
	 * @return
	 */
	@Override
	public int updatePaybillUserPrefer(PaybillUserPrefer paybillUserPrefer){
		return sqlSession.update("paybillUserPrefer.update_paybillUserPrefer", paybillUserPrefer);
	}
	/**
	 * 批量更新(账单优惠用户关系表)信息
	 * @param paybillUserPreferList
	 * @return
	 */
	@Override
	public int updatePaybillUserPreferBatch(List<PaybillUserPrefer> paybillUserPreferList) {
		return sqlSession.update("paybillUserPrefer.update_paybillUserPrefer_Batch", paybillUserPreferList);
	}
	
	@Override
	public PaybillUserPrefer getUserBillPrefer(BigInteger payBillId, BigInteger userId) {
		Map<String, BigInteger> parpMap = new HashMap<String, BigInteger>();
		parpMap.put("payBillId", payBillId);
		parpMap.put("userId", userId);
		return sqlSession.selectOne("paybillUserPrefer.select_paybillUserPrefer_by_user_bill",parpMap);
	}
	
	/**
	 * 根据序列号删除(账单优惠用户关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deletePaybillUserPreferLogic(java.math.BigInteger id){
		PaybillUserPrefer paybillUserPrefer = new PaybillUserPrefer();
		paybillUserPrefer.setId(id);
		paybillUserPrefer.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("paybillUserPreferBase.delete_paybillUserPrefer_Logic",paybillUserPrefer);
	}
	 */
	/**
	 * 根据Id 批量删除(账单优惠用户关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deletePaybillUserPreferLogicBatch(List<java.math.BigInteger> idList) {
		List<PaybillUserPrefer> delList = new java.util.ArrayList<PaybillUserPrefer>();
		for(java.math.BigInteger id:idList){
			PaybillUserPrefer paybillUserPrefer = new PaybillUserPrefer();
			paybillUserPrefer.setId(id);
			paybillUserPrefer.setSys0DelState(RecordState_DELETED);
			delList.add(paybillUserPrefer);
		}
		return sqlSession.update("paybillUserPreferBase.delete_paybillUserPrefer_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(账单优惠用户关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePaybillUserPrefer(java.math.BigInteger id){
//		return sqlSession.delete("paybillUserPreferBase.delete_paybillUserPrefer", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单优惠用户关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePaybillUserPreferBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("paybillUserPreferBase.delete_paybillUserPrefer_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.payBill.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBill.entity.PayBill;

/**
 * 描述:(物业账单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayBillBaseDao extends AbstractBaseDao implements IPayBillBaseDao{
	/**
	 * 根据条件查询(物业账单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBill> selectPayBillByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payBillBase.select_payBill",paramMap);
	}
	/**
	 * 按条件分页查询(物业账单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBill> selectPayBillByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayBillCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayBill> resMap= sqlSession.selectList("payBillBase.select_payBill_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业账单)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBill selectPayBillBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payBillBase.select_payBill_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业账单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayBillCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payBillBase.select_payBill_count", paramMap);
	}
	/**
	 * 往(物业账单)新增一条记录
	 * @param payBill
	 * @return
	 */
	@Override
	public int insertPayBill(PayBill payBill){
		return sqlSession.insert("payBillBase.insert_payBill",payBill);
	}
	/**
	 * 批量新增(物业账单)信息
	 * @param payBillList
	 * @return
	 */
	@Override
	public int insertPayBillBatch(List<PayBill> payBillList) {
		if (payBillList == null || payBillList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = payBillList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PayBill> batchList = payBillList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("payBillBase.insert_payBill_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业账单)信息
	 * @param payBill
	 * @return
	 */
	@Override
	public int updatePayBill(PayBill payBill){
		return sqlSession.update("payBillBase.update_payBill", payBill);
	}
	/**
	 * 批量更新(物业账单)信息
	 * @param payBillList
	 * @return
	 */
	@Override
	public int updatePayBillBatch(List<PayBill> payBillList) {
		if (payBillList == null || payBillList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("payBillBase.update_payBill_Batch", payBillList);
	}
	
	/**
	 * 根据序列号删除(物业账单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillLogic(java.math.BigInteger id){
		PayBill payBill = new PayBill();
		payBill.setId(id);
		payBill.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payBillBase.delete_payBill_Logic",payBill);
	}
	
	/**
	 * 根据Id 批量删除(物业账单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PayBill> delList = new java.util.ArrayList<PayBill>();
		for(java.math.BigInteger id:idList){
			PayBill payBill = new PayBill();
			payBill.setId(id);
			payBill.setSys0DelState(RecordState_DELETED);
			delList.add(payBill);
		}
		return sqlSession.update("payBillBase.delete_payBill_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业账单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBill(java.math.BigInteger id){
//		return sqlSession.delete("payBillBase.delete_payBill", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业账单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payBillBase.delete_payBill_Batch", idList);
//	}
	
	
}

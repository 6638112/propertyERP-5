package com.cnfantasia.server.domainbase.payConfigHb.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payConfigHb.entity.PayConfigHb;

/**
 * 描述:(粮票支付配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayConfigHbBaseDao extends AbstractBaseDao implements IPayConfigHbBaseDao{
	/**
	 * 根据条件查询(粮票支付配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayConfigHb> selectPayConfigHbByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payConfigHbBase.select_payConfigHb",paramMap);
	}
	/**
	 * 按条件分页查询(粮票支付配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayConfigHb> selectPayConfigHbByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayConfigHbCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayConfigHb> resMap= sqlSession.selectList("payConfigHbBase.select_payConfigHb_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(粮票支付配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayConfigHb selectPayConfigHbBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payConfigHbBase.select_payConfigHb_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(粮票支付配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayConfigHbCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payConfigHbBase.select_payConfigHb_count", paramMap);
	}
	/**
	 * 往(粮票支付配置表)新增一条记录
	 * @param payConfigHb
	 * @return
	 */
	@Override
	public int insertPayConfigHb(PayConfigHb payConfigHb){
		return sqlSession.insert("payConfigHbBase.insert_payConfigHb",payConfigHb);
	}
	/**
	 * 批量新增(粮票支付配置表)信息
	 * @param payConfigHbList
	 * @return
	 */
	@Override
	public int insertPayConfigHbBatch(List<PayConfigHb> payConfigHbList) {
		return sqlSession.insert("payConfigHbBase.insert_payConfigHb_Batch",payConfigHbList);
	}
	
	/**
	 * 更新(粮票支付配置表)信息
	 * @param payConfigHb
	 * @return
	 */
	@Override
	public int updatePayConfigHb(PayConfigHb payConfigHb){
		return sqlSession.update("payConfigHbBase.update_payConfigHb", payConfigHb);
	}
	/**
	 * 批量更新(粮票支付配置表)信息
	 * @param payConfigHbList
	 * @return
	 */
	@Override
	public int updatePayConfigHbBatch(List<PayConfigHb> payConfigHbList) {
		return sqlSession.update("payConfigHbBase.update_payConfigHb_Batch", payConfigHbList);
	}
	
	/**
	 * 根据序列号删除(粮票支付配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayConfigHbLogic(java.math.BigInteger id){
		PayConfigHb payConfigHb = new PayConfigHb();
		payConfigHb.setId(id);
		payConfigHb.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payConfigHbBase.delete_payConfigHb_Logic",payConfigHb);
	}
	
	/**
	 * 根据Id 批量删除(粮票支付配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayConfigHbLogicBatch(List<java.math.BigInteger> idList) {
		List<PayConfigHb> delList = new java.util.ArrayList<PayConfigHb>();
		for(java.math.BigInteger id:idList){
			PayConfigHb payConfigHb = new PayConfigHb();
			payConfigHb.setId(id);
			payConfigHb.setSys0DelState(RecordState_DELETED);
			delList.add(payConfigHb);
		}
		return sqlSession.update("payConfigHbBase.delete_payConfigHb_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(粮票支付配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayConfigHb(java.math.BigInteger id){
//		return sqlSession.delete("payConfigHbBase.delete_payConfigHb", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(粮票支付配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayConfigHbBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payConfigHbBase.delete_payConfigHb_Batch", idList);
//	}
	
	
}

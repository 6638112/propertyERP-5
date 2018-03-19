package com.cnfantasia.server.domainbase.payBillTimeCfg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;

/**
 * 描述:(账单类型对应缴费时间配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayBillTimeCfgBaseDao extends AbstractBaseDao implements IPayBillTimeCfgBaseDao{
	/**
	 * 根据条件查询(账单类型对应缴费时间配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillTimeCfg> selectPayBillTimeCfgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payBillTimeCfgBase.select_payBillTimeCfg",paramMap);
	}
	/**
	 * 按条件分页查询(账单类型对应缴费时间配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayBillTimeCfg> selectPayBillTimeCfgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayBillTimeCfgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayBillTimeCfg> resMap= sqlSession.selectList("payBillTimeCfgBase.select_payBillTimeCfg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(账单类型对应缴费时间配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayBillTimeCfg selectPayBillTimeCfgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payBillTimeCfgBase.select_payBillTimeCfg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(账单类型对应缴费时间配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayBillTimeCfgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payBillTimeCfgBase.select_payBillTimeCfg_count", paramMap);
	}
	/**
	 * 往(账单类型对应缴费时间配置)新增一条记录
	 * @param payBillTimeCfg
	 * @return
	 */
	@Override
	public int insertPayBillTimeCfg(PayBillTimeCfg payBillTimeCfg){
		return sqlSession.insert("payBillTimeCfgBase.insert_payBillTimeCfg",payBillTimeCfg);
	}
	/**
	 * 批量新增(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfgList
	 * @return
	 */
	@Override
	public int insertPayBillTimeCfgBatch(List<PayBillTimeCfg> payBillTimeCfgList) {
		return sqlSession.insert("payBillTimeCfgBase.insert_payBillTimeCfg_Batch",payBillTimeCfgList);
	}
	
	/**
	 * 更新(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfg
	 * @return
	 */
	@Override
	public int updatePayBillTimeCfg(PayBillTimeCfg payBillTimeCfg){
		return sqlSession.update("payBillTimeCfgBase.update_payBillTimeCfg", payBillTimeCfg);
	}
	/**
	 * 批量更新(账单类型对应缴费时间配置)信息
	 * @param payBillTimeCfgList
	 * @return
	 */
	@Override
	public int updatePayBillTimeCfgBatch(List<PayBillTimeCfg> payBillTimeCfgList) {
		return sqlSession.update("payBillTimeCfgBase.update_payBillTimeCfg_Batch", payBillTimeCfgList);
	}
	
	/**
	 * 根据序列号删除(账单类型对应缴费时间配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayBillTimeCfgLogic(java.math.BigInteger id){
		PayBillTimeCfg payBillTimeCfg = new PayBillTimeCfg();
		payBillTimeCfg.setId(id);
		payBillTimeCfg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payBillTimeCfgBase.delete_payBillTimeCfg_Logic",payBillTimeCfg);
	}
	
	/**
	 * 根据Id 批量删除(账单类型对应缴费时间配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayBillTimeCfgLogicBatch(List<java.math.BigInteger> idList) {
		List<PayBillTimeCfg> delList = new java.util.ArrayList<PayBillTimeCfg>();
		for(java.math.BigInteger id:idList){
			PayBillTimeCfg payBillTimeCfg = new PayBillTimeCfg();
			payBillTimeCfg.setId(id);
			payBillTimeCfg.setSys0DelState(RecordState_DELETED);
			delList.add(payBillTimeCfg);
		}
		return sqlSession.update("payBillTimeCfgBase.delete_payBillTimeCfg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(账单类型对应缴费时间配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayBillTimeCfg(java.math.BigInteger id){
//		return sqlSession.delete("payBillTimeCfgBase.delete_payBillTimeCfg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单类型对应缴费时间配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayBillTimeCfgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payBillTimeCfgBase.delete_payBillTimeCfg_Batch", idList);
//	}
	
	
}

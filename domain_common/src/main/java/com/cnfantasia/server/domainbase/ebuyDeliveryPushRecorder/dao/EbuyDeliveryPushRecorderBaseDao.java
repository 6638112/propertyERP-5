package com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity.EbuyDeliveryPushRecorder;

/**
 * 描述:(配送单推送记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyDeliveryPushRecorderBaseDao extends AbstractBaseDao implements IEbuyDeliveryPushRecorderBaseDao{
	/**
	 * 根据条件查询(配送单推送记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryPushRecorder> selectEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryPushRecorderBase.select_ebuyDeliveryPushRecorder",paramMap);
	}
	/**
	 * 按条件分页查询(配送单推送记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryPushRecorder> selectEbuyDeliveryPushRecorderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyDeliveryPushRecorderCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyDeliveryPushRecorder> resMap= sqlSession.selectList("ebuyDeliveryPushRecorderBase.select_ebuyDeliveryPushRecorder_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(配送单推送记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryPushRecorder selectEbuyDeliveryPushRecorderBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyDeliveryPushRecorderBase.select_ebuyDeliveryPushRecorder_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(配送单推送记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyDeliveryPushRecorderCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyDeliveryPushRecorderBase.select_ebuyDeliveryPushRecorder_count", paramMap);
	}
	/**
	 * 往(配送单推送记录)新增一条记录
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder){
		return sqlSession.insert("ebuyDeliveryPushRecorderBase.insert_ebuyDeliveryPushRecorder",ebuyDeliveryPushRecorder);
	}
	/**
	 * 批量新增(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorderList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryPushRecorderBatch(List<EbuyDeliveryPushRecorder> ebuyDeliveryPushRecorderList) {
		return sqlSession.insert("ebuyDeliveryPushRecorderBase.insert_ebuyDeliveryPushRecorder_Batch",ebuyDeliveryPushRecorderList);
	}
	
	/**
	 * 更新(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorder
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder){
		return sqlSession.update("ebuyDeliveryPushRecorderBase.update_ebuyDeliveryPushRecorder", ebuyDeliveryPushRecorder);
	}
	/**
	 * 批量更新(配送单推送记录)信息
	 * @param ebuyDeliveryPushRecorderList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryPushRecorderBatch(List<EbuyDeliveryPushRecorder> ebuyDeliveryPushRecorderList) {
		return sqlSession.update("ebuyDeliveryPushRecorderBase.update_ebuyDeliveryPushRecorder_Batch", ebuyDeliveryPushRecorderList);
	}
	
	/**
	 * 根据序列号删除(配送单推送记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryPushRecorderLogic(java.math.BigInteger id){
		EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder = new EbuyDeliveryPushRecorder();
		ebuyDeliveryPushRecorder.setId(id);
		ebuyDeliveryPushRecorder.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyDeliveryPushRecorderBase.delete_ebuyDeliveryPushRecorder_Logic",ebuyDeliveryPushRecorder);
	}
	
	/**
	 * 根据Id 批量删除(配送单推送记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryPushRecorderLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyDeliveryPushRecorder> delList = new java.util.ArrayList<EbuyDeliveryPushRecorder>();
		for(java.math.BigInteger id:idList){
			EbuyDeliveryPushRecorder ebuyDeliveryPushRecorder = new EbuyDeliveryPushRecorder();
			ebuyDeliveryPushRecorder.setId(id);
			ebuyDeliveryPushRecorder.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyDeliveryPushRecorder);
		}
		return sqlSession.update("ebuyDeliveryPushRecorderBase.delete_ebuyDeliveryPushRecorder_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(配送单推送记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryPushRecorder(java.math.BigInteger id){
//		return sqlSession.delete("ebuyDeliveryPushRecorderBase.delete_ebuyDeliveryPushRecorder", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(配送单推送记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryPushRecorderBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyDeliveryPushRecorderBase.delete_ebuyDeliveryPushRecorder_Batch", idList);
//	}
	
	
}

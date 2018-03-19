package com.cnfantasia.server.domainbase.ebuyPayRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;

/**
 * 描述:(支付记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyPayRecordBaseDao extends AbstractBaseDao implements IEbuyPayRecordBaseDao{
	/**
	 * 根据条件查询(支付记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyPayRecord> selectEbuyPayRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyPayRecordBase.select_ebuyPayRecord",paramMap);
	}
	/**
	 * 按条件分页查询(支付记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyPayRecord> selectEbuyPayRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyPayRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyPayRecord> resMap= sqlSession.selectList("ebuyPayRecordBase.select_ebuyPayRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(支付记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyPayRecord selectEbuyPayRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyPayRecordBase.select_ebuyPayRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(支付记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyPayRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyPayRecordBase.select_ebuyPayRecord_count", paramMap);
	}
	/**
	 * 往(支付记录表)新增一条记录
	 * @param ebuyPayRecord
	 * @return
	 */
	@Override
	public int insertEbuyPayRecord(EbuyPayRecord ebuyPayRecord){
		return sqlSession.insert("ebuyPayRecordBase.insert_ebuyPayRecord",ebuyPayRecord);
	}
	/**
	 * 批量新增(支付记录表)信息
	 * @param ebuyPayRecordList
	 * @return
	 */
	@Override
	public int insertEbuyPayRecordBatch(List<EbuyPayRecord> ebuyPayRecordList) {
		return sqlSession.insert("ebuyPayRecordBase.insert_ebuyPayRecord_Batch",ebuyPayRecordList);
	}
	
	/**
	 * 更新(支付记录表)信息
	 * @param ebuyPayRecord
	 * @return
	 */
	@Override
	public int updateEbuyPayRecord(EbuyPayRecord ebuyPayRecord){
		return sqlSession.update("ebuyPayRecordBase.update_ebuyPayRecord", ebuyPayRecord);
	}
	/**
	 * 批量更新(支付记录表)信息
	 * @param ebuyPayRecordList
	 * @return
	 */
	@Override
	public int updateEbuyPayRecordBatch(List<EbuyPayRecord> ebuyPayRecordList) {
		return sqlSession.update("ebuyPayRecordBase.update_ebuyPayRecord_Batch", ebuyPayRecordList);
	}
	
	/**
	 * 根据序列号删除(支付记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyPayRecordLogic(java.math.BigInteger id){
		EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
		ebuyPayRecord.setId(id);
		ebuyPayRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyPayRecordBase.delete_ebuyPayRecord_Logic",ebuyPayRecord);
	}
	
	/**
	 * 根据Id 批量删除(支付记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyPayRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyPayRecord> delList = new java.util.ArrayList<EbuyPayRecord>();
		for(java.math.BigInteger id:idList){
			EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();
			ebuyPayRecord.setId(id);
			ebuyPayRecord.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyPayRecord);
		}
		return sqlSession.update("ebuyPayRecordBase.delete_ebuyPayRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(支付记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPayRecord(java.math.BigInteger id){
//		return sqlSession.delete("ebuyPayRecordBase.delete_ebuyPayRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(支付记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyPayRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyPayRecordBase.delete_ebuyPayRecord_Batch", idList);
//	}
	
	
}

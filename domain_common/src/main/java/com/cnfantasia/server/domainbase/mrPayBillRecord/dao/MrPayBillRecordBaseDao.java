package com.cnfantasia.server.domainbase.mrPayBillRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;

/**
 * 描述:(抄表费收费 账单读表数) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MrPayBillRecordBaseDao extends AbstractBaseDao implements IMrPayBillRecordBaseDao{
	/**
	 * 根据条件查询(抄表费收费 账单读表数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrPayBillRecord> selectMrPayBillRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("mrPayBillRecordBase.select_mrPayBillRecord",paramMap);
	}
	/**
	 * 按条件分页查询(抄表费收费 账单读表数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrPayBillRecord> selectMrPayBillRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMrPayBillRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MrPayBillRecord> resMap= sqlSession.selectList("mrPayBillRecordBase.select_mrPayBillRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抄表费收费 账单读表数)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrPayBillRecord selectMrPayBillRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("mrPayBillRecordBase.select_mrPayBillRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费 账单读表数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMrPayBillRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("mrPayBillRecordBase.select_mrPayBillRecord_count", paramMap);
	}
	/**
	 * 往(抄表费收费 账单读表数)新增一条记录
	 * @param mrPayBillRecord
	 * @return
	 */
	@Override
	public int insertMrPayBillRecord(MrPayBillRecord mrPayBillRecord){
		return sqlSession.insert("mrPayBillRecordBase.insert_mrPayBillRecord",mrPayBillRecord);
	}
	/**
	 * 批量新增(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecordList
	 * @return
	 */
	@Override
	public int insertMrPayBillRecordBatch(List<MrPayBillRecord> mrPayBillRecordList) {
		if (mrPayBillRecordList == null || mrPayBillRecordList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = mrPayBillRecordList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<MrPayBillRecord> batchList = mrPayBillRecordList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("mrPayBillRecordBase.insert_mrPayBillRecord_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecord
	 * @return
	 */
	@Override
	public int updateMrPayBillRecord(MrPayBillRecord mrPayBillRecord){
		return sqlSession.update("mrPayBillRecordBase.update_mrPayBillRecord", mrPayBillRecord);
	}
	/**
	 * 批量更新(抄表费收费 账单读表数)信息
	 * @param mrPayBillRecordList
	 * @return
	 */
	@Override
	public int updateMrPayBillRecordBatch(List<MrPayBillRecord> mrPayBillRecordList) {
		if (mrPayBillRecordList == null || mrPayBillRecordList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("mrPayBillRecordBase.update_mrPayBillRecord_Batch", mrPayBillRecordList);
	}
	
	/**
	 * 根据序列号删除(抄表费收费 账单读表数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrPayBillRecordLogic(java.math.BigInteger id){
		MrPayBillRecord mrPayBillRecord = new MrPayBillRecord();
		mrPayBillRecord.setId(id);
		mrPayBillRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("mrPayBillRecordBase.delete_mrPayBillRecord_Logic",mrPayBillRecord);
	}
	
	/**
	 * 根据Id 批量删除(抄表费收费 账单读表数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrPayBillRecordLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<MrPayBillRecord> delList = new java.util.ArrayList<MrPayBillRecord>();
		for(java.math.BigInteger id:idList){
			MrPayBillRecord mrPayBillRecord = new MrPayBillRecord();
			mrPayBillRecord.setId(id);
			mrPayBillRecord.setSys0DelState(RecordState_DELETED);
			delList.add(mrPayBillRecord);
		}
		return sqlSession.update("mrPayBillRecordBase.delete_mrPayBillRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抄表费收费 账单读表数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrPayBillRecord(java.math.BigInteger id){
//		return sqlSession.delete("mrPayBillRecordBase.delete_mrPayBillRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费 账单读表数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrPayBillRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("mrPayBillRecordBase.delete_mrPayBillRecord_Batch", idList);
//	}
	
	
}

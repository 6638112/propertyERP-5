package com.cnfantasia.server.domainbase.dredgeBill.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;

/**
 * 描述:(疏通工单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillBaseDao extends AbstractBaseDao implements IDredgeBillBaseDao{
	/**
	 * 根据条件查询(疏通工单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBill> selectDredgeBillByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillBase.select_dredgeBill",paramMap);
	}
	/**
	 * 按条件分页查询(疏通工单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBill> selectDredgeBillByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBill> resMap= sqlSession.selectList("dredgeBillBase.select_dredgeBill_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通工单)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBill selectDredgeBillBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillBase.select_dredgeBill_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通工单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillBase.select_dredgeBill_count", paramMap);
	}
	/**
	 * 往(疏通工单)新增一条记录
	 * @param dredgeBill
	 * @return
	 */
	@Override
	public int insertDredgeBill(DredgeBill dredgeBill){
		return sqlSession.insert("dredgeBillBase.insert_dredgeBill",dredgeBill);
	}
	/**
	 * 批量新增(疏通工单)信息
	 * @param dredgeBillList
	 * @return
	 */
	@Override
	public int insertDredgeBillBatch(List<DredgeBill> dredgeBillList) {
		return sqlSession.insert("dredgeBillBase.insert_dredgeBill_Batch",dredgeBillList);
	}
	
	/**
	 * 更新(疏通工单)信息
	 * @param dredgeBill
	 * @return
	 */
	@Override
	public int updateDredgeBill(DredgeBill dredgeBill){
		return sqlSession.update("dredgeBillBase.update_dredgeBill", dredgeBill);
	}
	/**
	 * 批量更新(疏通工单)信息
	 * @param dredgeBillList
	 * @return
	 */
	@Override
	public int updateDredgeBillBatch(List<DredgeBill> dredgeBillList) {
		return sqlSession.update("dredgeBillBase.update_dredgeBill_Batch", dredgeBillList);
	}
	
	/**
	 * 根据序列号删除(疏通工单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillLogic(java.math.BigInteger id){
		DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.setId(id);
		dredgeBill.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillBase.delete_dredgeBill_Logic",dredgeBill);
	}
	
	/**
	 * 根据Id 批量删除(疏通工单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBill> delList = new java.util.ArrayList<DredgeBill>();
		for(java.math.BigInteger id:idList){
			DredgeBill dredgeBill = new DredgeBill();
			dredgeBill.setId(id);
			dredgeBill.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBill);
		}
		return sqlSession.update("dredgeBillBase.delete_dredgeBill_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(疏通工单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBill(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillBase.delete_dredgeBill", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通工单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillBase.delete_dredgeBill_Batch", idList);
//	}
	
	
}

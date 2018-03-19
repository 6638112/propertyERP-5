package com.cnfantasia.server.domainbase.realroomSoftwareFee.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.realroomSoftwareFee.entity.RealroomSoftwareFee;

/**
 * 描述:(查询的物业账单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RealroomSoftwareFeeBaseDao extends AbstractBaseDao implements IRealroomSoftwareFeeBaseDao{
	/**
	 * 根据条件查询(查询的物业账单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealroomSoftwareFee> selectRealroomSoftwareFeeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("realroomSoftwareFeeBase.select_realroomSoftwareFee",paramMap);
	}
	/**
	 * 按条件分页查询(查询的物业账单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RealroomSoftwareFee> selectRealroomSoftwareFeeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRealroomSoftwareFeeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RealroomSoftwareFee> resMap= sqlSession.selectList("realroomSoftwareFeeBase.select_realroomSoftwareFee_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(查询的物业账单)信息
	 * @param id
	 * @return
	 */
	@Override
	public RealroomSoftwareFee selectRealroomSoftwareFeeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("realroomSoftwareFeeBase.select_realroomSoftwareFee_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(查询的物业账单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRealroomSoftwareFeeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("realroomSoftwareFeeBase.select_realroomSoftwareFee_count", paramMap);
	}
	/**
	 * 往(查询的物业账单)新增一条记录
	 * @param realroomSoftwareFee
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee){
		return sqlSession.insert("realroomSoftwareFeeBase.insert_realroomSoftwareFee",realroomSoftwareFee);
	}
	/**
	 * 批量新增(查询的物业账单)信息
	 * @param realroomSoftwareFeeList
	 * @return
	 */
	@Override
	public int insertRealroomSoftwareFeeBatch(List<RealroomSoftwareFee> realroomSoftwareFeeList) {
		return sqlSession.insert("realroomSoftwareFeeBase.insert_realroomSoftwareFee_Batch",realroomSoftwareFeeList);
	}
	
	/**
	 * 更新(查询的物业账单)信息
	 * @param realroomSoftwareFee
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareFee(RealroomSoftwareFee realroomSoftwareFee){
		return sqlSession.update("realroomSoftwareFeeBase.update_realroomSoftwareFee", realroomSoftwareFee);
	}
	/**
	 * 批量更新(查询的物业账单)信息
	 * @param realroomSoftwareFeeList
	 * @return
	 */
	@Override
	public int updateRealroomSoftwareFeeBatch(List<RealroomSoftwareFee> realroomSoftwareFeeList) {
		return sqlSession.update("realroomSoftwareFeeBase.update_realroomSoftwareFee_Batch", realroomSoftwareFeeList);
	}
	
	/**
	 * 根据序列号删除(查询的物业账单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareFeeLogic(java.math.BigInteger id){
		RealroomSoftwareFee realroomSoftwareFee = new RealroomSoftwareFee();
		realroomSoftwareFee.setId(id);
		realroomSoftwareFee.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("realroomSoftwareFeeBase.delete_realroomSoftwareFee_Logic",realroomSoftwareFee);
	}
	
	/**
	 * 根据Id 批量删除(查询的物业账单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRealroomSoftwareFeeLogicBatch(List<java.math.BigInteger> idList) {
		List<RealroomSoftwareFee> delList = new java.util.ArrayList<RealroomSoftwareFee>();
		for(java.math.BigInteger id:idList){
			RealroomSoftwareFee realroomSoftwareFee = new RealroomSoftwareFee();
			realroomSoftwareFee.setId(id);
			realroomSoftwareFee.setSys0DelState(RecordState_DELETED);
			delList.add(realroomSoftwareFee);
		}
		return sqlSession.update("realroomSoftwareFeeBase.delete_realroomSoftwareFee_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(查询的物业账单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareFee(java.math.BigInteger id){
//		return sqlSession.delete("realroomSoftwareFeeBase.delete_realroomSoftwareFee", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(查询的物业账单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRealroomSoftwareFeeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("realroomSoftwareFeeBase.delete_realroomSoftwareFee_Batch", idList);
//	}
	
	
}

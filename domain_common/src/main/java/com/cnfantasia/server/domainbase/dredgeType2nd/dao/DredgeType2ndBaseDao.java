package com.cnfantasia.server.domainbase.dredgeType2nd.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;

/**
 * 描述:(疏通二级类型) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeType2ndBaseDao extends AbstractBaseDao implements IDredgeType2ndBaseDao{
	/**
	 * 根据条件查询(疏通二级类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeType2nd> selectDredgeType2ndByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeType2ndBase.select_dredgeType2nd",paramMap);
	}
	/**
	 * 按条件分页查询(疏通二级类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeType2nd> selectDredgeType2ndByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeType2ndCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeType2nd> resMap= sqlSession.selectList("dredgeType2ndBase.select_dredgeType2nd_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通二级类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeType2nd selectDredgeType2ndBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeType2ndBase.select_dredgeType2nd_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通二级类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeType2ndCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeType2ndBase.select_dredgeType2nd_count", paramMap);
	}
	/**
	 * 往(疏通二级类型)新增一条记录
	 * @param dredgeType2nd
	 * @return
	 */
	@Override
	public int insertDredgeType2nd(DredgeType2nd dredgeType2nd){
		return sqlSession.insert("dredgeType2ndBase.insert_dredgeType2nd",dredgeType2nd);
	}
	/**
	 * 批量新增(疏通二级类型)信息
	 * @param dredgeType2ndList
	 * @return
	 */
	@Override
	public int insertDredgeType2ndBatch(List<DredgeType2nd> dredgeType2ndList) {
		return sqlSession.insert("dredgeType2ndBase.insert_dredgeType2nd_Batch",dredgeType2ndList);
	}
	
	/**
	 * 更新(疏通二级类型)信息
	 * @param dredgeType2nd
	 * @return
	 */
	@Override
	public int updateDredgeType2nd(DredgeType2nd dredgeType2nd){
		return sqlSession.update("dredgeType2ndBase.update_dredgeType2nd", dredgeType2nd);
	}
	/**
	 * 批量更新(疏通二级类型)信息
	 * @param dredgeType2ndList
	 * @return
	 */
	@Override
	public int updateDredgeType2ndBatch(List<DredgeType2nd> dredgeType2ndList) {
		return sqlSession.update("dredgeType2ndBase.update_dredgeType2nd_Batch", dredgeType2ndList);
	}
	
	/**
	 * 根据序列号删除(疏通二级类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeType2ndLogic(java.math.BigInteger id){
		DredgeType2nd dredgeType2nd = new DredgeType2nd();
		dredgeType2nd.setId(id);
		dredgeType2nd.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeType2ndBase.delete_dredgeType2nd_Logic",dredgeType2nd);
	}
	
	/**
	 * 根据Id 批量删除(疏通二级类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeType2ndLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeType2nd> delList = new java.util.ArrayList<DredgeType2nd>();
		for(java.math.BigInteger id:idList){
			DredgeType2nd dredgeType2nd = new DredgeType2nd();
			dredgeType2nd.setId(id);
			dredgeType2nd.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeType2nd);
		}
		return sqlSession.update("dredgeType2ndBase.delete_dredgeType2nd_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(疏通二级类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeType2nd(java.math.BigInteger id){
//		return sqlSession.delete("dredgeType2ndBase.delete_dredgeType2nd", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通二级类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeType2ndBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeType2ndBase.delete_dredgeType2nd_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.dredgeBillOtherInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillOtherInfo.entity.DredgeBillOtherInfo;

/**
 * 描述:(维修单的与第三方供应商的信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillOtherInfoBaseDao extends AbstractBaseDao implements IDredgeBillOtherInfoBaseDao{
	/**
	 * 根据条件查询(维修单的与第三方供应商的信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillOtherInfo> selectDredgeBillOtherInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillOtherInfoBase.select_dredgeBillOtherInfo",paramMap);
	}
	/**
	 * 按条件分页查询(维修单的与第三方供应商的信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillOtherInfo> selectDredgeBillOtherInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillOtherInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillOtherInfo> resMap= sqlSession.selectList("dredgeBillOtherInfoBase.select_dredgeBillOtherInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修单的与第三方供应商的信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillOtherInfo selectDredgeBillOtherInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillOtherInfoBase.select_dredgeBillOtherInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修单的与第三方供应商的信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillOtherInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillOtherInfoBase.select_dredgeBillOtherInfo_count", paramMap);
	}
	/**
	 * 往(维修单的与第三方供应商的信息)新增一条记录
	 * @param dredgeBillOtherInfo
	 * @return
	 */
	@Override
	public int insertDredgeBillOtherInfo(DredgeBillOtherInfo dredgeBillOtherInfo){
		return sqlSession.insert("dredgeBillOtherInfoBase.insert_dredgeBillOtherInfo",dredgeBillOtherInfo);
	}
	/**
	 * 批量新增(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfoList
	 * @return
	 */
	@Override
	public int insertDredgeBillOtherInfoBatch(List<DredgeBillOtherInfo> dredgeBillOtherInfoList) {
		return sqlSession.insert("dredgeBillOtherInfoBase.insert_dredgeBillOtherInfo_Batch",dredgeBillOtherInfoList);
	}
	
	/**
	 * 更新(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfo
	 * @return
	 */
	@Override
	public int updateDredgeBillOtherInfo(DredgeBillOtherInfo dredgeBillOtherInfo){
		return sqlSession.update("dredgeBillOtherInfoBase.update_dredgeBillOtherInfo", dredgeBillOtherInfo);
	}
	/**
	 * 批量更新(维修单的与第三方供应商的信息)信息
	 * @param dredgeBillOtherInfoList
	 * @return
	 */
	@Override
	public int updateDredgeBillOtherInfoBatch(List<DredgeBillOtherInfo> dredgeBillOtherInfoList) {
		return sqlSession.update("dredgeBillOtherInfoBase.update_dredgeBillOtherInfo_Batch", dredgeBillOtherInfoList);
	}
	
	/**
	 * 根据序列号删除(维修单的与第三方供应商的信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillOtherInfoLogic(java.math.BigInteger id){
		DredgeBillOtherInfo dredgeBillOtherInfo = new DredgeBillOtherInfo();
		dredgeBillOtherInfo.setId(id);
		dredgeBillOtherInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillOtherInfoBase.delete_dredgeBillOtherInfo_Logic",dredgeBillOtherInfo);
	}
	
	/**
	 * 根据Id 批量删除(维修单的与第三方供应商的信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillOtherInfoLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBillOtherInfo> delList = new java.util.ArrayList<DredgeBillOtherInfo>();
		for(java.math.BigInteger id:idList){
			DredgeBillOtherInfo dredgeBillOtherInfo = new DredgeBillOtherInfo();
			dredgeBillOtherInfo.setId(id);
			dredgeBillOtherInfo.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillOtherInfo);
		}
		return sqlSession.update("dredgeBillOtherInfoBase.delete_dredgeBillOtherInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修单的与第三方供应商的信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillOtherInfo(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillOtherInfoBase.delete_dredgeBillOtherInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修单的与第三方供应商的信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillOtherInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillOtherInfoBase.delete_dredgeBillOtherInfo_Batch", idList);
//	}
	
	
}

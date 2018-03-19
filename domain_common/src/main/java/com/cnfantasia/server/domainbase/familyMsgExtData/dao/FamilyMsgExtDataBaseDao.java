package com.cnfantasia.server.domainbase.familyMsgExtData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyMsgExtData.entity.FamilyMsgExtData;

/**
 * 描述:(家庭留言板拓展信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FamilyMsgExtDataBaseDao extends AbstractBaseDao implements IFamilyMsgExtDataBaseDao{
	/**
	 * 根据条件查询(家庭留言板拓展信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyMsgExtData> selectFamilyMsgExtDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("familyMsgExtDataBase.select_familyMsgExtData",paramMap);
	}
	/**
	 * 按条件分页查询(家庭留言板拓展信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyMsgExtData> selectFamilyMsgExtDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFamilyMsgExtDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FamilyMsgExtData> resMap= sqlSession.selectList("familyMsgExtDataBase.select_familyMsgExtData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(家庭留言板拓展信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyMsgExtData selectFamilyMsgExtDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("familyMsgExtDataBase.select_familyMsgExtData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(家庭留言板拓展信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFamilyMsgExtDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("familyMsgExtDataBase.select_familyMsgExtData_count", paramMap);
	}
	/**
	 * 往(家庭留言板拓展信息)新增一条记录
	 * @param familyMsgExtData
	 * @return
	 */
	@Override
	public int insertFamilyMsgExtData(FamilyMsgExtData familyMsgExtData){
		return sqlSession.insert("familyMsgExtDataBase.insert_familyMsgExtData",familyMsgExtData);
	}
	/**
	 * 批量新增(家庭留言板拓展信息)信息
	 * @param familyMsgExtDataList
	 * @return
	 */
	@Override
	public int insertFamilyMsgExtDataBatch(List<FamilyMsgExtData> familyMsgExtDataList) {
		return sqlSession.insert("familyMsgExtDataBase.insert_familyMsgExtData_Batch",familyMsgExtDataList);
	}
	
	/**
	 * 更新(家庭留言板拓展信息)信息
	 * @param familyMsgExtData
	 * @return
	 */
	@Override
	public int updateFamilyMsgExtData(FamilyMsgExtData familyMsgExtData){
		return sqlSession.update("familyMsgExtDataBase.update_familyMsgExtData", familyMsgExtData);
	}
	/**
	 * 批量更新(家庭留言板拓展信息)信息
	 * @param familyMsgExtDataList
	 * @return
	 */
	@Override
	public int updateFamilyMsgExtDataBatch(List<FamilyMsgExtData> familyMsgExtDataList) {
		return sqlSession.update("familyMsgExtDataBase.update_familyMsgExtData_Batch", familyMsgExtDataList);
	}
	
	/**
	 * 根据序列号删除(家庭留言板拓展信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgExtDataLogic(java.math.BigInteger id){
		FamilyMsgExtData familyMsgExtData = new FamilyMsgExtData();
		familyMsgExtData.setId(id);
		familyMsgExtData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("familyMsgExtDataBase.delete_familyMsgExtData_Logic",familyMsgExtData);
	}
	
	/**
	 * 根据Id 批量删除(家庭留言板拓展信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyMsgExtDataLogicBatch(List<java.math.BigInteger> idList) {
		List<FamilyMsgExtData> delList = new java.util.ArrayList<FamilyMsgExtData>();
		for(java.math.BigInteger id:idList){
			FamilyMsgExtData familyMsgExtData = new FamilyMsgExtData();
			familyMsgExtData.setId(id);
			familyMsgExtData.setSys0DelState(RecordState_DELETED);
			delList.add(familyMsgExtData);
		}
		return sqlSession.update("familyMsgExtDataBase.delete_familyMsgExtData_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(家庭留言板拓展信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgExtData(java.math.BigInteger id){
//		return sqlSession.delete("familyMsgExtDataBase.delete_familyMsgExtData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭留言板拓展信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyMsgExtDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("familyMsgExtDataBase.delete_familyMsgExtData_Batch", idList);
//	}
	
	
}

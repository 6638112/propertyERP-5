package com.cnfantasia.server.domainbase.familyWealthOption.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.familyWealthOption.entity.FamilyWealthOption;

/**
 * 描述:(家庭财富选项) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FamilyWealthOptionBaseDao extends AbstractBaseDao implements IFamilyWealthOptionBaseDao{
	/**
	 * 根据条件查询(家庭财富选项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyWealthOption> selectFamilyWealthOptionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("familyWealthOptionBase.select_familyWealthOption",paramMap);
	}
	/**
	 * 按条件分页查询(家庭财富选项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FamilyWealthOption> selectFamilyWealthOptionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFamilyWealthOptionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FamilyWealthOption> resMap= sqlSession.selectList("familyWealthOptionBase.select_familyWealthOption_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(家庭财富选项)信息
	 * @param id
	 * @return
	 */
	@Override
	public FamilyWealthOption selectFamilyWealthOptionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("familyWealthOptionBase.select_familyWealthOption_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(家庭财富选项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFamilyWealthOptionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("familyWealthOptionBase.select_familyWealthOption_count", paramMap);
	}
	/**
	 * 往(家庭财富选项)新增一条记录
	 * @param familyWealthOption
	 * @return
	 */
	@Override
	public int insertFamilyWealthOption(FamilyWealthOption familyWealthOption){
		return sqlSession.insert("familyWealthOptionBase.insert_familyWealthOption",familyWealthOption);
	}
	/**
	 * 批量新增(家庭财富选项)信息
	 * @param familyWealthOptionList
	 * @return
	 */
	@Override
	public int insertFamilyWealthOptionBatch(List<FamilyWealthOption> familyWealthOptionList) {
		return sqlSession.insert("familyWealthOptionBase.insert_familyWealthOption_Batch",familyWealthOptionList);
	}
	
	/**
	 * 更新(家庭财富选项)信息
	 * @param familyWealthOption
	 * @return
	 */
	@Override
	public int updateFamilyWealthOption(FamilyWealthOption familyWealthOption){
		return sqlSession.update("familyWealthOptionBase.update_familyWealthOption", familyWealthOption);
	}
	/**
	 * 批量更新(家庭财富选项)信息
	 * @param familyWealthOptionList
	 * @return
	 */
	@Override
	public int updateFamilyWealthOptionBatch(List<FamilyWealthOption> familyWealthOptionList) {
		return sqlSession.update("familyWealthOptionBase.update_familyWealthOption_Batch", familyWealthOptionList);
	}
	
	/**
	 * 根据序列号删除(家庭财富选项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFamilyWealthOptionLogic(java.math.BigInteger id){
		FamilyWealthOption familyWealthOption = new FamilyWealthOption();
		familyWealthOption.setId(id);
		familyWealthOption.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("familyWealthOptionBase.delete_familyWealthOption_Logic",familyWealthOption);
	}
	
	/**
	 * 根据Id 批量删除(家庭财富选项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFamilyWealthOptionLogicBatch(List<java.math.BigInteger> idList) {
		List<FamilyWealthOption> delList = new java.util.ArrayList<FamilyWealthOption>();
		for(java.math.BigInteger id:idList){
			FamilyWealthOption familyWealthOption = new FamilyWealthOption();
			familyWealthOption.setId(id);
			familyWealthOption.setSys0DelState(RecordState_DELETED);
			delList.add(familyWealthOption);
		}
		return sqlSession.update("familyWealthOptionBase.delete_familyWealthOption_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(家庭财富选项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyWealthOption(java.math.BigInteger id){
//		return sqlSession.delete("familyWealthOptionBase.delete_familyWealthOption", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭财富选项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFamilyWealthOptionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("familyWealthOptionBase.delete_familyWealthOption_Batch", idList);
//	}
	
	
}

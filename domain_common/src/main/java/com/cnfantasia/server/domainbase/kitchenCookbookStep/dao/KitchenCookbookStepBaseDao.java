package com.cnfantasia.server.domainbase.kitchenCookbookStep.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookStep.entity.KitchenCookbookStep;

/**
 * 描述:(亨饪步骤信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookStepBaseDao extends AbstractBaseDao implements IKitchenCookbookStepBaseDao{
	/**
	 * 根据条件查询(亨饪步骤信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookStep> selectKitchenCookbookStepByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookStepBase.select_kitchenCookbookStep",paramMap);
	}
	/**
	 * 按条件分页查询(亨饪步骤信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookStep> selectKitchenCookbookStepByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookStepCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookStep> resMap= sqlSession.selectList("kitchenCookbookStepBase.select_kitchenCookbookStep_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(亨饪步骤信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookStep selectKitchenCookbookStepBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookStepBase.select_kitchenCookbookStep_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(亨饪步骤信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookStepCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookStepBase.select_kitchenCookbookStep_count", paramMap);
	}
	/**
	 * 往(亨饪步骤信息)新增一条记录
	 * @param kitchenCookbookStep
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStep(KitchenCookbookStep kitchenCookbookStep){
		return sqlSession.insert("kitchenCookbookStepBase.insert_kitchenCookbookStep",kitchenCookbookStep);
	}
	/**
	 * 批量新增(亨饪步骤信息)信息
	 * @param kitchenCookbookStepList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStepBatch(List<KitchenCookbookStep> kitchenCookbookStepList) {
		return sqlSession.insert("kitchenCookbookStepBase.insert_kitchenCookbookStep_Batch",kitchenCookbookStepList);
	}
	
	/**
	 * 更新(亨饪步骤信息)信息
	 * @param kitchenCookbookStep
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStep(KitchenCookbookStep kitchenCookbookStep){
		return sqlSession.update("kitchenCookbookStepBase.update_kitchenCookbookStep", kitchenCookbookStep);
	}
	/**
	 * 批量更新(亨饪步骤信息)信息
	 * @param kitchenCookbookStepList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStepBatch(List<KitchenCookbookStep> kitchenCookbookStepList) {
		return sqlSession.update("kitchenCookbookStepBase.update_kitchenCookbookStep_Batch", kitchenCookbookStepList);
	}
	
	/**
	 * 根据序列号删除(亨饪步骤信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepLogic(java.math.BigInteger id){
		KitchenCookbookStep kitchenCookbookStep = new KitchenCookbookStep();
		kitchenCookbookStep.setId(id);
		kitchenCookbookStep.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookStepBase.delete_kitchenCookbookStep_Logic",kitchenCookbookStep);
	}
	
	/**
	 * 根据Id 批量删除(亨饪步骤信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookStep> delList = new java.util.ArrayList<KitchenCookbookStep>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookStep kitchenCookbookStep = new KitchenCookbookStep();
			kitchenCookbookStep.setId(id);
			kitchenCookbookStep.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookStep);
		}
		return sqlSession.update("kitchenCookbookStepBase.delete_kitchenCookbookStep_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(亨饪步骤信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStep(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookStepBase.delete_kitchenCookbookStep", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(亨饪步骤信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStepBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookStepBase.delete_kitchenCookbookStep_Batch", idList);
//	}
	
	
}

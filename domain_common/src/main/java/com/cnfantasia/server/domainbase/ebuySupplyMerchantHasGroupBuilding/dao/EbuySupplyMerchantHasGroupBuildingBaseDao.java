package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity.EbuySupplyMerchantHasGroupBuilding;

/**
 * 描述:(供应商与小区关联表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuySupplyMerchantHasGroupBuildingBaseDao extends AbstractBaseDao implements IEbuySupplyMerchantHasGroupBuildingBaseDao{
	/**
	 * 根据条件查询(供应商与小区关联表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasGroupBuilding> selectEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuySupplyMerchantHasGroupBuildingBase.select_ebuySupplyMerchantHasGroupBuilding",paramMap);
	}
	/**
	 * 按条件分页查询(供应商与小区关联表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasGroupBuilding> selectEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuySupplyMerchantHasGroupBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuySupplyMerchantHasGroupBuilding> resMap= sqlSession.selectList("ebuySupplyMerchantHasGroupBuildingBase.select_ebuySupplyMerchantHasGroupBuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商与小区关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantHasGroupBuilding selectEbuySupplyMerchantHasGroupBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuySupplyMerchantHasGroupBuildingBase.select_ebuySupplyMerchantHasGroupBuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商与小区关联表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuySupplyMerchantHasGroupBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuySupplyMerchantHasGroupBuildingBase.select_ebuySupplyMerchantHasGroupBuilding_count", paramMap);
	}
	/**
	 * 往(供应商与小区关联表)新增一条记录
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding){
		return sqlSession.insert("ebuySupplyMerchantHasGroupBuildingBase.insert_ebuySupplyMerchantHasGroupBuilding",ebuySupplyMerchantHasGroupBuilding);
	}
	/**
	 * 批量新增(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasGroupBuildingBatch(List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList) {
		return sqlSession.insert("ebuySupplyMerchantHasGroupBuildingBase.insert_ebuySupplyMerchantHasGroupBuilding_Batch",ebuySupplyMerchantHasGroupBuildingList);
	}
	
	/**
	 * 更新(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding){
		return sqlSession.update("ebuySupplyMerchantHasGroupBuildingBase.update_ebuySupplyMerchantHasGroupBuilding", ebuySupplyMerchantHasGroupBuilding);
	}
	/**
	 * 批量更新(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasGroupBuildingBatch(List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList) {
		return sqlSession.update("ebuySupplyMerchantHasGroupBuildingBase.update_ebuySupplyMerchantHasGroupBuilding_Batch", ebuySupplyMerchantHasGroupBuildingList);
	}
	
	/**
	 * 根据序列号删除(供应商与小区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasGroupBuildingLogic(java.math.BigInteger id){
		EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
		ebuySupplyMerchantHasGroupBuilding.setId(id);
		ebuySupplyMerchantHasGroupBuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuySupplyMerchantHasGroupBuildingBase.delete_ebuySupplyMerchantHasGroupBuilding_Logic",ebuySupplyMerchantHasGroupBuilding);
	}
	
	/**
	 * 根据Id 批量删除(供应商与小区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuySupplyMerchantHasGroupBuilding> delList = new java.util.ArrayList<EbuySupplyMerchantHasGroupBuilding>();
		for(java.math.BigInteger id:idList){
			EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding = new EbuySupplyMerchantHasGroupBuilding();
			ebuySupplyMerchantHasGroupBuilding.setId(id);
			ebuySupplyMerchantHasGroupBuilding.setSys0DelState(RecordState_DELETED);
			delList.add(ebuySupplyMerchantHasGroupBuilding);
		}
		return sqlSession.update("ebuySupplyMerchantHasGroupBuildingBase.delete_ebuySupplyMerchantHasGroupBuilding_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商与小区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasGroupBuilding(java.math.BigInteger id){
//		return sqlSession.delete("ebuySupplyMerchantHasGroupBuildingBase.delete_ebuySupplyMerchantHasGroupBuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商与小区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasGroupBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuySupplyMerchantHasGroupBuildingBase.delete_ebuySupplyMerchantHasGroupBuilding_Batch", idList);
//	}
	
	
}

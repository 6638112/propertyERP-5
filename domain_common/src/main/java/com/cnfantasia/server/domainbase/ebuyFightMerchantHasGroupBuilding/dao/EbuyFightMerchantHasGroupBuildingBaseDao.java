package com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;

/**
 * 描述:(自提点服务小区关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyFightMerchantHasGroupBuildingBaseDao extends AbstractBaseDao implements IEbuyFightMerchantHasGroupBuildingBaseDao{
	/**
	 * 根据条件查询(自提点服务小区关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyFightMerchantHasGroupBuilding> selectEbuyFightMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyFightMerchantHasGroupBuildingBase.select_ebuyFightMerchantHasGroupBuilding",paramMap);
	}
	/**
	 * 按条件分页查询(自提点服务小区关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyFightMerchantHasGroupBuilding> selectEbuyFightMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyFightMerchantHasGroupBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyFightMerchantHasGroupBuilding> resMap= sqlSession.selectList("ebuyFightMerchantHasGroupBuildingBase.select_ebuyFightMerchantHasGroupBuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(自提点服务小区关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyFightMerchantHasGroupBuilding selectEbuyFightMerchantHasGroupBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyFightMerchantHasGroupBuildingBase.select_ebuyFightMerchantHasGroupBuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(自提点服务小区关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyFightMerchantHasGroupBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyFightMerchantHasGroupBuildingBase.select_ebuyFightMerchantHasGroupBuilding_count", paramMap);
	}
	/**
	 * 往(自提点服务小区关系表)新增一条记录
	 * @param ebuyFightMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int insertEbuyFightMerchantHasGroupBuilding(EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding){
		return sqlSession.insert("ebuyFightMerchantHasGroupBuildingBase.insert_ebuyFightMerchantHasGroupBuilding",ebuyFightMerchantHasGroupBuilding);
	}
	/**
	 * 批量新增(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int insertEbuyFightMerchantHasGroupBuildingBatch(List<EbuyFightMerchantHasGroupBuilding> ebuyFightMerchantHasGroupBuildingList) {
		return sqlSession.insert("ebuyFightMerchantHasGroupBuildingBase.insert_ebuyFightMerchantHasGroupBuilding_Batch",ebuyFightMerchantHasGroupBuildingList);
	}
	
	/**
	 * 更新(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int updateEbuyFightMerchantHasGroupBuilding(EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding){
		return sqlSession.update("ebuyFightMerchantHasGroupBuildingBase.update_ebuyFightMerchantHasGroupBuilding", ebuyFightMerchantHasGroupBuilding);
	}
	/**
	 * 批量更新(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int updateEbuyFightMerchantHasGroupBuildingBatch(List<EbuyFightMerchantHasGroupBuilding> ebuyFightMerchantHasGroupBuildingList) {
		return sqlSession.update("ebuyFightMerchantHasGroupBuildingBase.update_ebuyFightMerchantHasGroupBuilding_Batch", ebuyFightMerchantHasGroupBuildingList);
	}
	
	/**
	 * 根据序列号删除(自提点服务小区关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightMerchantHasGroupBuildingLogic(java.math.BigInteger id){
		EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding = new EbuyFightMerchantHasGroupBuilding();
		ebuyFightMerchantHasGroupBuilding.setId(id);
		ebuyFightMerchantHasGroupBuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyFightMerchantHasGroupBuildingBase.delete_ebuyFightMerchantHasGroupBuilding_Logic",ebuyFightMerchantHasGroupBuilding);
	}
	
	/**
	 * 根据Id 批量删除(自提点服务小区关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightMerchantHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyFightMerchantHasGroupBuilding> delList = new java.util.ArrayList<EbuyFightMerchantHasGroupBuilding>();
		for(java.math.BigInteger id:idList){
			EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding = new EbuyFightMerchantHasGroupBuilding();
			ebuyFightMerchantHasGroupBuilding.setId(id);
			ebuyFightMerchantHasGroupBuilding.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyFightMerchantHasGroupBuilding);
		}
		return sqlSession.update("ebuyFightMerchantHasGroupBuildingBase.delete_ebuyFightMerchantHasGroupBuilding_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(自提点服务小区关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightMerchantHasGroupBuilding(java.math.BigInteger id){
//		return sqlSession.delete("ebuyFightMerchantHasGroupBuildingBase.delete_ebuyFightMerchantHasGroupBuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自提点服务小区关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightMerchantHasGroupBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyFightMerchantHasGroupBuildingBase.delete_ebuyFightMerchantHasGroupBuilding_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.entity.BcInfoHasTGroupBuilding;

/**
 * 描述:(物业公司托收银行信息包含的小区) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcInfoHasTGroupBuildingBaseDao extends AbstractBaseDao implements IBcInfoHasTGroupBuildingBaseDao{
	/**
	 * 根据条件查询(物业公司托收银行信息包含的小区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcInfoHasTGroupBuilding> selectBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcInfoHasTGroupBuildingBase.select_bcInfoHasTGroupBuilding",paramMap);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的小区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcInfoHasTGroupBuilding> selectBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcInfoHasTGroupBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcInfoHasTGroupBuilding> resMap= sqlSession.selectList("bcInfoHasTGroupBuildingBase.select_bcInfoHasTGroupBuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的小区)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcInfoHasTGroupBuilding selectBcInfoHasTGroupBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcInfoHasTGroupBuildingBase.select_bcInfoHasTGroupBuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的小区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcInfoHasTGroupBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcInfoHasTGroupBuildingBase.select_bcInfoHasTGroupBuilding_count", paramMap);
	}
	/**
	 * 往(物业公司托收银行信息包含的小区)新增一条记录
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	@Override
	public int insertBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding){
		return sqlSession.insert("bcInfoHasTGroupBuildingBase.insert_bcInfoHasTGroupBuilding",bcInfoHasTGroupBuilding);
	}
	/**
	 * 批量新增(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuildingList
	 * @return
	 */
	@Override
	public int insertBcInfoHasTGroupBuildingBatch(List<BcInfoHasTGroupBuilding> bcInfoHasTGroupBuildingList) {
		if (bcInfoHasTGroupBuildingList == null || bcInfoHasTGroupBuildingList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcInfoHasTGroupBuildingList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcInfoHasTGroupBuilding> batchList = bcInfoHasTGroupBuildingList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcInfoHasTGroupBuildingBase.insert_bcInfoHasTGroupBuilding_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	@Override
	public int updateBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding){
		return sqlSession.update("bcInfoHasTGroupBuildingBase.update_bcInfoHasTGroupBuilding", bcInfoHasTGroupBuilding);
	}
	/**
	 * 批量更新(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuildingList
	 * @return
	 */
	@Override
	public int updateBcInfoHasTGroupBuildingBatch(List<BcInfoHasTGroupBuilding> bcInfoHasTGroupBuildingList) {
		if (bcInfoHasTGroupBuildingList == null || bcInfoHasTGroupBuildingList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcInfoHasTGroupBuildingBase.update_bcInfoHasTGroupBuilding_Batch", bcInfoHasTGroupBuildingList);
	}
	
	/**
	 * 根据序列号删除(物业公司托收银行信息包含的小区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTGroupBuildingLogic(java.math.BigInteger id){
		BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding = new BcInfoHasTGroupBuilding();
		bcInfoHasTGroupBuilding.setId(id);
		bcInfoHasTGroupBuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcInfoHasTGroupBuildingBase.delete_bcInfoHasTGroupBuilding_Logic",bcInfoHasTGroupBuilding);
	}
	
	/**
	 * 根据Id 批量删除(物业公司托收银行信息包含的小区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTGroupBuildingLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcInfoHasTGroupBuilding> delList = new java.util.ArrayList<BcInfoHasTGroupBuilding>();
		for(java.math.BigInteger id:idList){
			BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding = new BcInfoHasTGroupBuilding();
			bcInfoHasTGroupBuilding.setId(id);
			bcInfoHasTGroupBuilding.setSys0DelState(RecordState_DELETED);
			delList.add(bcInfoHasTGroupBuilding);
		}
		return sqlSession.update("bcInfoHasTGroupBuildingBase.delete_bcInfoHasTGroupBuilding_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息包含的小区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTGroupBuilding(java.math.BigInteger id){
//		return sqlSession.delete("bcInfoHasTGroupBuildingBase.delete_bcInfoHasTGroupBuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息包含的小区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTGroupBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcInfoHasTGroupBuildingBase.delete_bcInfoHasTGroupBuilding_Batch", idList);
//	}
	
	
}

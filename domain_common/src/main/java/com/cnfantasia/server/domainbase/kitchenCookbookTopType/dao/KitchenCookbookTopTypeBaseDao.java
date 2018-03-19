package com.cnfantasia.server.domainbase.kitchenCookbookTopType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTopType.entity.KitchenCookbookTopType;

/**
 * 描述:(顶级类别) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookTopTypeBaseDao extends AbstractBaseDao implements IKitchenCookbookTopTypeBaseDao{
	/**
	 * 根据条件查询(顶级类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTopType> selectKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookTopTypeBase.select_kitchenCookbookTopType",paramMap);
	}
	/**
	 * 按条件分页查询(顶级类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTopType> selectKitchenCookbookTopTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookTopTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookTopType> resMap= sqlSession.selectList("kitchenCookbookTopTypeBase.select_kitchenCookbookTopType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(顶级类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTopType selectKitchenCookbookTopTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookTopTypeBase.select_kitchenCookbookTopType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(顶级类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookTopTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookTopTypeBase.select_kitchenCookbookTopType_count", paramMap);
	}
	/**
	 * 往(顶级类别)新增一条记录
	 * @param kitchenCookbookTopType
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType){
		return sqlSession.insert("kitchenCookbookTopTypeBase.insert_kitchenCookbookTopType",kitchenCookbookTopType);
	}
	/**
	 * 批量新增(顶级类别)信息
	 * @param kitchenCookbookTopTypeList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTopTypeBatch(List<KitchenCookbookTopType> kitchenCookbookTopTypeList) {
		return sqlSession.insert("kitchenCookbookTopTypeBase.insert_kitchenCookbookTopType_Batch",kitchenCookbookTopTypeList);
	}
	
	/**
	 * 更新(顶级类别)信息
	 * @param kitchenCookbookTopType
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTopType(KitchenCookbookTopType kitchenCookbookTopType){
		return sqlSession.update("kitchenCookbookTopTypeBase.update_kitchenCookbookTopType", kitchenCookbookTopType);
	}
	/**
	 * 批量更新(顶级类别)信息
	 * @param kitchenCookbookTopTypeList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTopTypeBatch(List<KitchenCookbookTopType> kitchenCookbookTopTypeList) {
		return sqlSession.update("kitchenCookbookTopTypeBase.update_kitchenCookbookTopType_Batch", kitchenCookbookTopTypeList);
	}
	
	/**
	 * 根据序列号删除(顶级类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTopTypeLogic(java.math.BigInteger id){
		KitchenCookbookTopType kitchenCookbookTopType = new KitchenCookbookTopType();
		kitchenCookbookTopType.setId(id);
		kitchenCookbookTopType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookTopTypeBase.delete_kitchenCookbookTopType_Logic",kitchenCookbookTopType);
	}
	
	/**
	 * 根据Id 批量删除(顶级类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTopTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookTopType> delList = new java.util.ArrayList<KitchenCookbookTopType>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookTopType kitchenCookbookTopType = new KitchenCookbookTopType();
			kitchenCookbookTopType.setId(id);
			kitchenCookbookTopType.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookTopType);
		}
		return sqlSession.update("kitchenCookbookTopTypeBase.delete_kitchenCookbookTopType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(顶级类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTopType(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookTopTypeBase.delete_kitchenCookbookTopType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(顶级类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTopTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookTopTypeBase.delete_kitchenCookbookTopType_Batch", idList);
//	}
	
	
}

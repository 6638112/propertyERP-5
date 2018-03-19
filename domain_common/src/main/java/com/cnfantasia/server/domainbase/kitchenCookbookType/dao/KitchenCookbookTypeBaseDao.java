package com.cnfantasia.server.domainbase.kitchenCookbookType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookType.entity.KitchenCookbookType;

/**
 * 描述:(厨房菜谱分类) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookTypeBaseDao extends AbstractBaseDao implements IKitchenCookbookTypeBaseDao{
	/**
	 * 根据条件查询(厨房菜谱分类)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookType> selectKitchenCookbookTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookTypeBase.select_kitchenCookbookType",paramMap);
	}
	/**
	 * 按条件分页查询(厨房菜谱分类)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookType> selectKitchenCookbookTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookType> resMap= sqlSession.selectList("kitchenCookbookTypeBase.select_kitchenCookbookType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(厨房菜谱分类)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookType selectKitchenCookbookTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookTypeBase.select_kitchenCookbookType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱分类)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookTypeBase.select_kitchenCookbookType_count", paramMap);
	}
	/**
	 * 往(厨房菜谱分类)新增一条记录
	 * @param kitchenCookbookType
	 * @return
	 */
	@Override
	public int insertKitchenCookbookType(KitchenCookbookType kitchenCookbookType){
		return sqlSession.insert("kitchenCookbookTypeBase.insert_kitchenCookbookType",kitchenCookbookType);
	}
	/**
	 * 批量新增(厨房菜谱分类)信息
	 * @param kitchenCookbookTypeList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeBatch(List<KitchenCookbookType> kitchenCookbookTypeList) {
		return sqlSession.insert("kitchenCookbookTypeBase.insert_kitchenCookbookType_Batch",kitchenCookbookTypeList);
	}
	
	/**
	 * 更新(厨房菜谱分类)信息
	 * @param kitchenCookbookType
	 * @return
	 */
	@Override
	public int updateKitchenCookbookType(KitchenCookbookType kitchenCookbookType){
		return sqlSession.update("kitchenCookbookTypeBase.update_kitchenCookbookType", kitchenCookbookType);
	}
	/**
	 * 批量更新(厨房菜谱分类)信息
	 * @param kitchenCookbookTypeList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeBatch(List<KitchenCookbookType> kitchenCookbookTypeList) {
		return sqlSession.update("kitchenCookbookTypeBase.update_kitchenCookbookType_Batch", kitchenCookbookTypeList);
	}
	
	/**
	 * 根据序列号删除(厨房菜谱分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeLogic(java.math.BigInteger id){
		KitchenCookbookType kitchenCookbookType = new KitchenCookbookType();
		kitchenCookbookType.setId(id);
		kitchenCookbookType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookTypeBase.delete_kitchenCookbookType_Logic",kitchenCookbookType);
	}
	
	/**
	 * 根据Id 批量删除(厨房菜谱分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookType> delList = new java.util.ArrayList<KitchenCookbookType>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookType kitchenCookbookType = new KitchenCookbookType();
			kitchenCookbookType.setId(id);
			kitchenCookbookType.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookType);
		}
		return sqlSession.update("kitchenCookbookTypeBase.delete_kitchenCookbookType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜谱分类)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookType(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookTypeBase.delete_kitchenCookbookType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱分类)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookTypeBase.delete_kitchenCookbookType_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.kitchenCookbook.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;

/**
 * 描述:(厨房菜谱) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookBaseDao extends AbstractBaseDao implements IKitchenCookbookBaseDao{
	/**
	 * 根据条件查询(厨房菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbook> selectKitchenCookbookByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookBase.select_kitchenCookbook",paramMap);
	}
	/**
	 * 按条件分页查询(厨房菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbook> selectKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbook> resMap= sqlSession.selectList("kitchenCookbookBase.select_kitchenCookbook_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(厨房菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbook selectKitchenCookbookBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookBase.select_kitchenCookbook_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookBase.select_kitchenCookbook_count", paramMap);
	}
	/**
	 * 往(厨房菜谱)新增一条记录
	 * @param kitchenCookbook
	 * @return
	 */
	@Override
	public int insertKitchenCookbook(KitchenCookbook kitchenCookbook){
		return sqlSession.insert("kitchenCookbookBase.insert_kitchenCookbook",kitchenCookbook);
	}
	/**
	 * 批量新增(厨房菜谱)信息
	 * @param kitchenCookbookList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookBatch(List<KitchenCookbook> kitchenCookbookList) {
		return sqlSession.insert("kitchenCookbookBase.insert_kitchenCookbook_Batch",kitchenCookbookList);
	}
	
	/**
	 * 更新(厨房菜谱)信息
	 * @param kitchenCookbook
	 * @return
	 */
	@Override
	public int updateKitchenCookbook(KitchenCookbook kitchenCookbook){
		return sqlSession.update("kitchenCookbookBase.update_kitchenCookbook", kitchenCookbook);
	}
	/**
	 * 批量更新(厨房菜谱)信息
	 * @param kitchenCookbookList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookBatch(List<KitchenCookbook> kitchenCookbookList) {
		return sqlSession.update("kitchenCookbookBase.update_kitchenCookbook_Batch", kitchenCookbookList);
	}
	
	/**
	 * 根据序列号删除(厨房菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookLogic(java.math.BigInteger id){
		KitchenCookbook kitchenCookbook = new KitchenCookbook();
		kitchenCookbook.setId(id);
		kitchenCookbook.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookBase.delete_kitchenCookbook_Logic",kitchenCookbook);
	}
	
	/**
	 * 根据Id 批量删除(厨房菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbook> delList = new java.util.ArrayList<KitchenCookbook>();
		for(java.math.BigInteger id:idList){
			KitchenCookbook kitchenCookbook = new KitchenCookbook();
			kitchenCookbook.setId(id);
			kitchenCookbook.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbook);
		}
		return sqlSession.update("kitchenCookbookBase.delete_kitchenCookbook_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbook(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookBase.delete_kitchenCookbook", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookBase.delete_kitchenCookbook_Batch", idList);
//	}
	
	
}

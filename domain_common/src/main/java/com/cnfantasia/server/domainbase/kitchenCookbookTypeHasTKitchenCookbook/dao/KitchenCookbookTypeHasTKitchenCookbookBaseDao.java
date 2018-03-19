package com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeHasTKitchenCookbook.entity.KitchenCookbookTypeHasTKitchenCookbook;

/**
 * 描述:(菜谱类别与菜谱关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookTypeHasTKitchenCookbookBaseDao extends AbstractBaseDao implements IKitchenCookbookTypeHasTKitchenCookbookBaseDao{
	/**
	 * 根据条件查询(菜谱类别与菜谱关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeHasTKitchenCookbook> selectKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookTypeHasTKitchenCookbookBase.select_kitchenCookbookTypeHasTKitchenCookbook",paramMap);
	}
	/**
	 * 按条件分页查询(菜谱类别与菜谱关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeHasTKitchenCookbook> selectKitchenCookbookTypeHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookTypeHasTKitchenCookbookCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookTypeHasTKitchenCookbook> resMap= sqlSession.selectList("kitchenCookbookTypeHasTKitchenCookbookBase.select_kitchenCookbookTypeHasTKitchenCookbook_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(菜谱类别与菜谱关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTypeHasTKitchenCookbook selectKitchenCookbookTypeHasTKitchenCookbookBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookTypeHasTKitchenCookbookBase.select_kitchenCookbookTypeHasTKitchenCookbook_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱类别与菜谱关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookTypeHasTKitchenCookbookCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookTypeHasTKitchenCookbookBase.select_kitchenCookbookTypeHasTKitchenCookbook_count", paramMap);
	}
	/**
	 * 往(菜谱类别与菜谱关系表)新增一条记录
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook){
		return sqlSession.insert("kitchenCookbookTypeHasTKitchenCookbookBase.insert_kitchenCookbookTypeHasTKitchenCookbook",kitchenCookbookTypeHasTKitchenCookbook);
	}
	/**
	 * 批量新增(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeHasTKitchenCookbookBatch(List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList) {
		return sqlSession.insert("kitchenCookbookTypeHasTKitchenCookbookBase.insert_kitchenCookbookTypeHasTKitchenCookbook_Batch",kitchenCookbookTypeHasTKitchenCookbookList);
	}
	
	/**
	 * 更新(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeHasTKitchenCookbook(KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook){
		return sqlSession.update("kitchenCookbookTypeHasTKitchenCookbookBase.update_kitchenCookbookTypeHasTKitchenCookbook", kitchenCookbookTypeHasTKitchenCookbook);
	}
	/**
	 * 批量更新(菜谱类别与菜谱关系表)信息
	 * @param kitchenCookbookTypeHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeHasTKitchenCookbookBatch(List<KitchenCookbookTypeHasTKitchenCookbook> kitchenCookbookTypeHasTKitchenCookbookList) {
		return sqlSession.update("kitchenCookbookTypeHasTKitchenCookbookBase.update_kitchenCookbookTypeHasTKitchenCookbook_Batch", kitchenCookbookTypeHasTKitchenCookbookList);
	}
	
	/**
	 * 根据序列号删除(菜谱类别与菜谱关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeHasTKitchenCookbookLogic(java.math.BigInteger id){
		KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook = new KitchenCookbookTypeHasTKitchenCookbook();
		kitchenCookbookTypeHasTKitchenCookbook.setId(id);
		kitchenCookbookTypeHasTKitchenCookbook.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookTypeHasTKitchenCookbookBase.delete_kitchenCookbookTypeHasTKitchenCookbook_Logic",kitchenCookbookTypeHasTKitchenCookbook);
	}
	
	/**
	 * 根据Id 批量删除(菜谱类别与菜谱关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeHasTKitchenCookbookLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookTypeHasTKitchenCookbook> delList = new java.util.ArrayList<KitchenCookbookTypeHasTKitchenCookbook>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookTypeHasTKitchenCookbook kitchenCookbookTypeHasTKitchenCookbook = new KitchenCookbookTypeHasTKitchenCookbook();
			kitchenCookbookTypeHasTKitchenCookbook.setId(id);
			kitchenCookbookTypeHasTKitchenCookbook.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookTypeHasTKitchenCookbook);
		}
		return sqlSession.update("kitchenCookbookTypeHasTKitchenCookbookBase.delete_kitchenCookbookTypeHasTKitchenCookbook_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱类别与菜谱关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeHasTKitchenCookbook(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookTypeHasTKitchenCookbookBase.delete_kitchenCookbookTypeHasTKitchenCookbook", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱类别与菜谱关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeHasTKitchenCookbookBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookTypeHasTKitchenCookbookBase.delete_kitchenCookbookTypeHasTKitchenCookbook_Batch", idList);
//	}
	
	
}

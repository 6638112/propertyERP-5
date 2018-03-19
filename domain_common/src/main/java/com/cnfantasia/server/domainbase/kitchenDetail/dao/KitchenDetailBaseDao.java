package com.cnfantasia.server.domainbase.kitchenDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenDetail.entity.KitchenDetail;

/**
 * 描述:(厨房菜谱详情) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenDetailBaseDao extends AbstractBaseDao implements IKitchenDetailBaseDao{
	/**
	 * 根据条件查询(厨房菜谱详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenDetail> selectKitchenDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenDetailBase.select_kitchenDetail",paramMap);
	}
	/**
	 * 按条件分页查询(厨房菜谱详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenDetail> selectKitchenDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenDetail> resMap= sqlSession.selectList("kitchenDetailBase.select_kitchenDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(厨房菜谱详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenDetail selectKitchenDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenDetailBase.select_kitchenDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜谱详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenDetailBase.select_kitchenDetail_count", paramMap);
	}
	/**
	 * 往(厨房菜谱详情)新增一条记录
	 * @param kitchenDetail
	 * @return
	 */
	@Override
	public int insertKitchenDetail(KitchenDetail kitchenDetail){
		return sqlSession.insert("kitchenDetailBase.insert_kitchenDetail",kitchenDetail);
	}
	/**
	 * 批量新增(厨房菜谱详情)信息
	 * @param kitchenDetailList
	 * @return
	 */
	@Override
	public int insertKitchenDetailBatch(List<KitchenDetail> kitchenDetailList) {
		return sqlSession.insert("kitchenDetailBase.insert_kitchenDetail_Batch",kitchenDetailList);
	}
	
	/**
	 * 更新(厨房菜谱详情)信息
	 * @param kitchenDetail
	 * @return
	 */
	@Override
	public int updateKitchenDetail(KitchenDetail kitchenDetail){
		return sqlSession.update("kitchenDetailBase.update_kitchenDetail", kitchenDetail);
	}
	/**
	 * 批量更新(厨房菜谱详情)信息
	 * @param kitchenDetailList
	 * @return
	 */
	@Override
	public int updateKitchenDetailBatch(List<KitchenDetail> kitchenDetailList) {
		return sqlSession.update("kitchenDetailBase.update_kitchenDetail_Batch", kitchenDetailList);
	}
	
	/**
	 * 根据序列号删除(厨房菜谱详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenDetailLogic(java.math.BigInteger id){
		KitchenDetail kitchenDetail = new KitchenDetail();
		kitchenDetail.setId(id);
		kitchenDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenDetailBase.delete_kitchenDetail_Logic",kitchenDetail);
	}
	
	/**
	 * 根据Id 批量删除(厨房菜谱详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenDetailLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenDetail> delList = new java.util.ArrayList<KitchenDetail>();
		for(java.math.BigInteger id:idList){
			KitchenDetail kitchenDetail = new KitchenDetail();
			kitchenDetail.setId(id);
			kitchenDetail.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenDetail);
		}
		return sqlSession.update("kitchenDetailBase.delete_kitchenDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜谱详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenDetail(java.math.BigInteger id){
//		return sqlSession.delete("kitchenDetailBase.delete_kitchenDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜谱详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenDetailBase.delete_kitchenDetail_Batch", idList);
//	}
	
	
}

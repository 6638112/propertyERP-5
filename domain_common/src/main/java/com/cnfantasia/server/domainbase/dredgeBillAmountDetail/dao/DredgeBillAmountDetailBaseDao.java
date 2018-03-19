package com.cnfantasia.server.domainbase.dredgeBillAmountDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;

/**
 * 描述:(上门维修费用明细) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillAmountDetailBaseDao extends AbstractBaseDao implements IDredgeBillAmountDetailBaseDao{
	/**
	 * 根据条件查询(上门维修费用明细)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillAmountDetail> selectDredgeBillAmountDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillAmountDetailBase.select_dredgeBillAmountDetail",paramMap);
	}
	/**
	 * 按条件分页查询(上门维修费用明细)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillAmountDetail> selectDredgeBillAmountDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillAmountDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillAmountDetail> resMap= sqlSession.selectList("dredgeBillAmountDetailBase.select_dredgeBillAmountDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(上门维修费用明细)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillAmountDetail selectDredgeBillAmountDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillAmountDetailBase.select_dredgeBillAmountDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(上门维修费用明细)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillAmountDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillAmountDetailBase.select_dredgeBillAmountDetail_count", paramMap);
	}
	/**
	 * 往(上门维修费用明细)新增一条记录
	 * @param dredgeBillAmountDetail
	 * @return
	 */
	@Override
	public int insertDredgeBillAmountDetail(DredgeBillAmountDetail dredgeBillAmountDetail){
		return sqlSession.insert("dredgeBillAmountDetailBase.insert_dredgeBillAmountDetail",dredgeBillAmountDetail);
	}
	/**
	 * 批量新增(上门维修费用明细)信息
	 * @param dredgeBillAmountDetailList
	 * @return
	 */
	@Override
	public int insertDredgeBillAmountDetailBatch(List<DredgeBillAmountDetail> dredgeBillAmountDetailList) {
		return sqlSession.insert("dredgeBillAmountDetailBase.insert_dredgeBillAmountDetail_Batch",dredgeBillAmountDetailList);
	}
	
	/**
	 * 更新(上门维修费用明细)信息
	 * @param dredgeBillAmountDetail
	 * @return
	 */
	@Override
	public int updateDredgeBillAmountDetail(DredgeBillAmountDetail dredgeBillAmountDetail){
		return sqlSession.update("dredgeBillAmountDetailBase.update_dredgeBillAmountDetail", dredgeBillAmountDetail);
	}
	/**
	 * 批量更新(上门维修费用明细)信息
	 * @param dredgeBillAmountDetailList
	 * @return
	 */
	@Override
	public int updateDredgeBillAmountDetailBatch(List<DredgeBillAmountDetail> dredgeBillAmountDetailList) {
		return sqlSession.update("dredgeBillAmountDetailBase.update_dredgeBillAmountDetail_Batch", dredgeBillAmountDetailList);
	}
	
	/**
	 * 根据序列号删除(上门维修费用明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillAmountDetailLogic(java.math.BigInteger id){
		DredgeBillAmountDetail dredgeBillAmountDetail = new DredgeBillAmountDetail();
		dredgeBillAmountDetail.setId(id);
		dredgeBillAmountDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillAmountDetailBase.delete_dredgeBillAmountDetail_Logic",dredgeBillAmountDetail);
	}
	
	/**
	 * 根据Id 批量删除(上门维修费用明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillAmountDetailLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBillAmountDetail> delList = new java.util.ArrayList<DredgeBillAmountDetail>();
		for(java.math.BigInteger id:idList){
			DredgeBillAmountDetail dredgeBillAmountDetail = new DredgeBillAmountDetail();
			dredgeBillAmountDetail.setId(id);
			dredgeBillAmountDetail.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillAmountDetail);
		}
		return sqlSession.update("dredgeBillAmountDetailBase.delete_dredgeBillAmountDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(上门维修费用明细)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillAmountDetail(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillAmountDetailBase.delete_dredgeBillAmountDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(上门维修费用明细)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillAmountDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillAmountDetailBase.delete_dredgeBillAmountDetail_Batch", idList);
//	}
	
	
}

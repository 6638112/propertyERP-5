package com.cnfantasia.server.domainbase.ebuyOrder.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;

/**
 * 描述:(订单表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyOrderBaseDao extends AbstractBaseDao implements IEbuyOrderBaseDao{
	/**
	 * 根据条件查询(订单表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrder> selectEbuyOrderByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyOrderBase.select_ebuyOrder",paramMap);
	}
	/**
	 * 按条件分页查询(订单表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrder> selectEbuyOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyOrderCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyOrder> resMap= sqlSession.selectList("ebuyOrderBase.select_ebuyOrder_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(订单表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrder selectEbuyOrderBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyOrderBase.select_ebuyOrder_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(订单表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyOrderCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyOrderBase.select_ebuyOrder_count", paramMap);
	}
	/**
	 * 往(订单表)新增一条记录
	 * @param ebuyOrder
	 * @return
	 */
	@Override
	public int insertEbuyOrder(EbuyOrder ebuyOrder){
		return sqlSession.insert("ebuyOrderBase.insert_ebuyOrder",ebuyOrder);
	}
	/**
	 * 批量新增(订单表)信息
	 * @param ebuyOrderList
	 * @return
	 */
	@Override
	public int insertEbuyOrderBatch(List<EbuyOrder> ebuyOrderList) {
		return sqlSession.insert("ebuyOrderBase.insert_ebuyOrder_Batch",ebuyOrderList);
	}
	
	/**
	 * 更新(订单表)信息
	 * @param ebuyOrder
	 * @return
	 */
	@Override
	public int updateEbuyOrder(EbuyOrder ebuyOrder){
		return sqlSession.update("ebuyOrderBase.update_ebuyOrder", ebuyOrder);
	}
	/**
	 * 批量更新(订单表)信息
	 * @param ebuyOrderList
	 * @return
	 */
	@Override
	public int updateEbuyOrderBatch(List<EbuyOrder> ebuyOrderList) {
		return sqlSession.update("ebuyOrderBase.update_ebuyOrder_Batch", ebuyOrderList);
	}
	
	/**
	 * 根据序列号删除(订单表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderLogic(java.math.BigInteger id){
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setId(id);
		ebuyOrder.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyOrderBase.delete_ebuyOrder_Logic",ebuyOrder);
	}
	
	/**
	 * 根据Id 批量删除(订单表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyOrder> delList = new java.util.ArrayList<EbuyOrder>();
		for(java.math.BigInteger id:idList){
			EbuyOrder ebuyOrder = new EbuyOrder();
			ebuyOrder.setId(id);
			ebuyOrder.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyOrder);
		}
		return sqlSession.update("ebuyOrderBase.delete_ebuyOrder_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(订单表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrder(java.math.BigInteger id){
//		return sqlSession.delete("ebuyOrderBase.delete_ebuyOrder", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyOrderBase.delete_ebuyOrder_Batch", idList);
//	}
	
	
}

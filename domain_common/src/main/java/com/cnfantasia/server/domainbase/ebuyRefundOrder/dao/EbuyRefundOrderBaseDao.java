package com.cnfantasia.server.domainbase.ebuyRefundOrder.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundOrder.entity.EbuyRefundOrder;

/**
 * 描述:(退货订单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyRefundOrderBaseDao extends AbstractBaseDao implements IEbuyRefundOrderBaseDao{
	/**
	 * 根据条件查询(退货订单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyRefundOrder> selectEbuyRefundOrderByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyRefundOrderBase.select_ebuyRefundOrder",paramMap);
	}
	/**
	 * 按条件分页查询(退货订单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyRefundOrder> selectEbuyRefundOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyRefundOrderCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyRefundOrder> resMap= sqlSession.selectList("ebuyRefundOrderBase.select_ebuyRefundOrder_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(退货订单)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyRefundOrder selectEbuyRefundOrderBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyRefundOrderBase.select_ebuyRefundOrder_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(退货订单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyRefundOrderCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyRefundOrderBase.select_ebuyRefundOrder_count", paramMap);
	}
	/**
	 * 往(退货订单)新增一条记录
	 * @param ebuyRefundOrder
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrder(EbuyRefundOrder ebuyRefundOrder){
		return sqlSession.insert("ebuyRefundOrderBase.insert_ebuyRefundOrder",ebuyRefundOrder);
	}
	/**
	 * 批量新增(退货订单)信息
	 * @param ebuyRefundOrderList
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrderBatch(List<EbuyRefundOrder> ebuyRefundOrderList) {
		return sqlSession.insert("ebuyRefundOrderBase.insert_ebuyRefundOrder_Batch",ebuyRefundOrderList);
	}
	
	/**
	 * 更新(退货订单)信息
	 * @param ebuyRefundOrder
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrder(EbuyRefundOrder ebuyRefundOrder){
		return sqlSession.update("ebuyRefundOrderBase.update_ebuyRefundOrder", ebuyRefundOrder);
	}
	/**
	 * 批量更新(退货订单)信息
	 * @param ebuyRefundOrderList
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrderBatch(List<EbuyRefundOrder> ebuyRefundOrderList) {
		return sqlSession.update("ebuyRefundOrderBase.update_ebuyRefundOrder_Batch", ebuyRefundOrderList);
	}
	
	/**
	 * 根据序列号删除(退货订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderLogic(java.math.BigInteger id){
		EbuyRefundOrder ebuyRefundOrder = new EbuyRefundOrder();
		ebuyRefundOrder.setId(id);
		ebuyRefundOrder.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyRefundOrderBase.delete_ebuyRefundOrder_Logic",ebuyRefundOrder);
	}
	
	/**
	 * 根据Id 批量删除(退货订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyRefundOrder> delList = new java.util.ArrayList<EbuyRefundOrder>();
		for(java.math.BigInteger id:idList){
			EbuyRefundOrder ebuyRefundOrder = new EbuyRefundOrder();
			ebuyRefundOrder.setId(id);
			ebuyRefundOrder.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyRefundOrder);
		}
		return sqlSession.update("ebuyRefundOrderBase.delete_ebuyRefundOrder_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(退货订单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrder(java.math.BigInteger id){
//		return sqlSession.delete("ebuyRefundOrderBase.delete_ebuyRefundOrder", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(退货订单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrderBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyRefundOrderBase.delete_ebuyRefundOrder_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderComment.entity.EbuyDeliveryOrderComment;

/**
 * 描述:(送货单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyDeliveryOrderBaseDao extends AbstractBaseDao implements IEbuyDeliveryOrderBaseDao{
	/**
	 * 根据条件查询(送货单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrder> selectEbuyDeliveryOrderByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryOrderBase.select_ebuyDeliveryOrder",paramMap);
	}
	/**
	 * 按条件分页查询(送货单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrder> selectEbuyDeliveryOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyDeliveryOrderCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyDeliveryOrder> resMap= sqlSession.selectList("ebuyDeliveryOrderBase.select_ebuyDeliveryOrder_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(送货单)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryOrder selectEbuyDeliveryOrderBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyDeliveryOrderBase.select_ebuyDeliveryOrder_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(送货单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyDeliveryOrderCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyDeliveryOrderBase.select_ebuyDeliveryOrder_count", paramMap);
	}
	/**
	 * 往(送货单)新增一条记录
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder){
		return sqlSession.insert("ebuyDeliveryOrderBase.insert_ebuyDeliveryOrder",ebuyDeliveryOrder);
	}
	/**
	 * 批量新增(送货单)信息
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList) {
		return sqlSession.insert("ebuyDeliveryOrderBase.insert_ebuyDeliveryOrder_Batch",ebuyDeliveryOrderList);
	}
	
	/**
	 * 更新(送货单)信息
	 * @param ebuyDeliveryOrder
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrder(EbuyDeliveryOrder ebuyDeliveryOrder){
		return sqlSession.update("ebuyDeliveryOrderBase.update_ebuyDeliveryOrder", ebuyDeliveryOrder);
	}
	/**
	 * 批量更新(送货单)信息
	 * @param ebuyDeliveryOrderList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderBatch(List<EbuyDeliveryOrder> ebuyDeliveryOrderList) {
		return sqlSession.update("ebuyDeliveryOrderBase.update_ebuyDeliveryOrder_Batch", ebuyDeliveryOrderList);
	}
	
	/**
	 * 根据序列号删除(送货单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderLogic(java.math.BigInteger id){
		EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
		ebuyDeliveryOrder.setId(id);
		ebuyDeliveryOrder.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyDeliveryOrderBase.delete_ebuyDeliveryOrder_Logic",ebuyDeliveryOrder);
	}
	
	/**
	 * 根据Id 批量删除(送货单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyDeliveryOrder> delList = new java.util.ArrayList<EbuyDeliveryOrder>();
		for(java.math.BigInteger id:idList){
			EbuyDeliveryOrder ebuyDeliveryOrder = new EbuyDeliveryOrder();
			ebuyDeliveryOrder.setId(id);
			ebuyDeliveryOrder.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyDeliveryOrder);
		}
		return sqlSession.update("ebuyDeliveryOrderBase.delete_ebuyDeliveryOrder_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(送货单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrder(java.math.BigInteger id){
//		return sqlSession.delete("ebuyDeliveryOrderBase.delete_ebuyDeliveryOrder", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(送货单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyDeliveryOrderBase.delete_ebuyDeliveryOrder_Batch", idList);
//	}
	
	/**
	 * 新增一条订单备注信息
	 * @param ebuyDeliveryOrderComment
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderComment(EbuyDeliveryOrderComment ebuyDeliveryOrderComment){
		return sqlSession.insert("ebuyDeliveryOrderCommentBase.insert_ebuyDeliveryOrderComment",ebuyDeliveryOrderComment);
	}
	
	/**
	 * 查询该订单所有备注信息
	 * @param paramMap
	 * @param isDim
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderComment> selectEbuyDeliveryOrderCommentByCondition(Map<String,Object> paramMap,boolean isDim){
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryOrderCommentBase.select_ebuyDeliveryOrderComment",paramMap);
	}
	
}

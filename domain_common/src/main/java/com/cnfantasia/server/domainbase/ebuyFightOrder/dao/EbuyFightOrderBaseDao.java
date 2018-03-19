package com.cnfantasia.server.domainbase.ebuyFightOrder.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;

/**
 * 描述:(拼购详情订单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyFightOrderBaseDao extends AbstractBaseDao implements IEbuyFightOrderBaseDao{
	/**
	 * 根据条件查询(拼购详情订单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyFightOrder> selectEbuyFightOrderByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyFightOrderBase.select_ebuyFightOrder",paramMap);
	}
	/**
	 * 按条件分页查询(拼购详情订单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyFightOrder> selectEbuyFightOrderByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyFightOrderCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyFightOrder> resMap= sqlSession.selectList("ebuyFightOrderBase.select_ebuyFightOrder_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(拼购详情订单)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyFightOrder selectEbuyFightOrderBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyFightOrderBase.select_ebuyFightOrder_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(拼购详情订单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyFightOrderCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyFightOrderBase.select_ebuyFightOrder_count", paramMap);
	}
	/**
	 * 往(拼购详情订单)新增一条记录
	 * @param ebuyFightOrder
	 * @return
	 */
	@Override
	public int insertEbuyFightOrder(EbuyFightOrder ebuyFightOrder){
		return sqlSession.insert("ebuyFightOrderBase.insert_ebuyFightOrder",ebuyFightOrder);
	}
	/**
	 * 批量新增(拼购详情订单)信息
	 * @param ebuyFightOrderList
	 * @return
	 */
	@Override
	public int insertEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList) {
		return sqlSession.insert("ebuyFightOrderBase.insert_ebuyFightOrder_Batch",ebuyFightOrderList);
	}
	
	/**
	 * 更新(拼购详情订单)信息
	 * @param ebuyFightOrder
	 * @return
	 */
	@Override
	public int updateEbuyFightOrder(EbuyFightOrder ebuyFightOrder){
		return sqlSession.update("ebuyFightOrderBase.update_ebuyFightOrder", ebuyFightOrder);
	}
	/**
	 * 批量更新(拼购详情订单)信息
	 * @param ebuyFightOrderList
	 * @return
	 */
	@Override
	public int updateEbuyFightOrderBatch(List<EbuyFightOrder> ebuyFightOrderList) {
		return sqlSession.update("ebuyFightOrderBase.update_ebuyFightOrder_Batch", ebuyFightOrderList);
	}
	
	/**
	 * 根据序列号删除(拼购详情订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightOrderLogic(java.math.BigInteger id){
		EbuyFightOrder ebuyFightOrder = new EbuyFightOrder();
		ebuyFightOrder.setId(id);
		ebuyFightOrder.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyFightOrderBase.delete_ebuyFightOrder_Logic",ebuyFightOrder);
	}
	
	/**
	 * 根据Id 批量删除(拼购详情订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightOrderLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyFightOrder> delList = new java.util.ArrayList<EbuyFightOrder>();
		for(java.math.BigInteger id:idList){
			EbuyFightOrder ebuyFightOrder = new EbuyFightOrder();
			ebuyFightOrder.setId(id);
			ebuyFightOrder.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyFightOrder);
		}
		return sqlSession.update("ebuyFightOrderBase.delete_ebuyFightOrder_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(拼购详情订单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightOrder(java.math.BigInteger id){
//		return sqlSession.delete("ebuyFightOrderBase.delete_ebuyFightOrder", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼购详情订单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightOrderBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyFightOrderBase.delete_ebuyFightOrder_Batch", idList);
//	}
	
	
}

package com.cnfantasia.server.domainbase.ebuyRefundAudit.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundAudit.entity.EbuyRefundAudit;

/**
 * 描述:(商品审核不通过原因) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyRefundAuditBaseDao extends AbstractBaseDao implements IEbuyRefundAuditBaseDao{
	/**
	 * 根据条件查询(商品审核不通过原因)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyRefundAudit> selectEbuyRefundAuditByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyRefundAuditBase.select_ebuyRefundAudit",paramMap);
	}
	/**
	 * 按条件分页查询(商品审核不通过原因)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyRefundAudit> selectEbuyRefundAuditByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyRefundAuditCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyRefundAudit> resMap= sqlSession.selectList("ebuyRefundAuditBase.select_ebuyRefundAudit_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商品审核不通过原因)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyRefundAudit selectEbuyRefundAuditBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyRefundAuditBase.select_ebuyRefundAudit_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商品审核不通过原因)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyRefundAuditCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyRefundAuditBase.select_ebuyRefundAudit_count", paramMap);
	}
	/**
	 * 往(商品审核不通过原因)新增一条记录
	 * @param ebuyRefundAudit
	 * @return
	 */
	@Override
	public int insertEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit){
		return sqlSession.insert("ebuyRefundAuditBase.insert_ebuyRefundAudit",ebuyRefundAudit);
	}
	/**
	 * 批量新增(商品审核不通过原因)信息
	 * @param ebuyRefundAuditList
	 * @return
	 */
	@Override
	public int insertEbuyRefundAuditBatch(List<EbuyRefundAudit> ebuyRefundAuditList) {
		return sqlSession.insert("ebuyRefundAuditBase.insert_ebuyRefundAudit_Batch",ebuyRefundAuditList);
	}
	
	/**
	 * 更新(商品审核不通过原因)信息
	 * @param ebuyRefundAudit
	 * @return
	 */
	@Override
	public int updateEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit){
		return sqlSession.update("ebuyRefundAuditBase.update_ebuyRefundAudit", ebuyRefundAudit);
	}
	/**
	 * 批量更新(商品审核不通过原因)信息
	 * @param ebuyRefundAuditList
	 * @return
	 */
	@Override
	public int updateEbuyRefundAuditBatch(List<EbuyRefundAudit> ebuyRefundAuditList) {
		return sqlSession.update("ebuyRefundAuditBase.update_ebuyRefundAudit_Batch", ebuyRefundAuditList);
	}
	
	/**
	 * 根据序列号删除(商品审核不通过原因)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundAuditLogic(java.math.BigInteger id){
		EbuyRefundAudit ebuyRefundAudit = new EbuyRefundAudit();
		ebuyRefundAudit.setId(id);
		ebuyRefundAudit.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyRefundAuditBase.delete_ebuyRefundAudit_Logic",ebuyRefundAudit);
	}
	
	/**
	 * 根据Id 批量删除(商品审核不通过原因)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundAuditLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyRefundAudit> delList = new java.util.ArrayList<EbuyRefundAudit>();
		for(java.math.BigInteger id:idList){
			EbuyRefundAudit ebuyRefundAudit = new EbuyRefundAudit();
			ebuyRefundAudit.setId(id);
			ebuyRefundAudit.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyRefundAudit);
		}
		return sqlSession.update("ebuyRefundAuditBase.delete_ebuyRefundAudit_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商品审核不通过原因)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundAudit(java.math.BigInteger id){
//		return sqlSession.delete("ebuyRefundAuditBase.delete_ebuyRefundAudit", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品审核不通过原因)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundAuditBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyRefundAuditBase.delete_ebuyRefundAudit_Batch", idList);
//	}
	
	
}

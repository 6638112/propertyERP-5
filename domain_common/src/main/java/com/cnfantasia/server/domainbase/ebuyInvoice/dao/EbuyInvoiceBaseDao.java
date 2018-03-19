package com.cnfantasia.server.domainbase.ebuyInvoice.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyInvoice.entity.EbuyInvoice;

/**
 * 描述:(发票) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyInvoiceBaseDao extends AbstractBaseDao implements IEbuyInvoiceBaseDao{
	/**
	 * 根据条件查询(发票)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyInvoice> selectEbuyInvoiceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyInvoiceBase.select_ebuyInvoice",paramMap);
	}
	/**
	 * 按条件分页查询(发票)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyInvoice> selectEbuyInvoiceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyInvoiceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyInvoice> resMap= sqlSession.selectList("ebuyInvoiceBase.select_ebuyInvoice_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(发票)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyInvoice selectEbuyInvoiceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyInvoiceBase.select_ebuyInvoice_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(发票)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyInvoiceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyInvoiceBase.select_ebuyInvoice_count", paramMap);
	}
	/**
	 * 往(发票)新增一条记录
	 * @param ebuyInvoice
	 * @return
	 */
	@Override
	public int insertEbuyInvoice(EbuyInvoice ebuyInvoice){
		return sqlSession.insert("ebuyInvoiceBase.insert_ebuyInvoice",ebuyInvoice);
	}
	/**
	 * 批量新增(发票)信息
	 * @param ebuyInvoiceList
	 * @return
	 */
	@Override
	public int insertEbuyInvoiceBatch(List<EbuyInvoice> ebuyInvoiceList) {
		return sqlSession.insert("ebuyInvoiceBase.insert_ebuyInvoice_Batch",ebuyInvoiceList);
	}
	
	/**
	 * 更新(发票)信息
	 * @param ebuyInvoice
	 * @return
	 */
	@Override
	public int updateEbuyInvoice(EbuyInvoice ebuyInvoice){
		return sqlSession.update("ebuyInvoiceBase.update_ebuyInvoice", ebuyInvoice);
	}
	/**
	 * 批量更新(发票)信息
	 * @param ebuyInvoiceList
	 * @return
	 */
	@Override
	public int updateEbuyInvoiceBatch(List<EbuyInvoice> ebuyInvoiceList) {
		return sqlSession.update("ebuyInvoiceBase.update_ebuyInvoice_Batch", ebuyInvoiceList);
	}
	
	/**
	 * 根据序列号删除(发票)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyInvoiceLogic(java.math.BigInteger id){
		EbuyInvoice ebuyInvoice = new EbuyInvoice();
		ebuyInvoice.setId(id);
		ebuyInvoice.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyInvoiceBase.delete_ebuyInvoice_Logic",ebuyInvoice);
	}
	
	/**
	 * 根据Id 批量删除(发票)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyInvoiceLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyInvoice> delList = new java.util.ArrayList<EbuyInvoice>();
		for(java.math.BigInteger id:idList){
			EbuyInvoice ebuyInvoice = new EbuyInvoice();
			ebuyInvoice.setId(id);
			ebuyInvoice.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyInvoice);
		}
		return sqlSession.update("ebuyInvoiceBase.delete_ebuyInvoice_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(发票)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyInvoice(java.math.BigInteger id){
//		return sqlSession.delete("ebuyInvoiceBase.delete_ebuyInvoice", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(发票)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyInvoiceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyInvoiceBase.delete_ebuyInvoice_Batch", idList);
//	}
	
	
}

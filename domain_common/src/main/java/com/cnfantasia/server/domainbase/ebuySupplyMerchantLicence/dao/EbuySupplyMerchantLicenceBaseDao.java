package com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantLicence.entity.EbuySupplyMerchantLicence;

/**
 * 描述:(供应商营业执照图片) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuySupplyMerchantLicenceBaseDao extends AbstractBaseDao implements IEbuySupplyMerchantLicenceBaseDao{
	/**
	 * 根据条件查询(供应商营业执照图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantLicence> selectEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuySupplyMerchantLicenceBase.select_ebuySupplyMerchantLicence",paramMap);
	}
	/**
	 * 按条件分页查询(供应商营业执照图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantLicence> selectEbuySupplyMerchantLicenceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuySupplyMerchantLicenceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuySupplyMerchantLicence> resMap= sqlSession.selectList("ebuySupplyMerchantLicenceBase.select_ebuySupplyMerchantLicence_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商营业执照图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantLicence selectEbuySupplyMerchantLicenceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuySupplyMerchantLicenceBase.select_ebuySupplyMerchantLicence_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商营业执照图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuySupplyMerchantLicenceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuySupplyMerchantLicenceBase.select_ebuySupplyMerchantLicence_count", paramMap);
	}
	/**
	 * 往(供应商营业执照图片)新增一条记录
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence){
		return sqlSession.insert("ebuySupplyMerchantLicenceBase.insert_ebuySupplyMerchantLicence",ebuySupplyMerchantLicence);
	}
	/**
	 * 批量新增(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicenceList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantLicenceBatch(List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList) {
		return sqlSession.insert("ebuySupplyMerchantLicenceBase.insert_ebuySupplyMerchantLicence_Batch",ebuySupplyMerchantLicenceList);
	}
	
	/**
	 * 更新(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicence
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantLicence(EbuySupplyMerchantLicence ebuySupplyMerchantLicence){
		return sqlSession.update("ebuySupplyMerchantLicenceBase.update_ebuySupplyMerchantLicence", ebuySupplyMerchantLicence);
	}
	/**
	 * 批量更新(供应商营业执照图片)信息
	 * @param ebuySupplyMerchantLicenceList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantLicenceBatch(List<EbuySupplyMerchantLicence> ebuySupplyMerchantLicenceList) {
		return sqlSession.update("ebuySupplyMerchantLicenceBase.update_ebuySupplyMerchantLicence_Batch", ebuySupplyMerchantLicenceList);
	}
	
	/**
	 * 根据序列号删除(供应商营业执照图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLicenceLogic(java.math.BigInteger id){
		EbuySupplyMerchantLicence ebuySupplyMerchantLicence = new EbuySupplyMerchantLicence();
		ebuySupplyMerchantLicence.setId(id);
		ebuySupplyMerchantLicence.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuySupplyMerchantLicenceBase.delete_ebuySupplyMerchantLicence_Logic",ebuySupplyMerchantLicence);
	}
	
	/**
	 * 根据Id 批量删除(供应商营业执照图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantLicenceLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuySupplyMerchantLicence> delList = new java.util.ArrayList<EbuySupplyMerchantLicence>();
		for(java.math.BigInteger id:idList){
			EbuySupplyMerchantLicence ebuySupplyMerchantLicence = new EbuySupplyMerchantLicence();
			ebuySupplyMerchantLicence.setId(id);
			ebuySupplyMerchantLicence.setSys0DelState(RecordState_DELETED);
			delList.add(ebuySupplyMerchantLicence);
		}
		return sqlSession.update("ebuySupplyMerchantLicenceBase.delete_ebuySupplyMerchantLicence_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商营业执照图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantLicence(java.math.BigInteger id){
//		return sqlSession.delete("ebuySupplyMerchantLicenceBase.delete_ebuySupplyMerchantLicence", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商营业执照图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantLicenceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuySupplyMerchantLicenceBase.delete_ebuySupplyMerchantLicence_Batch", idList);
//	}
	
	
}

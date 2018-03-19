package com.cnfantasia.server.domainbase.ebuyExpressCompany.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyExpressCompany.entity.EbuyExpressCompany;

/**
 * 描述:(快递公司) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyExpressCompanyBaseDao extends AbstractBaseDao implements IEbuyExpressCompanyBaseDao{
	/**
	 * 根据条件查询(快递公司)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyExpressCompany> selectEbuyExpressCompanyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyExpressCompanyBase.select_ebuyExpressCompany",paramMap);
	}
	/**
	 * 按条件分页查询(快递公司)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyExpressCompany> selectEbuyExpressCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyExpressCompanyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyExpressCompany> resMap= sqlSession.selectList("ebuyExpressCompanyBase.select_ebuyExpressCompany_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(快递公司)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyExpressCompany selectEbuyExpressCompanyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyExpressCompanyBase.select_ebuyExpressCompany_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(快递公司)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyExpressCompanyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyExpressCompanyBase.select_ebuyExpressCompany_count", paramMap);
	}
	/**
	 * 往(快递公司)新增一条记录
	 * @param ebuyExpressCompany
	 * @return
	 */
	@Override
	public int insertEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany){
		return sqlSession.insert("ebuyExpressCompanyBase.insert_ebuyExpressCompany",ebuyExpressCompany);
	}
	/**
	 * 批量新增(快递公司)信息
	 * @param ebuyExpressCompanyList
	 * @return
	 */
	@Override
	public int insertEbuyExpressCompanyBatch(List<EbuyExpressCompany> ebuyExpressCompanyList) {
		return sqlSession.insert("ebuyExpressCompanyBase.insert_ebuyExpressCompany_Batch",ebuyExpressCompanyList);
	}
	
	/**
	 * 更新(快递公司)信息
	 * @param ebuyExpressCompany
	 * @return
	 */
	@Override
	public int updateEbuyExpressCompany(EbuyExpressCompany ebuyExpressCompany){
		return sqlSession.update("ebuyExpressCompanyBase.update_ebuyExpressCompany", ebuyExpressCompany);
	}
	/**
	 * 批量更新(快递公司)信息
	 * @param ebuyExpressCompanyList
	 * @return
	 */
	@Override
	public int updateEbuyExpressCompanyBatch(List<EbuyExpressCompany> ebuyExpressCompanyList) {
		return sqlSession.update("ebuyExpressCompanyBase.update_ebuyExpressCompany_Batch", ebuyExpressCompanyList);
	}
	
	/**
	 * 根据序列号删除(快递公司)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyExpressCompanyLogic(java.math.BigInteger id){
		EbuyExpressCompany ebuyExpressCompany = new EbuyExpressCompany();
		ebuyExpressCompany.setId(id);
		ebuyExpressCompany.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyExpressCompanyBase.delete_ebuyExpressCompany_Logic",ebuyExpressCompany);
	}
	
	/**
	 * 根据Id 批量删除(快递公司)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyExpressCompanyLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyExpressCompany> delList = new java.util.ArrayList<EbuyExpressCompany>();
		for(java.math.BigInteger id:idList){
			EbuyExpressCompany ebuyExpressCompany = new EbuyExpressCompany();
			ebuyExpressCompany.setId(id);
			ebuyExpressCompany.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyExpressCompany);
		}
		return sqlSession.update("ebuyExpressCompanyBase.delete_ebuyExpressCompany_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(快递公司)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyExpressCompany(java.math.BigInteger id){
//		return sqlSession.delete("ebuyExpressCompanyBase.delete_ebuyExpressCompany", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(快递公司)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyExpressCompanyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyExpressCompanyBase.delete_ebuyExpressCompany_Batch", idList);
//	}
	
	
}

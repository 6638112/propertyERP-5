package com.cnfantasia.server.domainbase.communitySupplyCompany.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyCompany.entity.CommunitySupplyCompany;

/**
 * 描述:(社区商户公司（管理多个社区商家）) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyCompanyBaseDao extends AbstractBaseDao implements ICommunitySupplyCompanyBaseDao{
	/**
	 * 根据条件查询(社区商户公司（管理多个社区商家）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompany> selectCommunitySupplyCompanyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyCompanyBase.select_communitySupplyCompany",paramMap);
	}
	/**
	 * 按条件分页查询(社区商户公司（管理多个社区商家）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyCompany> selectCommunitySupplyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyCompanyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyCompany> resMap= sqlSession.selectList("communitySupplyCompanyBase.select_communitySupplyCompany_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区商户公司（管理多个社区商家）)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyCompany selectCommunitySupplyCompanyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyCompanyBase.select_communitySupplyCompany_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区商户公司（管理多个社区商家）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyCompanyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyCompanyBase.select_communitySupplyCompany_count", paramMap);
	}
	/**
	 * 往(社区商户公司（管理多个社区商家）)新增一条记录
	 * @param communitySupplyCompany
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompany(CommunitySupplyCompany communitySupplyCompany){
		return sqlSession.insert("communitySupplyCompanyBase.insert_communitySupplyCompany",communitySupplyCompany);
	}
	/**
	 * 批量新增(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompanyList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyCompanyBatch(List<CommunitySupplyCompany> communitySupplyCompanyList) {
		return sqlSession.insert("communitySupplyCompanyBase.insert_communitySupplyCompany_Batch",communitySupplyCompanyList);
	}
	
	/**
	 * 更新(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompany
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompany(CommunitySupplyCompany communitySupplyCompany){
		return sqlSession.update("communitySupplyCompanyBase.update_communitySupplyCompany", communitySupplyCompany);
	}
	/**
	 * 批量更新(社区商户公司（管理多个社区商家）)信息
	 * @param communitySupplyCompanyList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyCompanyBatch(List<CommunitySupplyCompany> communitySupplyCompanyList) {
		return sqlSession.update("communitySupplyCompanyBase.update_communitySupplyCompany_Batch", communitySupplyCompanyList);
	}
	
	/**
	 * 根据序列号删除(社区商户公司（管理多个社区商家）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyLogic(java.math.BigInteger id){
		CommunitySupplyCompany communitySupplyCompany = new CommunitySupplyCompany();
		communitySupplyCompany.setId(id);
		communitySupplyCompany.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyCompanyBase.delete_communitySupplyCompany_Logic",communitySupplyCompany);
	}
	
	/**
	 * 根据Id 批量删除(社区商户公司（管理多个社区商家）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyCompanyLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyCompany> delList = new java.util.ArrayList<CommunitySupplyCompany>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyCompany communitySupplyCompany = new CommunitySupplyCompany();
			communitySupplyCompany.setId(id);
			communitySupplyCompany.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyCompany);
		}
		return sqlSession.update("communitySupplyCompanyBase.delete_communitySupplyCompany_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区商户公司（管理多个社区商家）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompany(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyCompanyBase.delete_communitySupplyCompany", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商户公司（管理多个社区商家）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyCompanyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyCompanyBase.delete_communitySupplyCompany_Batch", idList);
//	}
	
	
}

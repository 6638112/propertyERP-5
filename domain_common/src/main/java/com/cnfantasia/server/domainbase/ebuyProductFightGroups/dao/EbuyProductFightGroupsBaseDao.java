package com.cnfantasia.server.domainbase.ebuyProductFightGroups.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductFightGroups.entity.EbuyProductFightGroups;

/**
 * 描述:(拼购商品规则表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductFightGroupsBaseDao extends AbstractBaseDao implements IEbuyProductFightGroupsBaseDao{
	/**
	 * 根据条件查询(拼购商品规则表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductFightGroups> selectEbuyProductFightGroupsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductFightGroupsBase.select_ebuyProductFightGroups",paramMap);
	}
	/**
	 * 按条件分页查询(拼购商品规则表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductFightGroups> selectEbuyProductFightGroupsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductFightGroupsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductFightGroups> resMap= sqlSession.selectList("ebuyProductFightGroupsBase.select_ebuyProductFightGroups_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(拼购商品规则表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductFightGroups selectEbuyProductFightGroupsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductFightGroupsBase.select_ebuyProductFightGroups_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(拼购商品规则表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductFightGroupsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductFightGroupsBase.select_ebuyProductFightGroups_count", paramMap);
	}
	/**
	 * 往(拼购商品规则表)新增一条记录
	 * @param ebuyProductFightGroups
	 * @return
	 */
	@Override
	public int insertEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups){
		return sqlSession.insert("ebuyProductFightGroupsBase.insert_ebuyProductFightGroups",ebuyProductFightGroups);
	}
	/**
	 * 批量新增(拼购商品规则表)信息
	 * @param ebuyProductFightGroupsList
	 * @return
	 */
	@Override
	public int insertEbuyProductFightGroupsBatch(List<EbuyProductFightGroups> ebuyProductFightGroupsList) {
		return sqlSession.insert("ebuyProductFightGroupsBase.insert_ebuyProductFightGroups_Batch",ebuyProductFightGroupsList);
	}
	
	/**
	 * 更新(拼购商品规则表)信息
	 * @param ebuyProductFightGroups
	 * @return
	 */
	@Override
	public int updateEbuyProductFightGroups(EbuyProductFightGroups ebuyProductFightGroups){
		return sqlSession.update("ebuyProductFightGroupsBase.update_ebuyProductFightGroups", ebuyProductFightGroups);
	}
	/**
	 * 批量更新(拼购商品规则表)信息
	 * @param ebuyProductFightGroupsList
	 * @return
	 */
	@Override
	public int updateEbuyProductFightGroupsBatch(List<EbuyProductFightGroups> ebuyProductFightGroupsList) {
		return sqlSession.update("ebuyProductFightGroupsBase.update_ebuyProductFightGroups_Batch", ebuyProductFightGroupsList);
	}
	
	/**
	 * 根据序列号删除(拼购商品规则表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightGroupsLogic(java.math.BigInteger id){
		EbuyProductFightGroups ebuyProductFightGroups = new EbuyProductFightGroups();
		ebuyProductFightGroups.setId(id);
		ebuyProductFightGroups.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductFightGroupsBase.delete_ebuyProductFightGroups_Logic",ebuyProductFightGroups);
	}
	
	/**
	 * 根据Id 批量删除(拼购商品规则表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightGroupsLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductFightGroups> delList = new java.util.ArrayList<EbuyProductFightGroups>();
		for(java.math.BigInteger id:idList){
			EbuyProductFightGroups ebuyProductFightGroups = new EbuyProductFightGroups();
			ebuyProductFightGroups.setId(id);
			ebuyProductFightGroups.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductFightGroups);
		}
		return sqlSession.update("ebuyProductFightGroupsBase.delete_ebuyProductFightGroups_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(拼购商品规则表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightGroups(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductFightGroupsBase.delete_ebuyProductFightGroups", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼购商品规则表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightGroupsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductFightGroupsBase.delete_ebuyProductFightGroups_Batch", idList);
//	}
	
	
}

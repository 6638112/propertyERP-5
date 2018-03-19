package com.cnfantasia.server.domainbase.commSysPara.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSysPara.entity.CommSysPara;

/**
 * 描述:(系统参数) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommSysParaBaseDao extends AbstractBaseDao implements ICommSysParaBaseDao{
	/**
	 * 根据条件查询(系统参数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommSysPara> selectCommSysParaByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commSysParaBase.select_commSysPara",paramMap);
	}
	/**
	 * 按条件分页查询(系统参数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommSysPara> selectCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommSysParaCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommSysPara> resMap= sqlSession.selectList("commSysParaBase.select_commSysPara_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(系统参数)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommSysPara selectCommSysParaBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commSysParaBase.select_commSysPara_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(系统参数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommSysParaCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commSysParaBase.select_commSysPara_count", paramMap);
	}
	/**
	 * 往(系统参数)新增一条记录
	 * @param commSysPara
	 * @return
	 */
	@Override
	public int insertCommSysPara(CommSysPara commSysPara){
		return sqlSession.insert("commSysParaBase.insert_commSysPara",commSysPara);
	}
	/**
	 * 批量新增(系统参数)信息
	 * @param commSysParaList
	 * @return
	 */
	@Override
	public int insertCommSysParaBatch(List<CommSysPara> commSysParaList) {
		return sqlSession.insert("commSysParaBase.insert_commSysPara_Batch",commSysParaList);
	}
	
	/**
	 * 更新(系统参数)信息
	 * @param commSysPara
	 * @return
	 */
	@Override
	public int updateCommSysPara(CommSysPara commSysPara){
		return sqlSession.update("commSysParaBase.update_commSysPara", commSysPara);
	}
	/**
	 * 批量更新(系统参数)信息
	 * @param commSysParaList
	 * @return
	 */
	@Override
	public int updateCommSysParaBatch(List<CommSysPara> commSysParaList) {
		return sqlSession.update("commSysParaBase.update_commSysPara_Batch", commSysParaList);
	}
	
	/**
	 * 根据序列号删除(系统参数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommSysParaLogic(java.math.BigInteger id){
		CommSysPara commSysPara = new CommSysPara();
		commSysPara.setId(id);
		commSysPara.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commSysParaBase.delete_commSysPara_Logic",commSysPara);
	}
	
	/**
	 * 根据Id 批量删除(系统参数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommSysParaLogicBatch(List<java.math.BigInteger> idList) {
		List<CommSysPara> delList = new java.util.ArrayList<CommSysPara>();
		for(java.math.BigInteger id:idList){
			CommSysPara commSysPara = new CommSysPara();
			commSysPara.setId(id);
			commSysPara.setSys0DelState(RecordState_DELETED);
			delList.add(commSysPara);
		}
		return sqlSession.update("commSysParaBase.delete_commSysPara_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(系统参数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysPara(java.math.BigInteger id){
//		return sqlSession.delete("commSysParaBase.delete_commSysPara", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(系统参数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysParaBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commSysParaBase.delete_commSysPara_Batch", idList);
//	}
	
	
}

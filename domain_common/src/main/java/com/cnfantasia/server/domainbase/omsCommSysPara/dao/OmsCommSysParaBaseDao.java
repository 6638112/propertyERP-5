package com.cnfantasia.server.domainbase.omsCommSysPara.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsCommSysParaBaseDao extends AbstractBaseDao implements IOmsCommSysParaBaseDao{
	/**
	 * 根据条件查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsCommSysParaBase.select_omsCommSysPara",paramMap);
	}
	/**
	 * 按条件分页查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsCommSysParaCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsCommSysPara> resMap= sqlSession.selectList("omsCommSysParaBase.select_omsCommSysPara_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsCommSysPara selectOmsCommSysParaBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsCommSysParaBase.select_omsCommSysPara_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsCommSysParaCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsCommSysParaBase.select_omsCommSysPara_count", paramMap);
	}
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param omsCommSysPara
	 * @return
	 */
	@Override
	public int insertOmsCommSysPara(OmsCommSysPara omsCommSysPara){
		return sqlSession.insert("omsCommSysParaBase.insert_omsCommSysPara",omsCommSysPara);
	}
	/**
	 * 批量新增(OMS系统参数表)信息
	 * @param omsCommSysParaList
	 * @return
	 */
	@Override
	public int insertOmsCommSysParaBatch(List<OmsCommSysPara> omsCommSysParaList) {
		return sqlSession.insert("omsCommSysParaBase.insert_omsCommSysPara_Batch",omsCommSysParaList);
	}
	
	/**
	 * 更新(OMS系统参数表)信息
	 * @param omsCommSysPara
	 * @return
	 */
	@Override
	public int updateOmsCommSysPara(OmsCommSysPara omsCommSysPara){
		return sqlSession.update("omsCommSysParaBase.update_omsCommSysPara", omsCommSysPara);
	}
	/**
	 * 批量更新(OMS系统参数表)信息
	 * @param omsCommSysParaList
	 * @return
	 */
	@Override
	public int updateOmsCommSysParaBatch(List<OmsCommSysPara> omsCommSysParaList) {
		return sqlSession.update("omsCommSysParaBase.update_omsCommSysPara_Batch", omsCommSysParaList);
	}
	
	/**
	 * 根据序列号删除(OMS系统参数表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsCommSysParaLogic(java.math.BigInteger id){
		OmsCommSysPara omsCommSysPara = new OmsCommSysPara();
		omsCommSysPara.setId(id);
		omsCommSysPara.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsCommSysParaBase.delete_omsCommSysPara_Logic",omsCommSysPara);
	}
	
	/**
	 * 根据Id 批量删除(OMS系统参数表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsCommSysParaLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsCommSysPara> delList = new java.util.ArrayList<OmsCommSysPara>();
		for(java.math.BigInteger id:idList){
			OmsCommSysPara omsCommSysPara = new OmsCommSysPara();
			omsCommSysPara.setId(id);
			omsCommSysPara.setSys0DelState(RecordState_DELETED);
			delList.add(omsCommSysPara);
		}
		return sqlSession.update("omsCommSysParaBase.delete_omsCommSysPara_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS系统参数表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsCommSysPara(java.math.BigInteger id){
//		return sqlSession.delete("omsCommSysParaBase.delete_omsCommSysPara", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS系统参数表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsCommSysParaBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsCommSysParaBase.delete_omsCommSysPara_Batch", idList);
//	}
	
	
}
